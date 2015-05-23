package libgdx.revolutiondancers.engine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public abstract class DungeonLoader {
	
	private static String file;
	private static Array<String> map; //private Array<Array<String>> map;

	public static void load(String mapFile) {
		file = mapFile;
		map = new Array<String>(); //map = new Array<Array<String>>();
		
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(Gdx.files.internal("RevolutionDancersAssets/Dungeons/"+file).read()));
			String mapRowString;
			while ((mapRowString = in.readLine()) != null) map.add(mapRowString);
		} catch (Throwable e) {
			System.out.println("Error loading map!			|MapReader Error|");
		}
		
/*	       BufferedReader in = null; 
	       try {
	        	in = new BufferedReader(new InputStreamReader(Gdx.files.internal("RevolutionDancersAssets/Maps/"+file).read()));
	            String currentLineString;
	            int mapColumnStringJ;
	            while ((currentLineString = in.readLine()) != null) {			//Each row contains an ENTIRE line; Later to obtain information from it it is necessary to iterate each char;													
	            	map.get(mapColumnStringJ).add(currentLineString);  			//To find the next row, one has to enter what column it is;
	                mapColumnStringJ++;
	            }
	        } catch (Throwable e) {
	            System.out.println("IOException");
	        }*/
		
		
	}
	
	public static String getTile(int x, int z) {
		String tile = map.get(z).substring(x, x+1);
		return tile;
	}

	public static Array<String> getMap() {
		return map;
	}
	
}
