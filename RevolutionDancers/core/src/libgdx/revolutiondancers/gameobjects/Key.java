package libgdx.revolutiondancers.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import libgdx.revolutiondancers.engine.GameObjectPoolable;

public class Key extends GameObjectPoolable{

	
	public float centrePosX;
	public float centrePosY;
	public float width;
	public float height;
	public Rectangle bounds;
	public enum KeyType {SPECIAL, NORMAL};
	public KeyType type;
	
	public Key(float centrePosX, float centrePosY, float width, float height) {
		this.centrePosX = centrePosX;
		this.centrePosY = centrePosY;
		this.width = width;
		this.height = height;
		this.type = KeyType.NORMAL;
		bounds = new Rectangle(centrePosX - width/2, centrePosY - height/2, width, height);
	}
	
	public Key(float centrePosX, float centrePosY, float width, float height, boolean typeSpecial) {
		this.centrePosX = centrePosX;
		this.centrePosY = centrePosY;
		this.width = width;
		this.height = height;
		if(typeSpecial)  this.type = KeyType.SPECIAL;
		else this.type = KeyType.NORMAL;
		bounds = new Rectangle(centrePosX - width/2, centrePosY - height/2, width, height);
	}
	
	public KeyType getKeyType(){
		return type;
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getResizeWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getResizeHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getResizeDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setResetMeTrue() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void input() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(float x, float y, float z) {
		// TODO Auto-generated method stub
		
	}
	
}
