package com.portal2d.game.controller;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.*;
import com.portal2d.game.Portal2D;
import com.portal2d.game.controller.states.PlayState;
import com.portal2d.game.model.entities.*;
import com.portal2d.game.model.entities.enemies.Turret;
import com.portal2d.game.model.entities.portals.PortableSurface;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.level.LevelName;

import java.util.HashMap;
import java.util.Map;

import static com.portal2d.game.view.ViewConstants.PPM;

/**
 * Helper class for loading levels and creating entities from tmx files (TiledMaps).
 */
public class LevelLoader {

    private World world;
    private PlayState playState;

    /** Map for linking Switches with Switchables */
    private Map<Integer, Switchable> IDs;

    public LevelLoader(World world, PlayState playState) {
        this.world = world;
        this.playState = playState;
        IDs = new HashMap<Integer, Switchable>();
    }

    /**
     * @param levelName the name of the level to load
     * @return the specified level
     */
    public Level loadLevel(LevelName levelName) {
        TiledMap tiledMap = Portal2D.assets.getTiledMap(levelName);
        Level level = new Level(world, levelName, playState);
        createLevel(tiledMap, level);
        return level;
    }

    //TODO fix gapes between portable and non-portable surfaces
    private void createLevel(TiledMap tiledMap, Level level) {
        MapLayer layer = tiledMap.getLayers().get("turrets");
        createTurrets(level, layer);

        layer = tiledMap.getLayers().get("surfaces");
        createSurfaces(level, layer);

        layer = tiledMap.getLayers().get("portable-surfaces");
        createPortableSurfaces(level, layer);

        layer = tiledMap.getLayers().get("gates");
        createGates(level, layer);

        layer = tiledMap.getLayers().get("buttons");
        createButtons(level, layer);

        layer = tiledMap.getLayers().get("boxes");
        createBoxes(level, layer);

        layer = tiledMap.getLayers().get("exits");
        createExits(level, layer);

        layer = tiledMap.getLayers().get("acid");
        createAcid(level, layer);

        layer = tiledMap.getLayers().get("player");
        createPlayer(level, layer);
    }

    private void createPlayer(Level level, MapLayer layer) {
        RectangleMapObject rectangleMapObject = (RectangleMapObject)layer.getObjects().get("player");
        float x = rectangleMapObject.getRectangle().getX();
        float y = rectangleMapObject.getRectangle().getY();
        float width = rectangleMapObject.getRectangle().getWidth();
        float height = rectangleMapObject.getRectangle().getHeight();

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x / PPM, y / PPM);
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        body.setFixedRotation(true);

        level.add(new Player(level, body));

        shape.dispose();
    }

    private void createSurfaces(Level level, MapLayer layer) {

        BodyDef bodyDef = new BodyDef();
        RectangleMapObject rectangleMapObject;
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        float x;
        float y;
        float width;
        float height;

        for(MapObject mapObject : layer.getObjects()) {
            rectangleMapObject = (RectangleMapObject)mapObject;
            x = rectangleMapObject.getRectangle().getX();
            y = rectangleMapObject.getRectangle().getY();
            width = rectangleMapObject.getRectangle().getWidth();
            height = rectangleMapObject.getRectangle().getHeight();

            bodyDef.position.set((x + width / 2) / PPM, (y + height / 2) / PPM);

            Body body = world.createBody(bodyDef);

            shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
            fixtureDef.shape = shape;
            fixtureDef.friction = 0.6f;
            body.createFixture(fixtureDef);

            level.add(new Surface(level, body));
        }

        shape.dispose();
    }

    private void createPortableSurfaces(Level level, MapLayer layer) {

        BodyDef bodyDef = new BodyDef();
        RectangleMapObject rectangleMapObject;
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        float x;
        float y;
        float width;
        float height;

        for(MapObject mapObject : layer.getObjects()) {
            rectangleMapObject = (RectangleMapObject) mapObject;
            x = rectangleMapObject.getRectangle().getX();
            y = rectangleMapObject.getRectangle().getY();
            width = rectangleMapObject.getRectangle().getWidth();
            height = rectangleMapObject.getRectangle().getHeight();

            bodyDef.position.set((x + width / 2) / PPM, (y + height / 2) / PPM);

            Body body = world.createBody(bodyDef);

            shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
            fixtureDef.shape = shape;
            fixtureDef.friction = 0.6f;
            body.createFixture(fixtureDef);
            level.add(new PortableSurface(level, body));
        }

        shape.dispose();
    }

    /**
     * Puts all the gates in {@link #IDs} so they can later be retrieved by {@link #createButtons(Level, MapLayer)}
     */
    private void createGates(Level level, MapLayer layer) {
        BodyDef bodyDef = new BodyDef();
        RectangleMapObject rectangleMapObject;
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        float x;
        float y;
        float width;
        float height;

        for(MapObject mapObject : layer.getObjects()) {
            rectangleMapObject = (RectangleMapObject) mapObject;
            x = rectangleMapObject.getRectangle().getX();
            y = rectangleMapObject.getRectangle().getY();
            width = rectangleMapObject.getRectangle().getWidth();
            height = rectangleMapObject.getRectangle().getHeight();

            bodyDef.position.set((x + width / 2) / PPM, (y + height / 2) / PPM);

            shape.setAsBox(width / 2 / PPM, height / 2 / PPM);

            fixtureDef.shape = shape;
            Body body = world.createBody(bodyDef);
            body.createFixture(fixtureDef);

            String ID = (String) mapObject.getProperties().get("ID");
            int gateID = Integer.parseInt(ID);

            String number = (String) mapObject.getProperties().get("locks");
            int switches = Integer.parseInt(number);

            Gate gate = new Gate(level, body, switches);
            IDs.put(gateID, gate);

            level.add(gate);
        }

        shape.dispose();
    }


    /**
     * Creates all the buttons by retrieving their corresponding switchable from {@link #IDs}
     */
    private void createButtons(Level level, MapLayer layer) {
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        float x;
        float y;

        PolygonMapObject polygonMapObject;

        for(MapObject mapObject : layer.getObjects()) {
            polygonMapObject = (PolygonMapObject) mapObject;
            x = polygonMapObject.getPolygon().getX();
            y = polygonMapObject.getPolygon().getY();

            bodyDef.position.set(x / PPM, y / PPM);

            int vertexAmount = polygonMapObject.getPolygon().getVertices().length;
            float[] vertices = new float[vertexAmount];

            for(int i = 0; i < vertexAmount; i ++) {
                vertices[i] = polygonMapObject.getPolygon().getVertices()[i] / PPM;
            }

            shape.set(vertices);

            fixtureDef.shape = shape;

            Body body = world.createBody(bodyDef);
            body.createFixture(fixtureDef);

            String ID = (String) mapObject.getProperties().get("LinkedEntityID");
            Integer linkedEntityID = Integer.parseInt(ID);

            Button button = new Button(level, body, IDs.get(linkedEntityID));
            level.add(button);
        }

        shape.dispose();

    }

    private void createBoxes(Level level, MapLayer layer) {
        BodyDef bodyDef = new BodyDef();
        RectangleMapObject rectangleMapObject;
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        float x;
        float y;
        float width;
        float height;

        for(MapObject mapObject : layer.getObjects()) {

            rectangleMapObject = (RectangleMapObject)mapObject;
            x = rectangleMapObject.getRectangle().getX();
            y = rectangleMapObject.getRectangle().getY();
            width = rectangleMapObject.getRectangle().getWidth();
            height = rectangleMapObject.getRectangle().getHeight();

            bodyDef.position.set((x + width / 2) / PPM, (y + height / 2) / PPM);
            Body body = world.createBody(bodyDef);
            shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
            fixtureDef.shape = shape;
            fixtureDef.restitution = 0.2f;
            fixtureDef.friction = 1f;
            //fixtureDef.density = 4f; // uncomment this to make the boxes rotate
            body.createFixture(fixtureDef);
            Box box = new Box(level, body);
            level.add(box);
        }

        shape.dispose();
    }

    private void createTurrets(Level level, MapLayer layer) {
        BodyDef bodyDef = new BodyDef();
        RectangleMapObject rectangleMapObject;
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        float x;
        float y;
        float width;
        float height;

        for(MapObject mapObject : layer.getObjects()) {

            rectangleMapObject = (RectangleMapObject)mapObject;
            x = rectangleMapObject.getRectangle().getX();
            y = rectangleMapObject.getRectangle().getY();
            width = rectangleMapObject.getRectangle().getWidth();
            height = rectangleMapObject.getRectangle().getHeight();

            bodyDef.position.set((x + width / 2) / PPM, (y + height / 2) / PPM);
            Body body = world.createBody(bodyDef);
            shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
            fixtureDef.shape = shape;
            //body.createFixture(fixtureDef);
            Turret turret = new Turret(level, body);
            level.add(turret);
        }

        shape.dispose();
    }

    private void createAcid(Level level, MapLayer layer) {
        BodyDef bodyDef = new BodyDef();
        RectangleMapObject rectangleMapObject;
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        float x;
        float y;
        float width;
        float height;

        for(MapObject mapObject : layer.getObjects()) {
            rectangleMapObject = (RectangleMapObject)mapObject;
            x = rectangleMapObject.getRectangle().getX();
            y = rectangleMapObject.getRectangle().getY();
            width = rectangleMapObject.getRectangle().getWidth();
            height = rectangleMapObject.getRectangle().getHeight();

            bodyDef.position.set((x + width / 2) / PPM, (y + height / 2) / PPM);

            Body body = world.createBody(bodyDef);

            shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
            fixtureDef.shape = shape;
            fixtureDef.friction = 0.6f;
            body.createFixture(fixtureDef);

            level.add(new Acid(level, body));
        }

        shape.dispose();
    }

    private void createExits(Level level, MapLayer layer) {
        BodyDef bodyDef = new BodyDef();
        RectangleMapObject rectangleMapObject;
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        float x;
        float y;
        float width;
        float height;

        for(MapObject mapObject : layer.getObjects()) {
            rectangleMapObject = (RectangleMapObject)mapObject;
            x = rectangleMapObject.getRectangle().getX();
            y = rectangleMapObject.getRectangle().getY();
            width = rectangleMapObject.getRectangle().getWidth();
            height = rectangleMapObject.getRectangle().getHeight();

            bodyDef.position.set((x + width / 2) / PPM, (y + height / 2) / PPM);
            Body body = world.createBody(bodyDef);
            shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
            fixtureDef.shape = shape;
            fixtureDef.isSensor = true;
            body.createFixture(fixtureDef);

            String nextLevelString = (String)mapObject.getProperties().get("NextLevel");

            int nextLevel = Integer.parseInt(nextLevelString);

            Exit exit = new Exit(level, body, LevelName.values()[nextLevel]);
            level.add(exit);
        }

        shape.dispose();
    }
}
