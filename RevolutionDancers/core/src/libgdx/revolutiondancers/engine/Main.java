package libgdx.revolutiondancers.engine;

import libgdx.revolutiondancers.screens.MenuScreen;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Main extends Game {
	
	private static Main main;
	
	private OrthographicCamera worldCamera;
	public Viewport currentViewport;
	
	public boolean renderHD; //Versao OUYA e tela Retina. Ou Tablets Android com alta resolucao.
	
	
	public void create() {
		
		//set camera
		worldCamera = new OrthographicCamera();
		worldCamera.setToOrtho(false, Globals.WORLD_WIDTH_MIN, Globals.WORLD_HEIGHT_MIN);
		currentViewport = new ExtendViewport(Globals.WORLD_WIDTH_MIN, Globals.WORLD_HEIGHT_MIN, Globals.WORLD_WIDTH_MAX, Globals.WORLD_HEIGHT_MAX, worldCamera);
		worldCamera.update();
		Globals.currentWorldHeight = currentViewport.getWorldHeight(); 
		Globals.currentWorldWidth  = currentViewport.getWorldWidth(); 

		GlobalAssets.loadGlobalAssets();
		
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
				renderHD = true;
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
