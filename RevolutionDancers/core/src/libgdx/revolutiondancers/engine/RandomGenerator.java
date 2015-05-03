package libgdx.revolutiondancers.engine;

import java.util.Random;

public class RandomGenerator extends Random {

	private static final long serialVersionUID = 1L; //Apenas pro java parar de enxer o saco sobre isto. De resto ignorar.
	
	/***
	 * Extends the functionality of the standard Java Random class.
	 * Makes it more fit to use in a game. With better distributions.
	 * Using the WELL512 version of the WELL algorithms. 
	 * [here adapted to work with 64 bits (WELL1024)]
	 * **/
	
	private long[] state;
	private int index;
	
	public RandomGenerator(){
		state = new long[16];
		index = 0;
		Random rand = new Random();
		
		this.nextSeed(rand.nextInt());
	}
	
	public RandomGenerator(int seed){
		state = new long[16];
		index = 0;
		
		this.nextSeed(seed);
	}
	
	public int nextPositiveInt(){
		return Math.abs(this.nextInt());
	}
	
	public int nextPositiveInt(int bound){
		return Math.abs(this.nextInt(bound));
	}
	
	//Although it is public it isnt meant to be called anywhere else but privately and on the Globals class
	public void nextSeed(int seed) {	//Not the best naming. But its fun to use it like this.	
		seed = Math.abs(seed);			
		
		for (int i = 0; i < 16; i++) 
		{
			state[i] = (seed + 1) *  ((seed+1) << 2) * i;
		}
		
	}
	
	@Override
	protected int next(int nbits){
		long a , b , c , d; 
	
		a = state[index];
		c = state[(index+13)&15];
		b = a^c^(a<<16)^(c<<15);
		c = state[(index+9)&15];
		c ^= (c>>11);
		a = state[index] = b^c;
		d = a^((a<<5)&0xDA442D24L);
		index = (index + 15)&15;
		a = state[index];
		state[index] = a^b^d^(a<<2)^(b<<18)^(c<<28);
		return (int) state[index];
	}
	
	
	/***
	 * http://www.lomont.org/Math/Papers/2008/Lomont_PRNG_2008.pdf
	 * http://www.lomont.org/
	 * "However, if you use it, I wouldd appreciate a reference or at least an email with thanks!"
	 * **/
	
	
}
