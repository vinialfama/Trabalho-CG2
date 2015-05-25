package libgdx.revolutiondancers.pools;

import libgdx.revolutiondancers.gameobjects.Wall;

import com.badlogic.gdx.utils.Pool;

public class WallPool extends Pool<Wall>{

	@Override
	protected Wall newObject() {
		// TODO Auto-generated method stub
		return new Wall();
	}

}
