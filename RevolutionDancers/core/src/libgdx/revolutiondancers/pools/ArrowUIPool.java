package libgdx.revolutiondancers.pools;

import com.badlogic.gdx.utils.Pool;
import libgdx.revolutiondancers.gameobjects.ArrowUI;;

public class ArrowUIPool extends Pool<ArrowUI>{

	@Override
	protected ArrowUI newObject() {
		// TODO Auto-generated method stub
		return new ArrowUI();
	}

}
