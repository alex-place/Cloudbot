package com.porkopolis.main.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.porkopolis.main.Assets;
import com.porkopolis.main.Main;
import com.porkopolis.main.systems.SoundManager;
import com.porkopolis.main.utils.Constants;

public class CharacterCreationScreen implements Screen {

	Json json;

	Skin skin;
	Stage stage;
	Table table;

	Stack stack;

	Array<Image> heads = new Array<Image>();
	Array<Image> bodies = new Array<Image>();
	Array<Image> feets = new Array<Image>();
	Array<Image> tails = new Array<Image>();

	TextureRegion head, body, feet, tail;

	int headIndex = 0, bodyIndex = 0, feetIndex = 0, tailIndex = 0;

	private Main main;
	private Preferences prefs;
	private long id;
	private String description;
	private Label descriptionLabel;

	LabelStyle labelStyle;

	public CharacterCreationScreen(Main main) {
		this.main = main;
	}

	@Override
	public void show() {
		initArrays();

		BitmapFont font = new BitmapFont(Gdx.files.internal("skin/font.fnt"));
		font.setColor(Color.BLACK);

		labelStyle = new LabelStyle(font, Color.BLACK);
		descriptionLabel = new Label("Description derp herp \nDescription derp herp \nDescription derp herp",
				labelStyle);
		descriptionLabel.setAlignment(Align.center);

		prefs = Gdx.app.getPreferences(Constants.CHARACTER);
		headIndex = prefs.getInteger(Constants.HEAD_INDEX, 0);
		bodyIndex = prefs.getInteger(Constants.BODY_INDEX, 0);
		feetIndex = prefs.getInteger(Constants.FEET_INDEX, 0);
		tailIndex = prefs.getInteger(Constants.TAIL_INDEX, 0);

		json = new Json();

		skin = Assets.skin;
		skin.addRegions(Assets.getAtlas());
		stage = new Stage();
		table = new Table();
		table.setFillParent(true);
		table.defaults().align(Align.center).fill().expand();
		TextureRegionDrawable left = new TextureRegionDrawable(Assets.ui.leftArrow);
		left.setMinWidth(200);
		left.setMinHeight(200);
		TextureRegionDrawable right = new TextureRegionDrawable(Assets.ui.rightArrow);
		right.setMinWidth(200);
		right.setMinHeight(200);

		TextureRegionDrawable button = new TextureRegionDrawable(Assets.ui.blueButton);

		Table leftTable = new Table();
		leftTable.defaults().align(Align.center).fill(0.5f, 0.5f).expand();
		ImageButton leftHead = new ImageButton(left), leftBody = new ImageButton(left),
				leftTail = new ImageButton(left);
		leftHead.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				id = SoundManager.getSelect01().play();
				SoundManager.getHurt01().setVolume(id, Constants.VOLUME);
				headIndex--;
				stack = getStack();

			}
		});

		leftBody.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				id = SoundManager.getSelect01().play();
				SoundManager.getHurt01().setVolume(id, Constants.VOLUME);
				bodyIndex--;

				if (bodyIndex != 2) {
					feetIndex = 0;
				} else {
					feetIndex = 1;
				}
				stack = getStack();
			}
		});

		leftTail.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				id = SoundManager.getSelect01().play();
				SoundManager.getHurt01().setVolume(id, Constants.VOLUME);
				tailIndex--;
				stack = getStack();
			}
		});

		leftTable.add(leftHead).row();
		leftTable.add(leftBody).row();
		leftTable.add(leftTail).row();

		table.add(leftTable);

		Table centerTable = new Table();
		centerTable.defaults().align(Align.center).fill(1, 0.55f);// .fill().expand();

		stack = getStack();

		centerTable.add(stack).expand();

		table.add(centerTable).colspan(2);

		Table rightTable = new Table();
		ImageButton rightHead = new ImageButton(right), rightBody = new ImageButton(right),
				rightTail = new ImageButton(right);

		rightHead.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				id = SoundManager.getSelect01().play();
				SoundManager.getHurt01().setVolume(id, Constants.VOLUME);
				headIndex++;
				stack = getStack();
			}
		});

		rightBody.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				id = SoundManager.getSelect01().play();
				SoundManager.getHurt01().setVolume(id, Constants.VOLUME);
				bodyIndex++;
				if (bodyIndex != 2) {
					feetIndex = 0;
				} else {
					feetIndex = 1;
				}
				stack = getStack();
			}
		});

		rightTail.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				id = SoundManager.getSelect01().play();
				SoundManager.getHurt01().setVolume(id, Constants.VOLUME);
				tailIndex++;
				stack = getStack();
			}
		});

		rightTable.defaults().align(Align.center).fill(0.5f, 0.5f).expand();
		rightTable.add(rightHead).row();
		rightTable.add(rightBody).row();
		rightTable.add(rightTail).row();

		table.add(rightTable).row();

		table.add(descriptionLabel).center().colspan(4).row();

		TextButtonStyle buttonStyle = new TextButtonStyle(button, button, button, font);
		buttonStyle.fontColor = Color.BLACK;
		TextButton finish = new TextButton("FINISH", buttonStyle);

		finish.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				id = SoundManager.getPowerup01().play();
				SoundManager.getPowerup01().setVolume(id, Constants.VOLUME);
				prefs.putInteger(Constants.HEAD_INDEX, headIndex);
				prefs.putInteger(Constants.BODY_INDEX, bodyIndex);
				prefs.putInteger(Constants.FEET_INDEX, feetIndex);
				prefs.putInteger(Constants.TAIL_INDEX, tailIndex);
				prefs.flush();

				main.setScreen(new GameScreen(main));
			}
		});
		table.add(finish).fill(0.2f, 0.6f).colspan(4);
		Image javert = new Image(Assets.bosses.javert);
		javert.setSize(75, 75);
		javert.setPosition(MathUtils.random(0, Constants.WIDTH - 100), MathUtils.random(0, Constants.HEIGHT - 100));
		stage.addActor(javert);
		stage.addActor(table);

		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0.5f, 0.5f, 1f, 0f);

		stage.act();
		stage.draw();

		System.out.println("Head Index : " + headIndex + "Body Index: " + bodyIndex);
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

	}

	public TextureRegionDrawable toDrawable(TextureRegion reg) {
		return new TextureRegionDrawable(reg);
	}

	public Stack getStack() {
		if (stack == null) {
			stack = new Stack();
		}
		if (headIndex < 0) {
			headIndex = heads.size - 1;
		}

		if (headIndex > heads.size - 1) {
			headIndex = 0;
		}

		if (bodyIndex < 0) {
			bodyIndex = bodies.size - 1;
		}

		if (bodyIndex > bodies.size - 1) {
			bodyIndex = 0;
		}

		if (feetIndex < 0) {
			feetIndex = feets.size - 1;
		}

		if (feetIndex > feets.size - 1) {
			feetIndex = 0;
		}

		if (tailIndex < 0) {
			tailIndex = tails.size - 1;
		}

		if (tailIndex > tails.size - 1) {
			tailIndex = 0;
		}

		stack.clear();
		stack.add(tails.get(tailIndex));
		stack.add(bodies.get(bodyIndex));
		stack.add(feets.get(feetIndex));
		stack.add(heads.get(headIndex));

		updateDescription(tailIndex, bodyIndex, feetIndex, headIndex);

		return stack;

	}

	private void updateDescription(int tailIndex, int bodyIndex, int feetIndex, int headIndex) {
		description = new String(" ");

		switch (headIndex) {
		case 0:
			description += "A classic meme loved by all\n";
			break;
		case 1:
			description += "The life of any party\n";
			break;
		case 2:
			description += "A legendary pepe\n";
			break;
		case 3:
			description += "The best friend a man could have\n";
			break;
		}

		switch (bodyIndex) {
		case 0:
			description += "with a heart of strawberry\n";
			break;
		case 1:
			description += "with a heart of wildberries\n";
			break;
		case 2:
			description += "charged with passion\n";
			break;

		}

		switch (tailIndex) {
		case 0:
			description += "flying on the majesty of a rainbow.\n";
			break;
		case 1:
			description += "riding the lightning through space.\n";
			break;
		}

		descriptionLabel.setText(description);

	}

	private void initArrays() {
		heads.addAll(new Image(Assets.character.head01), new Image(Assets.character.head02),
				new Image(Assets.character.head03), new Image(Assets.character.head04));

		bodies.addAll(new Image(Assets.character.body01), new Image(Assets.character.body02),
				new Image(Assets.character.body03));

		feets.addAll(new Image(Assets.character.feet01), new Image(Assets.character.feet04));

		tails.addAll(new Image(Assets.character.tail01), new Image(Assets.character.tail03));
	}

}
