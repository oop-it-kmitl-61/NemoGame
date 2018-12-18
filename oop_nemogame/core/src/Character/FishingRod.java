package Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class FishingRod {
	Texture rod;
	Sprite test;
	Rectangle rect;
	public Rectangle getRect() {
		return rect;
	}
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	float x = 0;
	float y = 0;
	float width = 20, height = 20;
	
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public FishingRod(float x, float y) {
		rod = new Texture(Gdx.files.internal("hook.png"));
		test = new Sprite(rod);
		this.x = x;
		this.y = y;
		test.setPosition(x, y-200);
		test.setSize(width, height);
		rect = new Rectangle(x, y-200 ,width , height);
		rect.setPosition(x, y-200);
	}
	public void draw(SpriteBatch batch) {
		test.draw(batch);
		
	}
	public void hide() {
		test.setPosition(3000, 3000);
	}
	public void show() {

		test.setPosition(x, y);
		rect.setPosition(x, y);
	}
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		test.setPosition(this.x, this.y);
		rect.setPosition(this.x, this.y);
	}
	public void update(boolean check) {
		if(check) {
			
		}
		else{
		this.y--;
		}
		test.setPosition(this.x, this.y);
		rect.setPosition(this.x, this.y);
		
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
}
