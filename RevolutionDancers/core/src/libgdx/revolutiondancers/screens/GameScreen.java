package libgdx.revolutiondancers.screens;

import libgdx.revolutiondancers.engine.DungeonGenerator;
import libgdx.revolutiondancers.engine.DungeonLoader;
import libgdx.revolutiondancers.engine.GameObject;
import libgdx.revolutiondancers.engine.GameObjectDisposable;
import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.engine.GlobalAssets;
import libgdx.revolutiondancers.engine.Globals;
import libgdx.revolutiondancers.engine.Main;
import libgdx.revolutiondancers.gameobjects.Door;
import libgdx.revolutiondancers.gameobjects.ArrowUI.ArrowDirection;
import libgdx.revolutiondancers.gameobjects.Door.DoorState;
import libgdx.revolutiondancers.gameobjects.Exit;
import libgdx.revolutiondancers.gameobjects.FloorAndCeiling;
import libgdx.revolutiondancers.gameobjects.Key;
import libgdx.revolutiondancers.gameobjects.MojoGem;
import libgdx.revolutiondancers.gameobjects.Monster;
import libgdx.revolutiondancers.gameobjects.MonsterPack;
import libgdx.revolutiondancers.gameobjects.Player;
import libgdx.revolutiondancers.gameobjects.Wall;
import libgdx.revolutiondancers.pools.MonsterPackPool;
import libgdx.revolutiondancers.pools.MonsterPool;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
	
	public static final MonsterPool monsterPool = new MonsterPool();	//Monsters that populate each MonsterPack
	public static final MonsterPackPool monsterPackPool = new MonsterPackPool();  //Monster Packs that populate the Dungeon
	public static MonsterPack currentMonsterPackBeingFought;	//What monster pack to display on the screen when a Fight happens!
	public static Monster leftMonster, rightMonster, upMonster, downMonster; //Whatever the current Monsters in a battle are; Update these whenever a battle starts;
	
	public static Music notSoRandomEncounterMusic = Globals.assetManager.get("RevolutionDancersAssets/Audio/Music/randomEncounterMusic.mp3", Music.class);
	public static Music battleMusic;
	public static Music dungeonMusic;
	
	public static final Player player = new Player();
	public static final ObjectSet<Wall> walls = new ObjectSet<Wall>();
	public static final ObjectSet<Exit> exits = new ObjectSet<Exit>();
	public static final ObjectSet<MojoGem> mojoGems = new ObjectSet<MojoGem>();
	public static final ObjectSet<Door> doors = new ObjectSet<Door>();
	public static final ObjectSet<Key> keys = new ObjectSet<Key>();
	private static int currentDungeonNumber = 0;
	private static int inventoryMojoGems = 0;
	private static int inventoryKeys = 0;
	private static int inventorySpecialKeys = 0;
		
	
	public GameScreen() {											//inBattle = true;  //Testes
		inputMultiplexer.addProcessor(this);				
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		nextDungeon();
		
/*		MonsterPack testMonsterPack =  monsterPackPool.obtain();
		testMonsterPack.init(0, 0, 0);
		currentMonsterPackBeingFought = testMonsterPack;		updateCurrentMonsters();
		objects2D.add(testMonsterPack);*/
		
/*		//Group 1:
		Wall testWall = new Wall(0, 0, 1);   //Y [The height of stuff] is constant during map creation; Only x and Z vary;
		objects3D.add(testWall);

		FloorAndCeiling flacTest  = new FloorAndCeiling(testWall.x + Wall.width, testWall.z + Wall.depth, 1);
		FloorAndCeiling flacTest2 = new FloorAndCeiling(testWall.x - Wall.width, testWall.z - Wall.depth, 1);
		FloorAndCeiling flacTest3 = new FloorAndCeiling(testWall.x - Wall.width, testWall.z + Wall.depth, 1);
		FloorAndCeiling flacTest4 = new FloorAndCeiling(testWall.x + Wall.width, testWall.z - Wall.depth, 1);
		objects3D.add(flacTest);
		objects3D.add(flacTest2);
		objects3D.add(flacTest3);
		objects3D.add(flacTest4);*/
		
/*		
		//Group 2:
		testWall = new Wall(100, 0, 2);   //Y [The height of stuff] is constant during map creation; Only x and Z vary;
		objects3D.add(testWall);

		 flacTest  = new FloorAndCeiling(testWall.x + Wall.width, testWall.z + Wall.depth, 2);
		 flacTest2 = new FloorAndCeiling(testWall.x - Wall.width, testWall.z - Wall.depth, 2);
		 flacTest3 = new FloorAndCeiling(testWall.x - Wall.width, testWall.z + Wall.depth, 2);
		 flacTest4 = new FloorAndCeiling(testWall.x + Wall.width, testWall.z - Wall.depth, 2);
		objects3D.add(flacTest);
		objects3D.add(flacTest2);
		objects3D.add(flacTest3);
		objects3D.add(flacTest4);
		
		//Group 3:
		 testWall = new Wall(0, 100, 3);   //Y [The height of stuff] is constant during map creation; Only x and Z vary;
		objects3D.add(testWall);

		 flacTest  = new FloorAndCeiling(testWall.x + Wall.width, testWall.z + Wall.depth, 3);
		 flacTest2 = new FloorAndCeiling(testWall.x - Wall.width, testWall.z - Wall.depth, 3);
		 flacTest3 = new FloorAndCeiling(testWall.x - Wall.width, testWall.z + Wall.depth, 3);
		 flacTest4 = new FloorAndCeiling(testWall.x + Wall.width, testWall.z - Wall.depth, 3);
		objects3D.add(flacTest);
		objects3D.add(flacTest2);
		objects3D.add(flacTest3);
		objects3D.add(flacTest4);
		
		//Group 4:
		 testWall = new Wall(100, 100, 4);   //Y [The height of stuff] is constant during map creation; Only x and Z vary;
		objects3D.add(testWall);

		 flacTest  = new FloorAndCeiling(testWall.x + Wall.width, testWall.z + Wall.depth, 4);
		 flacTest2 = new FloorAndCeiling(testWall.x - Wall.width, testWall.z - Wall.depth, 4);
		 flacTest3 = new FloorAndCeiling(testWall.x - Wall.width, testWall.z + Wall.depth, 4);
		 flacTest4 = new FloorAndCeiling(testWall.x + Wall.width, testWall.z - Wall.depth, 4);
		objects3D.add(flacTest);
		objects3D.add(flacTest2);
		objects3D.add(flacTest3);
		objects3D.add(flacTest4);
		
		//Group 5:
		 testWall = new Wall(-100, 0, 5);   //Y [The height of stuff] is constant during map creation; Only x and Z vary;
		objects3D.add(testWall);

		 flacTest  = new FloorAndCeiling(testWall.x + Wall.width, testWall.z + Wall.depth, 5);
		 flacTest2 = new FloorAndCeiling(testWall.x - Wall.width, testWall.z - Wall.depth, 5);
		 flacTest3 = new FloorAndCeiling(testWall.x - Wall.width, testWall.z + Wall.depth, 5);
		 flacTest4 = new FloorAndCeiling(testWall.x + Wall.width, testWall.z - Wall.depth, 5);
		objects3D.add(flacTest);
		objects3D.add(flacTest2);
		objects3D.add(flacTest3);
		objects3D.add(flacTest4);*/
		
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
		
		inventoryMojoGems = 0;
		inventoryKeys = 0;
		inventorySpecialKeys = 0;
		walls.clear();
		exits.clear();
		mojoGems.clear();
		doors.clear();
		keys.clear();
		
		dungeonMusic = GlobalAssets.getRandomDungeonMusic();          //GlobalAssets.getRandomBattleMusic();   //Testes
		dungeonMusic.setLooping(true);
			dungeonMusic.play();			//Commented for now [Because Im listening to the Guardians of The Galaxy OST]
	
		//DungeonLoader.load(getCurrentDungeonNumber() + ".txt");
			DungeonLoader.load(1+".txt");
			
		int lastGroupIndex = 1;
		char lastGroupIndexChar = 'A';
		for (int z=0; z < DungeonLoader.getMap().size; z++) {
			for (int x=0; x < DungeonLoader.getMap().get(z).length(); x++) {
				
				if (DungeonLoader.getTile(x, z).equals("I")) {  //Inicio
					// Set start position
					createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar);
					Player.firstPersonCamera.position.set(x *Wall.width, Player.y, DungeonLoader.getMap().size +z *Wall.depth);			//Ainda esta spawnando no lugar errado
				}
				
				if (DungeonLoader.getTile(x, z).equals("1")) {
					// Generate walls
					lastGroupIndex = 1;
					objects3D.add(new Wall(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1));
				}
				if (DungeonLoader.getTile(x, z).equals("A")) {
					// Generate walls
					lastGroupIndexChar = 'A';
					objects3D.add(new FloorAndCeiling(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1));
				}
				
				if (DungeonLoader.getTile(x, z).equals("2")) {
					lastGroupIndex = 2;
					objects3D.add(new Wall(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1));
				}
				if (DungeonLoader.getTile(x, z).equals("B")) {
					// Generate walls
					lastGroupIndexChar = 'B';
					objects3D.add(new FloorAndCeiling(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1));
				}
				if (DungeonLoader.getTile(x, z).equals("3")) {
					lastGroupIndex = 3;
					objects3D.add(new Wall(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1));
				}
				if (DungeonLoader.getTile(x, z).equals("C")) {
					// Generate walls
					lastGroupIndexChar = 'C';
					objects3D.add(new FloorAndCeiling(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1));
				}
				if (DungeonLoader.getTile(x, z).equals("4")) {
					lastGroupIndex = 4;
					objects3D.add(new Wall(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1));
				}
				if (DungeonLoader.getTile(x, z).equals("D")) {
					// Generate walls
					lastGroupIndexChar = 'D';
					objects3D.add(new FloorAndCeiling(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1));
				}
				if (DungeonLoader.getTile(x, z).equals("5")) {
					lastGroupIndex = 5;
					objects3D.add(new Wall(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1));
				}
				if (DungeonLoader.getTile(x, z).equals("E")) {
					// Generate walls
					lastGroupIndexChar = 'E';
					objects3D.add(new FloorAndCeiling(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1));
				}
				
				
				if (DungeonLoader.getTile(x, z).equals("M")) {		//Fim
					// Monster Pack
					createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar);
				}
				
				if (DungeonLoader.getTile(x, z).equals("F")) {		//Fim
					// Create exit
					createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar);
					//exits.add(new Exit(x, DungeonLoader.getMap().size+z, 0.75f, 0.75f));
				}
				if (DungeonLoader.getTile(x, z).equals("G")) {
					// Generate gems
					createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar);
					//mojoGems.add(new MojoGem(x, DungeonLoader.getMap().size+z, 0.5f, 0.5f));
				}
				if (DungeonLoader.getTile(x, z).equals("U")) {
					// Generate unlocked doors
					createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar);
					//doors.add(new Door(x, DungeonLoader.getMap().size+z, 1.0f, 1.0f, DoorState.CLOSED));
				}
				if (DungeonLoader.getTile(x, z).equals("L")) {
					// Generate locked doors
					createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar);
					//doors.add(new Door(x, DungeonLoader.getMap().size+z, 1.0f, 1.0f, DoorState.LOCKED));
				}
				if (DungeonLoader.getTile(x, z).equals("K")) {
					// Generate keys
					createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar);
					//keys.add(new Key(x, DungeonLoader.getMap().size+z, 0.5f, 0.5f));
				}
				if (DungeonLoader.getTile(x, z).equals("*")) {  //Goal Key
					// Generate keys
					createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar);
					//keys.add(new Key(x, DungeonLoader.getMap().size+z, 0.5f, 0.5f));
				}
				
				if (DungeonLoader.getTile(x, z).equals("&")) {  //Unlocked Chest with Exit Key
					// Generate keys
					createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar);
					//keys.add(new Key(x, DungeonLoader.getMap().size+z, 0.5f, 0.5f));
				}
				if (DungeonLoader.getTile(x, z).equals("%")) {  //Unlocked Chest with Key
					// Generate keys
					createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar);
					//keys.add(new Key(x, DungeonLoader.getMap().size+z, 0.5f, 0.5f));
				}
				if (DungeonLoader.getTile(x, z).equals("$")) {  //Unlocked Chest with MojoGem
					// Generate keys
					createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar);
					//keys.add(new Key(x, DungeonLoader.getMap().size+z, 0.5f, 0.5f));
				}
				
				if (DungeonLoader.getTile(x, z).equals("!")) {  //Locked Chest with Exit Key
					// Generate keys
					createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar);
					//keys.add(new Key(x, DungeonLoader.getMap().size+z, 0.5f, 0.5f));
				}
				if (DungeonLoader.getTile(x, z).equals("@")) {  //Locked Chest with Key
					// Generate keys
					createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar);
					//keys.add(new Key(x, DungeonLoader.getMap().size+z, 0.5f, 0.5f));
				}
				if (DungeonLoader.getTile(x, z).equals("#")) {  //Locked Chest with MojoGem
					// Generate keys
					createFloorAndCeiling(x, z, lastGroupIndex, lastGroupIndexChar);
					//keys.add(new Key(x, DungeonLoader.getMap().size+z, 0.5f, 0.5f));
				}
				
			}
		}
		
	}
	
	//Used for Gems,Keys, Starting Position, etc; To create their floor and ceiling according to their group; Based on whats most likely their group;
	private void createFloorAndCeiling(float x, float z, int lastGroupIndex, char lastGroupIndexChar){
		switch (lastGroupIndex) {
		case 1:	 objects3D.add(new FloorAndCeiling(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1));  return;
		case 2:	 objects3D.add(new FloorAndCeiling(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 2));  return;
		case 3:	 objects3D.add(new FloorAndCeiling(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 3));  return;
		case 4:	 objects3D.add(new FloorAndCeiling(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 4));  return;
		case 5:	 objects3D.add(new FloorAndCeiling(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 5));  return;
		}
		
		switch (lastGroupIndexChar) {
		case 'A': objects3D.add(new FloorAndCeiling(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1));  return;
		case 'B': objects3D.add(new FloorAndCeiling(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 2));  return;
		case 'C': objects3D.add(new FloorAndCeiling(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 3));  return;
		case 'D': objects3D.add(new FloorAndCeiling(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 4));  return;
		case 'F': objects3D.add(new FloorAndCeiling(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 5));  return;
		}
		
	}
	
	
	public void nextBattle() {
		inBattle = true;
		//battleMusic = 
		//battleMusic.setLooping(true);
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
	
	/*private void generateMap(int dungeonNumber) {
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
		DungeonLoader.load("Level"+dungeonNumber+".map");
		for (int y=0; y < DungeonLoader.getMap().size; y++) {
			for (int x=0; x < DungeonLoader.getMap().get(y).length(); x++) {
				if (DungeonLoader.getTile(x, y).equals("S")) {
					// Set start position
					player.getCentrePos().set(x, DungeonLoader.getMap().size-y);
				}
				if (DungeonLoader.getTile(x, y).equals("W")) {
					// Generate walls
					walls.add(new Wall(x, DungeonLoader.getMap().size-y, 5f, 5f, 5f));
				}
				if (DungeonLoader.getTile(x, y).equals("E")) {
					// Create exit
					exits.add(new Exit(x, DungeonLoader.getMap().size-y, 0.75f, 0.75f));
				}
				if (DungeonLoader.getTile(x, y).equals("T")) {
					// Generate gems
					mojoGems.add(new MojoGem(x, DungeonLoader.getMap().size-y, 0.5f, 0.5f));
				}
				if (DungeonLoader.getTile(x, y).equals("U")) {
					// Generate unlocked doors
					doors.add(new Door(x, DungeonLoader.getMap().size-y, 1.0f, 1.0f, DoorState.CLOSED));
				}
				if (DungeonLoader.getTile(x, y).equals("L")) {
					// Generate locked doors
					doors.add(new Door(x, DungeonLoader.getMap().size-y, 1.0f, 1.0f, DoorState.LOCKED));
				}
				if (DungeonLoader.getTile(x, y).equals("K")) {
					// Generate keys
					keys.add(new Key(x, DungeonLoader.getMap().size-y, 0.5f, 0.5f));
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
						walls.add(new Wall(x, dungeonGenerator.getMap().size-y, 5f, 5f, 5f));
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
	}*/
	
/*	private void restartMap() {
		//player.setRotation(0.0f);
		generateMap(currentDungeonNumber);
	}
	
	private void nextLevel() {
		//player.setRotation(0.0f);
		currentDungeonNumber++;
		generateMap(currentDungeonNumber);
	}
	*/
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
		ScreenAbstract.spriteBatch.end();		
		/////////////////////2D////////////////////////
		
	}
	
	///////////////
}
