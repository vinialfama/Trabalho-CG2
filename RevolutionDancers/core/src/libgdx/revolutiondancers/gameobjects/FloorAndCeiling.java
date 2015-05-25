package libgdx.revolutiondancers.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;

import libgdx.revolutiondancers.engine.GameObjectDisposable;
import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.engine.GlobalAssets;
import libgdx.revolutiondancers.engine.Globals;
import libgdx.revolutiondancers.screens.ScreenAbstract;

public class FloorAndCeiling extends GameObjectPoolable {

	public Model modelFloor, modelCeiling;
	public ModelInstance modelInstanceFloor, modelInstanceCeiling;
	public static float floorY, ceilingY;
	
	public Texture floorTexture; 
	public Texture ceilingTexture;
	
	public void init(float x, float z, int myGroup) {
		this.x = x;
		this.z = z;
		boundingBox.x = x;
		boundingBox.y = z;
		
		floorTexture = GlobalAssets.getFloorTexture(myGroup);		floorTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		ceilingTexture = GlobalAssets.getCeilingTexture(myGroup);   ceilingTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		modelFloor = Globals.modelBuilder.createBox(Wall.width, 2f, Wall.depth, new Material(new TextureAttribute(TextureAttribute.Diffuse, floorTexture)), Usage.Position | Usage.TextureCoordinates);
		modelInstanceFloor = new ModelInstance(modelFloor);
		modelInstanceFloor.transform.setTranslation(x, Wall.y - Wall.height/1.88f, z);
		
		modelCeiling = Globals.modelBuilder.createBox(Wall.width, 2f, Wall.depth, new Material(new TextureAttribute(TextureAttribute.Diffuse, ceilingTexture)), Usage.Position | Usage.TextureCoordinates);
		modelInstanceCeiling = new ModelInstance(modelCeiling);
		modelInstanceCeiling.transform.setTranslation(x, Wall.y + Wall.height/1.88f, z);
		
		//model = Globals.modelBuilder.createBox(width, height, depth, new Material(new TextureAttribute(TextureAttribute.Diffuse, texture)), Usage.Position | Usage.TextureCoordinates);
	}
	


	public FloorAndCeiling() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public void init(float x, float y, float z) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void init(float x, float y) {
		// TODO Auto-generated method stub
		
	}
	
	
/*	private Model createPlaneModel(final float width, final float height, final Material material, 
            final float u1, final float v1, final float u2, final float v2) {

			Globals.modelBuilder.begin();
			MeshPartBuilder bPartBuilder = Globals.modelBuilder.part("rect", 
			GL20.GL_TRIANGLES, Usage.Position | Usage.Normal | Usage.TextureCoordinates, material);
			//NOTE ON TEXTURE REGION, MAY FILL OTHER REGIONS, USE GET region.getU() and so on
			bPartBuilder.setUVRange(u1, v1, u2, v2);
			bPartBuilder.rect(
                -(width*0.5f), -(height*0.5f), 0, 
                 (width*0.5f), -(height*0.5f), 0, 
                 (width*0.5f),  (height*0.5f), 0, 
                -(width*0.5f),  (height*0.5f), 0,
                							   
                							   0,  //NormalX 
                							   0,  //NormalY
                							  -1); //NormalZ
			
			return (Globals.modelBuilder.end());
    }*/


	@Override
	public void reset() {
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
	
	
	/////////////////////////////////////////////////
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
			ScreenAbstract.modelBatch.render(modelInstanceFloor, Globals.environment);	
			ScreenAbstract.modelBatch.render(modelInstanceCeiling, Globals.environment);	
		}
	}
	/////////////////////////////////////////////////

	
}
