package libgdx.revolutiondancers.screens;

import libgdx.revolutiondancers.engine.GameObject;
import libgdx.revolutiondancers.engine.GameObjectDisposable;
import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.engine.Globals;
import libgdx.revolutiondancers.engine.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen extends ScreenAbstract {

	
	
	//singleton
	private static MenuScreen menuScreen;
	
	private Music backgroundMusic = Globals.assetManager.get("RevolutionDancersAssets/Audio/Music/tittleScreen.wav", Music.class);;
	protected Texture background = Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/TemporarySplashScreen.png", Texture.class);
	
	private BitmapFont title;
	private BitmapFont pressToPlay;
	
	public enum menuState{TITTLE, IDDLE};
	private static menuState currentState;
	
	private MenuScreen(){		 
		
		loadAssets();
		getAssets();
		
		//playing music
		backgroundMusic.setLooping(true);			
		backgroundMusic.play();
	}
	
	public static synchronized MenuScreen getInstance(){
		if(menuScreen == null)
			menuScreen = new MenuScreen();
		return menuScreen;
	}
	
	public void loadAssets(){
		/*Globals.assetManager.load("Assets/Audio/Music/Intro.wav", Music.class);
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Assets/Fonts/SaucerBB.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 56;
		title = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
		
		FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("Assets/Fonts/ArcadeClassic.ttf"));
		FreeTypeFontParameter parameter2 = new FreeTypeFontParameter();
		parameter2.size = 26;
		pressToPlay = generator2.generateFont(parameter2);
		generator2.dispose();
		
		Globals.assetManager.finishLoading(); // Mantém o funcionamento de carregar os assets como Sincrono. 
														 //E soh sairah deste metodo ao terminar de carregar.
		*/
	}
	
	public void getAssets() {
		//backgroundMusic = Globals.assetManager.get("Assets/Audio/Music/Intro.wav", Music.class);
		//title = Globals.assetManager.get("Resources/Fonts/SaucerBB.ttf", BitmapFont.class);
		//playButton = Globals.assetManager.get("Resources/Fonts/ArcadeClassic.ttf", BitmapFont.class);
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		Controllers.addListener(this);
	}

	

	@Override
	public void resize(int width, int height) {
		Main.getInstance().current2DViewport.update(width, height);
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
		//this.dispose();
	}

	@Override
	public void dispose() {

	}
	
	//////////////////
	//**ControllerListener implementation**//
	//////////////////
		
	private int indexOf (Controller controller) {	//Metodo usado para saber qual eh o controle. Basta passar o controle do parametro atual como parametro deste metodo indexOf para saber qual o numero do controle. 
		return Controllers.getControllers().indexOf(controller, true);
	}
	
	@Override
	public void connected(Controller controller) {
		// TODO Auto-generated method stub
		int index = indexOf(controller); //now index has this controller's index
	}


	@Override
	public void disconnected(Controller controller) {
		// TODO Auto-generated method stub
		int index = indexOf(controller); //now index has this controller's index
	}


	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		
		if(buttonCode == Ouya.BUTTON_O) 
		{
			Main.getInstance().setScreen(GameScreen.getInstance());
			backgroundMusic.stop();
			this.dispose();//dispose?
		}
		
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
	/////////////////////////////////////////	
	/////////////////////////////////////////
	//Keyboard/Mouse (Mouse clicks and Touch clicks are considered the same thing on libGDX)
	/////////////////////////////////////////
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(keycode == Keys.O || keycode == Keys.SPACE){
			//set screen to multiplayer screen
				//System.out.println("Multiplayer Screen");
			Main.getInstance().setScreen(GameScreen.getInstance());
			backgroundMusic.stop();
			//dispose?
		}
		
		return true;
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
		
		Vector3 coords = Globals.usefulVector3;
		coords.set(screenX, screenY, 0);
		coords = Main.getInstance().current2DViewport.getCamera().unproject(coords);

		if(coords.x > 0 && coords.x < Globals.WORLD_WIDTH_MIN) 
		{
			Main.getInstance().setScreen(GameScreen.getInstance());
			backgroundMusic.stop();
			//dispose?
		}
		
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
	/////////////////////////////////////////
	
	
	
		
	///////////////
	
	public void input(){
		
	}
	
	
	public void update(){
		
		//Update Camera
		Main.getInstance().current2DViewport.getCamera().update();
		
		for (GameObject gameObject : objects2D) {	
			gameObject.input();
			gameObject.update();
			
			if(gameObject instanceof GameObjectDisposable && ((GameObjectDisposable) gameObject).isDisposable()
				/*&& !Physics.WORLD.isLocked()*/) {
				disposeDisposableObject2D(((GameObjectDisposable) gameObject));	//Para um objeto GameObject nao poolable ser disposable significa que estamos saindo desta tela
			}
	
			if(gameObject.isResetable() /*&& !Physics.WORLD.isLocked()*/ && gameObject instanceof GameObjectDisposable) {  
										//Nao podemos dar dispose ou reset se estes objetos estao sendo calculados em WORLD
				gameObject.reset();     //Reseta objetos nao Poolable fixos do cenario; 
										//Objetos poolable resetados/removidos, saem do array de objects no update da tela; Antes de chegarem aqui;
			}							//Objetos poolable devem ser resetados/removidos cada um de suas pools, no momento correto, manualmente;
		}
		
			resetAndDisposeObjects2D();

			addAddLaterObjects2D();
		
		///////////////////////////////////////////////////
		
	}
	

	@Override
	public void render(float delta) {
		
		input();
		update();

		Gdx.gl.glClearColor(0.13f, 0.61f, 0.124f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
		ScreenAbstract.spriteBatch.setProjectionMatrix(Main.getInstance().current2DViewport.getCamera().combined);
		ScreenAbstract.spriteBatch.begin();
		ScreenAbstract.spriteBatch.draw(background, 0, 0);
	    ScreenAbstract.spriteBatch.enableBlending();
	    ScreenAbstract.rendering3D = false;
		for (GameObject gameObject : objects2D) {
			
			if(gameObject instanceof GameObjectPoolable && isPoolableObjectToBeRemoved3D((GameObjectPoolable)gameObject)) continue;
			
			if(!gameObject.isResetable() || !gameObject.isDisposable())
			{
				gameObject.draw();
			}

		}
	    
		//Tittle Screen tittles being drawn
		//title.draw(ScreenAbstract.batch, "Revolution    Dancers", Globals.WORLD_WIDTH_MIN - 540, Globals.WORLD_HEIGHT_MIN - 245);
		//pressToPlay.draw(ScreenAbstract.batch, "Press   something   or  Click   or  Touch   to   Play", 225 , 225);
		
		ScreenAbstract.spriteBatch.end();
	   
	}
	
	///////////////
}
