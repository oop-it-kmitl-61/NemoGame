package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import GameManager.GameState;
import GameManager.GameStateManager;

public class TutorialState extends GameState{
	private SpriteBatch batch;
	private Texture tutorial_bg;
	private Stage stage;
	private Skin mySkin;
	private Sound click;
	
	
	public TutorialState(GameStateManager gsm) {
		super(gsm);
		
	}

	@Override
	public void create() {
		batch =  new SpriteBatch();
		stage = new Stage(new ScreenViewport());
		tutorial_bg = new Texture(Gdx.files.internal("tutorial_1.png"));
		stage = new Stage();
		mySkin = new Skin(Gdx.files.internal("rainbow/skin/rainbow-ui.json"));
		Gdx.input.setInputProcessor(stage);
		
		
		Button menuBn = new TextButton("Main Menu" ,mySkin);
	    menuBn.setSize(350,90);
        menuBn.setPosition(620,50);
        menuBn.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	gsm.setState(GameStateManager.MENU);
            	click = Gdx.audio.newSound(Gdx.files.internal("water_click.mp3"));
            	click.play();
            	
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(menuBn);
		
	}

	@Override
	public void render() {
		batch.begin();
		batch.draw(tutorial_bg, 0, 0);
		batch.end();
		stage.draw();
		
	}

	@Override
	public void dispose() {
		
	}

}
