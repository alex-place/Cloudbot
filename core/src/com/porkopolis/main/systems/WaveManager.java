package com.porkopolis.main.systems;

import java.util.concurrent.ConcurrentHashMap;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.porkopolis.main.model.Entity;
import com.porkopolis.main.utils.Constants;

public class WaveManager {

	private float time = 0f;
	private float maxWaveTime = 2f;
	private int difficulty = 1;
	int bossWave = 3;

	private ConcurrentHashMap<String, Entity> entities;

	private EntityFactory factory;

	private float[] lanes;

	public WaveManager(EntityFactory factory) {
		this.factory = factory;
		lanes = new float[8];
		float inc = 1.75f;
		for (int x = 0; x < lanes.length; x++) {
			lanes[x] = inc * x;
		}

	}

	public void update(float delta) {
		time += delta;

		if (time >= maxWaveTime) {
			if (difficulty == bossWave) {
				if (factory.getBoss01() != null) {
					if (factory.getBoss01().destroyed == false) {
						time = 0;
					} else {
						difficulty++;
					}
				} else {
					createWave(difficulty);
				}

			} else {
				createWave(difficulty);
				time = 0;
				difficulty++;
			}
		}

	}

	public void createSineWave(float entities) {
		for (int x = 0; x < entities; x++) {
			factory.createSinEnemy01(new Vector2(MathUtils.random(16, 20), MathUtils.random(3, 6)),
					MathUtils.random(5, 11));
		}
	}

	public void createStopWave(float entities) {

		for (int x = 0; x < entities; x++) {
			factory.createStopEnemy01(new Vector2(MathUtils.random(16, 20), MathUtils.random(3, 6)),
					MathUtils.random(5, 11));
		}
	}

	public void createWave(int index) {
		switch (index) {

		case 1:
			createStopLine(16, 6f, 3);
			createStopLine(17.2f, 7.2f, 4);
			createTankLine(18.4f, 4f, 5);
			createStopLine(20f, 8.6f, 5);

			createStopLine(26f, 8.6f, 8);
			createStopLine(28f, 6f, 8);
			createTankLine(32, 2f, 6);

			maxWaveTime = 25f;
			break;

		case 2:
			createStopLine(16, 6f, 4);
			createStopLine(17.2f, 7.2f, 5);
			createTankLine(18.4f, 4f, 4);
			createStopLine(20f, 8.6f, 6);

			createTankLine(23, 2f, 5);
			createStopLine(26f, 8.6f, 6);
			createStopLine(28f, 6f, 6);

			createStopLine(40.4f, 4f, 3);
			createStopLine(42.6f, 5f, 4);
			createStopLine(44f, 6f, 5);
			createStopLine(48f, 8f, 6);
			createTankLine(52f, 4f, 3);
			createTankLine(56f, 2f, 4);

			maxWaveTime = 40f;
			break;
		case 3:
			factory.createBoss01();
			maxWaveTime = 0f;
			break;
		case 4:
			createStopLine(16, 6f, 1);
			createStopLine(17.2f, 7.2f, 2);
			createStopLine(18.4f, 8.4f, 3);
			createStopLine(19.6f, 9.6f, 4);
			createStopLine(20.8f, 10.8f, 5);
			createStopLine(22f, 12f, 6);
			createTankLine(27f, 4f, 3);
			createTankLine(50f, 4f, 4);

			maxWaveTime = 11f;
			break;

		default:
			createStopLine(16, MathUtils.random(3, 4), MathUtils.random(3, 8));
			createTankLine(18, MathUtils.random(2, 4), MathUtils.random(3, 8));

			if (MathUtils.randomBoolean()) {
				createStopLine(20, MathUtils.random(4, 6), MathUtils.random(4, 4));

			}

			createStopLine(24, MathUtils.random(6, 8), MathUtils.random(4, 8));
			createTankLine(28, MathUtils.random(2, 4), MathUtils.random(3, 8));

			if (MathUtils.randomBoolean()) {
				createStopLine(32, MathUtils.random(2, 4), MathUtils.random(3, 4));

			}
			if (MathUtils.random() < 0.1) {
				if (factory.getBoss01() != null) {
					if (factory.getBoss01().getHealth() <= 0)
						factory.createBoss01();
				} else {
					factory.createBoss01();
					difficulty = bossWave;

				}
			}

			maxWaveTime = 10 + MathUtils.random(0, 10);

			break;
		}
	}

	public void createStopLine(float startX, float stopX, float units) {

		if (units > 8) {
			units = 8;
		}

		if (units <= 1) {
			factory.createStopEnemy01(new Vector2(startX, Constants.WORLD_HEIGHT / 2 - 0.5f), stopX);
			return;
		}

		float space = Constants.WORLD_HEIGHT / (units + 2f);

		float spaceTmp = 0f;

		float nine = 9f / 4f;

		for (int i = 1; i < units + 1; i++) {
			spaceTmp = space * i;
			factory.createStopEnemy01(new Vector2(startX, spaceTmp), stopX);

		}

	}

	public void createStopLine(int units) {
		float startX = Constants.WORLD_WIDTH;
		float stopX = Constants.WORLD_WIDTH / 2;

		createStopLine(startX, stopX, units);

	}

	public void createSineLine(float startX, float stopX, float units) {

		if (units > 8) {
			units = 8;
		}

		if (units <= 1) {
			factory.createSinEnemy01(new Vector2(startX, Constants.WORLD_HEIGHT / 2 - 0.5f), stopX);
			return;
		}

		if (units == 5) {
			float space = Constants.WORLD_HEIGHT / (units + 2f);

			float spaceTmp = 0f;

			float nine = 9f / 4f;

			for (int i = 1; i < units + 1; i++) {
				spaceTmp = space * i;
				factory.createSinEnemy01(new Vector2(startX, spaceTmp + 3), stopX);

			}
			return;
		}

		float space = Constants.WORLD_HEIGHT / (units + 2f);

		float spaceTmp = 0f;

		float nine = 9f / 4f;

		for (int i = 1; i < units + 1; i++) {
			spaceTmp = space * i;
			factory.createSinEnemy01(new Vector2(startX, spaceTmp), stopX);

		}

	}

	public void createTankLine(float startX, float stopX, float units) {

		if (units > 8) {
			units = 8;
		}

		if (units <= 1) {
			factory.createStopEnemy02(new Vector2(startX, Constants.WORLD_HEIGHT / 2 - 0.5f), stopX);
			return;
		}

		float space = Constants.WORLD_HEIGHT / (units + 2f);

		float spaceTmp = 0f;

		float nine = 9f / 4f;

		for (int i = 1; i < units + 1; i++) {
			spaceTmp = space * i;
			factory.createStopEnemy02(new Vector2(startX, spaceTmp), stopX);

		}

	}
}
