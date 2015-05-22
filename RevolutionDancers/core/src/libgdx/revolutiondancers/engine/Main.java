package libgdx.revolutiondancers.engine;

import libgdx.revolutiondancers.gameobjects.Player;
import libgdx.revolutiondancers.screens.MenuScreen;
import libgdx.revolutiondancers.screens.ScreenAbstract;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Main extends Game {
	
	private static Main main;
	
	private OrthographicCamera worldCamera2D;
	public Viewport current2DViewport;  //User Interface, Mini-Map and stuff; Camera and viewport;
	private PerspectiveCamera worldCamera3D;
	private Viewport worldCamera3DViewport;
	public Viewport current3DViewport;  //The game itself camera and viewport;	
	//Remember that the player [is |also has] a Camera  [But uses the same viewport];
	
	public void create() {
		
		//Sets 2D camera
		worldCamera2D = new OrthographicCamera();
		worldCamera2D.setToOrtho(false, Globals.WORLD_WIDTH_MIN, Globals.WORLD_HEIGHT_MIN);
		current2DViewport = new ExtendViewport(Globals.WORLD_WIDTH_MIN, Globals.WORLD_HEIGHT_MIN, Globals.WORLD_WIDTH_MAX, Globals.WORLD_HEIGHT_MAX, worldCamera2D);
		worldCamera2D.update(); 
		//Sets 3D camera
		worldCamera3D = new PerspectiveCamera(70, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		worldCamera3D.near = 1f;
		worldCamera3D.far = 300f;			//World3D Camera is the camera that "films" the sweet 3D vs 2D Dungeon Crawler Battle Action!
		worldCamera3D.update();
		worldCamera3DViewport = new ExtendViewport(Globals.WORLD_WIDTH_MIN, Globals.WORLD_HEIGHT_MIN, Globals.WORLD_WIDTH_MAX, Globals.WORLD_HEIGHT_MAX, worldCamera3D);
		
		Player.firstPersonCamera.position.set(50f, 1f, 50f); //Colocar a position de acordo com a posicao de inicio da jogadora no mapa!
		Player.firstPersonCamera.near = 1f;
		Player.firstPersonCamera.far = 300f;
		Player.firstPersonCamera.update();
		current3DViewport = Player.firstPersonCameraViewport; //The first Camera (the one the Game starts in) is the Player first person point of view camera;		
		
		
		GlobalAssets.loadGlobalAssets();
		
		Globals.environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		Globals.environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		
		
		//Physics.initContactListener();
		
        //On Ouya the Camera has to be a little different
        
        //set everything else that is platform specific 
		this.setSettingsAccordingToDevice();
		
		//change to other screen
		this.setScreen(MenuScreen.getInstance());	
	}
	
	public static synchronized Main getInstance() {
		if(main == null)
			main = new Main();
		return main;
	}

	@Override
	public void render() {
	    super.render(); //important!
	}

	@Override
	public void dispose() {
		Globals.assetManager.dispose();
	}
	
	private void setSettingsAccordingToDevice() {
		
		ApplicationType type =  Gdx.app.getType();
		if(type == ApplicationType.Android) {
			System.out.println("Running on Android");
			//in case it is android, test for Ouya
			if(Ouya.isRunningOnOuya()) {
				System.out.println("and on Ouya");
			}
			else {
				/*Verificar a resolucao do dispositivo para ver se eh HD*/
				//if it is not Ouya, then it is a common Android Device
				//renderHD = false;
			}
		}
		else if(type == ApplicationType.iOS) {
			System.out.println("Running on iOS");
			//verificar se eh retina caso for set HD
			//renderHD = false;
		}
		else if(type == ApplicationType.Desktop) {
			System.out.println("Running on Desktop");
		}
		else if(type == ApplicationType.HeadlessDesktop) {
			System.out.println("Running on HeadlessDesktop");
		}
	}		
}
