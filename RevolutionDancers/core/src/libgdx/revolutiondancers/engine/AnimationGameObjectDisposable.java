package libgdx.revolutiondancers.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationGameObjectDisposable extends GameObjectDisposable{		//Shouldnt this be abstract?!
	
	public enum AnimationTypeLoad {

		SPRITE_FOLDER, SPRITE_SHEET, GIF, SCML;  //SCML eh o formato utilizado pelo Spriter
	}

	//this object's animation type
	private AnimationTypeLoad animationType;
	
	//constants
	private static final float animationInterval = 0.025f; //default animation interval between frames
	
	//animation variables
	private Animation animation; //the animation
	private float customAnimationInterval;
    private PlayMode animationMode; // LOOP, NORMAL, RANDOM, (...)
    private float animationTime; //according to the time passed since the beginning of animation, tells the current frame of the animation
	//private int timesPlayed; //Legacy attribute. Counts how many times this animation has finished playing.
    
    //array with file's paths, so that we can dispose all the textures later in the AssetManager
    private Array<String> filesPath;
    
    
    //SCML
    public AnimationGameObjectDisposable(String path, float animationInterval, PlayMode animationMode) {
        
    }
    
    
    //SpriteFolder or GIF
    public AnimationGameObjectDisposable(String path, float x, float y, float animationInterval, PlayMode animationMode) {
    	filesPath = new Array<String>();
    	
    	FileHandle files = Gdx.files.internal(path);
    	if(files.isDirectory()) {
    		Array<TextureRegion> animation_frames = new Array<TextureRegion>();
        	this.loadAnimation(path);
        	for(FileHandle file : files.list()) {
        		Texture texture = Globals.assetManager.get(files.path(), Texture.class);
            	filesPath.add(file.path());
        		animation_frames.add((TextureRegion.split(texture, texture.getWidth(), texture.getHeight()))[0][0]); //Para evitar um new TextureRegion (também conhecido como Keanu Reeves)  
        	}
        	
    		this.animationType = AnimationTypeLoad.SPRITE_FOLDER;
            this.animationMode = animationMode;
        	this.animation = new Animation(animationInterval, animation_frames);   
        	this.customAnimationInterval = animationInterval;
        	this.animationTime = 0.0f; //start animation time
        	//this.x = x;
        	//this.y = y;
    	}
    	else {
    		System.out.println("Volta que deu &#%$*!" + "   |AnimationGameObjectDisposable cosntructor Error|");
    	}
 
	}
    
    //SpriteSheet
    public AnimationGameObjectDisposable(String path, float x, float y, int rows, int columns, float animationInterval, PlayMode animationMode) {
    	filesPath = new Array<String>();
    	
    	this.loadAnimation(path);
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
        
    	this.animationType = AnimationTypeLoad.SPRITE_SHEET;
        this.animationMode = animationMode;
        this.animation = new Animation(animationInterval, animation_frames); //each frame has 0.025 seconds
        this.customAnimationInterval = animationInterval;
        this.animationTime = 0.0f; //start animation time
        //this.x = x;
        //this.y = y;
        
    }
    	
    //SpriteFolder or GIF
    public AnimationGameObjectDisposable(String path, float x, float y, PlayMode animationMode) {
    	this(path, x, y, AnimationGameObjectDisposable.animationInterval, animationMode);
	} 
    
    //SpriteSheet
    public AnimationGameObjectDisposable (String path, float x, float y, int rows, int columns, PlayMode animationMode) {		
    	this(path, x, y, rows, columns, AnimationGameObjectDisposable.animationInterval, animationMode);
    }
    
    //load assets method used for SpriteSheet or SpriteFolder
    //blocking method, until everything is loaded it won't leave here
    private void loadAnimation(String path) {
    	FileHandle handle = Gdx.files.internal(path); 
    	if(handle.isDirectory()) {
    		for (FileHandle file : handle.list()) {
        		Globals.assetManager.load(file.path(), Texture.class);
			}
    	}
    	else {
    		Globals.assetManager.load(handle.path(), Texture.class);
    	}
    	Globals.assetManager.finishLoading();
    }
    
    public AnimationTypeLoad getAnimationType() {
		return animationType;
	}

	public float getAnimationInterval() {
		return customAnimationInterval;
	}

	public PlayMode getAnimationMode() {
		return animationMode;
	}

	public float getAnimationTime() {
		return animationTime;
	}

	@Override
	public float getWidth() {
		return 0;
	}

	@Override
	public float getHeight() {
		return 0;
	}
	
	//if is removeable, then use dispose
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		for (String file : filesPath) {
			Globals.assetManager.unload(file);
		}
	}

	@Override
	public void setResetMeTrue(){ resetMe = true; }
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.animationTime = 0.0f;
		//this.x = 0.0f;
		//this.y = 0.0f;
	}


	@Override
	public float getResizeWidth() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public float getResizeHeight() {			//Thats one reason this Class should be abstract! This method here should be abstract. Inherited and then implemented.
		// TODO Auto-generated method stub
		return 0;
	}
   	
	//////////////////////////////////////////
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
    	animationTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(animationTime, true);  
    	//batch.begin();
        
    	//ScreenAbstract.batch.draw(currentFrame, getX(), getY());
    
    	//batch.end();
	}
	//////////////////////////////////////////


}
