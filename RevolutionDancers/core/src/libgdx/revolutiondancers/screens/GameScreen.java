package libgdx.revolutiondancers.screens;

import libgdx.revolutiondancers.engine.DungeonGenerator;
import libgdx.revolutiondancers.engine.DungeonLoader;
import libgdx.revolutiondancers.engine.GameObject;
import libgdx.revolutiondancers.engine.GameObjectDisposable;
import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.engine.GlobalAssets;
import libgdx.revolutiondancers.engine.Globals;
import libgdx.revolutiondancers.engine.Main;
import libgdx.revolutiondancers.gameobjects.ArrowUI;
import libgdx.revolutiondancers.gameobjects.Chest;
import libgdx.revolutiondancers.gameobjects.Door;
import libgdx.revolutiondancers.gameobjects.ArrowUI.ArrowDirection;
import libgdx.revolutiondancers.gameobjects.Chest.ChestState;
import libgdx.revolutiondancers.gameobjects.Door.DoorState;
import libgdx.revolutiondancers.gameobjects.Exit;
import libgdx.revolutiondancers.gameobjects.FloorAndCeiling;
import libgdx.revolutiondancers.gameobjects.Key;
import libgdx.revolutiondancers.gameobjects.Key.KeyType;
import libgdx.revolutiondancers.gameobjects.MojoGem;
import libgdx.revolutiondancers.gameobjects.Monster;
import libgdx.revolutiondancers.gameobjects.MonsterPack;
import libgdx.revolutiondancers.gameobjects.Player;
import libgdx.revolutiondancers.gameobjects.Wall;
import libgdx.revolutiondancers.pools.ChestPool;
import libgdx.revolutiondancers.pools.DoorPool;
import libgdx.revolutiondancers.pools.ExitPool;
import libgdx.revolutiondancers.pools.FloorAndCeilingPool;
import libgdx.revolutiondancers.pools.KeyPool;
import libgdx.revolutiondancers.pools.MojoGemPool;
import libgdx.revolutiondancers.pools.MonsterPackPool;
import libgdx.revolutiondancers.pools.MonsterPool;
import libgdx.revolutiondancers.pools.WallPool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ObjectSet;


public class GameScreen extends ScreenAbstract {
	
	private static GameScreen gameScreen;
	protected static InputMultiplexer inputMultiplexer = new InputMultiplexer();
	
	public final Sprite UILayout =  new Sprite(Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/UILayout.png", Texture.class));
	private static boolean inBattle = false; //Eh setado para true quando uma batalha esta acontecendo, assim os elementos de UI da batalha aparecem;
	public final Sprite danceDanceUILayout =  new Sprite(Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/DanceDanceUILayout.png", Texture.class));
	public static final float danceDanceLayoutUIX = 126;
	public static final float danceDanceLayoutUIY = Globals.WORLD_HEIGHT_MIN - 192 -16;
	
	public static final WallPool wallPool = new WallPool();
	public static final FloorAndCeilingPool floorAndCeilingPool = new FloorAndCeilingPool();
	public static final DoorPool doorPool = new DoorPool();
	public static final ExitPool exitPool = new ExitPool();
	public static final KeyPool  keyPool  = new KeyPool();
	public static final ChestPool chestPool = new ChestPool();
	public static final MojoGemPool mojoGemPool = new MojoGemPool();
	public static final MonsterPool monsterPool = new MonsterPool();	//Monsters that populate each MonsterPack
	public static final MonsterPackPool monsterPackPool = new MonsterPackPool();  //Monster Packs that populate the Dungeon
	public static MonsterPack currentMonsterPackBeingFought;	//What monster pack to display on the screen when a Fight happens!
	public static Monster leftMonster, rightMonster, upMonster, downMonster; //Whatever the current Monsters in a battle are; Update these whenever a battle starts;
	
	public static Music notSoRandomEncounterMusic = Globals.assetManager.get("RevolutionDancersAssets/Audio/Music/randomEncounterMusic.wav", Music.class);
	public static Music battleMusic;
	public static Music dungeonMusic;
	
	public static final Player player = new Player();		public float playerInitialX, playerInitialZ;
	public Wall wall;
	public static FloorAndCeiling floorAndCeiling;
	public static MonsterPack monsterPack;
	public Door door;
	public Exit exit;
	public Key key;		public static int keyAmount = 0; 	public static int inventoryKeys = 0; 	public boolean hasExitKey = false;
	public Chest chest;
	public MojoGem mojoGem;	public static int mojoGemAmount = 0; public static int inventoryMojoGems = 0;
	private static int currentDungeonNumber = 0;
	private static int currentDungeonDifficulty = 3;
	private boolean nextDungeon = false;
	
	
	public GameScreen() {											//inBattle = true;  //Testes
		inputMultiplexer.addProcessor(this);				
		Gdx.input.setInputProcessor(inputMultiplexer);
		Gdx.input.setCursorCatched(true);
		
		nextDungeon();
		
		/*MonsterPack testMonsterPack =  monsterPackPool.obtain();
		testMonsterPack.init(0, 0, 0);
		currentMonsterPackBeingFought = testMonsterPack;		updateCurrentMonsters();
		objects2D.add(testMonsterPack);*/
		
		objects3D.add(player);
		inputMultiplexer.addProcessor(Player.firstPersonCameraController);
	
		notSoRandomEncounterMusic.setLooping(false);
	}
	
	public static synchronized GameScreen getInstance(){
		if(gameScreen == null)
			gameScreen = new GameScreen();
		return gameScreen;
	}

	
	/////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////
	
	public void nextDungeon() {
		
		ScreenAbstract.objects2D.clear();
		ScreenAbstract.objects3D.clear();
		resetAndDisposeObjects2D();
		resetAndDisposeObjects3D();
		inventoryMojoGems = 0;
		inventoryKeys = 0;
		hasExitKey = false;
		nextDungeon = false;
		
		dungeonMusic = GlobalAssets.getRandomDungeonMusic();          //GlobalAssets.getRandomBattleMusic();   //Testes
		dungeonMusic.setLooping(true);
		//dungeonMusic.play();			//Commented for now [Because Im listening to the Guardians of The Galaxy OST]
	
		
			//DungeonGenerator.nextDungeon(5);
		//DungeonGenerator.nextDungeon(currentDungeonDifficulty);
		
		if(currentDungeonDifficulty < 5) currentDungeonDifficulty++;
		currentDungeonNumber++;
		
		
		DungeonLoader.load("0t.txt");
			//DungeonLoader.load("procedurallyGeneratedDungeon.txt");
			
		int lastGroupIndex = 1;
		char lastGroupIndexChar = 'A';
		for (int z=0; z < DungeonLoader.getMap().size; z++) {
			for (int x=0; x < DungeonLoader.getMap().get(z).length(); x++) {
				
				if (DungeonLoader.getTile(x, z).equals("I")) {  //Inicio
					// Set start position
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);
					Player.firstPersonCamera.position.set(x *Wall.width, Player.y, DungeonLoader.getMap().size +z *Wall.depth);			//Ainda esta spawnando no lugar errado
					player.collidedThisFrame = false;
					playerInitialX = x *Wall.width; playerInitialZ =  DungeonLoader.getMap().size +z *Wall.depth;
				}
				
				if (DungeonLoader.getTile(x, z).equals("1")) {
					// Generate walls
					lastGroupIndex = 1;
					wall = wallPool.obtain();
					wall.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1);
					objects3D.add(wall);
				}
				if (DungeonLoader.getTile(x, z).equals("A")) {
					// Generate walls
					lastGroupIndexChar = 'A';
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1);
					objects3D.add(floorAndCeiling);
				}
				
				if (DungeonLoader.getTile(x, z).equals("2")) {
					lastGroupIndex = 2;
					wall = wallPool.obtain();
					wall.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 2);
					objects3D.add(wall);
				}
				if (DungeonLoader.getTile(x, z).equals("B")) {
					// Generate walls
					lastGroupIndexChar = 'B';
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 2);
					objects3D.add(floorAndCeiling);
				}
				
				if (DungeonLoader.getTile(x, z).equals("3")) {
					lastGroupIndex = 3;
					wall = wallPool.obtain();
					wall.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 3);
					objects3D.add(wall);
				}
				if (DungeonLoader.getTile(x, z).equals("C")) {
					// Generate walls
					lastGroupIndexChar = 'C';
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 3);
					objects3D.add(floorAndCeiling);
				}
				
				if (DungeonLoader.getTile(x, z).equals("4")) {
					lastGroupIndex = 4;
					wall = wallPool.obtain();
					wall.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 4);
					objects3D.add(wall);
				}
				if (DungeonLoader.getTile(x, z).equals("D")) {
					// Generate walls
					lastGroupIndexChar = 'D';
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 4);
					objects3D.add(floorAndCeiling);
				}
				
				if (DungeonLoader.getTile(x, z).equals("5")) {
					lastGroupIndex = 5;
					wall = wallPool.obtain();
					wall.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 5);
					objects3D.add(wall);
				}
				if (DungeonLoader.getTile(x, z).equals("E")) {
					// Generate walls
					lastGroupIndexChar = 'E';
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 5);
					objects3D.add(floorAndCeiling);
				}
				
				
				if (DungeonLoader.getTile(x, z).equals("F")) {		//Fim
					// Create exit
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);
					exit = exitPool.obtain();
					exit.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(exit);
				}
				
				
				if (DungeonLoader.getTile(x, z).equals("M")) {		//Monstro
					// Monster Pack
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);
					
					monsterPack = monsterPackPool.obtain();
					monsterPack.init(x *Wall.width, Player.y,DungeonLoader.getMap().size+z *Wall.depth);
					objects3D.add(monsterPack);
				}

				if (DungeonLoader.getTile(x, z).equals("G")) {		//Gema
					// Generate gems
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);
					
					mojoGem = mojoGemPool.obtain();
					mojoGem.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth);
					objects3D.add(mojoGem);
				}
				
				
				//Doors
				if (DungeonLoader.getTile(x, z).equals("N")) {		//UNLOCKED
					// Generate unlocked doors
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);
					door = doorPool.obtain();
					door.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, DoorState.UNLOCKED);
					objects3D.add(door);
				}
				if (DungeonLoader.getTile(x, z).equals("T")) {		//LOCKED
					// Generate locked doors
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);
					door = doorPool.obtain();
					door.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, DoorState.LOCKED);
					objects3D.add(door);
				}
				
				
				//Keys
				if (DungeonLoader.getTile(x, z).equals("K")) {		//GOLDEN
					// Generate golden keys
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);

					key = keyPool.obtain();
					key.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, KeyType.GOLDEN);
					objects3D.add(key);
				}
				if (DungeonLoader.getTile(x, z).equals("P")) {		//SILVER
					// Generate silver keys
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);
					
					key = keyPool.obtain();
					key.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, KeyType.SILVER);
					objects3D.add(key);
				}
				
				
				//Baus				
				if (DungeonLoader.getTile(x, z).equals("O")) { //Destrancado
					// Generate keys
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);
					chest = chestPool.obtain();
					chest.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, ChestState.UNLOCKED, null);
					objects3D.add(chest);
				}
				if (DungeonLoader.getTile(x, z).equals("L")) {  //Trancado
					// Generate keys
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);
					chest = chestPool.obtain();
					chest.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, ChestState.LOCKED, null);
					objects3D.add(chest);
				}
				if (DungeonLoader.getTile(x, z).equals("H")) {  //Com Gema Trancado
					// Generate keys
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);
					
					mojoGem = mojoGemPool.obtain();
					mojoGem.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth);
					
					chest = chestPool.obtain();
					chest.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, ChestState.LOCKED, mojoGem);
					objects3D.add(chest);
				}
				if (DungeonLoader.getTile(x, z).equals("J")) {  //Com Gema Destrancado
					// Generate keys
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);
					
					mojoGem = mojoGemPool.obtain();
					mojoGem.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth);
					
					chest = chestPool.obtain();
					chest.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, ChestState.UNLOCKED, mojoGem);
					objects3D.add(chest);
				}
				
				if (DungeonLoader.getTile(x, z).equals("z")) {  //Com chave prateada trancado
					// Generate keys
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);
					
					key = keyPool.obtain();
					key.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, KeyType.SILVER);
					
					chest = chestPool.obtain();
					chest.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, ChestState.LOCKED, key);
					objects3D.add(chest);
				}
				if (DungeonLoader.getTile(x, z).equals("x")) {  //Com chave prateada destrancado
					// Generate keys
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);
					
					key = keyPool.obtain();
					key.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, KeyType.SILVER);
					
					chest = chestPool.obtain();
					chest.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, ChestState.UNLOCKED, key);
					objects3D.add(chest);
				}
				if (DungeonLoader.getTile(x, z).equals("v")) {  //Com chave dourada trancado
					// Generate keys
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);
					
					key = keyPool.obtain();
					key.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, KeyType.GOLDEN);
					
					chest = chestPool.obtain();
					chest.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, ChestState.LOCKED, key);
					objects3D.add(chest);
				}
				if (DungeonLoader.getTile(x, z).equals("R")) {  //Com chave dourada destrancado
					// Generate keys
					floorAndCeiling = floorAndCeilingPool.obtain();
					floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar));
					objects3D.add(floorAndCeiling);
					
					key = keyPool.obtain();
					key.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, KeyType.GOLDEN);
					
					chest = chestPool.obtain();
					chest.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, ChestState.UNLOCKED, key);
					objects3D.add(chest);
				}
				
				
			}
		}
		
	}
	
	//Used for Gems,Keys, Starting Position, etc; To create their floor and ceiling according to their group; Based on whats most likely their group;
	private int createFloorAndCeiling(float x, float z, int lastGroupIndex, char lastGroupIndexChar){
		//floorAndCeiling = floorAndCeilingPool.obtain();
		switch (lastGroupIndex) {
		case 1:	   return 1;  //floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1);
		case 2:	   return 2;
		case 3:	   return 3;
		case 4:	   return 4;
		case 5:	   return 5;
/*		default:
			objects3D.add(floorAndCeiling);*/
		
		}
		
		switch (lastGroupIndexChar) {
		case 'A':   return 1;
		case 'B':   return 2;
		case 'C':   return 3;
		case 'D':   return 4;
		case 'F':   return 5;
		}
			return 5;
	}
	
	
	public void nextBattle() {
		//inBattle = true;
		battleMusic = GlobalAssets.getRandomBattleMusic();   
		battleMusic.setLooping(true);
		battleMusic.play();
	}
	
	public void updateCurrentMonsters(){
		leftMonster = findMonster(ArrowDirection.LEFT);
		rightMonster = findMonster(ArrowDirection.RIGHT); 
		upMonster = findMonster(ArrowDirection.UP); 
		downMonster = findMonster(ArrowDirection.DOWN);
	}
	
	//Gambiarra!
	private Monster findMonster(ArrowDirection direction) {
		for (Monster monster : GameScreen.currentMonsterPackBeingFought.monsterPack) {
			if(monster.monsterArrowDirection.equals(direction)) return monster;
		}
		return null;
	}

	
	private void restartMap() {
		Player.firstPersonCamera.position.set(playerInitialX, Player.y ,playerInitialZ);
	}

	public static boolean isInBattle(){
		return inBattle;
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
		
		Player.firstPersonCameraController.touchDragged(screenX, screenY, 0);
		
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
		if(Gdx.input.isKeyPressed(Keys.R)){
			restartMap();
		}
	}
	
	
	public void update(){		
		
		if(nextDungeon){
			nextDungeon();
		}
	
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
		
	  if(inBattle && !battleMusic.isPlaying()){
			if(!notSoRandomEncounterMusic.isPlaying() && !battleMusic.isPlaying()) {   
				battleMusic.setLooping(true);
				battleMusic.play();
			}
		}
		
			
		if(inBattle && currentMonsterPackBeingFought.battleEnded) {
			currentMonsterPackBeingFought.isCurrent = false;
			inBattle = false;
			dungeonMusic.play();
			battleMusic.stop();
		}
			
		for (GameObject gameObject : objects3D) {
			gameObject.input();						
			gameObject.update();
			

			if(!(gameObject instanceof FloorAndCeiling || gameObject instanceof Player)) {				
				if(player.boundingBox.overlaps(gameObject.boundingBox)) {
					player.collidedThisFrame = true;
					
					if(!inBattle && gameObject instanceof MonsterPack) {
						player.collidedThisFrame = false;
						currentMonsterPackBeingFought = (MonsterPack) gameObject;
						currentMonsterPackBeingFought.isCurrent = true;
						inBattle = true;
						dungeonMusic.stop();
						notSoRandomEncounterMusic.play();
						battleMusic = GlobalAssets.getRandomBattleMusic();
						//nextBattle();
					}
					
					if(gameObject instanceof Exit) { 
						if(mojoGemAmount == inventoryMojoGems && hasExitKey) {
							nextDungeon = true;
							player.collidedThisFrame = false;
						}
					}
					
					if(gameObject instanceof Door) { 
						switch(((Door) gameObject).state){
						case LOCKED:							
							if(inventoryKeys > 0)	{					
								doorPool.free((Door) gameObject);
								ScreenAbstract.freePoolableObject3D((GameObjectPoolable)gameObject);
								player.collidedThisFrame = false;
								inventoryKeys--;
							}
							else player.collidedThisFrame = true;
							
							break;
						case UNLOCKED: 						
							doorPool.free((Door) gameObject);
							ScreenAbstract.freePoolableObject3D((GameObjectPoolable)gameObject);
							player.collidedThisFrame = false;
							break;			
						}
					}
					
					if(gameObject instanceof Chest) { 
						switch(((Chest) gameObject).state){
						case LOCKED:
							if(inventoryKeys > 0)	{						System.out.println("Unlocked!");
								chestPool.free((Chest) gameObject);
								ScreenAbstract.freePoolableObject3D((GameObjectPoolable)gameObject);
								player.collidedThisFrame = false;
								inventoryKeys--;
								((Chest) gameObject).spawnTreasure();
							}
							else player.collidedThisFrame = true;
							
							break;
						case UNLOCKED: 						
							chestPool.free((Chest) gameObject);
							ScreenAbstract.freePoolableObject3D((GameObjectPoolable)gameObject);
							player.collidedThisFrame = false;
							((Chest) gameObject).spawnTreasure();
							break;			
						}
					}
					
					
					if(gameObject instanceof MojoGem) { 	System.out.println("Got MojoGem!");
						inventoryMojoGems++;
						mojoGemPool.free((MojoGem) gameObject);
						ScreenAbstract.freePoolableObject3D((GameObjectPoolable)gameObject);
						player.collidedThisFrame = false;
					}
					if(gameObject instanceof Key) { 	System.out.println("Got Key!");
						if(((Key) gameObject).type.toString().equalsIgnoreCase("SILVER")) inventoryKeys++;
						if(((Key) gameObject).type.toString().equalsIgnoreCase("GOLDEN")) hasExitKey = true;
						keyPool.free((Key) gameObject);
						ScreenAbstract.freePoolableObject3D((GameObjectPoolable)gameObject);
						player.collidedThisFrame = false;
					}
					
				}
				//if(!player.boundingBox.overlaps(gameObject.boundingBox)) player.collidedThisFrame = false;
			}
			
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
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		/////////////////////3D////////////////////////
		//The camera to be used on the current frame; Well have multiple cameras; So use this to select which one is the current one; 
		ScreenAbstract.modelBatch.begin(Main.getInstance().current3DViewport.getCamera());     //Which perspective camera is the current perspective camera; Adjusted to its viewport;
		//For example, the player firstPersonCamera or the worldCamera3D;
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
	    
	    /////////////////////2D////////////////////////
	    /////////////////////2D////////////////////////
		ScreenAbstract.spriteBatch.setProjectionMatrix(Main.getInstance().current2DViewport.getCamera().combined);
		ScreenAbstract.spriteBatch.begin();
	    ScreenAbstract.spriteBatch.enableBlending();

	    //Corrigir!: //ScreenAbstract.spriteBatch.draw(UILayout.getTexture(), 0, 0, Globals.WORLD_WIDTH_MIN, Globals.WORLD_HEIGHT_MIN);
	    
	    if(inBattle) ScreenAbstract.spriteBatch.draw(danceDanceUILayout.getTexture(), danceDanceLayoutUIX, danceDanceLayoutUIY, Globals.WORLD_WIDTH_MIN/2.02f, Globals.WORLD_HEIGHT_MIN/8);
	    ScreenAbstract.rendering3D = false;
		for (GameObject gameObject : objects2D) {
			
			if(gameObject instanceof GameObjectPoolable && isPoolableObjectToBeRemoved3D((GameObjectPoolable)gameObject)) continue;
			
			if(!gameObject.isResetable() || !gameObject.isDisposable())
			{
				gameObject.draw();			
			}

		}
		if(currentMonsterPackBeingFought!= null && inBattle){
			for (Monster monster : currentMonsterPackBeingFought.monsterPack) {
				for (ArrowUI arrowUI : monster.myArrowArray) {
						arrowUI.draw();
				}
			}
		}
		ScreenAbstract.spriteBatch.end();		
		/////////////////////2D////////////////////////
		
	}
	
	///////////////
}
