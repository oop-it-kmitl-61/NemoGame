package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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

public class MenuStage extends GameState {
	SpriteBatch batch;
	private Stage stage;
	private Texture menu_bg;
	private Music main_sound;
	private Sound button_click;

	public MenuStage(GameStateManager gsm) {
		super(gsm);
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
		main_sound = Gdx.audio.newMusic(Gdx.files.internal("main_sound.mp3"));
		main_sound.setLooping(true);
		main_sound.setVolume(1);
		main_sound.play();
		
		
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport());
		menu_bg = new Texture("main_menu.png");
		Gdx.input.setInputProcessor(stage);
	    Skin mySkin = new Skin(Gdx.files.internal("rainbow/skin/rainbow-ui.json"));
	    
	    Button playbn = new TextButton("PLAY" ,mySkin);
	    playbn.setSize(350,90);
        playbn.setPosition(450,345);
        playbn.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	gsm.setState(GameStateManager.PLAY);
            	button_click = Gdx.audio.newSound(Gdx.files.internal("water_click.mp3"));
            	button_click.play(1);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
	   
      

        Button scorebn = new TextButton("High Score" ,mySkin);
        scorebn.setSize(350, 90);
        scorebn.setPosition(450 , 210);
        scorebn.addListener(new InputListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	button_click = Gdx.audio.newSound(Gdx.files.internal("water_click.mp3"));
            	button_click.play(1);
            	gsm.setState(GameStateManager.HIGH);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        
        
	    //exit button
	    Button exitbn = new TextButton("Tutorial" ,mySkin);
	    exitbn.setSize(350, 90);
        exitbn.setPosition(450, 80);
        exitbn.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	button_click = Gdx.audio.newSound(Gdx.files.internal("water_click.mp3"));
            	button_click.play(1);
            	gsm.setState(GameStateManager.TUTOR);
            	
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(playbn);
        stage.addActor(scorebn);
        stage.addActor(exitbn);
		
	}
 
	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(menu_bg, 0, 0);
		batch.end();
		stage.draw();
	}

	@Override
	public void dispose() {
		main_sound.dispose();
		stage.dispose();
		
	}

}
