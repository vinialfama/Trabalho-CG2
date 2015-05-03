package libgdx.revolutiondancers.screens;

import libgdx.revolutiondancers.engine.Globals;
import libgdx.revolutiondancers.engine.GameObject;
import libgdx.revolutiondancers.engine.GameObjectDisposable;
import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.engine.Main;
import libgdx.revolutiondancers.engine.Physics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;


public class GameScreen extends ScreenAbstract {
	
	private static GameScreen gameScreen;

	protected static InputMultiplexer inputMultiplexer;

		
	
	public GameScreen() {
		inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(this);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}
	
	public static synchronized GameScreen getInstance(){
		if(gameScreen == null)
			gameScreen = new GameScreen();
		return gameScreen;
	}

	
	/////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void loadAssets() {
		
	}

	@Override
	public void getAssets() {
		
	}
	
	/////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		Main.getInstance().currentViewport.update(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void connected(Controller controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnected(Controller controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode,
			PovDirection value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode,
			boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode,
			boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller controller,
			int accelerometerCode, Vector3 value) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//////////////////////////////
	
	public static InputMultiplexer getInputMultiplexer() {
		return inputMultiplexer;
	}

	//////////////////////////////
	public void input(){
				
	}
	
	
	public void update(){
		
		//update camera
		Main.getInstance().currentViewport.getCamera().update();
		
		///////////////////////////////////////////////////
		
		for (GameObject gameObject : objects) {	
			gameObject.input();
			gameObject.update();
			
			if(gameObject instanceof GameObjectDisposable && ((GameObjectDisposable) gameObject).isDisposable()
				/*&& !Physics.WORLD.isLocked()*/) {
				disposeDisposableObject(((GameObjectDisposable) gameObject));	//Para um objeto GameObject nao poolable ser disposable significa que estamos saindo desta tela
			}
	
			if(gameObject.isResetable() /*&& !Physics.WORLD.isLocked()*/ && gameObject instanceof GameObjectDisposable) {  
										//Nao podemos dar dispose ou reset se estes objetos estao sendo calculados em WORLD
				gameObject.reset();     //Reseta objetos nao Poolable fixos do cenario; 
										//Objetos poolable resetados/removidos, saem do array de objects no update da tela; Antes de chegarem aqui;
			}							//Objetos poolable devem ser resetados/removidos cada um de suas pools, no momento correto, manualmente;
		}
		
		
		/////WORLD Step////////////////////
		/***///Physics.simulateWorld();
		/////WORLD Step////////////////////
		
			resetAndDisposeObjects();

			addAddLaterObjects();
		
		
	}

	@Override
	public void render(float delta) {
		
		input();
		update();
		
		//clear screen
		Gdx.gl.glClearColor(0.83f, 0.61f, 0.124f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		ScreenAbstract.batch.setProjectionMatrix(Main.getInstance().currentViewport.getCamera().combined);
		ScreenAbstract.batch.begin();
	    ScreenAbstract.batch.enableBlending();
		for (GameObject gameObject : objects) {
			
			if(gameObject instanceof GameObjectPoolable && isPoolableObjectToBeRemoved((GameObjectPoolable)gameObject)) continue;
			
			if(!gameObject.isResetable() || !gameObject.isDisposable())
			{
				gameObject.draw();
			}

		}
		ScreenAbstract.batch.end();		
	    	
	}
	
	///////////////
}
