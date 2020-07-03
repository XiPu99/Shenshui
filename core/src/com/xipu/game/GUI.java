package com.xipu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class GUI {
    public static void showDialog(Stage stage) {
        DialogBox dialogBox = new DialogBox();
        // TODO: add a getter method to get current stage
        stage.addActor(dialogBox);
    }

    public static void showMenu() {

    }

    public static void showTextInput(Stage stage) {
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextField usernameTextField = new TextField("", skin);
        usernameTextField.setPosition(24, 73);
        usernameTextField.setSize(88, 30);
        stage.addActor(usernameTextField);
    }

}
