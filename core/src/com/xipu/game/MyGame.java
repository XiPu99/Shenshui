package com.xipu.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kyper.yarn.Library;
import com.kyper.yarn.Value;
import com.xipu.game.actors.NPCActor;
import com.xipu.game.actors.PlayerActor;
import com.xipu.game.actors.TileActor;
import com.kyper.yarn.Dialogue;

import java.util.List;

public class MyGame extends ApplicationAdapter {

	GameStage stage;
	Skin skin;
	String text = "is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
	Label label;
	StringBuilder sb = new StringBuilder();
	float stateTime = 0;
	private int index;
	Dialogue dialogue;

	@Override
	public void create () {
		ScreenViewport viewport = new ScreenViewport();
		stage = new GameStage(viewport);
		Gdx.input.setInputProcessor(stage);

		List<TileActor> map = JSONParser.loadMapFromJSON("test.json");
		for (TileActor actor1: map){
			stage.addActor(actor1);
		}

		Actor actor = new PlayerActor("gcc_sprite_sheet.png");
		Actor npcActor = new NPCActor("gcc_sprite_sheet.png");
		npcActor.setPosition(400, 400);
		stage.addActor(actor);
		stage.addActor(npcActor);
		stage.setKeyboardFocus(actor);
		actor.setPosition(100, 100);
//		System.out.println(stage.screenToStageCoordinates(actor.get));

		DialogueData data = new DialogueData("test_data");
		dialogue = new Dialogue(data);
		dialogue.getLibrary().registerFunction("setAction", 1, new Library.Function() {
			@Override
			public void invoke(Value... params) {
				Value action = params[0];

				if(!action.getStringValue().isEmpty()){
					System.out.println("Getting string from yarn!!!");
				}

			}
		});

		dialogue.loadFile("dialog.json");
		dialogue.start();

//		GUI.stage = stage;
//		GUI.showDialog(stage);

//		GUI.showTextInput(stage);
//		System.out.println(map);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stateTime += Gdx.graphics.getDeltaTime();

//		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//			stage.getCamera().translate(3, 0, 0);
//		}
//		stage.setDebugAll(true);
		stage.draw();

		if (dialogue.isNextLine()){
			Dialogue.LineResult lineResult = dialogue.getNextAsLine();
			System.out.println(lineResult.getText());
		}

	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}
}
