package com.porkopolis.main.ai;

import com.porkopolis.main.model.Entity;
import com.porkopolis.main.systems.WorldManager;

public abstract class Behavior {

	Entity entity;

	public Behavior(Entity entity) {
		this.entity = entity;
	}

	public abstract void update(float delta, WorldManager worldManager);

}
