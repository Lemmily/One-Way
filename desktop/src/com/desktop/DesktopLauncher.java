package com.desktop;

import com.LibGdxUtils;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "<3<3<3<3<3";
		new LwjglApplication(new LibGdxUtils(), config);
	}
}
