package com.porkopolis.main.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.porkopolis.main.model.Player;
import com.porkopolis.main.systems.SoundManager;
import com.porkopolis.main.systems.WorldManager;
import com.porkopolis.main.utils.Constants;
import com.porkopolis.main.view.CharacterCreationScreen;

public class DesktopInputHandler implements InputProcessor {

	private Player player;
	private OrthographicCamera camera;
	private float zoom = 1;
	private WorldManager manager;
	private float timer = 0;

	float x, y;
	Vector2 tmp;

	public DesktopInputHandler(WorldManager manager, OrthographicCamera camera) {
		this.player = (Player) manager.getEntity(Constants.PLAYER);
		this.camera = camera;
		zoom = camera.zoom;
		this.manager = manager;

	}

	/** For polling */
	public void update(float delta) {

		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
			player.setOverdrive(true);
		} else {
			player.setOverdrive(false);

		}

		if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
			player.move(new Vector2(0, player.getSpeed() * delta));
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			player.move(new Vector2(0, -player.getSpeed() * delta));

		}

		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			SoundManager.getHurt01().play();
		}

		timer += delta;

		if (player.isOverdrive()) {
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				if (timer >= player.getAttackDelay() / 2) {
					player.fire(manager);
					player.hit(1);
					timer = 0;
				}

			}
		} else {
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				if (timer >= player.getAttackDelay()) {
					player.fire(manager);
					timer = 0;
				}

			}
		}

	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.SPACE:

			break;
		default:
			break;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		switch (keycode) {
		case Keys.SPACE:
			break;
		case Keys.F1:
			manager.getWaveManager().createStopWave(4);
			break;
		case Keys.BACKSPACE:
			manager.pause();
			break;
		case Keys.ESCAPE:
			manager.setScreen(new CharacterCreationScreen(manager.getMain()));
		default:
			break;
		}

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if (amount > 0) {
			zoom += 0.1f;
		}

		// Zoom in
		if (amount < 0) {
			zoom -= 0.1f;
		}
		camera.zoom = zoom;
		camera.update();

		return false;
	}

}
