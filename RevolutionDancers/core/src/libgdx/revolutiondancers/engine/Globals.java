package libgdx.revolutiondancers.engine;

import java.util.Random;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public abstract class Globals {
	
	public static final AssetManager assetManager = new AssetManager();
	
	public static final int WORLD_WIDTH_MAX = 2048;
	public static final int WORLD_HEIGHT_MAX = 1536;
	public static final int WORLD_WIDTH_MIN = 1024;
	public static final int WORLD_HEIGHT_MIN = 768;
	
	//This is used to convert between Bullet units and pixels (Pixels Per Meter / PPM)
	public static final float PPM = 100;

	//Whenever in need of a Vector3 or a Vector2 use these globally instantiated ones instead of creating new ones; 
	//Remember to always clean before and after using; Or at least keep in mind that they will probably be dirty beforehand;
	public static Vector3 usefulVector3 = new Vector3(); 
	public static Vector2 usefulVector2Number1 = new Vector2();
	public static Vector2 usefulVector2Number2 = new Vector2();
	public static float usefulFloat = 0;
	
	
	//An smart use of these three randomGenerators will give us a pretty good random behavior
	public static final RandomGenerator randomGenerator = new RandomGenerator(); //A 64 bit WELL512 implementation; This algorithm is also known as the modern version of the Mersenne Twister;
	public static final Random randomGenerator2 = new RandomXS128();
	public static final Random randomGenerator3 = new Random(randomGenerator2.nextLong());  
	private static Random currentRandomGenerator = randomGenerator;
	private static Delay changeRandomGeneratorDelay = new Delay((int)Math.abs(32200 + randomGenerator.nextInt(32200) - randomGenerator2.nextInt(3200 / randomGenerator3.nextInt(3200)))); //In miliseconds 32200 equals 32.2 seconds 
	private static Delay renewSeedDelay = new Delay(300000); //5 minutes in miliseconds
	private static int whichRandomGeneratorTurnIsIt = 1; 
	
	
	/***
	 * Returns the currentRandomGenerator being used by the Globals class;
	 * Every time it is used it controls and updates itself so that every 32.2 seconds plus or less some random interval
	 * another randomGenerator becomes the currentRandomGenerator being used;
	 * Sometimes, it even decides by random that instead of the regular next in line randomGenerator to be used, some other
	 * randomGenerator should be the next in line randomGenerator; 
	 * Also, it tries to minimize bad seed behavior by changing the current seed of the next candidates every 5 minutes plus or less some random interval;
	 * The idea is to avoid predictability on the long term; */
	public static Random getRandomGenerator(){
		if(!renewSeedDelay.hasStarted()) renewSeedDelay.start();
		if((randomGenerator.nextBoolean() || randomGenerator2.nextBoolean() || randomGenerator3.nextBoolean()) && renewSeedDelay.hasEnded()) //Really high chance of happening; But it could still not happen;
		{
			switch (whichRandomGeneratorTurnIsIt) {
			case 1:
			randomGenerator2.setSeed(currentRandomGenerator.nextLong());
			randomGenerator3.setSeed(currentRandomGenerator.nextLong());
			break;
			case 2:
			randomGenerator.nextSeed(currentRandomGenerator.nextInt());
			randomGenerator3.setSeed(currentRandomGenerator.nextLong());
			break;
			case 3:
			randomGenerator.nextSeed(currentRandomGenerator.nextInt());
			randomGenerator2.setSeed(currentRandomGenerator.nextLong());	
			break;
			}
			renewSeedDelay.setLength((int)Math.abs(300000 + randomGenerator.nextInt(132000) - randomGenerator2.nextInt(32200 /1 + randomGenerator3.nextInt(32000))));
			renewSeedDelay.start();
		}
		if(!changeRandomGeneratorDelay.hasStarted()) changeRandomGeneratorDelay.start();
		if(whichRandomGeneratorTurnIsIt > 3) whichRandomGeneratorTurnIsIt = 1;
		if (changeRandomGeneratorDelay.hasEnded()){
			if(randomGenerator.nextBoolean() && randomGenerator2.nextBoolean() && randomGenerator3.nextBoolean()) { //Surprise behavior
				whichRandomGeneratorTurnIsIt = currentRandomGenerator.nextInt(4);
				if(whichRandomGeneratorTurnIsIt > 3) whichRandomGeneratorTurnIsIt = 1; 
				if(whichRandomGeneratorTurnIsIt <= 0) whichRandomGeneratorTurnIsIt = 3;
			}
			switch (whichRandomGeneratorTurnIsIt) {
			case 1:
			currentRandomGenerator = randomGenerator;
			break;
			case 2:
			currentRandomGenerator = randomGenerator2;
			break;
			case 3:
			currentRandomGenerator = randomGenerator3;	
			break;
			}
			whichRandomGeneratorTurnIsIt++;	//Regular behavior
			changeRandomGeneratorDelay.setLength((int)Math.abs(32200 + randomGenerator.nextInt(32200) - randomGenerator2.nextInt(3200 / 1 + randomGenerator3.nextInt(3200))));
			changeRandomGeneratorDelay.start();
		}
		
		return currentRandomGenerator;
		
	}
	
	
}
