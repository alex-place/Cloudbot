package com.porkopolis.main.ai;

import com.badlogic.gdx.math.Vector2;
import com.porkopolis.main.model.Entity;
import com.porkopolis.main.systems.WorldManager;
import com.porkopolis.main.utils.Constants;

public class StopShoot extends Behavior {

	private float stopX;
	private Vector2 velocity;
	private float timer = 99;
	private Vector2 tmp;

	public StopShoot(Entity entity) {
		super(entity);
	}

	public Behavior init(float stopX, Vector2 velocity) {
		this.stopX = stopX;
		this.velocity = velocity;
		return this;
	}

	public void update(float delta, WorldManager manager) {
		if (entity.getPosition().x <= stopX) {
			fire(delta, manager);
			if (!entity.getVelocity().isZero()) {
				entity.setVelocity(Vector2.Zero);
			}
		} else {
			if (entity.getVelocity() != velocity) {
				entity.setVelocity(velocity);
			}
		}

	}

	private void fire(float delta, WorldManager manager) {
		if (timer >= entity.getAttackDelay()) {
			timer = 0;
			entity.fire(manager);

		} else {
			timer += delta;
		}

	}

}
