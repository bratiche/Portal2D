package com.portal2d.game.controller;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.*;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.*;
import com.portal2d.game.model.entities.portals.PortableSurface;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.level.LevelName;

import java.util.HashMap;
import java.util.Map;

import static com.portal2d.game.view.ViewConstants.PPM;
import static com.portal2d.game.model.ModelConstants.*;

/**
 * Helper class for loading levels and creating entities from tmx files (TiledMaps).
 */
public class LevelLoader {

    private World world;

    //map for linking Gates with Buttons
    private Map<Integer, Gate> gatesID;

    public LevelLoader(World world) {
        this.world = world;
        gatesID = new HashMap<Integer, Gate>();
    }

    /**
     *
     * @param levelName the name of the level to load
     * @return the specified level
     */
    public Level loadLevel(LevelName levelName) {
        TiledMap tiledMap = Portal2D.assets.getTiledMap(levelName);
        Level level = new Level(world, tiledMap, levelName);
        createLevel(tiledMap, level);
        return level;
    }

    //TODO fix gapes between portable and non-portable surfaces
    private void createLevel(TiledMap tiledMap, Level level) {
        MapLayer layer = tiledMap.getLayers().get("player");
        createPlayer(level, layer);

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

        level.addPlayer(new Player(level, body));

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
            if(mapObject instanceof  RectangleMapObject) {
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
            else if(mapObject instanceof PolygonMapObject) {
                PolygonMapObject polygonMapObject = (PolygonMapObject) mapObject;
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
                fixtureDef.friction = 0.6f;

                Body body = world.createBody(bodyDef);
                body.createFixture(fixtureDef);
                level.add(new PortableSurface(level, body));
            }
        }

        shape.dispose();
    }

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


            /**
             * Put all the gates in a map with their respective ids
             */
            String string = (String) mapObject.getProperties().get("ID");
            int gateID = Integer.parseInt(string);
            Gate gate = new Gate(level, body);
            gatesID.put(gateID, gate);

            level.add(gate);
        }

        shape.dispose();
    }

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
            //fixtureDef.isSensor = true;

            Body body = world.createBody(bodyDef);
            body.createFixture(fixtureDef);

            /**
             * Retrieve the corresponding gate linked to this button
             */
            String linkedEntity = (String) mapObject.getProperties().get("LinkedEntityID");
            Integer gateID = Integer.parseInt(linkedEntity);
            for(Integer ID : gatesID.keySet()) {
                if(ID.equals(gateID)) {
                    Button button = new Button(level, body, gatesID.get(ID));
                    level.add(button);
                }
            }
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

    private void createExits(Level level, MapLayer layer) {
        BodyDef bodyDef = new BodyDef();
        RectangleMapObject rectangleMapObject;
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        float x;
        float y;
        float width;
        float height;

        //remove, test variable
        int i = 0;

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

            //System.out.println("Exit " + (i + 1)  + ": takes to level " + nextLevel);

            Exit exit = new Exit(level, body, LevelName.getLevelName(nextLevel));
            level.add(exit);
            i++;

        }

        shape.dispose();
    }
}
