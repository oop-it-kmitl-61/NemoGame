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
import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
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

import GameManager.GameState;
import GameManager.GameStateManager;
import Tool.ScoreManager;

public class ScoreState extends GameState{
	private SpriteBatch batch;
	private Texture tutorial_bg;
	private Stage stage;
	private Skin mySkin;
	private BitmapFont h_num, m_num, l_num, hn,mn, ln, name, score;
	String high, medium, low;
	Button menuBn;
	String first_score ="", first_name = "", second_score="", second_name="", third_score="", third_name="";
	
	public ScoreState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		
		FileHandle file2 = Gdx.files.external("data.txt");
		String a = file2.readString();
    	ArrayList<String> arr;
    	arr = new ArrayList();
    	String keep = "";
    	arr.add("2|Wai");
    	arr.add("1|nuk");
    	for( int i =0 ; i < a.length(); i++) {
    		if (a.charAt(i) != ' ') {
    			keep+=a.charAt(i);
    			
    		}
    		if (a.charAt(i) == ' ') {
    			arr.add(keep);
    			keep = "";
    		}
    		
    	}
    		
    	
    	Collections.sort(arr, Collections.reverseOrder()); 
    	for (String temp: arr){
    		System.out.println(temp);
    	}
    	
    	
    	for(int i = 0; i < 3; i++) {
    		boolean number = true;
    		for(int j = 0; j < arr.get(i).length() ; j++ ) {
    			if(i == 0) {
    				if (arr.get(i).charAt(j) != '|' && number == true) {
    					first_score += arr.get(i).charAt(j);
    				}
    				if (arr.get(i).charAt(j) == '|') {
    					number = false;
    				}
    				else if (arr.get(i).charAt(j) != '|' && number == false){
    					first_name += arr.get(i).charAt(j);
    				}
    			}
    			if(i == 1) {
    				if (arr.get(i).charAt(j) != '|' && number == true) {
    					second_score += arr.get(i).charAt(j);
    				}
    				if (arr.get(i).charAt(j) == '|') {
    					number = false;
    				}
    				else if (arr.get(i).charAt(j) != '|' && number == false){
    					second_name += arr.get(i).charAt(j);
    				}
    			}
    			if(i == 2) {
    				if (arr.get(i).charAt(j) != '|' && number == true) {
    					third_score += arr.get(i).charAt(j);
    				}
    				if (arr.get(i).charAt(j) == '|') {
    					number = false;
    				}
    				else if (arr.get(i).charAt(j) != '|' && number == false){
    					third_name += arr.get(i).charAt(j);
    				}
    			}
    		}
    	}
    	
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		tutorial_bg = new Texture(Gdx.files.internal("highscore.png"));
		mySkin = new Skin(Gdx.files.internal("rainbow/skin/rainbow-ui.json"));
		
		menuBn = new TextButton("Main Menu" ,mySkin);
	    menuBn.setSize(350,90);
        menuBn.setPosition(620,80);
        menuBn.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	gsm.setState(GameStateManager.MENU);
            	
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(menuBn);
        
        h_num = new BitmapFont();
        m_num = new BitmapFont();
        l_num = new BitmapFont();
        hn = new BitmapFont();
        mn = new BitmapFont();
        ln = new BitmapFont();
        name = new BitmapFont();
        score = new BitmapFont();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Winter flakes.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
		params.size = 36;
		params.color = Color.DARK_GRAY;
		
		FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("Winter flakes.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter params2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		params2.size = 60;
		params2.color = Color.BLACK;
		h_num = generator.generateFont(params);
		m_num = generator.generateFont(params);
		l_num = generator.generateFont(params);
		hn = generator.generateFont(params);
		mn = generator.generateFont(params);
		ln = generator.generateFont(params);
		name = generator2.generateFont(params2);
		score = generator2.generateFont(params2);
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		batch.begin();
		batch.draw(tutorial_bg, 0, 0);
		
		score.draw(batch, "Score", 800, 480);
		h_num.draw(batch, first_score, 900, 380);
		m_num.draw(batch, second_score, 900, 320);
		l_num.draw(batch, third_score, 900, 260);
		
		name.draw(batch, "Name", 350, 480);
		hn.draw(batch, first_name, 380, 380);
		mn.draw(batch, second_name, 380, 320);
		ln.draw(batch, third_name, 380, 260);
		batch.end();
		stage.draw();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
