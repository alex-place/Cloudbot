package com.porkopolis.main.systems;

import java.util.concurrent.ConcurrentHashMap;

import com.badlogic.gdx.math.Vector2;
import com.porkopolis.main.Assets;
import com.porkopolis.main.ai.Boss01Behavior;
import com.porkopolis.main.ai.Boss02Behavior;
import com.porkopolis.main.ai.SinMove;
import com.porkopolis.main.ai.StopShoot;
import com.porkopolis.main.model.Boss01;
import com.porkopolis.main.model.Enemy01;
import com.porkopolis.main.model.Enemy02;
import com.porkopolis.main.model.Entity;
import com.porkopolis.main.model.HealthPack;
import com.porkopolis.main.model.ShieldPack;
import com.porkopolis.main.utils.Constants;

public class EntityFactory {

	private ConcurrentHashMap<String, Entity> entities;
	private int enemyCount = 0;
	private int maxEnemies = 100;

	private Boss01 boss01;
	private WorldManager manager;

	private float powerupCount = 0;
	private float maxPowerups = 20;

	public EntityFactory(ConcurrentHashMap<String, Entity> entities, WorldManager manager) {
		this.manager = manager;
		this.entities = entities;
	}

	public void createStopEnemy01(Vector2 position, float stopX) {
		String name = Constants.ENEMY + enemyCount;

		Entity enemy = new Enemy01().init(position.x, position.y, Assets.ships.enemy01, name);
		enemy.setBehavior(new StopShoot(enemy).init(stopX, new Vector2(-Constants.ENEMY01_VELOCITY, 0)));
		enemy.setAttackSpeed(10f);
		entities.put(name, enemy);

		if (enemyCount >= maxEnemies) {
			enemyCount = 0;
		} else {
			enemyCount++;
		}
	}

	public void createStopEnemy02(Vector2 position, float stopX) {
		String name = Constants.ENEMY + enemyCount;

		Entity enemy = new Enemy02().init(position.x, position.y, Assets.ships.enemy02, name);
		enemy.setBehavior(new StopShoot(enemy).init(stopX, new Vector2(-Constants.ENEMY01_VELOCITY, 0)));
		enemy.setAttackSpeed(10f);
		entities.put(name, enemy);

		if (enemyCount >= maxEnemies) {
			enemyCount = 0;
		} else {
			enemyCount++;
		}
	}

	public void createSinEnemy01(Vector2 position, float stopX) {
		String name = Constants.ENEMY + enemyCount;

		Entity enemy = new Enemy01().init(position.x, position.y, Assets.ships.enemy01, name);
		enemy.setVelocity(new Vector2(-Constants.ENEMY01_VELOCITY, 0));
		enemy.setBehavior(new SinMove(enemy).init(stopX));
		enemy.setAttackSpeed(10f);

		entities.put(name, enemy);

		if (enemyCount >= maxEnemies) {
			enemyCount = 0;
		} else {
			enemyCount++;
		}
	}

	public void createBoss01() {
		String name = Constants.BOSS01;

		boss01 = new Boss01().init(Constants.WORLD_WIDTH, 0, 4, 4, 0, 0.5f, Assets.bosses.gnome, name);

		boss01.setVelocity(new Vector2(-Constants.ENEMY01_VELOCITY, 0));
		boss01.setBehavior(new Boss01Behavior(boss01).init(Constants.WORLD_WIDTH - 5,
				new Vector2(-Constants.ENEMY01_VELOCITY, 0)));
		entities.put(name, boss01);
	}

	public Boss01 getBoss01() {
		return boss01;
	}

	public void createBoss02() {
		String name = Constants.BOSS02;

		boss01 = new Boss01().init(Constants.WORLD_WIDTH, 0, 4, 4, 0, 0.5f, Assets.bosses.mrt, name);

		boss01.setVelocity(new Vector2(-Constants.ENEMY01_VELOCITY, 0));
		boss01.setBehavior(new Boss02Behavior(boss01).init(Constants.WORLD_WIDTH - 5,
				new Vector2(-Constants.ENEMY01_VELOCITY, 0)));
		entities.put(name, boss01);
	}

	public Boss01 getBoss02() {
		return boss01;
	}

	public void createHealthPack(Vector2 position, float amount) {
		String name = Constants.PICKUP + Constants.HEALTH_PACK + powerupCount;

		HealthPack healthPack = new HealthPack(manager.getPlayer(), amount);
		healthPack.init(position.x, position.y, Assets.ships.healthPack, name);
		healthPack.setAttackSpeed(10f);
		healthPack.setVelocity(new Vector2(-2, 0));
		healthPack.setWidth(0.5f);
		healthPack.setHeight(0.5f);
		entities.put(name, healthPack);

		powerupCount++;
		if (powerupCount >= maxPowerups) {
			powerupCount = 0;
		}

	}

	public void createShieldPack(Vector2 position, float amount) {
		String name = Constants.PICKUP + Constants.SHIELD_PACK + powerupCount;

		ShieldPack shieldPack = new ShieldPack(manager.getPlayer(), amount);
		shieldPack.init(position.x, position.y, Assets.ships.shieldPack, name);
		shieldPack.setAttackSpeed(10f);
		shieldPack.setVelocity(new Vector2(-2, 0));
		shieldPack.setWidth(0.5f);
		shieldPack.setHeight(0.5f);

		entities.put(name, shieldPack);
		powerupCount++;
		if (powerupCount >= maxPowerups) {
			powerupCount = 0;
		}
	}

}
