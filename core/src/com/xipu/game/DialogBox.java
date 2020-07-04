package com.xipu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kyper.yarn.Dialogue;


public class DialogBox extends Actor {

    private static final float TEXT_ANIMATION_FREQUENCY = 0.05f;
    private static final float DIALOG_BOX_WIDTH_PROPORTION = 0.7f;
    private static final int DEFAULT_FONT_SIZE = 20;
    private static final String DEFAULT_FONT_FILE_PATH = "Zpix.ttf";
    private static final String EMPTY_STRING = "";
    private Stack stack;
    private String currentDialogText;
    private StringBuilder displayedCharactersSoFar = new StringBuilder();
    private Label dialogTextArea;
    private float stateTime;
    private int caretIndex = 0;
    private Dialogue dialogue;

    public DialogBox(Stage stage) {
        // load dialog
        DialogueData data = new DialogueData("test_dialog_box");
        this.dialogue = new Dialogue(data);
        this.dialogue.loadFile("dialog.json");

        // set up dialog box UI widget
        stack = new Stack();
        stack.setWidth(DIALOG_BOX_WIDTH_PROPORTION * Gdx.graphics.getWidth());
        Image bg = new Image(StageUtility.loadTextureFromPath("bg.png"));
        stack.add(bg);
        GUI.centerHorizontally(stack);

        // use table to organize the layout of the portrait and the dialog text area
        Table overlay = new Table();
        // add character portrait
        Image image = new Image(StageUtility.loadTextureFromPath("portrait.png"));
        overlay.add(image).left().padLeft(30).width(100).height(100).center();
        // add dialog text area
        Label.LabelStyle labelStyle = new Label.LabelStyle(generateBitMapFontFromCharacters(FreeTypeFontGenerator.DEFAULT_CHARS), Color.BLACK);
        dialogTextArea = new Label(EMPTY_STRING, labelStyle);
        dialogTextArea.setWrap(true);
        dialogTextArea.setAlignment(Align.topLeft);
        dialogTextArea.setDebug(true);
        overlay.add(dialogTextArea).expandX().left().top().fill().spaceLeft(40);
        stack.add(overlay);
        stack.setDebug(true);
        this.setDebug(true);

        this.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                updateDialogue();
                return true;
            }
        });

        // need to set bounds here so that the click listener can fire when there is mouse click within the bound
        this.setBounds(stack.getX(), stack.getY(), stack.getWidth(), stack.getHeight());

        startDialog(); // start getting the first line from the dialogue
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

    private void startDialog(){
        this.dialogue.start();
        updateDialogue();
    }

    private void resetDialogText() {
        dialogTextArea.setText(EMPTY_STRING);
        dialogTextArea.setStyle(new Label.LabelStyle(generateBitMapFontFromCharacters(currentDialogText), Color.BLACK));
        caretIndex = 0;
        displayedCharactersSoFar.setLength(0);
    }

    private BitmapFont generateBitMapFontFromCharacters(String characters) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(DEFAULT_FONT_FILE_PATH));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = characters;
        parameter.size = DEFAULT_FONT_SIZE;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }

    void updateDialogue() {
        if (!dialogue.isRunning()) {
            throw new RuntimeException("Still updating dialogue when it's already stopped");
        }

        if (dialogue.isNextLine()) {
            Dialogue.LineResult lineResult = dialogue.getNextAsLine();
            currentDialogText = lineResult.getText();
            System.out.println(currentDialogText);
            resetDialogText();
        } else if (dialogue.isNextCommand()) {
            System.out.println("Command!");
        } else if (dialogue.isNextOptions()) {
            Dialogue.OptionResult optionResult = dialogue.getNextAsOptions();
            optionResult.choose(0);
        } else if (dialogue.isNextComplete()) {
            Dialogue.NodeCompleteResult completeResult = dialogue.getNextAsComplete();
            if (completeResult.next_node == null){
                System.out.println("Dialog ends!");
                dialogue.stop();
                this.setVisible(false);
            } else{
                updateDialogue();
            }
        } else {
            throw new RuntimeException("Invalid dialogue's next content");
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        super.draw(batch, parentAlpha);
//        stack.setDebug(true, true);
//        stack.debugAll();
//        overlay.setDebug(true);
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        stack.drawDebug(shapeRenderer);
//        overlay.debugAll();
//        overlay.drawDebug(shapeRenderer);


//        label.drawDebug(shapeRenderer);
        shapeRenderer.end();
        stack.draw(batch, parentAlpha);
    }

    private boolean displayFinished() {
        return caretIndex >= currentDialogText.length();
    }

    private void displayOneMoreCharacterIfPossible() {
        if (displayFinished()) {
            return;
        }
        displayedCharactersSoFar.append(currentDialogText.charAt(caretIndex++));
        dialogTextArea.setText(displayedCharactersSoFar.toString());
    }

}
