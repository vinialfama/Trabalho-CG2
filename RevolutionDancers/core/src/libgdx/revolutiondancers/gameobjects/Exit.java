package libgdx.revolutiondancers.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.math.Rectangle;

import libgdx.revolutiondancers.engine.DungeonLoader;
import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.engine.GlobalAssets;
import libgdx.revolutiondancers.engine.Globals;
import libgdx.revolutiondancers.screens.GameScreen;
import libgdx.revolutiondancers.screens.ScreenAbstract;

public class Exit extends GameObjectPoolable {
	
/*	public enum ExitState {LOCKED_SPECIAL, OPEN};
	public ExitState state;*/

	public Model model;
	public ModelInstance modelInstance;
	
	public Texture texture = Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Doors/6.png", Texture.class);
	
	public void init(float x, float z, int myGroup) {
		this.x = x ;
		this.z = z ;			
		boundingBox.x = x;
		boundingBox.y = z;
		
		//texture = GlobalAssets.getWallTexture(myGroup);		texture.setFilter(TextureFilter.Linear, TextureFilter.Nearest);
		
		model = Globals.modelBuilder.createBox(Wall.width, Wall.height, Wall.depth, new Material(new TextureAttribute(TextureAttribute.Diffuse, texture)), Usage.Position | Usage.TextureCoordinates);
		modelInstance = new ModelInstance(model);			
		modelInstance.transform.setTranslation(x, Wall.y, z);
		modelInstance.transform.rotate(0, 0, 1, 90);
		
		/*GameScreen.floorAndCeiling = GameScreen.floorAndCeilingPool.obtain();
		GameScreen.floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1);
		ScreenAbstract.addLaterPoolableObject3D(GameScreen.floorAndCeiling);*/
	}
	
	/*public void unlock() {
		state = ExitState.OPEN;
	}*/
	
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(float x, float y) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void init(float x, float y, float z) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void input() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		if(ScreenAbstract.rendering3D) {
			ScreenAbstract.modelBatch.render(modelInstance, Globals.environment);			
		}
	}


}
