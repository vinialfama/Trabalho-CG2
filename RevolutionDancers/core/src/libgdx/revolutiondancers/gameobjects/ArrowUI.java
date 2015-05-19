package libgdx.revolutiondancers.gameobjects;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.engine.Globals;
import libgdx.revolutiondancers.screens.GameScreen;
import libgdx.revolutiondancers.screens.ScreenAbstract;

public class ArrowUI extends GameObjectPoolable {

	float x, y;
	public static final Sprite arrowSpriteUIUp = new Sprite(Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/UP.png", Texture.class));
	public static final Sprite arrowSpriteUIDown = new Sprite(Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/DOWN.png", Texture.class));
	public static final Sprite arrowSpriteUILeft = new Sprite(Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/LEFT.png", Texture.class));
	public static final Sprite arrowSpriteUIRight = new Sprite(Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/RIGHT.png", Texture.class));
	protected Sprite myArrowSprite;
	public enum ArrowDirection{UP,DOWN,LEFT,RIGHT};
	public ArrowDirection myArrowDirection; 
	public boolean passedTheScreenEnd;
	
	@Override  //Do not use for this class:
	public void init(float x, float y, float z) {}
	@Override
	public void init(float x, float y) {}
	//Use this one instead:
	public void init(ArrowDirection arrowDirection) {
		myArrowDirection = arrowDirection;
		x = 8;
		switch(myArrowDirection){
		case DOWN:
			myArrowSprite = arrowSpriteUIDown;
			x += 2 * GameScreen.danceDanceLayoutUIX;
			break;
		case LEFT:
			myArrowSprite = arrowSpriteUILeft;
			x += 1 * GameScreen.danceDanceLayoutUIX;
			break;
		case RIGHT:
			myArrowSprite = arrowSpriteUIRight;
			x += 4 * GameScreen.danceDanceLayoutUIX;
			break;
		case UP:
			myArrowSprite = arrowSpriteUIUp;
			x += 3 * GameScreen.danceDanceLayoutUIX;
			break;
		}
		
	}


	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getDepth() {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public float getResizeDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setResetMeTrue() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void reset() {
		passedTheScreenEnd = false;
		y = 0;			//System.out.println("Resetted arrow here!");
	}

	//////////////////////////////////////////////////////////////
	
	@Override
	public void input() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		y += 2.45f;  //O Random eh pra dar uma emocao a mais; //The player will try to make sense of it of course, but it really has none;
		if(y >= Globals.WORLD_HEIGHT_MIN) {
			passedTheScreenEnd = true;			
			//Monster.arrowUIPool.free(this);
			//Falta uma logica pra que eu saia do array do monstro....
			
		}
	}

	@Override
	public void draw() {
		//ScreenAbstract.spriteBatch.draw(myArrowSprite.getTexture(), x, y, Globals.WORLD_WIDTH_MIN/3, Globals.WORLD_HEIGHT_MIN/8);
		ScreenAbstract.spriteBatch.draw(myArrowSprite.getTexture(), x, y);
	}
	

}
