package libgdx.revolutiondancers.gameobjects;

import libgdx.revolutiondancers.engine.Delay;
import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.engine.Globals;
import libgdx.revolutiondancers.gameobjects.ArrowUI.ArrowDirection;
import libgdx.revolutiondancers.pools.ArrowUIPool;
import libgdx.revolutiondancers.screens.GameScreen;
import sun.misc.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
															
public class Monster extends GameObjectPoolable {

	//We ask the pool for arrows to put inside our correspondent Queue;
	public static ArrowUIPool arrowUIPool = new ArrowUIPool();					//Tudo eh public pra ser mais facil fazer o trabalho; Mas tomar cuidado!
	public Queue<ArrowUI> myArrowQueue = new Queue<ArrowUI>();
	public Array<ArrowUI> myArrowArray = new Array<ArrowUI>();
	public ArrowUI currentArrowBeingEnqueued, currentArrowBeingDequeued;
	public static final Queue<ArrowUI> leftArrows = new Queue<ArrowUI>();
	public static final Queue<ArrowUI> rightArrows = new Queue<ArrowUI>();
	public static final Queue<ArrowUI> downArrows = new Queue<ArrowUI>();
	public static final Queue<ArrowUI> upArrows = new Queue<ArrowUI>();
	public final Delay appendAnotherArrowDelay = new Delay(Math.abs(Globals.getRandomGenerator().nextInt(10000)));
	public ArrowDirection monsterArrowDirection;  //Each enemy in a battle is responsible for a specific queue; Each representing an arrow;
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
	}
	
	@Override
	public void init(float x, float y, float z) {}
	
	@Override //Sounds crazy but enemies are actually 3D; [2D Sprites placed in a 3D space]; //So dont use this one!
	public void init(float x, float y) {}
	
	
	@Override
	public void reset() {
		
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
	//////////////////////////////////////////
	
	//Thanks to the stupidity of Java6 Queue having no peek() method;
	//I decided that the Player has only one chance to get each arrow right;
	//If she fails, then she takes damage for that mistake;
	public boolean hasPlayerDancedAtTheRightTime() {	
																	  try {
			currentArrowBeingDequeued = myArrowQueue.dequeue();		} catch (InterruptedException erroInutil) { erroInutil.printStackTrace();}
			Globals.usefulFloat = currentArrowBeingDequeued.y;
			arrowUIPool.free(currentArrowBeingDequeued);
			myArrowArray.removeValue(currentArrowBeingDequeued, false);
		
		if(Globals.usefulFloat >= GameScreen.danceDanceLayoutUIY - 32 && Globals.usefulFloat <= GameScreen.danceDanceLayoutUIY + 128) 
			return true;  //Acertou!!
			
		return false;	//Errou!!
	}
	
	public void attack() {
		
		if(lifeAmount <= 0) return;
		
		//Decreases player life [if she has that kind of thing]
		//Executes attack animation!
		
	}
	
	public void takeDamage(){
		
		if(lifeAmount <= 0) {   System.out.println("Im dead!");
			return;
		}
		
		//Decreases life
		lifeAmount -= (1.3 + Math.abs(Globals.getRandomGenerator().nextInt(2)))/2; //Can lose almost no life or a lot of life; Totally luck based;
		
		//lifeAmount -= (/*GameScreen.battleMusic.getVolume() + [Fix!!] */ (0.001 + GameScreen.battleMusic.getPosition()/360))/2;	//Monsters lose more life as the Song progresses and when the Volume is louder 
		
		//Executes taking damage animation
		System.out.println("Ouch!  Monster Life = "+ lifeAmount);
		
		//Executes random Player dance move animation
		//Executes random FlashMob dance move animation
	}
	
	public void populateMyArrowQueue(){		
		if(appendAnotherArrowDelay.hasEnded()){		
			currentArrowBeingEnqueued = arrowUIPool.obtain();
			currentArrowBeingEnqueued.init(monsterArrowDirection);
			myArrowQueue.enqueue(currentArrowBeingEnqueued);
			myArrowArray.add(currentArrowBeingEnqueued);
			appendAnotherArrowDelay.setLength(1115 + Globals.getRandomGenerator().nextInt(1000) + Globals.getRandomGenerator().nextInt(100) + Globals.getRandomGenerator().nextInt(27));  //This will make the arrows look like they are trying to follow the song; In all kinds of patterns; The minimum delay between arrows is 5 ms;
		 /* if((Globals.randomGenerator.nextBoolean() && Globals.randomGenerator.nextBoolean() &&  Globals.randomGenerator2.nextBoolean() && Globals.randomGenerator2.nextBoolean() && Globals.randomGenerator3.nextBoolean() && Globals.randomGenerator3.nextBoolean())){  //Low chance of happening
				currentArrowBeingEnqueued = arrowUIPool.obtain();
				currentArrowBeingEnqueued.initDoubleArrow(monsterArrowDirection);		System.out.println("And you feel wasted on youself");
			}*/  //Needs fixing!
			appendAnotherArrowDelay.start();					//System.out.println(appendAnotherArrowDelay.getLength());
		}
	}
	
	//////////////////////////////////////////
	@Override
	public void input() {
		
		if(GameScreen.isInBattle() && !myArrowQueue.isEmpty()) {
			if(Gdx.input.isKeyJustPressed(Keys.LEFT) && monsterArrowDirection.name() == "LEFT")
			{
				if(myArrowQueue.isEmpty()) {
					attack();		System.out.println("Attack! LEFT");		
				}
				if(hasPlayerDancedAtTheRightTime())	
				{takeDamage();		System.out.println("DMG! LEFT");}
				else {attack();		System.out.println("Attack! LEFT");}
			}

			if(Gdx.input.isKeyJustPressed(Keys.RIGHT) && monsterArrowDirection.name() == "RIGHT")
			{
				if(myArrowQueue.isEmpty()) {
					attack();		System.out.println("Attack! RIGHT");		
				}
				if(hasPlayerDancedAtTheRightTime())	
				{takeDamage();		System.out.println("DMG! RIGHT");}
				else {attack();		System.out.println("Attack! RIGHT");}
			}

			if(Gdx.input.isKeyJustPressed(Keys.UP) && monsterArrowDirection.name() == "UP")
			{
				if(myArrowQueue.isEmpty()) {
					attack();		System.out.println("Attack! UP");		
				}
				if(hasPlayerDancedAtTheRightTime())	
				{takeDamage();		System.out.println("DMG! UP");}
				else {attack();		System.out.println("Attack! UP");}
			}

			if(Gdx.input.isKeyJustPressed(Keys.DOWN) && monsterArrowDirection.name() == "DOWN")
			{
				if(myArrowQueue.isEmpty()) {
					attack();		System.out.println("Attack! DOWN");		
				}
				if(hasPlayerDancedAtTheRightTime())	
				{takeDamage();		System.out.println("DMG! DOWN");}
				else {attack();		System.out.println("Attack! DOWN");}
			}
			
			
			if(Gdx.input.isKeyJustPressed(Keys.K))   //Cheat for killing monsters
			{
				System.out.println("You cheating bastard!");
				lifeAmount = 0;
			}
			
		}
		
	}
	
	@Override
	public void update() {  
		
		populateMyArrowQueue();			
		
		boolean dequeuedStuff = false;					

		for (ArrowUI arrowUI : myArrowArray) {
			arrowUI.update();
			if(arrowUI.passedTheScreenEnd) { //Then it must be the first-out on the Queue;
			  if(!myArrowQueue.isEmpty()) {			  												try {
						currentArrowBeingDequeued = myArrowQueue.dequeue(); 					   }catch (InterruptedException erroInutil) { erroInutil.printStackTrace();}	
			  			dequeuedStuff = true;
			 
			  }
			}
		}
		
			if(dequeuedStuff){
				arrowUIPool.free(currentArrowBeingDequeued);
				myArrowArray.removeValue(currentArrowBeingDequeued, false);
			}
	}

	@Override
	public void draw() {
		for (ArrowUI arrowUI : myArrowArray) {
			arrowUI.draw();
		}
	}
	
	////////////////////////////////////////////

}
