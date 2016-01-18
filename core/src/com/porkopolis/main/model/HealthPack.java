package com.porkopolis.main.model;

import com.porkopolis.main.systems.SoundManager;
import com.porkopolis.main.systems.WorldManager;
import com.porkopolis.main.utils.Constants;

public class HealthPack extends Pickup {

	private Player player;
	private float amount;
	private long id;

	public HealthPack(Player player, float amount) {
		this.player = player;
		this.amount = amount;
	}

	@Override
	public void hit(float damage) {
	}

	@Override
	public void fire(WorldManager manager) {
	}

	public void pickup() {
		if (player.getHealth() + amount > 120) {
			player.setHealth(120);
		} else {
			player.setHealth(player.getHealth() + amount);
		}

		id = SoundManager.getPowerup01().play();
		SoundManager.getPowerup01().setVolume(id, Constants.VOLUME);
		destroy(null);
	}

	@Override
	public void update(float delta) {

		super.update(delta);
	}

}
