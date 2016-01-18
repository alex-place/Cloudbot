package com.porkopolis.main.model;

import com.badlogic.gdx.math.Vector2;
import com.porkopolis.main.Assets;
import com.porkopolis.main.systems.SoundManager;
import com.porkopolis.main.systems.WorldManager;
import com.porkopolis.main.utils.Constants;

public class Enemy02 extends Entity {
	private Vector2 tmp, player;
	private float health = 6;
	private float maxHealth = 6;
	private long id;

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	@Override
	public void hit(float damage) {
		health -= damage;
		if (health <= 0) {
			destroy(null);
		}

		else if (health <= maxHealth / 2) {
			setReg(Assets.ships.enemy021);

		} else {
			id = SoundManager.getHurt01().play();
			SoundManager.getHurt01().setVolume(id, Constants.VOLUME);
		}

	}

	@Override
	public void destroy(WorldManager manager) {
		id = SoundManager.getDestroyed01().play();
		SoundManager.getDestroyed01().setVolume(id, Constants.VOLUME);
		super.destroy(manager);
	}

	@Override
	public void update(float delta) {
		super.update(delta);

	}

	@Override
	public void fire(WorldManager manager) {

	}

}
