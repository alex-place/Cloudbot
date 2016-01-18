package com.porkopolis.main;

import com.badlogic.gdx.Game;
import com.porkopolis.main.systems.SoundManager;
import com.porkopolis.main.view.CharacterCreationScreen;
import com.porkopolis.main.view.GameOverScreen;

public class Main extends Game {

	@Override
	public void create() {
		Assets.load();
		Assets.create();
		new SoundManager().init();
		setScreen(new CharacterCreationScreen(this));
	}

}
