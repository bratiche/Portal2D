package com.portal2d.game.controller.save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.portal2d.game.model.level.LevelName;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Slots for saving the game.
 */
public enum GameSlot {

    SLOT_1("core/data/slot_1.sav", "SLOT 1"),
    SLOT_2("core/data/slot_2.sav", "SLOT 2"),
    SLOT_3("core/data/slot_3.sav", "SLOT 3"),
    SLOT_4("core/data/slot_4.sav", "SLOT 4");

    public final String path;
    public final String name;

    GameSlot(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public void save() {
        FileHandle file = Gdx.files.local(path);
        StringBuilder sb = new StringBuilder();

        for(LevelName level : LevelName.values()) {
            sb.append(level.isLocked());
            sb.append("\n");
        }

        file.writeString(sb.toString(), false); // false is to overwrite the file
    }

    public void load() {
        FileHandle file = Gdx.files.internal(path);

        if(!file.exists()) {
            create();
        }

        BufferedReader reader = file.reader(1024);

        for(int i = 0; i < LevelName.values().length; i++) {
            try {
                boolean locked = Boolean.parseBoolean(reader.readLine());
                LevelName.values()[i].setLocked(locked);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /** Creates a new file for this slot. */
    private void create() {
        FileHandle file = Gdx.files.local(path);
        StringBuilder sb = new StringBuilder();

        // First level is unlocked
        sb.append(false);
        sb.append("\n");
        for(int i = 1; i < LevelName.values().length; i++) {
            sb.append(true);
            sb.append("\n");
        }

        file.writeString(sb.toString(), false);
    }

}
