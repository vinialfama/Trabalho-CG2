package libgdx.revolutiondancers.engine;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

//This is pretty much a container for all Math related stuff that we had to make ourselves;
public abstract class MathGlobals {
	
	/*2D Euclidian Distance*/	
	public static float euclideanDistance(float x1, float y1, float x2, float y2)
	{
		float x = x2 - x1;
		float y = y2 - y1;
		
		return (float)Math.sqrt(x*x + y*y);
	}
	
	/*2D Angle Between Points*/
	public static float angleBetweenTwoPoints(float x, float y, float x2, float y2)
	{
		float angle = (float) Math.toDegrees(MathUtils.atan2(y2 - y, x2 - x));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}

	/*2D Mid Point*/
	public static Vector2 midPoint(float x1, float y1, float x2, float y2)
	{
		Globals.usefulVector2Number1.x = ((x1 + x2)/2);
		Globals.usefulVector2Number1.y = ((y1 + y2)/2);
		
		return Globals.usefulVector2Number1;
	}
	
	
	/*Do them in 3D later if needed*/
	
}
