package libgdx.revolutiondancers.pools;

import libgdx.revolutiondancers.gameobjects.Monster;
import com.badlogic.gdx.utils.Pool;

public class MonsterPool extends Pool<Monster>{

	@Override
	protected Monster newObject() {
		// TODO Auto-generated method stub
		return new Monster();
	}

}
