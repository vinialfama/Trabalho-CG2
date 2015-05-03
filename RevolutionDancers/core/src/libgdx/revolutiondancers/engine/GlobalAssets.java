package libgdx.revolutiondancers.engine;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class where we'll keep globally loaded assets;
 * */
public abstract class GlobalAssets {

	/**
	 * This Class is supposed to have these 2 methods and only these 2 methods.
	 * It only has the function of loading assets (never unloading them) and keeping the most relevant ones in globally defined variables;
	 * If it is to have variables containing assets (via assetManager.get) these assets have to be of global relevance;
	 * **/
	
	public static enum AssetType{Texture, Sound, Music}; //Etc
	
	/*
	 * Examples:
	 */
	//To be called when the game is initializing [On the Main Class]:
	public static void loadGlobalAssets(){
		/*-Textures-*/
		
		//Example:
		//Globals.assetManager.load("Assets/Graphics/background.png", Texture.class);
			
		/*-Textures-*/
		/*-Sounds-*/
		
		//Example:
		//Globals.assetManager.load("Assets/Audio/Sounds/explosion.wav", Sound.class);
		
		
		Globals.assetManager.finishLoading();
	}
	
	//Use whenever the program has reached a point where a certain asset is now certain to have global relevance
	//Understand that there is no unloading of globally relevant loaded assets
	public static void loadGlobalAsset(String filePath, AssetType type){
		switch(type){
		case Music:
			Globals.assetManager.load(filePath, Music.class);
			break;
		case Sound:
			Globals.assetManager.load(filePath, Sound.class);
			break;
		case Texture:
			Globals.assetManager.load(filePath, Texture.class);
			break;
		default:
			Globals.assetManager.finishLoading();
			break;
		}
	}
	
}
