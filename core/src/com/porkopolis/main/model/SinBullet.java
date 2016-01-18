package com.porkopolis.main.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.porkopolis.main.systems.WorldManager;
import com.porkopolis.main.utils.Constants;

public class SinBullet extends Bullet {

	private float damage;

	private float type;
	float y;
	float speed = 5;

	boolean top = false;

	public void init(float x, float y, float width, float height, float rotation, float attackSpeed, TextureRegion reg,
			String name, float type) {
		super.init(x, y, width, height, rotation, attackSpeed, reg, name);
		this.type = type;

		if (getPosition().y >= 4.5) {
			top = true;
		} else {
			top = false;
		}

	}

	public void setTop(boolean top) {
		this.top = top;
	}

	public float getDamage() {

		if (damage == 0f)
			damage = 1f;

		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	@Override
	public void hit(float damage) {
	}

	public float getType() {
		return type;
	}

	@Override
	public void fire(WorldManager manager) {
		return;
	}

	@Override
	public void update(float delta) {

		if (top == false) {
			y = -MathUtils.sin(getPosition().x) * speed / 2;

		}
		if (top == true) {
			y = MathUtils.sin(getPosition().x) * speed / 2;

		}
		if (!velocity.isZero()) {
			position.add(velocity.x / 2 * delta, y * delta);

			bounds.set(position.x, position.y, width, height);
		}

		if (getPosition().x > Constants.WORLD_WIDTH || getPosition().x < 0) {
			destroy(null);
		}

	}
}
