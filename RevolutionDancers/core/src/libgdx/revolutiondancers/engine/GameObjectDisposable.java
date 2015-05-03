package libgdx.revolutiondancers.engine;

import com.badlogic.gdx.utils.Disposable;

//GameObjects que dao render, sao disposables e nao sao poolables

public abstract class GameObjectDisposable extends GameObject implements Disposable {
	
	@Override
	public abstract void dispose(); //Create eh opcional. Dispose nao. 
	//lembrar caso der load nos assets pelo assetmanager tem que obrigatoriamente dar unload
	//nos assets pelo assetmanager dentro deste dispose
	
	public abstract void reset(); //Nao eh o mesmo reset do poolable. Eh uma maneira de evitar new quando possivel.
	
	public abstract void input();
	
	public abstract void update();
	
	@Override
	public abstract void draw();  //Input e Update (nesta ordem) sao responsabilidade da tela atual; dentro do render;
	
}
