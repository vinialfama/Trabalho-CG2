package libgdx.revolutiondancers.engine;

import libgdx.revolutiondancers.screens.ScreenAbstract;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.utils.Array;

/** 
 * 
 * @author caetanoportodasilva
 *	
 * Difference from AnimationObject to this class is that
 * with poolable we will only init position (x,y) animation interval and playmode
 * assets are static and must be loaded before the pool starts to instantiate animations
 * what this means is that, after loading the assets of a random poolable object
 * that object will have that animation/animation interval/animation mode for the rest of the game
 * (unless we explicitly want to change that by loading new assets for the object)
 * (which would be done by something like Object.loadAssets(path, interval, playmode, asset_type)
 *
 */

public class AnimationGameObjectPoolable extends GameObjectPoolable{
	
	public enum AnimationTypeLoad {

		SPRITE_FOLDER, SPRITE_SHEET, GIF, SCML;  //SCML eh o formato utilizado pelo Spriter
	}

	//this object's animation type
	protected static AnimationTypeLoad animationType;
	
	//constants
	public static final float animationInterval = 0.025f; //default animation interval between frames
	
	//animation variables
	protected static Animation animation; //the animation
	protected static float customAnimationInterval;
    protected static PlayMode animationMode; // LOOP, NORMAL, RANDOM, (...)
    protected float animationTime; //according to the time passed since the beginning of animation, tells the current frame of the animation
	//private int timesPlayed; //Legacy attribute. Counts how many times this animation has finished playing.
    
    //array with file's paths, so that we can dispose all the textures later in the AssetManager
    protected static Array<String> filesPath;
    
    //if the animation (assets) are loaded or not
    public static boolean animationLoaded = false;
    
    public AnimationGameObjectPoolable() {
    	this.animationTime = 0.0f;
    }

    
    //SpriteFolder
    public static void initAnimationObjectPoolableSpriteFolder(String path, float animationInterval, PlayMode animationMode) {
    	filesPath = new Array<String>();
    	FileHandle files = Gdx.files.internal(path);
		Array<TextureRegion> animation_frames = new Array<TextureRegion>();
    	AnimationGameObjectPoolable.loadAnimation(path, true);
    	for(FileHandle file : files.list()) {
    		Texture texture = Globals.assetManager.get(files.path(), Texture.class);
        	filesPath.add(file.path());
    		animation_frames.add((TextureRegion.split(texture, texture.getWidth(), texture.getHeight()))[0][0]); //Para evitar um new TextureRegion (também conhecido como Keanu Reeves)  
    	}
		AnimationGameObjectPoolable.animationType = AnimationTypeLoad.SPRITE_FOLDER;
        AnimationGameObjectPoolable.animationMode = animationMode;
        AnimationGameObjectPoolable.animation = new Animation(animationInterval, animation_frames);   
    	AnimationGameObjectPoolable.customAnimationInterval = animationInterval;
		AnimationGameObjectPoolable.animationLoaded = true;
	}
    
    
    //SpriteSheet
    public static void initAnimationObjectPoolableSpriteSheet(String path, int rows, int columns, float animationInterval, PlayMode animationMode) {
    	filesPath = new Array<String>();
    	AnimationGameObjectPoolable.loadAnimation(path, false);
    	Texture sprite_sheet = Globals.assetManager.get(path, Texture.class);
    	filesPath.add(path);
        TextureRegion[][] tmp = TextureRegion.split(sprite_sheet, sprite_sheet.getWidth()/rows, sprite_sheet.getHeight()/columns);
        TextureRegion[] animation_frames = new TextureRegion[rows * columns]; //the frames inside animation
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                animation_frames[index++] = tmp[i][j];
            }
        }
    	AnimationGameObjectPoolable.animationType = AnimationTypeLoad.SPRITE_SHEET;
    	AnimationGameObjectPoolable.animationMode = animationMode;
    	AnimationGameObjectPoolable.animation = new Animation(animationInterval, animation_frames); 
    	AnimationGameObjectPoolable.customAnimationInterval = animationInterval;
        AnimationGameObjectPoolable.animationLoaded = true;
    }
    
    //SCML
    public static void initAnimationObjectPoolableSCML(String path, float animationInterval, PlayMode animationMode) {
        
    }
    
    //load assets method used for SpriteSheet or SpriteFolder
    //blocking method, until everything is loaded it won't leave here
    private static void loadAnimation(String path, boolean isDirectory) {
    	FileHandle handle = Gdx.files.internal(path); 
    	if(isDirectory) {
    		for (FileHandle file : handle.list()) {
        		Globals.assetManager.load(file.path(), Texture.class);
			}
    	}
    	else {
    		Globals.assetManager.load(handle.path(), Texture.class);
    	}
    	Globals.assetManager.finishLoading();
    }
    
    public static AnimationTypeLoad getAnimationType() {
		return AnimationGameObjectPoolable.animationType;
	}

	public static float getAnimationInterval() {
		return AnimationGameObjectPoolable.customAnimationInterval;
	}

	public static PlayMode getAnimationMode() {
		return AnimationGameObjectPoolable.animationMode;
	}

	public float getAnimationTime() {
		return this.animationTime;
	}
	
	@Override
	public float getWidth() {
		return 0;
	}

	@Override
	public float getHeight() {
		return 0;
	}
	
	public void init(float x, float y)
	{
		
	}
	
	@Override
	public void setResetMeTrue(){ resetMe = true; }
	
	@Override
	public void reset() {
		this.animationTime = 0.0f;
		//this.x = 0.0f;
		//this.y = 0.0f;
	}

	@Override
	public void input() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		//batch.setProjectionMatrix(Main.getInstance().currentViewport.getCamera().combined);
    	this.animationTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(animationTime, true);  
    	//batch.begin();
        
    	//ScreenAbstract.batch.draw(currentFrame, getX(), getY());
        
    	//batch.end();
	}

	@Override
	public float getResizeWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getResizeHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
