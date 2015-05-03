package libgdx.revolutiondancers.gameobjects;

import com.badlogic.gdx.math.Rectangle;

import libgdx.revolutiondancers.engine.GameObjectPoolable;

public class Door extends GameObjectPoolable {

	public float centrePosX;
	public float centrePosY;
	public float width;
	public float height;
	public Rectangle bounds;
	public enum DoorState {LOCKED, CLOSED, OPEN};
	public DoorState state;
	
	
	public Door(float centrePosX, float centrePosY, float width, float height, DoorState state) {
		this.centrePosX = centrePosX;
		this.centrePosY = centrePosY;
		this.width = width;
		this.height = height;
		this.state = state;
		bounds = new Rectangle(centrePosX - width/2, centrePosY - height/2, width, height);
	}
	
	public void open() {
		state = DoorState.OPEN;
		// TODO: auto close door after x seconds
	}
	
	public void unlock() {
		state = DoorState.CLOSED;
	}

	public void close() {
		state = DoorState.CLOSED;
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
	
}
