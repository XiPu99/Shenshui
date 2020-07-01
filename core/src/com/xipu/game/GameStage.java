package com.xipu.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

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


}
