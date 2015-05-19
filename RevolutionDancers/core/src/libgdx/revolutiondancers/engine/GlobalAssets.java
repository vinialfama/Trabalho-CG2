package libgdx.revolutiondancers.engine;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

/**
 * Class where we'll keep globally loaded assets;
 * */
public abstract class GlobalAssets {

	Mesh cube;
	Mesh mojoGem;
	Mesh key;
	
	public static enum AssetType{Texture, Sound, Music}; //Etc
	
	public static Array<Music> dungeonMusicArray = new Array<Music>();
	public static Array<Music> battleMusicArray = new Array<Music>();
	private Music auxMusic;
	
	/*
	 * Examples:
	 */
	//To be called when the game is initializing [On the Main Class]:
	public static void loadGlobalAssets(){
		/*-Textures-*/
		//2D
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/TemporarySplashScreen.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/UILayout.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/DanceDanceUILayout.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/DOWN.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/LEFT.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/RIGHT.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/UP.png", Texture.class);
		
		/*-Textures-*/
		/*-Audio-*/
		//Music
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/tittleScreen.wav", Music.class);
		
		//auxMusic = new (get musica1 bla bla bla)
		
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
