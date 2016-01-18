package com.porkopolis.main.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.porkopolis.main.Assets;
import com.porkopolis.main.systems.SoundManager;
import com.porkopolis.main.systems.WorldManager;
import com.porkopolis.main.utils.Constants;

public class Enemy01 extends Entity {
	private Vector2 tmp, player;
	private float health = 2;
	long id;

	private float fireTimer = 0;
	private float maxFireAnimation = 2;

	@Override
	public Entity init(float x, float y, float width, float height, float rotation, float attackSpeed,
			TextureRegion reg, String name) {
		dropChance = 0.05f;
		return super.init(x, y, width, height, rotation, attackSpeed, reg, name);
	}

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
		fireTimer -= delta;

		if (fireTimer >= 1) {
			setReg(Assets.ships.enemy012);
		} else if (fireTimer > 0) {
			setReg(Assets.ships.enemy011);
		} else {
			setReg(Assets.ships.enemy01);
		}

	}

	@Override
	public void fire(WorldManager manager) {
		fireTimer = maxFireAnimation;
		fireAtPlayer(manager);
	}

	public void fireStraight(WorldManager manager) {
		tmp = getPosition().cpy();
		tmp.add(getWidth() / 2, getHeight() / 2);
		manager.getBulletManager().createBullet(tmp, new Vector2(-Constants.BULLET_SPEED, 0), 10, Constants.HOSTILE);
	}

	public void fireAtPlayer(WorldManager manager) {
		tmp = getPosition().cpy();
		tmp.add(getWidth() / 2, getHeight() / 2);
		player = manager.getPlayer().getPosition().cpy().sub(tmp)
				.add(manager.getPlayer().getWidth() / 2, manager.getPlayer().getHeight() / 2).nor()
				.scl(getAttackSpeed());

		player.add(0, MathUtils.random(-2, 2));

		manager.getBulletManager().createBullet(tmp, player, 10, Constants.HOSTILE);
	}

}
