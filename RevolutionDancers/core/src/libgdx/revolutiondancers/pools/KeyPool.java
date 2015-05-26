package libgdx.revolutiondancers.pools;

import libgdx.revolutiondancers.gameobjects.Key;

import com.badlogic.gdx.utils.Pool;

public class KeyPool extends Pool<Key>{

	@Override
	protected Key newObject() {
		// TODO Auto-generated method stub
		return new Key();
	}

}
