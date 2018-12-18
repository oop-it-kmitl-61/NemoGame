package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import Character.BigFish;
import Character.FishingRod;
import GameManager.GameState;
import GameManager.GameStateManager;
import Tool.ScoreManager;

public class PlayState extends GameState {
	private String str = "2:00";
	private int mili = 0;
	private int sec = 0;
	private int min = 2;
	private boolean ck;
	private BitmapFont font, countScore, myScore, lb_time;
	
	private boolean checkboat = true;
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT= 720;
	private int score =0;
	private FishingRod rod;
	Rectangle rect_fish1, rect_rod, rect_fish2 , rect_fish3;
    private Texture bg_Texture;
    
	Random rand = new Random (); //อันนี้เอาไว้randomค่าตัวเลข
	
	private SpriteBatch batch;
	private ArrayList <BigFish> keep_fish;
	private Texture img; //รูป
	private Music play_bg;
	private Sound rod_sound, right_sound, error_sound, time_out;
	
	float x1 = 0; // ค่า x ของเรือ
	float y1 = 480; // ค่า y ของเรือ
	
	float x_rod = x1;
	float y_rod = 0;
	boolean check = false;
	boolean check_rod = false;
	boolean check_sound = true;
	int temp = 0;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		play_bg = Gdx.audio.newMusic(Gdx.files.internal("playstate.mp3"));
		play_bg.setLooping(true);
		play_bg.setVolume(1);
		play_bg.play();
		
		countScore = new BitmapFont();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Winter flakes.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
		params.size = 80;
		params.color = Color.PINK;
		countScore = generator.generateFont(params);
		
		font = new BitmapFont();
		font = generator.generateFont(params);
		
		myScore = new BitmapFont();
		myScore = generator.generateFont(params);
		
		lb_time = new BitmapFont();
		lb_time = generator.generateFont(params);
		
		rod = new FishingRod(x1, y1);
		batch = new SpriteBatch();
		//set รูปภาพ
		img = new Texture(Gdx.files.internal("fisherman.png"));
		bg_Texture = new Texture("sea_bg.png");

		rect_rod = new Rectangle(rod.getX(), rod.getY(), rod.getHeight(), rod.getWidth());
		
		keep_fish = new ArrayList();
		for(int i = 0 ; i < 10 ; i++) {
			keep_fish.add(new BigFish(rand.nextInt(840),rand.nextInt(300), rand.nextInt(4)));
		}
		
		
	}


	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(bg_Texture, 0, 0);
		
		myScore.draw(batch, ""+score, 1200, 600);
		
		lb_time.draw(batch, "TIME", 50, 680);
		
		countScore.draw(batch, "Score", 1050, 700);
		rod.draw(batch);
		rod.hide();
		
		if(min <=0  && sec <=0 ) {
			time_out = Gdx.audio.newSound(Gdx.files.internal("timeout.mp3"));
			time_out.play(1);
			play_bg.dispose();
			ScoreManager.SCORE = score;
			gsm.setState(GameStateManager.END);
			
		}
		
		font.draw(batch, str, 50, 600);
		if(sec < 0) {
		min--;
		sec = 59;
		}
		if(mili < 0) {
		sec--;
		mili = 100;
		}
		else {
		mili-= 2;
		}
		if (min < 10) {
		str = "0" + min + ":"+sec;
		}
		if(sec < 10) {
		str = "0" + min + ":0"+sec;
		}
		
		//set text และ set ตำแหน่ง
		//font.draw(batch, "Hello World", 300, 300);
		
		//สั่งให้batch ทำการวาดภาพที่ตำแหน่ง (ตำแปรรูป, ตำแหน่งแกน x, ตำแหน่งแกน y, width, height)
		batch.draw(img, x1, y1, 80, 60);//อันนี้คือสร้างเรือ
		

		//อันนี้คือดักว่าถ้ากดลูกศรซ้ายจะทำไร ขวาทำไร
		if (Gdx.input.isKeyPressed(Keys.RIGHT) && checkboat == true) {
			x1 += 500 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)&& checkboat == true) {
			x1 -= 500 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE) && check_sound == true) {
			rod_sound = Gdx.audio.newSound(Gdx.files.internal("rod_sound.mp3"));
			rod_sound.play(1);
			checkboat = false;
			check = true;
			check_sound = false;
			x_rod = x1;
			rod.setPosition(x1, y1-50);
			
		}
		
		
		
		if (check == true) {
			rod.show();
		
			if(Gdx.input.isKeyPressed(Keys.W)) {
				y_rod = rod .getY();
				y_rod += 500 * Gdx.graphics.getDeltaTime();
				rod.setY(y_rod);
				rod.update(false);
				rect_rod.setPosition(rod.getX(), rod.getY());
			}
			if(Gdx.input.isKeyPressed(Keys.A)) {
				x_rod -= 300 * Gdx.graphics.getDeltaTime();
				rod.setX(x_rod);
				rod.update(false);
				rect_rod.setPosition(rod.getX(), rod.getY());
			}
			if(Gdx.input.isKeyPressed(Keys.D)) {
				x_rod += 300 * Gdx.graphics.getDeltaTime();
				rod.setX(x_rod);
				rod.update(false);
				rect_rod.setPosition(rod.getX(), rod.getY());
			}
			for(int i = 0;i < keep_fish.size(); i++) {
				if(!rect_rod.overlaps((Rectangle)keep_fish.get(i).getRect()) && check_rod == false) {
					ck = false;
				}
				else {
					ck = true;
				}
			}
			
			
			
			rod.update(ck);
			rect_rod.setPosition(rod.getX(), rod.getY());
		}
		for(BigFish col:keep_fish) {
			col.draw(batch);
			col.update();
		}
		
		for(int i = 0;i < keep_fish.size(); i++) {
			if(rect_rod.overlaps((Rectangle)keep_fish.get(i).getRect()) && check_rod == false) {
				keep_fish.get(i).setPosition(rect_rod.getX(), rect_rod.getY());
				check_rod = true;
				temp = i;
			}
			if (check_rod == true) {
				keep_fish.get(temp).setPosition(rect_rod.getX(), rect_rod.getY());
				if (rect_rod.getY() >= 450) {
					checkboat = true;
					rod.hide();
					check_rod = false;
					check = false;
					check_sound = true;
					rod.hide();
					temp = 0;
				}
			}
		}
		for(int hh =0;hh < keep_fish.size();hh++) {
			if(keep_fish.get(hh).getY() >=450) {
				if (keep_fish.get(hh).checkWord() == true){
					
					score += 1;
					right_sound = Gdx.audio.newSound(Gdx.files.internal("right_sound.mp3"));
					right_sound.play(1);
					keep_fish.remove(hh);
					keep_fish.add(new BigFish(rand.nextInt(840),rand.nextInt(300), rand.nextInt(4)));
					break;

				}else if(keep_fish.get(hh).checkWord() == false) {
					error_sound = Gdx.audio.newSound(Gdx.files.internal("error_sound.mp3"));
					error_sound.play(1);
					sec -= 5;
					keep_fish.remove(hh);
					keep_fish.add(new BigFish(rand.nextInt(840),rand.nextInt(300), rand.nextInt(4)));
					break;
				}
			}
		} 
				
		//อันนี้คือเวลา x มันหลุดขอบ ก็ให้มันมาเริ่มที่ 750 ใหม่
		if (x1 > 1200) {
			x1 = 1200;
		}
		//อันนี้คือเวลา x มันหลุดขอบ ก็ให้มันมาเริ่มที่ 0 ใหม่
		if (x1 < 0) {
			x1 = 0;
		}
		batch.end();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		play_bg.dispose();
	}

}
