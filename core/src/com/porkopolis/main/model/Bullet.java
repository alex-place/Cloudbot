package com.porkopolis.main.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.porkopolis.main.systems.WorldManager;
import com.porkopolis.main.utils.Constants;

public class Bullet extends Entity {

	private float damage;

	private float type;

	public void init(float x, float y, float width, float height, float rotation, float attackSpeed, TextureRegion reg,
			String name, float type) {
		super.init(x, y, width, height, rotation, attackSpeed, reg, name);
		this.type = type;
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
		// TODO Auto-generated method stub
		super.update(delta);
		if (getPosition().x > Constants.WORLD_WIDTH || getPosition().x < 0) {
			destroy(null);
		}

	}

}
