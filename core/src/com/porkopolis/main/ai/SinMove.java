package com.porkopolis.main.ai;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.porkopolis.main.model.Entity;
import com.porkopolis.main.systems.WorldManager;

public class SinMove extends Behavior {

	private float speed = 2;
	private float x, y;

	private float startY;
	private float stopX;

	float timer = 99;

	public SinMove(Entity entity) {
		super(entity);
		this.startY = entity.getPosition().y;
	}

	public Behavior init(float stopX) {
		this.stopX = stopX;
		return this;

	}

	@Override
	public void update(float delta, WorldManager manager) {

		if (entity.getPosition().x <= stopX) {
			fire(delta, manager);

			if (entity.getVelocity() != Vector2.Zero) {

				entity.getVelocity().set(Vector2.Zero);
			}
		} else {
			y = MathUtils.sin(entity.getPosition().x) * speed;

			entity.getPosition().set(entity.getPosition().x, startY + y);
		}

	}

	private void fire(float delta, WorldManager manager) {
		if (timer >= entity.getAttackDelay()) {
			timer = 0;
			entity.fire(manager);

		} else {
			timer += delta;
		}

		// manager.getBulletManager().createBullet(entity.getPosition(),
		// velocity, 0);

	}

}
