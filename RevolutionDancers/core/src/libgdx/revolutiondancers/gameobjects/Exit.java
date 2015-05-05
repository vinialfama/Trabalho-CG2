package libgdx.revolutiondancers.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import libgdx.revolutiondancers.engine.GameObjectPoolable;

public class Exit extends GameObjectPoolable {
	
	public float centrePosX;
	public float centrePosY;
	public float width;
	public float height;
	public Rectangle bounds;
	
	public enum ExitState {LOCKED_SPECIAL, OPEN};
	public ExitState state;

	
	public Exit(float centrePosX, float centrePosY, float width, float height) {
		this.centrePosX = centrePosX;
		this.centrePosY = centrePosY;
		this.width = width;
		this.height = height;
		unlock();
		bounds = new Rectangle(centrePosX - width/2, centrePosY - height/2, width, height);
	}
	
	public Exit(float centrePosX, float centrePosY, float width, float height, boolean locked_special) {
		this.centrePosX = centrePosX;
		this.centrePosY = centrePosY;
		this.width = width;
		this.height = height;
		if(locked_special)  this.state = ExitState.LOCKED_SPECIAL;
		else unlock();
		bounds = new Rectangle(centrePosX - width/2, centrePosY - height/2, width, height);
	}
	
	public void unlock() {
		state = ExitState.OPEN;
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
	public void init(float x, float y, float z) {
		// TODO Auto-generated method stub
		
	}

}
