package com.xipu.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xipu.game.actors.PlayerActor;
import com.xipu.game.actors.TileActor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStage extends Stage {

    private TileActor[][] matrix;
    private PlayerActor playerActor;
    private Map<int[], List<TileActor>> tileMap;


    public GameStage(Viewport viewport) {
        super(viewport);
        tileMap = new HashMap<>();
    }

    private void checkCollision() {
//        matrix[0][0]
    }

    private void loadTileMapFromJSON() {

    }

    private void buildStage() {
        tileMap.values().forEach(list -> list.forEach(this::addActor));
    }



    @Override
    public boolean keyDown(int keyCode) {
        switch (keyCode){
            case Input.Keys.ESCAPE:

                System.exit(0);
//                Gdx.app.exit();
                break;
        }
        return super.keyDown(keyCode);
    }
}
