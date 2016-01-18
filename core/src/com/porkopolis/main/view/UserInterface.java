package com.porkopolis.main.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.porkopolis.main.Assets;
import com.porkopolis.main.systems.ScoreManager;
import com.porkopolis.main.systems.WorldManager;
import com.porkopolis.main.utils.Constants;

public class UserInterface {

	private Stage stage;
	private Table table;
	private Skin skin;

	private float fps = 0;
	private Label fpsLabel;

	private boolean debug = false;
	private WorldManager manager;

	private float offsetX = Constants.WIDTH - 275;
	private float offsetY = Constants.HEIGHT - 100;

	BitmapFont font;

	Image shieldBarLeft;
	Image shieldBarMid;
	Image shieldBarRight;

	Image healthBarLeft;
	Image healthBarMid;
	Image healthBarRight;

	Image bossBarLeft;
	Image bossBarMid;
	Image bossBarRight;
	Label bossName;

	Label score;

	public UserInterface(WorldManager manager) {
		this.manager = manager;
	}

	public UserInterface init() {
		font = new BitmapFont(Gdx.files.internal("skin/font.fnt"));
		LabelStyle style = new LabelStyle(font, Color.WHITE);
		skin = Assets.skin;
		skin.addRegions(Assets.getAtlas());
		StretchViewport viewport = new StretchViewport(Constants.WIDTH, Constants.HEIGHT);
		stage = new Stage(viewport);
		table = new Table();
		table.setFillParent(true);
		// table.debug();
		// table.setDebug(true);
		table.defaults();

		// Begin filling table

		if (debug) {
			fpsLabel = new Label("Fps: " + fps, skin);

			table.add(fpsLabel).align(Align.topLeft);
		}

		score = new Label("", style);
		score.setPosition(0, Constants.HEIGHT - 15f);

		Image metalPanel = new Image(Assets.ui.metalPanel);
		metalPanel.setPosition(offsetX, offsetY);
		metalPanel.setWidth(275);

		Image head = new Image(manager.getPlayerHead());
		head.setPosition(offsetX + -90, offsetY + 20);
		head.setSize(175, 75);

		shieldBarLeft = new Image(Assets.ui.blueLeft);
		shieldBarLeft.setPosition(offsetX + 110, offsetY + 60);

		shieldBarMid = new Image(Assets.ui.blueMiddle);
		shieldBarMid.setPosition(offsetX + 115, offsetY + 60);
		shieldBarMid.setWidth(120);

		shieldBarRight = new Image(Assets.ui.blueRight);
		shieldBarRight.setPosition(offsetX + 235, offsetY + 60);

		healthBarLeft = new Image(Assets.ui.greenLeft);
		healthBarLeft.setPosition(offsetX + 110, offsetY + 20);

		healthBarMid = new Image(Assets.ui.greenMiddle);
		healthBarMid.setPosition(offsetX + 115, offsetY + 20);
		healthBarMid.setWidth(120);

		healthBarRight = new Image(Assets.ui.greenRight);
		healthBarRight.setPosition(offsetX + 235, offsetY + 20);

		Group group = new Group();
		group.addActor(metalPanel);
		group.addActor(head);
		group.addActor(shieldBarLeft);
		group.addActor(shieldBarMid);
		group.addActor(shieldBarRight);
		group.addActor(healthBarLeft);
		group.addActor(healthBarMid);
		group.addActor(healthBarRight);
		group.addActor(score);

		table.add(group).expand().bottom().left();

		bossBarLeft = new Image(Assets.ui.redLeft);
		bossBarLeft.setPosition(320, 780);

		bossBarMid = new Image(Assets.ui.redMiddle);
		bossBarMid.setPosition(325, 780);
		bossBarMid.setWidth(640);

		bossBarRight = new Image(Assets.ui.redRight);
		bossBarRight.setPosition(965, 780);

		bossName = new Label("Space Gnome", style);
		bossName.setPosition(615, 755);

		Group group2 = new Group();
		group.addActor(bossBarLeft);
		group.addActor(bossBarMid);
		group.addActor(bossBarRight);
		group.addActor(bossName);

		table.add(group2);

		// End filling table

		stage.addActor(table);
		// stage.setDebugAll(true);

		// table.setDebug(true);

		return this;
	}

	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	public void render(float delta) {
		update();
		if (debug) {
			fps = Gdx.graphics.getFramesPerSecond();
			fpsLabel.setText("Fps: " + fps);
		}
		stage.act(delta);

		stage.draw();
	}

	private void update() {

		if (manager.getPlayer().getHealth() <= 0) {
			healthBarLeft.setVisible(false);
			healthBarMid.setVisible(false);
			healthBarRight.setVisible(false);
			gameOver();
		} else {

			if (manager.getPlayer().getShield() <= 0) {
				shieldBarLeft.setVisible(false);
				shieldBarMid.setVisible(false);
				shieldBarRight.setVisible(false);
			} else {
				shieldBarLeft.setVisible(true);
				shieldBarMid.setVisible(true);
				shieldBarRight.setVisible(true);

				shieldBarMid.setWidth(manager.getPlayer().getShield());
				shieldBarRight.setPosition(offsetX + 115 + manager.getPlayer().getShield(), offsetY + 60);
			}

			healthBarMid.setWidth(manager.getPlayer().getHealth());
			healthBarRight.setPosition(offsetX + 115 + manager.getPlayer().getHealth(), offsetY + 20);
		}

		if (manager.getFactory().getBoss01() == null) {
			bossBarLeft.setVisible(false);
			bossBarMid.setVisible(false);
			bossBarRight.setVisible(false);
			bossName.setVisible(false);
		} else {
			if (manager.getFactory().getBoss01().getHealth() <= 0) {
				bossBarLeft.setVisible(false);
				bossBarMid.setVisible(false);
				bossBarRight.setVisible(false);
				bossName.setVisible(false);

			} else {
				bossBarLeft.setVisible(true);
				bossBarMid.setVisible(true);
				bossBarRight.setVisible(true);
				bossName.setVisible(true);

				bossBarMid.setWidth(manager.getFactory().getBoss01().getHealth());
				bossBarRight.setPosition(325 + manager.getFactory().getBoss01().getHealth(), 780);

			}
		}

		if (manager.getFactory().getBoss02() == null) {
			bossBarLeft.setVisible(false);
			bossBarMid.setVisible(false);
			bossBarRight.setVisible(false);
			bossName.setVisible(false);
		} else {
			if (manager.getFactory().getBoss02().getHealth() <= 0) {
				bossBarLeft.setVisible(false);
				bossBarMid.setVisible(false);
				bossBarRight.setVisible(false);
				bossName.setVisible(false);

			} else {
				bossBarLeft.setVisible(true);
				bossBarMid.setVisible(true);
				bossBarRight.setVisible(true);
				bossName.setVisible(true);

				bossBarMid.setWidth(manager.getFactory().getBoss02().getHealth());
				bossBarRight.setPosition(325 + manager.getFactory().getBoss02().getHealth(), 780);

			}
		}

		score.setText("" + ScoreManager.getScore());
		System.out.println(ScoreManager.getScore());
	}

	private void gameOver() {

	}

	public void dispose() {
		stage.dispose();
	}

	public Stage getStage() {
		return stage;
	}

}
