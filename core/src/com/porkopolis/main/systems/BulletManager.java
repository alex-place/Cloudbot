package com.porkopolis.main.systems;

import java.util.concurrent.ConcurrentHashMap;

import com.badlogic.gdx.math.Vector2;
import com.porkopolis.main.Assets;
import com.porkopolis.main.model.Bullet;
import com.porkopolis.main.model.Entity;
import com.porkopolis.main.model.SinBullet;
import com.porkopolis.main.utils.Constants;

public class BulletManager {

	private int bulletCount = 0;
	private int maxBullets = 500;
	private ConcurrentHashMap<String, Entity> entities;

	private float enemyBulletWidth = 0.3f;
	private float enemyBulletHeight = 0.3f;

	private float playerBulletSize = 0.5f;

	public BulletManager(ConcurrentHashMap<String, Entity> entities) {
		this.entities = entities;
	}

	public void createBullet(Vector2 position, Vector2 velocity, float damage,
			float type) {

		if (bulletCount >= maxBullets) {
			bulletCount = 0;
		}

		if (type == Constants.HOSTILE) {
			Bullet b = new Bullet();
			b.init(position.x, position.y, enemyBulletWidth, enemyBulletHeight,
					0, 0f, Assets.bullets.red01, "b_" + bulletCount, type);
			b.getPosition().sub(b.getWidth() / 2f, b.getHeight() / 2f);
			b.setVelocity(velocity);
			b.setDamage(damage);
			entities.put("b_" + bulletCount++, b);

		} else if (type == Constants.FRIENDLY) {
			Bullet b = new Bullet();
			position.sub(playerBulletSize / 2, playerBulletSize / 2f);
			b.init(position.x, position.y, playerBulletSize, playerBulletSize,
					0, 0, Assets.bullets.getPlayerBullet(), "b_" + bulletCount,
					type);
			// b.setVelocity(new Vector2(Constants.BULLET_SPEED, 0));
			b.setVelocity(velocity);
			b.setDamage(damage);
			entities.put("b_" + bulletCount++, b);

		} else {
			System.out.println("Unexpected bullet type " + type);
		}

	}

	public void createSinBullet(Vector2 position, Vector2 velocity,
			float rotation, float type, boolean top) {

		if (bulletCount >= maxBullets) {
			bulletCount = 0;
		}

		SinBullet b = new SinBullet();
		position.sub(playerBulletSize / 2, playerBulletSize / 2f);
		b.init(position.x, position.y, playerBulletSize, playerBulletSize,
				rotation, 0, Assets.bullets.getPlayerBullet(), "b_"
						+ bulletCount, type);
		b.setVelocity(velocity);
		b.setTop(top);
		entities.put("b_" + bulletCount++, b);

	}

	public void createSinBullet(Vector2 position, Vector2 velocity,
			float rotation, float type) {

		if (bulletCount >= maxBullets) {
			bulletCount = 0;
		}

		SinBullet b = new SinBullet();
		position.sub(playerBulletSize / 2, playerBulletSize / 2f);
		b.init(position.x, position.y, playerBulletSize, playerBulletSize,
				rotation, 0, Assets.bullets.getPlayerBullet(), "b_"
						+ bulletCount, type);
		b.setVelocity(velocity);
		entities.put("b_" + bulletCount++, b);

	}

}
