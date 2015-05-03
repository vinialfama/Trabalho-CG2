package libgdx.revolutiondancers.engine;

public class Delay {

	/**
	 * Classe utilizada extensivamente para calcular Delays temporais; 
	 * Os delays sao medidos em milisegundos;
	 * */
	
	private int length;
	private long longLength;
	private long endTime;
	boolean started; 
	private boolean isLong = false;
	//private boolean decreasingCounter;		//We'll see.
	
	public Delay(int length){
		this.length = length;
		started = false;
	}
	
	public Delay(long longLength){
		this.longLength = longLength;
		started = false;
		isLong = true;
	}
	
	public boolean hasEnded() {							
		if(!started) return false;
		
		return (Time.getTime() >= endTime);
	}
	
	public boolean isActive() {
		if(started) return true;
		
		return (Time.getTime() <= endTime);
	}

	public void start() {
		if(isLong){
			started = true;
			endTime = longLength * 1000000 + Time.getTime();	
		}
		else{
		started = true;
		endTime = length * 1000000 + Time.getTime(); 		// nanoseconds * 1000000 = 1 millisecond
		}
	}														// 1 milliseconds = 0.001 seconds; x milliseconds = 0.00x seconds; yx milliseconds = 0.0yx seconds; etc.
	
	public void stop () {
		started = false; 
	}
	
	public boolean hasStarted() {						
		return started;
	}
	
	public void end(){		
		started = true;
		endTime = 0; 
	}
	
	public int getLength(){
		return length;
	}
	
	public void setLength(int length){
		this.length = length;
	}
	
/*	public void setToDecrementalCounter(){
		decreasingCounter = true;
	}
//Maybe it will be necessary. Maybe it won't.
	public void setToIncrementalCounter(){
		decreasingCounter = false;
	}*/

}


