package com.portal2d.game.controller.level;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.*;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.*;

import static com.portal2d.game.controller.Box2DConstants.*;

/**
 * Class for loading levels from tmx files (TiledMaps).
 */
public class LevelLoader {

    private World world;

    public LevelLoader(World world) {
        this.world = world;
    }

    /**
     *
     * @param levelName the name of the level to load
     * @return the specified level
     */
    public Level loadLevel(LevelName levelName) {
        return loadLevel(levelName.ordinal());
    }

    public Level loadNextLevel() {
        return loadLevel(Level.levelNumber);
    }

    private Level loadLevel(int levelNumber) {
        TiledMap tiledMap = Portal2D.assets.getTiledMap(levelNumber);
        Level level = new Level(world, tiledMap);
        createLevel(tiledMap, level);
        return level;
    }

    private void createLevel(TiledMap tiledMap, Level level) {
        MapLayer layer = tiledMap.getLayers().get("player");
        createPlayer(level, layer);

        layer = tiledMap.getLayers().get("portal-able");
        createTiles(level, (TiledMapTileLayer) layer, Tile.Type.PORTAL_ABLE);

        layer = tiledMap.getLayers().get("non-portal-able");
        createTiles(level, (TiledMapTileLayer) layer, Tile.Type.NON_PORTAL_ABLE);

        layer = tiledMap.getLayers().get("collision");
        createWalls(level, layer);

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
        Fixture fixture = body.createFixture(fixtureDef);
        body.setFixedRotation(true);

        level.player = new Player(world, body);

        shape.dispose();
    }

    private void createTiles(Level level, TiledMapTileLayer layer, Tile.Type type) {
        float x;
        float y;
        float width = layer.getWidth();
        float height = layer.getHeight();

        float tileWidth = layer.getTileWidth();
        float tileHeight = layer.getTileHeight();

        Body body;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(tileWidth / 2 / PPM, tileHeight / 2 / PPM);

        //TODO: ChainShape, or collision layer, or surfaces
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        for(int row = 0; row < width; row++) {
            for(int col = 0; col < height; col++) {

                TiledMapTileLayer.Cell cell = layer.getCell(row, col);

                if(cell == null || cell.getTile() == null)
                    continue;

//                BodyDef bodyDef = new BodyDef();
//                bodyDef.position.set((row + 0.5f) * tileHeight / PPM, (col + 0.5f) * tileWidth / PPM);
//                body = world.createBody(bodyDef);
//                body.createFixture(fixtureDef);
//                Tile tile = new Tile(world, body, type);
//                level.tiles.add(tile);
            }
        }

        shape.dispose();
    }

    //TODO: remove, it's a test
    private void createWalls(Level level, MapLayer layer) {

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
        }

        shape.dispose();
    }

    private void createButtons(Level level, MapLayer layer) {
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
            fixtureDef.isSensor = true;

            Body body = world.createBody(bodyDef);
            body.createFixture(fixtureDef);

            Button button = new Button(world, body);
            level.buttons.add(button);
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
            fixtureDef.friction = 0.5f;
            fixtureDef.density = 1;
            body.createFixture(fixtureDef);
            Box box = new Box(world, body);
            level.boxes.add(box);
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

            System.out.println(nextLevel);

            Exit exit = new Exit(world, body, LevelName.getLevelName(nextLevel));
            level.exits.add(exit);

        }

        shape.dispose();
    }
}
