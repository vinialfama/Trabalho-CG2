package libgdx.revolutiondancers.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import sun.misc.Queue;
import libgdx.revolutiondancers.engine.Delay;
import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.engine.Globals;
import libgdx.revolutiondancers.gameobjects.ArrowUI.ArrowDirection;
import libgdx.revolutiondancers.pools.ArrowUIPool;
import libgdx.revolutiondancers.screens.GameScreen;
															//So, the monsters are the ones listening if the Player hitted the correct combos! So they can take damage or attack accordingly! 
public class Monster extends GameObjectPoolable implements ControllerListener, InputProcessor {

	//We ask the pool for arrows to put inside our correspondent Queue;
	public static ArrowUIPool arrowUIPool = new ArrowUIPool();
	private Queue<ArrowUI> myArrowQueue = new Queue<ArrowUI>();
	private Array<ArrowUI> myArrowArray = new Array<ArrowUI>();
	private ArrowUI currentArrowBeingEnqueued, currentArrowBeingDequeued;
	public static final Queue<ArrowUI> leftArrows = new Queue<ArrowUI>();
	public static final Queue<ArrowUI> rightArrows = new Queue<ArrowUI>();
	public static final Queue<ArrowUI> downArrows = new Queue<ArrowUI>();
	public static final Queue<ArrowUI> upArrows = new Queue<ArrowUI>();
	private final Delay appendAnotherArrowDelay = new Delay(Globals.getRandomGenerator().nextInt(10000));
	private ArrowDirection monsterArrowDirection;  //Each enemy in a battle is responsible for a specific queue; Each representing an arrow;
	public float lifeAmount = 100f;
	
	//This is the correct init:
	public void init(float x, float y, float z, ArrowDirection enemyArrowDirection) {
		this.monsterArrowDirection = enemyArrowDirection;
		switch(this.monsterArrowDirection){
		case DOWN:
			myArrowQueue = downArrows;
			break;
		case LEFT:
			myArrowQueue = leftArrows;
			break;
		case RIGHT:
			myArrowQueue = rightArrows;
			break;
		case UP:
			myArrowQueue = upArrows;
			break;
		default:
			break;
		}
			appendAnotherArrowDelay.start();
			GameScreen.getInputMultiplexer().addProcessor(this);
	}
	
	@Override
	public void init(float x, float y, float z) {}
	
	@Override //Sounds crazy but enemies are actually 3D; [2D Sprites placed in a 3D space]; //So dont use this one!
	public void init(float x, float y) {}
	
	
	@Override
	public void reset() {
		GameScreen.getInputMultiplexer().removeProcessor(this);
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
	
	
	///////////////////////////////////////////////////////

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
		// TODO Auto-generated method stub
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
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {

		if(keycode == Keys.LEFT) {	
			if(myArrowQueue.isEmpty()) {
				attack();		System.out.println("Attack! LEFT");
				return false;		
			}
			if(monsterArrowDirection.name() == "LEFT"){
				if(hasPlayerDancedAtTheRightTime())	
					 {takeDamage();		System.out.println("DMG! LEFT");}
				else {attack();		System.out.println("Attack! LEFT");}
			}
		}
		
		if(keycode == Keys.RIGHT) {	
			if(myArrowQueue.isEmpty()) {
				attack();		System.out.println("Attack! RIGHT");
				return false;		
			}
			if(monsterArrowDirection.name() == "RIGHT"){
				if(hasPlayerDancedAtTheRightTime())	
					 {takeDamage();		System.out.println("DMG! RIGHT");}
				else {attack();		System.out.println("Attack! RIGHT");}
			}
		}
		
		if(keycode == Keys.UP) {	
			if(myArrowQueue.isEmpty()) {
				attack();		System.out.println("Attack! UP");
				return false;		
			}
			if(monsterArrowDirection.name() == "UP"){
				if(hasPlayerDancedAtTheRightTime())	
					 {takeDamage();		System.out.println("Attack! UP");}
				else {attack();	System.out.println("Attack! UP");}
			}
		}
		
		if(keycode == Keys.DOWN) {	
			if(myArrowQueue.isEmpty()) {
				attack();		System.out.println("Attack! DOWN");
				return false;		
			}
			if(monsterArrowDirection.name() == "DOWN"){
				if(hasPlayerDancedAtTheRightTime())	
					 {takeDamage();		System.out.println("DMG! DOWN");}
				else {attack();	System.out.println("Attack! DOWN");}
			}
		}
		
		return false;
	}
	
	//////////////////////////////////////////
	
	//Thanks to the stupidity of Java6 Queue having no peek() method;
	//I decided that the Player has only one chance to get each arrow right;
	//If she fails, then she takes damage for that mistake;
	private boolean hasPlayerDancedAtTheRightTime() {	
		try {
			currentArrowBeingDequeued = myArrowQueue.dequeue();		} catch (InterruptedException erroInutil) { erroInutil.printStackTrace();}
			myArrowArray.removeValue(currentArrowBeingDequeued, false);
			arrowUIPool.free(currentArrowBeingDequeued);
		
		if(currentArrowBeingDequeued.y >= GameScreen.danceDanceLayoutUIY && currentArrowBeingDequeued.y <= GameScreen.danceDanceLayoutUIY + 64) 
			return true;  //Acertou!!
			
		return false;	//Errou!!
	}
	
	private void attack() {
		//Decreases player life [if she has that kind of thing]
		//Executes attack animation!
		
	}
	
	private void takeDamage(){
		System.out.println("Ouch!  Monster Life - "+ lifeAmount);
		//Decreases life
		lifeAmount -= 1.1 + (Globals.getRandomGenerator().nextInt(50)/1 + Globals.getRandomGenerator().nextInt(10)); //Can lose almost no life or a lot of life; Totally luck based;
		if(Globals.getRandomGenerator().nextBoolean() && Globals.randomGenerator2.nextBoolean()) lifeAmount -= Globals.getRandomGenerator().nextInt(75); //Critical damage!!
		//Executes taking damage animation
		
		//Executes random Player dance move animation
		//Executes random FlashMob dance move animation
	}
	
	private void populateMyArrowQueue(){
		if(appendAnotherArrowDelay.hasEnded()){
			currentArrowBeingEnqueued = arrowUIPool.obtain();
			currentArrowBeingEnqueued.init(monsterArrowDirection);
			myArrowQueue.enqueue(currentArrowBeingEnqueued);
			myArrowArray.add(currentArrowBeingEnqueued);
			appendAnotherArrowDelay.setLength(5 + Globals.getRandomGenerator().nextInt(1000) + Globals.getRandomGenerator().nextInt(100) + Globals.getRandomGenerator().nextInt(5));  //This will make the arrows look like they are trying to follow the song; In all kinds of patterns; The minimum delay between arrows is 5 ms;
			appendAnotherArrowDelay.start();
		}
	}
	
	//////////////////////////////////////////
	@Override
	public void input() {
		
		
	}

	@Override
	public void update() {
		
		populateMyArrowQueue();
		
		boolean dequeueStuff = false;
		for (ArrowUI arrowUI : myArrowArray) {
			arrowUI.update();
			if(arrowUI.passedTheScreenEnd) { //Then it must be the first-out on the Queue;
				dequeueStuff = true;
			}
		}
		
			if(dequeueStuff){
				try {
				currentArrowBeingDequeued = myArrowQueue.dequeue(); } catch (InterruptedException e) { e.printStackTrace(); }
				myArrowArray.removeValue(currentArrowBeingDequeued, false);
				arrowUIPool.free(currentArrowBeingDequeued);
			}
		
			//Yeap... Im responsible for reseting arrows and stuff;
	}

	@Override
	public void draw() {
		for (ArrowUI arrowUI : myArrowArray) {
			arrowUI.draw();
		}
	}
	
	////////////////////////////////////////////

}
