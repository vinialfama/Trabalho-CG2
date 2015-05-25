package libgdx.revolutiondancers.pools;

import libgdx.revolutiondancers.gameobjects.FloorAndCeiling;

import com.badlogic.gdx.utils.Pool;

public class FloorAndCeilingPool extends Pool<FloorAndCeiling>{

	@Override
	protected FloorAndCeiling newObject() {
		// TODO Auto-generated method stub
		return new FloorAndCeiling();
	}
	
}
