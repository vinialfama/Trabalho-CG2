package libgdx.revolutiondancers.pools;

import libgdx.revolutiondancers.gameobjects.MonsterPack;

import com.badlogic.gdx.utils.Pool;

public class MonsterPackPool extends Pool<MonsterPack>{

	@Override
	protected MonsterPack newObject() {
		// TODO Auto-generated method stub
		return new MonsterPack();
	}

}
