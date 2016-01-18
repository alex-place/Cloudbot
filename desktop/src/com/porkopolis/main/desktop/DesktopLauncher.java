package com.porkopolis.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.porkopolis.main.Main;

public class DesktopLauncher {
	public static void main(String[] arg) {
		// if (Assets.rebuildAtlas) {
		Settings settings = new Settings();
		settings.maxWidth = 2048;
		settings.maxHeight = 2048;
		settings.stripWhitespaceX = false;
		settings.stripWhitespaceY = false;
		settings.debug = false;
		try {
			TexturePacker.process(settings, "assets-raw", "../core/assets", "assets");
		} catch (Exception e) {
			e.printStackTrace();
		}

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 800;
		new LwjglApplication(new Main(), config);
	}

}
