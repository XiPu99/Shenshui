package com.xipu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.HashMap;
import java.util.Map;

public final class StageUtility {

    static Stage currentStage;
    static Map<String, Texture> textureMap = new HashMap<>();

    public static void changeCursor() {
        Pixmap pm = new Pixmap(Gdx.files.internal("gcc_sprite_sheet.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
        pm.dispose();
    }

    public static void setDefaultCursor() {
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
    }

    public static Texture loadTextureFromPath(String path) {
        if (!textureMap.containsKey(path)) {
            textureMap.put(path, new Texture(Gdx.files.internal(path)));
        }

        return textureMap.get(path);
    }

    public void switchStage() {

    }

    public static void spawnNPCAtRandomLocations() {
        //TODO: fill in the logic here
    }

}
