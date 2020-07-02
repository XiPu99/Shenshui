package com.xipu.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class GUI {
    static Stage stage;

    public static void showDialog(){
        DialogBox dialogBox = new DialogBox();
        // TODO: add a getter method to get current stage
        stage.addActor(dialogBox);
    }

    public static void showMenu(){

    }
}
