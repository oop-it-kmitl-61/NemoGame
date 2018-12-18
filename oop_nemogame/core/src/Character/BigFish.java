package Character;

import java.util.Arrays;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class BigFish {
	private BitmapFont font;
	private Texture fish,te,te2;
	private SpriteBatch aa;
	private Sprite test;
	private Rectangle rect;
	private String word;
	private String allword[] = {"acheive", "accross", "arguement", "belive", "buisness", 
			  "calender","collegue", "concious", "dissapear","dissapoint",
			  "ecstacy", "embarass", "enviroment", "familar", "finaly",
			  "foriegn", "freind", "futher", "jist", "fourty", "foward",
			  "gaurd", "happend", "interupt","knowlege", "lollypiop","pharoah",
			  "peice", "politican", "realy", "religous", "sence", "seperate",
			  "seige", "suprise", "succesful", "tatoo", "therefor", "tounge",
			  "tounge", "truely", "unforseen", "untill", "wierd", "wich", 
			  "tommorow","unforseen","ecstacy", "buffer", "budget", "broad", "breed", "award", "author", "attack", "center", "censor", "certain", "champ", "channel", "chapter", "circus", "claim", "clever", "cloud", "combat", "dearly", "decade", "dental", "delete", "deploy", "desert", "device", "direct", "dismiss", "disturb", "divide", "eagle", "earth", "ecology", "effect", "elite", "enable", "enemy", "engine", "enter", "entire", "factory", "factor", "fake", "fancy", "fellow", "garden", "gentle", "glass", "gossip", "hammer", "heart", "honest",  "humor", "hybrid", "image", "income", "inject","insist", "invent", "island", "jacket", "kidnap", "leather"};
	
	private String correctWord[] = {"buffer", "budget", "broad", "breed", "award", "author", "attack", "center", "censor", "certain", "champ", "channel", "chapter", "circus", "claim", "clever", "cloud", "combat", "dearly", "decade", "dental", "delete", "deploy", "desert", "device", "direct", "dismiss", "disturb", "divide", "eagle", "earth", "ecology", "effect", "elite", "enable", "enemy", "engine", "enter", "entire", "factory", "factor", "fake", "fancy", "fellow", "garden", "gentle", "glass", "gossip", "hammer", "heart", "honest",  "humor", "hybrid", "image", "income", "inject","insist", "invent", "island", "jacket", "kidnap", "leather"};
	private boolean check = true;
	private String type_fish;
	Random number_word;
	
	float x, y; // เอาค่า x กับ y มากำหนด position ของปลา
	int a= 10;
	int width = 100, height = 100;
	
	
	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BigFish(float x2, float y2, float types) {
		aa = new SpriteBatch();
		number_word = new Random();
		this.word = allword[number_word.nextInt(80)];
		
		font = new BitmapFont();
		te = new Texture(Gdx.files.internal("fish3_2.png"));
		te2 = new Texture(Gdx.files.internal("fish3_2.png"));
		if (types == 2.0) {
			fish = new Texture(Gdx.files.internal("fish3_2.png"));
			this.type_fish = "a";
		}
		else if (types == 1.0) {
			fish = new Texture(Gdx.files.internal("fish2_2.png"));
			this.type_fish = "a";
		}
		else {
			fish = new Texture(Gdx.files.internal("nemo_fish.png"));
			this.type_fish = "b";
		}
		
		test = new Sprite(fish);
		this.x = x2;
		this.y = y2;
		test.setPosition(this.x, this.y);
		test.setSize(50, 50); // setขนาด
		rect = new Rectangle(x, y ,width , height);
		
		

	}
	
	public BigFish() {
		fish = new Texture(Gdx.files.internal("nemo_fish.png"));
		test = new Sprite(fish);
		test.setPosition(0, 0);
		test.setSize(100, 100); //set ขนาด
		rect = new Rectangle(x, y ,width , height);
		
	}
	public String getWord() {
		return this.word;
	}
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	//สร้างปลา
	public void draw(SpriteBatch batch) {
		

		test.draw(batch);
		font.draw(batch, word, x+30, y+55);
		
		font.setColor(Color.BLACK);
		font.getData().setScale(1,1);
	}
	// ฟังก์ชั่นนี้ทำเพื่อให้ปลาเคลื่อนที่ ถ้าปลาชนขอบขวาให้ปลาไปทางซ้ายแทน
	public void update() {
		if(this.x >= 1280) {
			check = false;
		}
		if (this.x <= 0) {
			check = true;
		}
		if (check) {
			this.x+=0.00000001;
			this.x = x + a/5;
		}else {
			this.x-=0.0000001;
			this.x = x - a/5;
		}
		test.setPosition(this.x, this.y);
		rect.setPosition(this.x, this.y);
	}
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		test.setPosition(this.x, this.y);
		rect.setPosition(this.x, this.y);
	}
	
	public boolean checkWord() {
		
		boolean key = true;
		for(int i=0;i<60;i++) {

			if(word == correctWord[i]) {
				System.out.println(word);
				key = true;
				break;
			}
			else {
				key = false;
				
			}
		}
		return key;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
}
