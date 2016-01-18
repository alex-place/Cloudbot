package com.porkopolis.main.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	private static Sound select01;
	private static Sound destroyed01;
	private static Sound hurt01;
	private static Sound shoot01;
	private static Sound powerup01;

	public SoundManager init() {

		select01 = Gdx.audio.newSound(Gdx.files.internal("audio/blipselect01.wav"));

		destroyed01 = Gdx.audio.newSound(Gdx.files.internal("audio/hitdestroyed01.wav"));

		hurt01 = Gdx.audio.newSound(Gdx.files.internal("audio/hithurt01.wav"));

		shoot01 = Gdx.audio.newSound(Gdx.files.internal("audio/lasershoot01.wav"));

		powerup01 = Gdx.audio.newSound(Gdx.files.internal("audio/powerup01.wav"));

		return this;
	}

	public static Sound getSelect01() {
		return select01;
	}

	public static Sound getDestroyed01() {
		return destroyed01;
	}

	public static Sound getHurt01() {
		return hurt01;
	}

	public static Sound getShoot01() {
		return shoot01;
	}

	public static Sound getPowerup01() {
		return powerup01;
	}

	public SoundManager() {
	}

	public static void dispose() {
		select01.dispose();
		destroyed01.dispose();
		hurt01.dispose();
		shoot01.dispose();
		powerup01.dispose();
	}

}
