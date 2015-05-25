package libgdx.revolutiondancers.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.collision.BoundingBox;

import libgdx.revolutiondancers.engine.DungeonLoader;
import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.engine.GlobalAssets;
import libgdx.revolutiondancers.engine.Globals;
import libgdx.revolutiondancers.screens.GameScreen;
import libgdx.revolutiondancers.screens.ScreenAbstract;

public class Wall extends GameObjectPoolable {

	//public float centrePosX;
	//public float centrePosY;
	public static float width = 35f;
	public static float height = 35f;
	public static float depth = 35f;
	public static float y = 0;
	
	public boolean isArtistic, isTwoParter;  //Is this a sortition random decoration wall? If it is, is it a two parter? That is, that demands two walls?   //The two parter stuff is extra juice, do only if possible [it demands knowledge of the wall on the right, and cant happen in corners];
	
	public short groupNumber, spriteNumber;  //I belong to a goup of Sprites; For example, Im a blue wall; From this group, I was sortitioned as the shaded blue wall with blood variation;
											 //I must not be a decoration wall then;
	
	
	
	public Model model;
	public ModelInstance modelInstance;
	
	public Texture texture;
	
	public void init(float x, float z, int myGroup) {
		this.x = x ;
		this.z = z ;			
		boundingBox.x = x;
		boundingBox.y = z;
		
		texture = GlobalAssets.getWallTexture(myGroup);		texture.setFilter(TextureFilter.Linear, TextureFilter.Nearest);
		
		model = Globals.modelBuilder.createBox(width, height, depth, new Material(new TextureAttribute(TextureAttribute.Diffuse, texture)), Usage.Position | Usage.TextureCoordinates);
		modelInstance = new ModelInstance(model);			
		modelInstance.transform.setTranslation(x, y, z);
		modelInstance.transform.rotate(0, 0, 1, 90);
		
		GameScreen.floorAndCeiling = GameScreen.floorAndCeilingPool.obtain();
		GameScreen.floorAndCeiling.init(x *Wall.width, DungeonLoader.getMap().size+z *Wall.depth, 1);
		ScreenAbstract.addLaterPoolableObject3D(GameScreen.floorAndCeiling);
		
		//FloorAndCeiling floorAndCeiling = new FloorAndCeiling(x, z, myGroup);
		//ScreenAbstract.addLaterPoolableObject3D(floorAndCeiling);
	}
	
	public Wall() {
		// TODO Auto-generated constructor stub
	}

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
		return height;
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
	public void input() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {			//System.out.println(Globals.getRandomGenerator().nextInt(2));  //Printava de 0 ate 2  [com o 0 e com o 2] [soh que com valores as vezes negativos (nao abs)]
		//Wall doesnt have a 2D representation; So it can only render in the rendering3D phase;
		if(ScreenAbstract.rendering3D) {
			ScreenAbstract.modelBatch.render(modelInstance, Globals.environment);			
		}
		
	}

	@Override
	public void init(float x, float y, float z) {
		// TODO Auto-generated method stub
		
	}

}
