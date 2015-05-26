package libgdx.revolutiondancers.pools;

import libgdx.revolutiondancers.gameobjects.Door;

import com.badlogic.gdx.utils.Pool;

public class DoorPool extends Pool<Door>{

	@Override
	public Door newObject(){
		return new Door();
	}
	
}
