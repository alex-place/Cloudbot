package com.porkopolis.main.ai;

import com.badlogic.gdx.math.Vector2;
import com.porkopolis.main.model.Boss01;
import com.porkopolis.main.systems.WorldManager;

public class Boss02Behavior extends Behavior {

	private float stopX;
	private Vector2 velocity;
	private float timer = 0f;
	private float maxStateTime = 20f;
	private Vector2 tmp;

	private Boss01 boss;

	private STATE state;

	private enum STATE {
		ENTRY, EYE_ATTACK, HAT_ATTACK, FULL_ATTACK, OVERDRIVE, DEAD;
	}

	public Boss02Behavior(Boss01 boss) {
		super(boss);
		this.boss = boss;
		state = STATE.ENTRY;

	}

	public Behavior init(float stopX, Vector2 velocity) {
		this.stopX = stopX;
		this.velocity = velocity;
		return this;
	}

	public void update(float delta, WorldManager manager) {

		if (state == STATE.ENTRY) {
			boss.setVelocity(velocity);
			boss.setHealth(boss.getMaxHealth());
			if (boss.getPosition().x <= stopX) {
				if (!boss.getVelocity().isZero()) {
					boss.setVelocity(Vector2.Zero);
					state = STATE.EYE_ATTACK;
				}
			}
		}

		if (state == STATE.EYE_ATTACK) {
			if (timer >= maxStateTime || boss.getHealth() < (boss.getMaxHealth() * 0.8f)) {
				state = STATE.HAT_ATTACK;
				timer = 0;
			} else {
				boss.fireEyes(manager);
				timer += delta;
			}

		}

		if (state == STATE.HAT_ATTACK) {
			if (timer >= maxStateTime || boss.getHealth() < (boss.getMaxHealth() * 0.6f)) {
				state = STATE.FULL_ATTACK;
				timer = 0;
			} else {
				boss.fireHat(manager);
				timer += delta;
			}

		}

		if (state == STATE.FULL_ATTACK) {
			if (boss.getHealth() < (boss.getMaxHealth() * 0.3f)) {
				state = STATE.OVERDRIVE;
				timer = 0;
			} else {
				boss.fireEyes(manager);
				boss.fireHat(manager);
				timer += delta;
			}

		}

		if (state == STATE.OVERDRIVE) {
			boss.setEyeTimerMax(0.3f);
			boss.setHatTimerMax(0.3f);
			boss.setHatAttackSpeed(25);
			boss.setEyeAttackSpeed(25);

			if (boss.getHealth() <= 0) {
				state = STATE.DEAD;
				timer = 0;
			} else {
				boss.fireEyes(manager);
				boss.fireHat(manager);
				timer += delta;
			}
		}
		if (state == STATE.DEAD) {
			boss.destroy(null);
		}
	}
}
