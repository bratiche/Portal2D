package com.portal2d.game.controller;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.*;
import com.portal2d.game.model.entities.enemies.Turret;
import com.portal2d.game.model.entities.portals.PortableSurface;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.level.LevelName;
import com.portal2d.game.model.level.LevelObserver;

import java.util.HashMap;
import java.util.Map;

import static com.portal2d.game.model.ModelConstants.*;
import static com.portal2d.game.view.ViewConstants.PPM;

/**
 * Helper class for loading levels and creating entities from tmx files (TiledMaps).
 */
public class LevelLoader {

    /** Map for linking Switches with Switchables */
    private Map<Integer, Switchable> IDs;

    public LevelLoader() {
        IDs = new HashMap<Integer, Switchable>();
    }

    /**
     * @param levelName the name of the level to load
     * @return the specified level
     */
    public Level loadLevel(LevelName levelName, LevelObserver...observers) {
        TiledMap tiledMap = Portal2D.assets.getTiledMap(levelName);
        Level level = new Level(levelName, observers);
        createLevel(tiledMap, level);
        return level;
    }

    //TODO fix gapes between portable and non-portable surfaces
    private void createLevel(TiledMap tiledMap, Level level) {
        MapLayer layer = tiledMap.getLayers().get("turrets");
        createTurrets(level, layer);

        layer = tiledMap.getLayers().get("player");
        createPlayer(level, layer);

        layer = tiledMap.getLayers().get("boxes");
        createBoxes(level, layer);

        layer = tiledMap.getLayers().get("acid");
        createAcid(level, layer);

        layer = tiledMap.getLayers().get("surfaces");
        createSurfaces(level, layer);

        layer = tiledMap.getLayers().get("portable-surfaces");
        createPortableSurfaces(level, layer);

        layer = tiledMap.getLayers().get("gates");
        createGates(level, layer);

        layer = tiledMap.getLayers().get("exits");
        createExits(level, layer);

        layer = tiledMap.getLayers().get("buttons");
        createButtons(level, layer);
    }

    private void createPlayer(Level level, MapLayer layer) {
        RectangleMapObject rectangleMapObject = (RectangleMapObject)layer.getObjects().get("player");
        float x = rectangleMapObject.getRectangle().getX();
        float y = rectangleMapObject.getRectangle().getY();

        Player player = new Player(level, new Vector2(x / PPM + PLAYER_WIDTH / 2, y / PPM + PLAYER_HEIGHT / 2));
        level.add(player);
    }

    private void createBoxes(Level level, MapLayer layer) {
        RectangleMapObject rectangleMapObject;
        float x;
        float y;

        for(MapObject mapObject : layer.getObjects()) {
            rectangleMapObject = (RectangleMapObject)mapObject;
            x = rectangleMapObject.getRectangle().getX();
            y = rectangleMapObject.getRectangle().getY();

            Box box = new Box(level, new Vector2(x / PPM + BOX_WIDTH / 2, y / PPM + BOX_HEIGHT / 2));
            level.add(box);
        }
    }

    private void createAcid(Level level, MapLayer layer) {
        RectangleMapObject rectangleMapObject;
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

            Vector2 position = new Vector2((x + width / 2) / PPM, (y + height / 2) / PPM);

            level.add(new Acid(level, position, width / PPM, height / PPM));
        }
    }

    // Surfaces, PortableSurfaces and Acid have variable width and height
    private void createSurfaces(Level level, MapLayer layer) {
        RectangleMapObject rectangleMapObject;
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

            Vector2 position = new Vector2((x + width / 2) / PPM, (y + height / 2) / PPM);

            level.add(new Surface(level, position, width, height));
        }
    }

    private void createPortableSurfaces(Level level, MapLayer layer) {
        RectangleMapObject rectangleMapObject;
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

            Vector2 position = new Vector2((x + width / 2) / PPM, (y + height / 2) / PPM);
            level.add(new PortableSurface(level, position, width, height));
        }
    }

    /**
     * Puts all the gates in {@link #IDs} so they can later be retrieved by {@link #createButtons(Level, MapLayer)}
     */
    private void createGates(Level level, MapLayer layer) {
        RectangleMapObject rectangleMapObject;
        float x;
        float y;

        for(MapObject mapObject : layer.getObjects()) {
            rectangleMapObject = (RectangleMapObject) mapObject;
            x = rectangleMapObject.getRectangle().getX();
            y = rectangleMapObject.getRectangle().getY();

            String ID = (String) mapObject.getProperties().get("ID");
            int gateID = Integer.parseInt(ID);

            String number = (String) mapObject.getProperties().get("locks");
            int switches = Integer.parseInt(number);

            Vector2 position = new Vector2(x / PPM + GATE_WIDTH / 2, y / PPM + GATE_HEIGHT / 2);

            Gate gate = new Gate(level, position, switches);
            IDs.put(gateID, gate);

            level.add(gate);
        }
    }

    /**
     * Creates all the buttons by retrieving their corresponding switchable from {@link #IDs}
     */
    private void createButtons(Level level, MapLayer layer) {
        PolygonMapObject polygonMapObject;
        float x;
        float y;

        for(MapObject mapObject : layer.getObjects()) {
            polygonMapObject = (PolygonMapObject) mapObject;
            x = polygonMapObject.getPolygon().getX();
            y = polygonMapObject.getPolygon().getY();

            String ID = (String) mapObject.getProperties().get("LinkedEntityID");
            Integer linkedEntityID = Integer.parseInt(ID);

            Vector2 position = new Vector2(x / PPM, y / PPM);
            Button button = new Button(level, position, IDs.get(linkedEntityID));
            level.add(button);
        }
    }

    private void createTurrets(Level level, MapLayer layer) {
        RectangleMapObject rectangleMapObject;
        float x;
        float y;

        for(MapObject mapObject : layer.getObjects()) {
            rectangleMapObject = (RectangleMapObject)mapObject;
            x = rectangleMapObject.getRectangle().getX();
            y = rectangleMapObject.getRectangle().getY();

            Vector2 position = new Vector2(x / PPM + TURRET_WIDTH / 2, y / PPM + TURRET_HEIGHT / 2);
            Turret turret = new Turret(level, position);
            level.add(turret);
        }

    }

    private void createExits(Level level, MapLayer layer) {
        RectangleMapObject rectangleMapObject;
        float x;
        float y;

        for(MapObject mapObject : layer.getObjects()) {
            rectangleMapObject = (RectangleMapObject)mapObject;
            x = rectangleMapObject.getRectangle().getX();
            y = rectangleMapObject.getRectangle().getY();

            String nextLevelString = (String)mapObject.getProperties().get("NextLevel");

            int nextLevel = Integer.parseInt(nextLevelString);

            Vector2 position = new Vector2(x / PPM + EXIT_WIDTH / 2, y / PPM + EXIT_HEIGHT / 2);

            Exit exit = new Exit(level, position, LevelName.values()[nextLevel]);
            level.add(exit);
        }
    }
}
