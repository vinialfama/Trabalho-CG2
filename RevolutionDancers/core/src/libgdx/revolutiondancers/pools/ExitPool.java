package libgdx.revolutiondancers.pools;

import libgdx.revolutiondancers.gameobjects.Exit;
import com.badlogic.gdx.utils.Pool;

public class ExitPool extends Pool<Exit>{

	@Override
	protected Exit newObject() {
		
		return new Exit();
	}

}
