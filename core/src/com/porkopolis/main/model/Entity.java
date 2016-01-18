package com.porkopolis.main.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.porkopolis.main.ai.Behavior;
import com.porkopolis.main.systems.WorldManager;
import com.porkopolis.main.utils.Constants;

public abstract class Entity {

	protected Vector2 position;
	protected Vector2 velocity;
	protected float rotation = 0f;
	protected float width = 1f;
	protected float height = 1f;
	protected TextureRegion reg;
	protected float attackSpeed = 1f;
	protected float attackDelay = 3f;
	protected Rectangle bounds;
	protected String name;
	protected Behavior behavior = null;

	public boolean destroyed = false;

	public float dropChance = 0;

	public Entity init(float x, float y, float width, float height, float rotation, float attackSpeed,
			TextureRegion reg, String name) {
		position = new Vector2(x, y);
		velocity = new Vector2();
		this.width = width;
		this.height = height;
		this.rotation = rotation;
		this.reg = reg;
		this.attackSpeed = attackSpeed;
		this.name = name;
		bounds = new Rectangle(x, y, width, height);
		return this;

	}

	public Entity init(float x, float y, TextureRegion reg, String name) {
		return init(x, y, 1f, 1f, 0f, 0.5f, reg, name);
	}

	public Entity() {
	}

	public float getHeight() {
		return height;
	}

	public Vector2 getPosition() {
		return position;
	}

	public TextureRegion getReg() {
		return reg;
	}

	public float getRotation() {
		return rotation;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public float getWidth() {
		return width;
	}

	public void move(Vector2 distance) {
		if (position.y + distance.y + height > Constants.PLAYER_MAX_Y
				|| position.y + distance.y < Constants.PLAYER_MIN_Y) {
			return;
		}

		position.add(distance);
		updateBounds();

	}

	public void setHeight(float height) {
		this.height = height;
		updateBounds();

	}

	public void setPosition(Vector2 position) {
		this.position = position;
		updateBounds();

	}

	public void setReg(TextureRegion reg) {
		this.reg = reg;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public void setWidth(float width) {
		this.width = width;
		updateBounds();
	}

	public void update(float delta) {
		if (!velocity.isZero()) {
			position.add(velocity.x * delta, velocity.y * delta);

			bounds.set(position.x, position.y, width, height);
		}

	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(float attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	protected void updateBounds() {
		bounds.set(position.x, position.y, width, height);

	}

	public String getName() {
		return name;
	}

	public abstract void hit(float damage);

	public void destroy(WorldManager manager) {
		destroyed = true;
	}

	public Behavior getBehavior() {
		return behavior;
	}

	public void setBehavior(Behavior behavior) {
		this.behavior = behavior;
	}

	public float getAttackDelay() {
		return attackDelay;
	}

	public void setAttackDelay(float attackDelay) {
		this.attackDelay = attackDelay;
	}

	public abstract void fire(WorldManager manager);

	public void dropPickup(Pickup pickup) {

	}

}
