package com.porkopolis.main.systems;

import java.util.concurrent.ConcurrentHashMap;

import com.porkopolis.main.model.Bullet;
import com.porkopolis.main.model.Entity;
import com.porkopolis.main.model.HealthPack;
import com.porkopolis.main.model.Player;
import com.porkopolis.main.model.ShieldPack;
import com.porkopolis.main.utils.Constants;

public class CollisionManager {

	private ConcurrentHashMap<String, Entity> entities;

	public CollisionManager(ConcurrentHashMap<String, Entity> entities) {
		this.entities = entities;
	}

	public void handleCollision(Entity entity1) {
		for (Entity entity2 : entities.values()) {
			if (entity1.getName() == entity2.getName()) {
				return;
			}
			if (entity1.getBounds().overlaps(entity2.getBounds())) {

				if (entity1.getName().contains(Constants.PLAYER)) {
					if (entity2.getName().contains(Constants.HEALTH_PACK)) {
						if (entity2 instanceof HealthPack) {
							HealthPack pack = (HealthPack) entity2;
							pack.pickup();
						}
					}

				} else if (entity2.getName().contains(Constants.PLAYER)) {
					if (entity1.getName().contains(Constants.HEALTH_PACK)) {
						if (entity1 instanceof HealthPack) {
							HealthPack pack = (HealthPack) entity1;
							pack.pickup();

						}
					}

				}

				if (entity1.getName().contains(Constants.PLAYER)) {
					if (entity2.getName().contains(Constants.SHIELD_PACK)) {
						if (entity2 instanceof ShieldPack) {
							ShieldPack pack = (ShieldPack) entity2;
							pack.pickup();
						}
					}

				} else if (entity2.getName().contains(Constants.PLAYER)) {
					if (entity1.getName().contains(Constants.SHIELD_PACK)) {
						if (entity1 instanceof ShieldPack) {
							ShieldPack pack = (ShieldPack) entity1;
							pack.pickup();

						}
					}

				}

				if (entity2.getName().contains("b_")) {
					Bullet bullet = (Bullet) entity2;
					if (bullet.getType() == Constants.FRIENDLY) {

						if (entity1.getName().contains(Constants.ENEMY)) {
							if (!bullet.destroyed) {
								entity1.hit(bullet.getDamage());
								bullet.destroy(null);
							}
						}
					} else if (bullet.getType() == Constants.HOSTILE) {
						if (entity1.getName().contains(Constants.PLAYER)) {
							Player player = (Player) entity1;
							player.hit(bullet.getDamage());
							bullet.destroy(null);
						}

					}

				}
				if (entity1.getName().contains("b_")) {
					Bullet bullet = (Bullet) entity1;
					if (bullet.getType() == Constants.FRIENDLY) {

						if (entity2.getName().contains(Constants.ENEMY)) {
							if (!bullet.destroyed) {
								entity2.hit(bullet.getDamage());
								bullet.destroy(null);

							}
						}
					} else if (bullet.getType() == Constants.HOSTILE) {
						if (entity2.getName().contains(Constants.PLAYER)) {
							Player player = (Player) entity2;
							player.hit(bullet.getDamage());
							bullet.destroy(null);
						}

					}

				} else {
					// System.out.println("Entity1: " + entity1.getName()
					// + " Entity2: " + entity2.getName());
				}

			}

		}
	}

}
