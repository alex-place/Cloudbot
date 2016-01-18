package com.porkopolis.main.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.porkopolis.main.Assets;
import com.porkopolis.main.Main;
import com.porkopolis.main.systems.ScoreManager;

public class GameOverScreen implements Screen {

	Stage stage;
	Label gameOver;
	Label scoreLabel;
	Skin skin;
	TextButton retry, quit;
	private Main main;

	public GameOverScreen(Main main) {
		this.main = main;
	}

	@Override
	public void show() {

		Image mrt = new Image(Assets.bosses.mrt);
		mrt.setSize(600, 600);

		BitmapFont font = new BitmapFont(Gdx.files.internal("skin/font.fnt"));
		font.setColor(Color.BLACK);
		LabelStyle labelStyle = new LabelStyle(font, Color.BLACK);
		TextureRegionDrawable button = new TextureRegionDrawable(Assets.ui.blueButton);
		TextButtonStyle buttonStyle = new TextButtonStyle(button, button, button, font);
		buttonStyle.fontColor = Color.BLACK;

		StretchViewport viewport = new StretchViewport(600, 600);
		stage = new Stage(viewport);
		skin = Assets.skin;
		gameOver = new Label("GAME OVER", skin);
		gameOver.setColor(Color.WHITE);
		gameOver.setAlignment(Align.center);
		scoreLabel = new Label("", skin);
		scoreLabel.setColor(Color.WHITE);
		scoreLabel.setAlignment(Align.center);

		retry = new TextButton("RETRY", buttonStyle);
		retry.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				main.setScreen(new CharacterCreationScreen(main));

			}
		});
		quit = new TextButton("QUIT", buttonStyle);
		quit.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
				// System.exit(0);

			}
		});

		Table table = new Table();
		// table.debug();
		table.setFillParent(true);
		table.defaults().expand().fill(0.5f, 0);
		table.add(gameOver).center().colspan(2).row();
		table.add(scoreLabel).center().colspan(2).row();
		table.add(retry);
		table.add(quit);

		stage.addActor(mrt);
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		// scoreLabel.setText("SCORE\n" + ScoreManager.getScore());
		scoreLabel.setText("SCORE\n" + 1080);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);

		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {

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
		stage.dispose();
	}

}
