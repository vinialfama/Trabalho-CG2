package libgdx.revolutiondancers.pools;

import com.badlogic.gdx.utils.Pool;

import libgdx.revolutiondancers.gameobjects.Chest;

public class ChestPool extends Pool<Chest>{

	@Override
	public Chest newObject(){
		return new Chest();
	}

}
