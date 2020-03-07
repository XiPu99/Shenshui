package com.xipu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class DialogBox extends Actor {

    Stack stack;
    String text;
    StringBuilder sb = new StringBuilder();
    Label label;
    private float stateTime;
    private final static float TEXT_ANIMATION_FREQUENCY = 0.05f;
    private int textIndex = 0;
    private String test = "is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.";

    public DialogBox() {
        stack = new Stack();
        stack.setWidth(Gdx.graphics.getWidth());
        Image bg = new Image(StageUtility.loadTextureFromPath("bg.png"));
        stack.add(bg);

        Image image = new Image(StageUtility.loadTextureFromPath("portrait.png"));
        Table overlay = new Table();
        overlay.add(image).left().padLeft(30);
        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
        text = test;
        label = new Label("", uiSkin);

        label.setWrap(true);

        overlay.add(label).expandX().center().right().width(600);
        stack.add(overlay);
        stack.setDebug(true);
        this.setDebug(true);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;

        if (stateTime > TEXT_ANIMATION_FREQUENCY) {
            stateTime = 0;
            displayOneMoreCharacterIfPossible();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        stack.draw(batch, parentAlpha);
//        stack.drawDebug(new ShapeRenderer(ShapeRenderer.ShapeType.Filled));
    }

    private boolean displayFinished() {
        return textIndex >= text.length();
    }

    private void displayOneMoreCharacterIfPossible(){
        if(displayFinished()){
            return;
        }
        sb.append(text.charAt(textIndex++));
        label.setText(sb.toString());
    }
}
