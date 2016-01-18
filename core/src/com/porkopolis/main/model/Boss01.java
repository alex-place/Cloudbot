package com.porkopolis.main.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.porkopolis.main.systems.SoundManager;
import com.porkopolis.main.systems.WorldManager;
import com.porkopolis.main.utils.Constants;

public class Boss01 extends Entity {
	private float health = 640;
	private float maxHealth = 640;

	private Vector2 hatTip = new Vector2(2.3f, 4f);
	private Vector2 leftEye = new Vector2(1.1f, 1.7f);
	private Vector2 rightEye = new Vector2(1.8f, 1.6f);

	private float hatTimer = 0f;
	private boolean canFireHat = false;
	private float hatTimerMax = 0.4f;

	private float eyeTimer = 0f;
	private boolean canFireEyes = false;
	private float eyeTimerMax = 0.5f;

	private Vector2 player;
	private Vector2 tmp;

	private float eyeAttackSpeed = 15;
	private float hatAttackSpeed = 17;

	private long id;

	public Boss01() {

	}

	@Override
	public Boss01 init(float x, float y, float width, float height, float rotation, float attackSpeed,
			TextureRegion reg, String name) {
		super.init(x, y, width, height, rotation, attackSpeed, reg, name);
		return this;
	}

	@Override
	public void hit(float damage) {
		health -= damage * 4;

		id = SoundManager.getHurt01().play();
		SoundManager.getHurt01().setVolume(id, Constants.VOLUME);

		if (health <= 0) {
			destroy(null);
		}
	}

	@Override
	public void destroy(WorldManager manager) {
		super.destroy(manager);
	}

	@Override
	public void update(float delta) {
		if (!velocity.isZero()) {
			position.add(velocity.x * delta, velocity.y * delta);
			bounds.set(position.x + 1, position.y, width - 1, height);
		}

		if (hatTimer >= hatTimerMax) {
			canFireHat = true;
			hatTimer = 0;
		} else {
			hatTimer += delta;
			canFireHat = false;

		}

		if (eyeTimer >= eyeTimerMax) {
			canFireEyes = true;
			eyeTimer = 0;
		} else {
			eyeTimer += delta;
			canFireEyes = false;

		}
	}

	@Override
	public void fire(WorldManager manager) {
		fireHat(manager);
		fireLeftEye(manager);
		fireRightEye(manager);

	}

	public void fireHat(WorldManager manager) {
		if (canFireHat) {
			tmp = getPosition().cpy();
			tmp.add(hatTip);
			player = manager.getPlayer().getPosition().cpy().sub(tmp)
					.add(manager.getPlayer().getWidth() / 2, manager.getPlayer().getHeight() / 2).nor()
					.scl(hatAttackSpeed);
			manager.getBulletManager().createBullet(tmp, player, 5, Constants.HOSTILE);
		}

	}

	public void fireEyes(WorldManager manager) {
		if (canFireEyes) {
			fireLeftEye(manager);
			fireRightEye(manager);
		}
	}

	private void fireLeftEye(WorldManager manager) {
		tmp = getPosition().cpy();
		tmp.add(leftEye);
		player = manager.getPlayer().getPosition().cpy().sub(tmp)
				.add(manager.getPlayer().getWidth() / 2, manager.getPlayer().getHeight() / 2).nor().scl(eyeAttackSpeed);
		manager.getBulletManager().createBullet(tmp, player, 5, Constants.HOSTILE);

	}

	private void fireRightEye(WorldManager manager) {
		tmp = getPosition().cpy();
		tmp.add(rightEye);
		player = manager.getPlayer().getPosition().cpy().sub(tmp)
				.add(manager.getPlayer().getWidth() / 2, manager.getPlayer().getHeight() / 2).nor().scl(eyeAttackSpeed);
		manager.getBulletManager().createBullet(tmp, player, 5, Constants.HOSTILE);
	}

	public float getHealth() {
		return health;

	}

	public float getMaxHealth() {
		return maxHealth;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public void setHatTimerMax(float hatTimer) {
		this.hatTimer = hatTimer;
	}

	public void setEyeTimerMax(float eyeTimerMax) {
		this.eyeTimerMax = eyeTimerMax;
	}

	public void setEyeAttackSpeed(float eyeAttackSpeed) {
		this.eyeAttackSpeed = eyeAttackSpeed;
	}

	public void setHatAttackSpeed(float hatAttackSpeed) {
		this.hatAttackSpeed = hatAttackSpeed;
	}

}
