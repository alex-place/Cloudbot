package com.porkopolis.main.systems;

import java.util.concurrent.ConcurrentHashMap;

import com.badlogic.gdx.math.Vector2;
import com.porkopolis.main.Assets;
import com.porkopolis.main.model.Entity;
import com.porkopolis.main.model.HealthPack;
import com.porkopolis.main.model.ShieldPack;
import com.porkopolis.main.utils.Constants;

public class PickupManager {

	float healthCount = 3;
	float shieldCount = 3;

	private ConcurrentHashMap<String, Entity> entities;
	private WorldManager manager;

	public PickupManager(ConcurrentHashMap<String, Entity> entities, WorldManager manager) {
		this.entities = entities;
		this.manager = manager;
	}

	public void createHealthPack(Vector2 position) {
		String name = Constants.PICKUP + Constants.HEALTH_PACK + healthCount;

		HealthPack healthPack = new HealthPack(manager.getPlayer(), 20);
		healthPack.init(position.x, position.y, Assets.ships.healthPack, name);
		healthPack.setAttackSpeed(10f);
		healthPack.setVelocity(new Vector2(-2, 0));
		healthPack.setWidth(0.5f);
		healthPack.setHeight(0.5f);
		entities.put(name, healthPack);

	}

	public void createShieldPack(Vector2 position) {
		String name = Constants.PICKUP + Constants.SHIELD_PACK + shieldCount;

		ShieldPack shieldPack = new ShieldPack(manager.getPlayer(), 20);
		shieldPack.init(position.x, position.y, Assets.ships.shieldPack, name);
		shieldPack.setAttackSpeed(10f);
		shieldPack.setVelocity(new Vector2(-2, 0));
		shieldPack.setWidth(0.5f);
		shieldPack.setHeight(0.5f);
		entities.put(name, shieldPack);

	}
}
