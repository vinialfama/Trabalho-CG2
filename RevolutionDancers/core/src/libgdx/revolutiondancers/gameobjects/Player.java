package libgdx.revolutiondancers.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import libgdx.revolutiondancers.engine.GameObjectDisposable;
import libgdx.revolutiondancers.engine.Globals;
import libgdx.revolutiondancers.engine.Main;
import libgdx.revolutiondancers.gameobjects.ArrowUI.ArrowDirection;
import libgdx.revolutiondancers.screens.GameScreen;

public class Player extends GameObjectDisposable /*implements ControllerListener, InputProcessor*/ {

	
	private Vector2 centrePosition = new Vector2(0.0f, 0.0f);
	public static PerspectiveCamera firstPersonCamera = new PerspectiveCamera(70, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	public static Viewport firstPersonCameraViewport = new ExtendViewport(Globals.WORLD_WIDTH_MIN, Globals.WORLD_HEIGHT_MIN, Globals.WORLD_WIDTH_MAX, Globals.WORLD_HEIGHT_MAX, firstPersonCamera);
	public static FirstPersonCameraController firstPersonCameraController = new FirstPersonCameraController(firstPersonCameraViewport.getCamera());
	
	public Player(){
		firstPersonCameraController.setVelocity(35f);
	}
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void reset() {
		
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
	

	public Vector2 getCentrePosition() {
		// TODO Auto-generated method stub
		return centrePosition;
	}
	
/*	@Override
	public boolean keyDown(int keycode) {
		
		if(!GameScreen.isInBattle()) 
		{
			if(keycode == Keys.W) {		
				//move(0, 0 , -1);
				return false;
			}
		
			if(keycode == Keys.S) {	
				//move(0, 0 , 1);		
				return false;
			}
		
			if(keycode == Keys.A) {	
				//move(-1,0, 0);
				return false;
			}
		
			if(keycode == Keys.D) {	
				//move(1,0, 0);
				return false;
			}
			
		}
		return false;
	}*/

	

	
	/*@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void connected(Controller controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnected(Controller controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode,
			PovDirection value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode,
			boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode,
			boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller controller,
			int accelerometerCode, Vector3 value) {
		// TODO Auto-generated method stub
		return false;
	}*/
	
	
	
	//=========================================================

	@Override
	public void input() {
		
	}

	@Override
	public void update() {
		//Salvar antes a posicao antiga da camera
		firstPersonCameraController.update();
		//Se a posicao nova ultrapassa o cenario
		 //Entao setar a camera pra posicao velha
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	
}
