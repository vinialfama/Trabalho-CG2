package libgdx.revolutiondancers.pools;

import libgdx.revolutiondancers.gameobjects.MojoGem;
import com.badlogic.gdx.utils.Pool;

public class MojoGemPool extends Pool<MojoGem>{

	@Override
	protected MojoGem newObject() {
		// TODO Auto-generated method stub
		return new MojoGem();
	}

}
