package libgdx.revolutiondancers.screens;

import libgdx.revolutiondancers.engine.DungeonGenerator;
import libgdx.revolutiondancers.engine.DungeonLoader;
import libgdx.revolutiondancers.engine.GameObject;
import libgdx.revolutiondancers.engine.GameObjectDisposable;
import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.engine.Main;
import libgdx.revolutiondancers.gameobjects.Door;
import libgdx.revolutiondancers.gameobjects.Door.DoorState;
import libgdx.revolutiondancers.gameobjects.Exit;
import libgdx.revolutiondancers.gameobjects.Key;
import libgdx.revolutiondancers.gameobjects.MojoGem;
import libgdx.revolutiondancers.gameobjects.Player;
import libgdx.revolutiondancers.gameobjects.Wall;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ObjectSet;


public class GameScreen extends ScreenAbstract {
	
	private static GameScreen gameScreen;
	protected static InputMultiplexer inputMultiplexer;
	
	public static final Player player = new Player();
	public static DungeonLoader dungeonLoader;
	public static DungeonGenerator dungeonGenerator;
	public static final ObjectSet<Wall> walls = new ObjectSet<Wall>();
	public static final ObjectSet<Exit> exits = new ObjectSet<Exit>();
	public static final ObjectSet<MojoGem> mojoGems = new ObjectSet<MojoGem>();
	public static final ObjectSet<Door> doors = new ObjectSet<Door>();
	public static final ObjectSet<Key> keys = new ObjectSet<Key>();
	private static int currentDungeonNumber = 0;
	private static int inventoryMojoGems = 0;
	private static int inventoryKeys = 0;
	private static int inventorySpecialKeys = 0;
	
	public GameScreen() {
		inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(this);
		Gdx.input.setInputProcessor(inputMultiplexer);
		//generateMap(currentDungeonNumber +1);  //Getting error; Probably some dumb null pointer thing because of the path;
	}
	
	public static synchronized GameScreen getInstance(){
		if(gameScreen == null)
			gameScreen = new GameScreen();
		return gameScreen;
	}

	
	/////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////
	
	
	private void generateMap(int dungeonNumber) {
		inventoryMojoGems = 0;
		inventoryKeys = 0;
		inventorySpecialKeys = 0;
		walls.clear();
		exits.clear();
		mojoGems.clear();
		doors.clear();
		keys.clear();
		 //After level 3, starts generating procedurally generated levels;
		//The first 2 levels are tutorial levels, without enemies; 
		//The third level is a tutorial level about fighting enemies;
		if(currentDungeonNumber <= 3) {
		dungeonLoader.load("Level"+dungeonNumber+".map");
		for (int y=0; y < dungeonLoader.getMap().size; y++) {
			for (int x=0; x < dungeonLoader.getMap().get(y).length(); x++) {
				if (dungeonLoader.getTile(x, y).equals("S")) {
					// Set start position
					player.getCentrePos().set(x, dungeonLoader.getMap().size-y);
				}
				if (dungeonLoader.getTile(x, y).equals("W")) {
					// Generate walls
					walls.add(new Wall(x, dungeonLoader.getMap().size-y, 1f, 1f));
				}
				if (dungeonLoader.getTile(x, y).equals("E")) {
					// Create exit
					exits.add(new Exit(x, dungeonLoader.getMap().size-y, 0.75f, 0.75f));
				}
				if (dungeonLoader.getTile(x, y).equals("T")) {
					// Generate gems
					mojoGems.add(new MojoGem(x, dungeonLoader.getMap().size-y, 0.5f, 0.5f));
				}
				if (dungeonLoader.getTile(x, y).equals("U")) {
					// Generate unlocked doors
					doors.add(new Door(x, dungeonLoader.getMap().size-y, 1.0f, 1.0f, DoorState.CLOSED));
				}
				if (dungeonLoader.getTile(x, y).equals("L")) {
					// Generate locked doors
					doors.add(new Door(x, dungeonLoader.getMap().size-y, 1.0f, 1.0f, DoorState.LOCKED));
				}
				if (dungeonLoader.getTile(x, y).equals("K")) {
					// Generate keys
					keys.add(new Key(x, dungeonLoader.getMap().size-y, 0.5f, 0.5f));
				}
			}
		   }
		  }
		else {
			dungeonGenerator.generateDungeon(dungeonNumber);
			for (int y=0; y < dungeonGenerator.getMap().size; y++) {
				for (int x=0; x < dungeonGenerator.getMap().get(y).length(); x++) {
					if (dungeonGenerator.getTile(x, y).equals("S")) {
						// Set start position
						player.getCentrePos().set(x, dungeonGenerator.getMap().size-y);
					}
					if (dungeonGenerator.getTile(x, y).equals("W")) {
						// Generate walls
						walls.add(new Wall(x, dungeonGenerator.getMap().size-y, 1f, 1f));
					}
					if (dungeonGenerator.getTile(x, y).equals("E")) {
						// Create exit
						exits.add(new Exit(x, dungeonGenerator.getMap().size-y, 0.75f, 0.75f));
					}
					if (dungeonGenerator.getTile(x, y).equals("T")) {
						// Generate gems
						mojoGems.add(new MojoGem(x, dungeonGenerator.getMap().size-y, 0.5f, 0.5f));
					}
					if (dungeonGenerator.getTile(x, y).equals("U")) {
						// Generate unlocked doors
						doors.add(new Door(x, dungeonGenerator.getMap().size-y, 1.0f, 1.0f, DoorState.CLOSED));
					}
					if (dungeonGenerator.getTile(x, y).equals("L")) {
						// Generate locked doors
						doors.add(new Door(x, dungeonGenerator.getMap().size-y, 1.0f, 1.0f, DoorState.LOCKED));
					}
					if (dungeonGenerator.getTile(x, y).equals("K")) {
						// Generate keys
						keys.add(new Key(x, dungeonGenerator.getMap().size-y, 0.5f, 0.5f));
					}
				}
			   }
		}
	}
	
	private void restartMap() {
		//player.setRotation(0.0f);
		generateMap(currentDungeonNumber);
	}
	
	private void nextLevel() {
		//player.setRotation(0.0f);
		currentDungeonNumber++;
		generateMap(currentDungeonNumber);
	}
	
	public static int getCurrentDungeonNumber(){
		return currentDungeonNumber;
	}
	
	public static void addMojoGem() {
		inventoryMojoGems++;
	}

	public static void addKey() {
		inventoryKeys++;
	}
	
	public static void removeKey() {
		inventoryKeys--;
	}

	public static void addSpecialKey() {
		inventorySpecialKeys++;
	}

	public static void removeSpecialKey() {
		inventorySpecialKeys--;
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
		
		//Update cameras///////////////////////////////////
		Main.getInstance().current2DViewport.getCamera().update();
		Main.getInstance().current3DViewport.getCamera().update();
		///////////////////////////////////////////////////
		
		
	    /////////////////////2D////////////////////////
	    /////////////////////2D////////////////////////
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
	    /////////////////////2D////////////////////////
	    /////////////////////3D////////////////////////
		
		for (GameObject gameObject : objects3D) {	
			gameObject.input();
			gameObject.update();
			
			if(gameObject instanceof GameObjectDisposable && ((GameObjectDisposable) gameObject).isDisposable()
				/*&& !Physics.WORLD.isLocked()*/) {
				disposeDisposableObject3D(((GameObjectDisposable) gameObject));	//Para um objeto GameObject nao poolable ser disposable significa que estamos saindo desta tela
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
		
			resetAndDisposeObjects3D();

			addAddLaterObjects3D();
		
		/////////////////////3D////////////////////////
		/////////////////////3D////////////////////////
			
	}

	@Override
	public void render(float delta) {
		
		input();
		update();
		
		//clear screen
		Gdx.gl.glClearColor(0.83f, 0.61f, 0.124f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
	    /////////////////////2D////////////////////////
	    /////////////////////2D////////////////////////
		ScreenAbstract.spriteBatch.setProjectionMatrix(Main.getInstance().current2DViewport.getCamera().combined);
		ScreenAbstract.spriteBatch.begin();
	    ScreenAbstract.spriteBatch.enableBlending();
	    ScreenAbstract.rendering3D = false;
		for (GameObject gameObject : objects2D) {
			
			if(gameObject instanceof GameObjectPoolable && isPoolableObjectToBeRemoved3D((GameObjectPoolable)gameObject)) continue;
			
			if(!gameObject.isResetable() || !gameObject.isDisposable())
			{
				gameObject.draw();			
			}

		}
		ScreenAbstract.spriteBatch.end();		
		/////////////////////2D////////////////////////
		/////////////////////3D////////////////////////
		//ScreenAbstract.modelBatch.setCamera(Main.getInstance().current3DViewport.getCamera());  //Tenho que aprender como isso funciona em 3D
		ScreenAbstract.modelBatch.begin(modelBatch.getCamera());
		ScreenAbstract.rendering3D = true;
		for (GameObject gameObject : objects3D) {
			
			if(gameObject instanceof GameObjectPoolable && isPoolableObjectToBeRemoved3D((GameObjectPoolable)gameObject)) continue;
			
			if(!gameObject.isResetable() || !gameObject.isDisposable())
			{
				gameObject.draw();
			}

		}
		ScreenAbstract.modelBatch.end();
		
		/////////////////////3D////////////////////////
		/////////////////////3D////////////////////////
	}
	
	///////////////
}
