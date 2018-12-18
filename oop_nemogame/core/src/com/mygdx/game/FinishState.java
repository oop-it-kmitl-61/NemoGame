package com.mygdx.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


import GameManager.GameState;
import GameManager.GameStateManager;
import Tool.ScoreManager;

public class FinishState extends GameState{
	private SpriteBatch batch;
	private Texture image_bg;
	private int yourScore;
	ScoreManager end_Score;
	private BitmapFont showScore;
	private Stage stage;
	private Skin mySkin;
	private TextField input_name;
	private String namePlayer = "player1";
	private Music main_sound;
	private Sound click;
	private float delay =4;
	
	public FinishState(GameStateManager gsm) {
		super(gsm);
	}


	@Override
	public void create() {
		Timer.schedule(new Task(){
		    @Override
		    public void run() {
		        // Do your work
		    }
		}, delay);
		main_sound = Gdx.audio.newMusic(Gdx.files.internal("Baby_shark.mp3"));
		main_sound.setLooping(true);
		main_sound.setVolume(0.5f);
		main_sound.play();
		
		end_Score = new ScoreManager();
		batch = new SpriteBatch();
		image_bg = new Texture(Gdx.files.internal("timeout_gameover.png"));
		showScore = new BitmapFont();
		mySkin = new Skin(Gdx.files.internal("rainbow/skin/rainbow-ui.json"));
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Winter flakes.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
		params.size = 80;
		params.color = Color.WHITE;
		showScore = generator.generateFont(params);
		
		input_name = new TextField("Player1", mySkin);
        input_name.setAlignment(1);
        input_name.setPosition(480, 160);
        input_name.setSize(350, 80);
        input_name.setMessageText("Enter Your Name");
        
        
        stage.addActor(input_name);
        
		
		Button playAgain = new TextButton("Play Again" ,mySkin);
	    playAgain.setSize(350,90);
        playAgain.setPosition(300,50);
        playAgain.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	gsm.setState(GameStateManager.PLAY);
            	click = Gdx.audio.newSound(Gdx.files.internal("water_click.mp3"));
            	click.play(1);
            	main_sound.dispose();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        
        Button submitBn = new TextButton("Submit" ,mySkin);
	    submitBn.setSize(350,90);
        submitBn.setPosition(620,50);
        submitBn.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	namePlayer = input_name.getText();
            	click = Gdx.audio.newSound(Gdx.files.internal("water_click.mp3"));
            	click.play(1);
            	FileHandle file = Gdx.files.external("data1.txt");
            	FileHandle file2 = Gdx.files.external("data.txt");
            	file2.writeString(ScoreManager.SCORE+"|"+namePlayer+" ", true);
            	
   
            
            	gsm.setState(GameStateManager.MENU);
            	
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(playAgain);
        stage.addActor(submitBn);
        
	}

	@Override
	public void render() {
		batch.begin();
		batch.draw(image_bg, 0, 0);
		showScore.draw(batch, ""+ScoreManager.SCORE, 630, 330);
		batch.end();
		stage.draw();
		
		
	}
	

	@Override
	public void dispose() {
		main_sound.dispose();
		batch.dispose();
		stage.dispose();
	}


}
