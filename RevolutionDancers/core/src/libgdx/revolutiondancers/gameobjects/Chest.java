package libgdx.revolutiondancers.gameobjects;

import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.math.Rectangle;

import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.engine.GlobalAssets;
import libgdx.revolutiondancers.engine.Globals;
import libgdx.revolutiondancers.gameobjects.Door.DoorState;
import libgdx.revolutiondancers.screens.ScreenAbstract;

//Chests may contain special keys inside; Special keys are sometimes necessary to unlock the level exit [From level 3 onwards]; 
//Chests may contain MojoGems
public class Chest extends GameObjectPoolable {


	public enum ChestState {LOCKED, UNLOCKED};
	public ChestState state;

	GameObjectPoolable treasure;
	
	public void init(float x, float z, ChestState state, GameObjectPoolable treasure) {
		this.x = x ;
		this.z = z ;			
		boundingBox.set(x, z, 2*Wall.width, 2*Wall.depth);	
		this.state = state;
		
		switch(state){
		case LOCKED:  
			break;
		case UNLOCKED:  
			break;		
		}
		
		if(treasure != null) this.treasure = treasure;
		else{
			treasure = null;
		}
	
	}
	
	public void spawnTreasure() {
		if(treasure == null) return;  //Bau vazio!
		ScreenAbstract.addLaterPoolableObject3D(treasure);
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
	
	
	///////////////////////////////////////////
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
