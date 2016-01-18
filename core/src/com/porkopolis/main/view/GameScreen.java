package com.porkopolis.main.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.porkopolis.main.Main;
import com.porkopolis.main.controller.DesktopInputHandler;
import com.porkopolis.main.systems.WorldManager;
import com.porkopolis.main.utils.Constants;

public class GameScreen implements Screen {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Box2DDebugRenderer renderer;
	private InputMultiplexer input;
	private DesktopInputHandler desktop;

	private WorldManager manager;
	private Main main;

	public GameScreen(Main main) {
		this.main = main;
	}

	@Override
	public void show() {
		manager = new WorldManager(main);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
		camera.translate(0, 0);
		batch = new SpriteBatch(100);
		renderer = new Box2DDebugRenderer();
		renderer.setDrawBodies(true);

		input = new InputMultiplexer();
		desktop = new DesktopInputHandler(manager, camera);
		input.addProcessor(desktop);
		Gdx.input.setInputProcessor(input);

	}

	@Override
	public void render(float delta) {
		desktop.update(delta);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);

		camera.update();

		batch.setProjectionMatrix(camera.combined);

		manager.render(batch, delta);

	}

	@Override
	public void resize(int width, int height) {
		manager.resize(width, height);

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		manager.dispose();

	}
}
