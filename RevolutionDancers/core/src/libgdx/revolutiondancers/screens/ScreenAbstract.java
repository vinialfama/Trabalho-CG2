package libgdx.revolutiondancers.screens;

import libgdx.revolutiondancers.engine.GameObject;
import libgdx.revolutiondancers.engine.GameObjectDisposable;
import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.engine.Globals;
import libgdx.revolutiondancers.engine.Physics;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;

public abstract class ScreenAbstract implements Screen, ControllerListener, InputProcessor{
	
	public static boolean rendering3D = false;  //This way GameObjects can render in 2D when this is false; using spriteBatch; and in 3D using modelBatch; GameObjects can then have 2D and 3D representations; But then it has to be added to both objects2D and objects3D arrays;
	public static final ModelBatch modelBatch = new ModelBatch();
	
	/////////////////////3D////////////////////////
	/////////////////////3D////////////////////////
	
	//ObjectSets arent ordered; Object sets are O(1) for contains, insert and remove operations [O(logN) when there are collisions]; They are implemented using Cuckoo Hashing [Currently the most advanced and efficient Hashing technique]; The bad part is: Their space-complexity is a little expensive;
	protected static final Array<GameObject> objects3D = new Array<GameObject>();  //Our objects Array needs to be ordered, so it isnt an ObjectSet; Also, Arrays can perform the removeAll operation when given another Array to work with; Which is important!;
	protected static final ObjectSet<GameObjectPoolable> addLaterObjects3D = new ObjectSet<GameObjectPoolable>();
	protected static final ObjectSet<GameObjectDisposable> disposeMeLaterDisposableObjects3D = new ObjectSet<GameObjectDisposable>();
	protected static final ObjectSet<GameObjectPoolable> freeMeLaterPoolableObjects3D = new ObjectSet<GameObjectPoolable>();
	private static final Array<GameObject> freeMeLaterDisposeMeLateGameObjectsAux3D = new Array<GameObject>(); //Concatena e auxilia os ObjectSets a terem forma de Array para uma melhor delecao;
	
	public static void addLaterPoolableObject3D(GameObjectPoolable obj){
		addLaterObjects3D.add(obj);
	}
	public static void disposeDisposableObject3D(GameObjectDisposable obj){
		disposeMeLaterDisposableObjects3D.add(obj);
	}
	public static void freePoolableObject3D(GameObjectPoolable obj){
		freeMeLaterPoolableObjects3D.add(obj);
	}
	public static void freePoolableObjects3D(Array<? extends GameObjectPoolable> objectCollection){
		freeMeLaterPoolableObjects3D.addAll(objectCollection);
	}
	public static boolean isPoolableObjectToBeRemoved3D(GameObjectPoolable obj){
		return freeMeLaterPoolableObjects3D.contains(obj);  
	}
	
	public void resetAndDisposeObjects3D() {
		if(/*!Physics.WORLD.isLocked() &&*/ (freeMeLaterPoolableObjects3D.size > 0)) 
		{
			for (GameObjectPoolable freeMeLaterPoolableObject : freeMeLaterPoolableObjects3D) 
			{
					freeMeLaterDisposeMeLateGameObjectsAux3D.add(freeMeLaterPoolableObject);
			}				
			freeMeLaterPoolableObjects3D.clear();
		}
		
		if(/*!Physics.WORLD.isLocked() &&*/ (disposeMeLaterDisposableObjects3D.size > 0)) 
		{
			for (GameObjectDisposable gameObjectDisposable : disposeMeLaterDisposableObjects3D) 
			{
					freeMeLaterDisposeMeLateGameObjectsAux3D.add(gameObjectDisposable);
					gameObjectDisposable.dispose();
			}	
			disposeMeLaterDisposableObjects3D.clear();
		}
			objects3D.removeAll(freeMeLaterDisposeMeLateGameObjectsAux3D,false);
			freeMeLaterDisposeMeLateGameObjectsAux3D.clear();
	}
	
	
	public void addAddLaterObjects3D(){
		for (GameObjectPoolable gameObjectPoolable : addLaterObjects3D) {
			objects3D.add(gameObjectPoolable);
		}		
		addLaterObjects3D.clear();
	}
	
	/////////////////////3D////////////////////////
	/////////////////////2D////////////////////////
	
	public static final SpriteBatch spriteBatch = new SpriteBatch();  //Sprites and UI Elements; Etc; Any kind of 2D rendering in general;
	
	protected static final Array<GameObject> objects2D = new Array<GameObject>();  //Our objects Array needs to be ordered, so it isnt an ObjectSet; Also, Arrays can perform the removeAll operation when given another Array to work with; Which is important!;
	protected static final ObjectSet<GameObjectPoolable> addLaterObjects2D = new ObjectSet<GameObjectPoolable>();
	protected static final ObjectSet<GameObjectDisposable> disposeMeLaterDisposableObjects2D = new ObjectSet<GameObjectDisposable>();
	protected static final ObjectSet<GameObjectPoolable> freeMeLaterPoolableObjects2D = new ObjectSet<GameObjectPoolable>();
	private static final Array<GameObject> freeMeLaterDisposeMeLateGameObjectsAux2D = new Array<GameObject>(); //Concatena e auxilia os ObjectSets a terem forma de Array para uma melhor delecao;
	
	public static void addLaterPoolableObject2D(GameObjectPoolable obj){
		addLaterObjects2D.add(obj);
	}
	public static void disposeDisposableObject2D(GameObjectDisposable obj){
		disposeMeLaterDisposableObjects2D.add(obj);
	}
	public static void freePoolableObject2D(GameObjectPoolable obj){
		freeMeLaterPoolableObjects2D.add(obj);
	}
	public static void freePoolableObjects2D(Array<? extends GameObjectPoolable> objectCollection){
		freeMeLaterPoolableObjects2D.addAll(objectCollection);
	}
	public static boolean isPoolableObjectToBeRemoved2D(GameObjectPoolable obj){
		return freeMeLaterPoolableObjects2D.contains(obj);  
	}
	
	public void resetAndDisposeObjects2D() {
		if(/*!Physics.WORLD.isLocked() &&*/ (freeMeLaterPoolableObjects2D.size > 0)) 
		{
			for (GameObjectPoolable freeMeLaterPoolableObject : freeMeLaterPoolableObjects2D) 
			{
					freeMeLaterDisposeMeLateGameObjectsAux2D.add(freeMeLaterPoolableObject);
			}				
			freeMeLaterPoolableObjects2D.clear();
		}
		
		if(/*!Physics.WORLD.isLocked() &&*/ (disposeMeLaterDisposableObjects2D.size > 0)) 
		{
			for (GameObjectDisposable gameObjectDisposable : disposeMeLaterDisposableObjects2D) 
			{
					freeMeLaterDisposeMeLateGameObjectsAux2D.add(gameObjectDisposable);
					gameObjectDisposable.dispose();
			}	
			disposeMeLaterDisposableObjects2D.clear();
		}
			objects2D.removeAll(freeMeLaterDisposeMeLateGameObjectsAux2D,false);
			freeMeLaterDisposeMeLateGameObjectsAux2D.clear();
	}
	
	
	public void addAddLaterObjects2D(){
		for (GameObjectPoolable gameObjectPoolable : addLaterObjects2D) {
			objects2D.add(gameObjectPoolable);
		}		
		addLaterObjects2D.clear();
	}
	
	
	///////////////////////////////////////////////
	//////////////////////////////////////////////
	public abstract void loadAssets();
	public abstract void getAssets();

	public abstract void input();
	public abstract void update();
	public abstract void render(float delta);
}
