package com.RevolutionDancers.desktop;

import libgdx.revolutiondancers.engine.Main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.fullscreen = true;
		config.width = 1024;
		config.height = 768;
		new LwjglApplication(Main.getInstance(), config);
	}
}
