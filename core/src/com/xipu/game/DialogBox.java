package com.xipu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

import java.awt.*;

public class DialogBox extends Actor {

    Stack stack;
    String text;
    StringBuilder sb = new StringBuilder();
    Label label;
    Table overlay;
    private float stateTime;
    private final static float TEXT_ANIMATION_FREQUENCY = 0.05f;
    private int textIndex = 0;
    private String test = "is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";

    public DialogBox() {
        stack = new Stack();
        stack.setWidth(0.7f * Gdx.graphics.getWidth());
        Image bg = new Image(StageUtility.loadTextureFromPath("bg.png"));
        stack.add(bg);
        stack.setX(100);
        Image image = new Image(StageUtility.loadTextureFromPath("portrait.png"));
        overlay = new Table();
//        overlay.setDebug(true);
//        overlay = overlay.debugTable();
        overlay.add(image).left().padLeft(30).width(100).height(100).center();
        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Zpix.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.characters="你好我是谁？";
//		generator.generateData(24, "hds", false);
		parameter.size = 20;
//        generator.gene
////		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/ui/fangzheng.ttf"));
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        text = "你好我是谁？";
//        label = new Label("", uiSkin);
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLACK);
        label = new Label("", labelStyle);
        label.setWrap(true);
        label.setAlignment(Align.topLeft);
        label.setDebug(true);
        float width = overlay.add(label).expandX().left().top().fill().spaceLeft(40).getMaxWidth();
        System.out.println(width);


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
//        super.draw(batch, parentAlpha);
//        stack.setDebug(true, true);
        stack.debugAll();
//        overlay.setDebug(true);
//        overlay.debug();
//        overlay.drawDebugBo
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        stack.drawDebug(shapeRenderer);
        overlay.debugAll();
//        overlay.drawDebug(shapeRenderer);


//        label.drawDebug(shapeRenderer);
        shapeRenderer.end();
        stack.draw(batch, parentAlpha);

//        batch.end();
//        batch.begin();
//
//        shapeRenderer.begin();
//        label.drawDebug(shapeRenderer);
//        shapeRenderer.end();
//        batch.end();
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
