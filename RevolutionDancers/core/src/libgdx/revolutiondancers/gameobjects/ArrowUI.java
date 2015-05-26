package libgdx.revolutiondancers.gameobjects;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.engine.Globals;
import libgdx.revolutiondancers.engine.Main;
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
	public boolean passedTheHitBox;
	public boolean isDoubleArrow;
	
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
			y -= Math.abs(0.1 + Globals.getRandomGenerator().nextFloat());
	}
	
	public void initDoubleArrow(ArrowDirection arrowDirection) {
		init(arrowDirection);
		isDoubleArrow = true;
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
		isDoubleArrow = false;
		passedTheScreenEnd = false;
		passedTheHitBox = false;
		y = 0;			//System.out.println("Resetted arrow here!");
	}

	//////////////////////////////////////////////////////////////
	
	@Override
	public void input() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		y += 2.45f;//2.45f;  
		
		//y += GameScreen.battleMusic.getVolume();   //Fix!!
		
		if(y >= Globals.WORLD_HEIGHT_MIN) {
			passedTheScreenEnd = true;			
		}
		if(y >= GameScreen.danceDanceLayoutUIY + 28) passedTheHitBox = true; 
	}

	@Override
	public void draw() {  

		{ 
			 ScreenAbstract.spriteBatch.draw(myArrowSprite.getTexture(), x, y);
			ScreenAbstract.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_CONSTANT_COLOR);  //GL_ONE
			ScreenAbstract.spriteBatch.setColor(Math.abs(Globals.getRandomGenerator().nextFloat()));
			 ScreenAbstract.spriteBatch.draw(myArrowSprite.getTexture(), x, y);
			ScreenAbstract.spriteBatch.setColor(Color.WHITE);
			ScreenAbstract.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		}

	}
	

}
