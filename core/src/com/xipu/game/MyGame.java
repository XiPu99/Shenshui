package com.xipu.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MyGame extends ApplicationAdapter {

	Stage stage;

	@Override
	public void create () {
		ScreenViewport viewport = new ScreenViewport();
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);

		MyActor actor = new MyActor("gcc_sprite_sheet.png");
		stage.addActor(actor);
		stage.setKeyboardFocus(actor);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

//		stateTime += Gdx.graphics.getDeltaTime();
//		TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
//		spriteBatch.begin();
//		spriteBatch.draw(currentFrame, 50, 50); // Draw current frame at (50, 50)
//		spriteBatch.end();

//		if(stateTime>0.25f*16) stateTime = 0;

//		Gdx.gl.glClearColor(1, 1, 1, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.setProjectionMatrix(camera.projection);
//		batch.begin();
////		batch.draw(img, 0, 0);
//		BitmapFont font = new BitmapFont();
//		font.draw(batch, new Vector3(inputProcessor.screenX, inputProcessor.screenY, 0).toString(), 50, 50);
//		batch.end();
//
//
//		Gdx.gl.glLineWidth(1);
//		debugRenderer.begin(ShapeRenderer.ShapeType.Line);
//		debugRenderer.setColor(Color.BLACK);
//		debugRenderer.line(new Vector2(0, 16), new Vector2(800, 16));
//		debugRenderer.end();

	}
	
	@Override
	public void dispose () {

	}
}
