package libgdx.revolutiondancers.gameobjects;

import com.badlogic.gdx.math.Rectangle;

import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.screens.ScreenAbstract;

public class Wall extends GameObjectPoolable {

	public float centrePosX;
	public float centrePosY;
	public float width;
	public float height;
	public Rectangle bounds;
	
	public Wall(float centrePosX, float centrePosY, float width, float height) {
		this.centrePosX = centrePosX;
		this.centrePosY = centrePosY;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(centrePosX - width/2, centrePosY - height/2, width, height);
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
		//Wall doesnt have a 2D representation; So it can only render in the rendering3D phase;
		if(ScreenAbstract.rendering3D) {
			
		}
		
	}

	@Override
	public void init(float x, float y, float z) {
		// TODO Auto-generated method stub
		
	}

}
