package com.xipu.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MyGame extends ApplicationAdapter {

	GameStage stage;
	Skin skin;
	String text = "is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
	Label label;
	StringBuilder sb = new StringBuilder();
	float stateTime = 0;
	private int index;

	@Override
	public void create () {
		ScreenViewport viewport = new ScreenViewport();
		stage = new GameStage(viewport);
		Gdx.input.setInputProcessor(stage);

		PlayerActor actor = new PlayerActor("gcc_sprite_sheet.png");
		stage.addActor(actor);
		stage.setKeyboardFocus(actor);
		actor.setPosition(100, 100);


		GUI.stage = stage;
//		GUI.showDialog(stage);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stateTime += Gdx.graphics.getDeltaTime();

//		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//			stage.getCamera().translate(3, 0, 0);
//		}

		stage.draw();

	}
	
	@Override
	public void dispose () {

	}
}
