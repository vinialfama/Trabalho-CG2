package libgdx.revolutiondancers.engine;


/*
 * A razao desta classe existir eh permitir que GameObject e GameObjectPoolable 
 * sejam parentes entre si e possam ser agrupados atraves desta classe.
 */
public abstract class GameObject {
	
	//Used to check if the game object has to be reset or disposed
	protected boolean resetMe = false;
	protected boolean disposeMe = false;
	
	//The physics' representation of the game object
	//protected Body body;
	
	//public Body getBody() { return body; }
	
	//PIXEL AND DRAWING POSITION
	//public float getX() { return body.getPosition().x * Globals.PPM - getWidth()/2; }
	//public float getY() { return body.getPosition().y * Globals.PPM - getHeight()/2; }
	
	//PHYSICAL POSITION
	//public float getXBody() { return body.getPosition().x; }
	//public float getYBody() { return body.getPosition().y; }
	
	
	//Renderable representation width and height
	public abstract float getWidth();
	public abstract float getHeight();
	
	//Renderable representation width and height RESIZE
	public abstract float getResizeWidth();
	public abstract float getResizeHeight();
	
	public boolean isResetable() { return resetMe; }
	public boolean isDisposable() { return disposeMe; }
	
	public abstract void setResetMeTrue();	//Safer, follows the flow of the program;
	public abstract void reset(); //Very strong implications; Use with caution;
	
	public abstract void input();
	public abstract void update();
	public abstract void draw();  //Input e Update (nesta ordem) sao responsabilidade da tela atual; dentro do render da tela;

}
