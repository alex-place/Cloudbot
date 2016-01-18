package com.porkopolis.main.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.porkopolis.main.systems.FiringManager;
import com.porkopolis.main.systems.WorldManager;
import com.porkopolis.main.utils.Constants;

public class Player extends Entity {
	private float speed;

	private TextureRegion head, body, feet, tail;

	private Animation tailAnimation, feetAnimation;

	private float health = 120f;
	private float shield = 120f;

	private float shieldTimer = 0f;
	private float maxShieldTime = 3f;
	private float shieldStep = 0f;
	private float shieldRegen = 5;

	private float damage = 1f;

	private boolean invincible = false;
	private boolean overdrive = false;

	FiringManager fire;

	private int headIndex;

	private int bodyIndex;

	private int feetIndex;

	private int tailIndex;

	private Preferences prefs;

	public Player() {
	}

	public void init(float x, float y, float speed, String name, TextureRegion head, TextureRegion body,
			Animation feetAnimation, Animation tailAnimation) {
		this.speed = speed;
		this.head = head;
		this.body = body;
		this.feet = feetAnimation.getKeyFrames()[0];
		this.tail = tailAnimation.getKeyFrames()[0];

		this.tailAnimation = tailAnimation;
		this.feetAnimation = feetAnimation;
		prefs = Gdx.app.getPreferences(Constants.CHARACTER);
		headIndex = prefs.getInteger(Constants.HEAD_INDEX, 0);
		bodyIndex = prefs.getInteger(Constants.BODY_INDEX, 0);
		feetIndex = prefs.getInteger(Constants.FEET_INDEX, 0);
		tailIndex = prefs.getInteger(Constants.TAIL_INDEX, 0);
		fire = new FiringManager(this);

		super.init(x, y, null, name);

	}

	public void initCharacterTraits() {
		switch (headIndex) {
		case 0:
			setDamage(getDamage() * 2f);
			break;
		case 1:
			setAttackDelay(getAttackDelay() / 2f);
			break;
		case 2:
			break;
		case 3:
			break;
		}
	}

	public TextureRegion getHead() {
		return head;
	}

	public void setHead(TextureRegion head) {
		this.head = head;
	}

	public TextureRegion getBody() {
		return body;
	}

	public void setBody(TextureRegion body) {
		this.body = body;
	}

	public TextureRegion getFeet() {
		return feet;
	}

	public void setFeet(TextureRegion feet) {
		this.feet = feet;
	}

	public TextureRegion getTail() {
		return tail;
	}

	public void setTail(TextureRegion tail) {
		this.tail = tail;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public void move(Vector2 distance) {
		if (health > 0) {
			super.move(distance);
		}
	}

	@Override
	public void hit(float damage) {
		if (!invincible) {
			shieldTimer = 0;

			if (shield > 0) {
				shield -= damage * 2;
			} else {

				health -= damage;

				if (health <= 0) {
					destroy(null);
				}
			}
		}
	}

	@Override
	public void fire(WorldManager manager) {
		switch (headIndex) {
		case 0:
			fire.straightFire(manager);
			break;
		case 1:
			fire.straightFire(manager);
			break;
		case 2:
			fire.doubleSinFire(manager);
			break;
		case 3:
			fire.doubleStraightFire(manager);
			break;
		}
	}

	@Override
	public void destroy(WorldManager manager) {
		super.destroy(manager);
	}

	public Animation getTailAnimation() {
		return tailAnimation;
	}

	public Animation getFeetAnimation() {
		return feetAnimation;
	}

	public float getHealth() {
		return health;
	}

	public float getShield() {
		return shield;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		if (shield < 120) {
			shieldTimer += delta;
			if (shieldTimer >= maxShieldTime) {
				shieldStep += delta;
				if (shieldStep >= 1) {
					shield += shieldRegen;
					shieldStep = 0;
				}
			}
		}
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public void setShield(float shield) {
		this.shield = shield;
	}

	public boolean isOverdrive() {
		return overdrive;
	}

	public void setOverdrive(boolean overdrive) {
		this.overdrive = overdrive;
	}
}
