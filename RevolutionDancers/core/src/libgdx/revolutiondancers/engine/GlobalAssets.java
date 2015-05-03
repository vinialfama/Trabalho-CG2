package libgdx.revolutiondancers.engine;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class where we'll keep globally loaded assets;
 * */
public abstract class GlobalAssets {

	Mesh cube;
	Mesh mojoGem;
	Mesh key;
	
	public static enum AssetType{Texture, Sound, Music}; //Etc
	
	/*
	 * Examples:
	 */
	//To be called when the game is initializing [On the Main Class]:
	public static void loadGlobalAssets(){
		/*-Textures-*/
		
		//Example:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/TemporarySplashScreen.png", Texture.class);
		
		/*-Textures-*/
		/*-Audio-*/
		
		//Example:
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/tittleScreen.wav", Music.class);
		
		
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
