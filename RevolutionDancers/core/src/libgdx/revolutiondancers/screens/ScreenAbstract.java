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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;

public abstract class ScreenAbstract implements Screen, ControllerListener, InputProcessor{

	public static final SpriteBatch batch = new SpriteBatch();
	
	//ObjectSets arent ordered; Object sets are O(1) for contains, insert and remove operations [O(logN) when there are collisions]; They are implemented using Cuckoo Hashing [Currently the most advanced and efficient Hashing technique]; The bad part is: Their space-complexity is a little expensive;
	protected static final Array<GameObject> objects = new Array<GameObject>();  //Our objects Array needs to be ordered, so it isnt an ObjectSet; Also, Arrays can perform the removeAll operation when given another Array to work with; Which is important!;
	protected static final ObjectSet<GameObjectPoolable> addLaterObjects = new ObjectSet<GameObjectPoolable>();
	protected static final ObjectSet<GameObjectDisposable> disposeMeLaterDisposableObjects = new ObjectSet<GameObjectDisposable>();
	protected static final ObjectSet<GameObjectPoolable> freeMeLaterPoolableObjects = new ObjectSet<GameObjectPoolable>();
	private static final Array<GameObject> freeMeLaterDisposeMeLateGameObjectsAux = new Array<GameObject>(); //Concatena e auxilia os ObjectSets a terem forma de Array para uma melhor delecao;
	
	public static void addLaterPoolableObject(GameObjectPoolable obj){
		addLaterObjects.add(obj);
	}
	public static void disposeDisposableObject(GameObjectDisposable obj){
		disposeMeLaterDisposableObjects.add(obj);
	}
	public static void freePoolableObject(GameObjectPoolable obj){
		freeMeLaterPoolableObjects.add(obj);
	}
	public static void freePoolableObjects(Array<? extends GameObjectPoolable> objectCollection){
		freeMeLaterPoolableObjects.addAll(objectCollection);
	}
	public static boolean isPoolableObjectToBeRemoved(GameObjectPoolable obj){
		return freeMeLaterPoolableObjects.contains(obj);  
	}
	
	public void resetAndDisposeObjects() {
		if(/*!Physics.WORLD.isLocked() &&*/ (freeMeLaterPoolableObjects.size > 0)) 
		{
			for (GameObjectPoolable freeMeLaterPoolableObject : freeMeLaterPoolableObjects) 
			{
					freeMeLaterDisposeMeLateGameObjectsAux.add(freeMeLaterPoolableObject);
			}				
			freeMeLaterPoolableObjects.clear();
		}
		
		if(/*!Physics.WORLD.isLocked() &&*/ (disposeMeLaterDisposableObjects.size > 0)) 
		{
			for (GameObjectDisposable gameObjectDisposable : disposeMeLaterDisposableObjects) 
			{
					freeMeLaterDisposeMeLateGameObjectsAux.add(gameObjectDisposable);
					gameObjectDisposable.dispose();
			}	
			disposeMeLaterDisposableObjects.clear();
		}
			objects.removeAll(freeMeLaterDisposeMeLateGameObjectsAux,false);
			freeMeLaterDisposeMeLateGameObjectsAux.clear();
	}
	
	
	public void addAddLaterObjects(){
		for (GameObjectPoolable gameObjectPoolable : addLaterObjects) {
			objects.add(gameObjectPoolable);
		}		
		addLaterObjects.clear();
	}
	
	public abstract void loadAssets();
	public abstract void getAssets();

	public abstract void input();
	public abstract void update();
	public abstract void render(float delta);
}
