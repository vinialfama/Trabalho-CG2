package libgdx.revolutiondancers.engine;

import com.badlogic.gdx.utils.Array;

public abstract class DungeonGenerator {
	
	private String file;
	private Array<String> map;
	private int level; //How tough will this Dungeon be; 
	
	public void generateDungeon(int level) {
		
	}
	
	public String getTile(int x, int y) {
		String tile = map.get(y).substring(x, x+1);
		return tile;
	}

	public Array<String> getMap() {
		return map;
	}
	
}
