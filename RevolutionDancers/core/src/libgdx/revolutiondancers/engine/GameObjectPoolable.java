package libgdx.revolutiondancers.engine;

import com.badlogic.gdx.utils.Pool.Poolable;

public abstract class GameObjectPoolable extends GameObject implements Poolable {
	protected final boolean disposeMe = false;  //Safety measures; Shouldn't be used, but it's good to avoid future generalization bugs;
	//lembrar que o objeto poolable nao tem dispose e nao DEVE tentar dar dispose
	//a pool responsavel pelo objeto que se encarrega de limpar os assets do objeto
	//isso quando a pool termina


	/**
	 * Como o objeto eh poolable ele nao pode ter construtores. Nao pode haver 'new'. 
	 * **/
	
	
	//Nao eh obrigatorio ter um create.
	
	
	@Override
	public abstract void reset();
	
	
	public abstract void init(float x, float y);
	
	public abstract void input();
	
	public abstract void update();
	
	@Override
	public abstract void draw();  
	
}
