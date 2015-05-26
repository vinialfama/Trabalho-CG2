package libgdx.revolutiondancers.gameobjects;

import com.badlogic.gdx.math.Rectangle;

import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.screens.GameScreen;

//Collect all MojoGems!! Scattered around the map.
//They drop from enemies too, btw;
public class MojoGem extends GameObjectPoolable {

	
	public void init(float x, float z){
		GameScreen.mojoGemAmount++;
		this.x = x ;
		this.z = z ;			
		boundingBox.x = x;
		boundingBox.y = z;
			//Model bla bla bla
	}
	
	
	public MojoGem() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public void init(float x, float y) {
		// TODO Auto-generated method stub
		
	}*/

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
