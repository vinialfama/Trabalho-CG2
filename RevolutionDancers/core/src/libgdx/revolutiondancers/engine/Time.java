package libgdx.revolutiondancers.engine;

import com.badlogic.gdx.utils.TimeUtils;

public class Time {

	private static long curTime;
	private static long lastTime;
	
	private Time() {
		
	}
	
	public static long getTime()
	{
		return TimeUtils.nanoTime();	//Antigamente usavamos System.nanoTime(); Entretanto a versao do LibGDX eh cross-plataform;
	}
	
	
	public static long getDelta()
	{
		return curTime - lastTime;
	}
	
	
	public static void update()
	{
		lastTime = curTime;
		curTime = getTime();
	}
		 
	public static void init() {
		lastTime = getTime();
		curTime = getTime();
	}
	
}
