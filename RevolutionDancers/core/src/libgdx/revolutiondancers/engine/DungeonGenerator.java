package libgdx.revolutiondancers.engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public abstract class DungeonGenerator {
	
	private Array<String> map;
	//private int difficulty; //How tough will this Dungeon be; 
	
	public static void nextDungeon(int difficulty ) {
        CriarSalas sala =  new CriarSalas();
        
        CriarCaminho caminho = new CriarCaminho();
        
        CustomizandoDungeon interior = new CustomizandoDungeon();
        
        String[][] tabuleiro = sala.DesenhaSala(difficulty);
        
        tabuleiro = caminho.DesenhaCaminho(tabuleiro);
        
        tabuleiro = interior.organizaDungeon(tabuleiro, sala.getSalas(), difficulty);
        
        
        FileHandle file = Gdx.files.local("RevolutionDancersAssets/Dungeons/procedurallyGeneratedDungeon.txt");
        if(file.exists()) file.delete();
         for (String[] strings : tabuleiro) {
        	for (String string : strings) {
        		file.writeString(string, true);
    		}
        		file.writeString("\r\n", true);
		}
        
        //file.writeString("My god, it's full of stars", false);
        
        		//new BufferedReader(new InputStreamReader(Gdx.files.internal("RevolutionDancersAssets/Dungeons/"+file).read()));
	}
	
}
