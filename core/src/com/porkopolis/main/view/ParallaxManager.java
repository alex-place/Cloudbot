package com.porkopolis.main.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.porkopolis.main.Assets;
import com.porkopolis.main.utils.Constants;

public class ParallaxManager {

	float x1 = 0;
	float x2 = 16;

	float x3 = 0;
	float x4 = 16;

	float x5 = 0;
	float x6 = 16;

	float speed1 = 0.5f;

	float speed2 = 1.5f;

	float speed3 = 3;

	public ParallaxManager() {
	}

	public void render(SpriteBatch batch, float delta) {

		if (x1 <= -Constants.WORLD_WIDTH) {
			x1 = x2 + Constants.WORLD_WIDTH;
		}

		if (x2 <= -Constants.WORLD_WIDTH) {
			x2 = x1 + Constants.WORLD_WIDTH;
		}

		if (x3 <= -Constants.WORLD_WIDTH) {
			x3 = x4 + Constants.WORLD_WIDTH;
		}

		if (x4 <= -Constants.WORLD_WIDTH) {
			x4 = x3 + Constants.WORLD_WIDTH;
		}

		if (x5 <= -Constants.WORLD_WIDTH) {
			x5 = x6 + Constants.WORLD_WIDTH;
		}

		if (x6 <= -Constants.WORLD_WIDTH) {
			x6 = x5 + Constants.WORLD_WIDTH;
		}

		// batch.draw(Assets.background.bg1, x1, 0, 16, 9);
		// batch.draw(Assets.background.bg1, x2, 0, 16, 9);

		batch.draw(Assets.background.bg1, x1, 0, 16.01f, 9f);
		batch.draw(Assets.background.bg1, x2, 0, 16.01f, 9f);

		batch.draw(Assets.background.bg2, x3, 0, 16, 9);
		batch.draw(Assets.background.bg2, x4, 0, 16, 9);
		batch.draw(Assets.background.bg3, x5, 0, 16, 9);
		batch.draw(Assets.background.bg3, x6, 0, 16, 9);

		x1 -= speed1 * delta;
		x2 -= speed1 * delta;

		x3 -= speed2 * delta;
		x4 -= speed2 * delta;

		x5 -= speed3 * delta;
		x6 -= speed3 * delta;

	}

}
