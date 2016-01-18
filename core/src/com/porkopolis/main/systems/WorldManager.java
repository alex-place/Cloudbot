package com.porkopolis.main.systems;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.porkopolis.main.Main;
import com.porkopolis.main.model.Boss01;
import com.porkopolis.main.model.Enemy01;
import com.porkopolis.main.model.Enemy02;
import com.porkopolis.main.model.Entity;
import com.porkopolis.main.model.Player;
import com.porkopolis.main.utils.Constants;
import com.porkopolis.main.view.GameOverScreen;
import com.porkopolis.main.view.ParallaxManager;
import com.porkopolis.main.view.UserInterface;

public class WorldManager {

	private ParallaxManager parallax;
	private CollisionManager collision;
	private BulletManager bullet;
	private EntityFactory factory;
	private UserInterface ui;
	private WaveManager wave;
	private AnimationManager animation;
	private PickupManager pickups;

	private ConcurrentHashMap<String, Entity> entities = new ConcurrentHashMap<String, Entity>();
	private Player player;

	private ShapeRenderer renderer;
	private float stateTime = 0;
	private Preferences prefs;
	private Main main;
	private boolean paused = false;

	public WorldManager(Main main) {
		this.main = main;
		prefs = Gdx.app.getPreferences(Constants.CHARACTER);
		// x, y, speed, rotation

		parallax = new ParallaxManager();
		collision = new CollisionManager(entities);
		bullet = new BulletManager(entities);
		factory = new EntityFactory(entities, this);
		wave = new WaveManager(factory);
		animation = new AnimationManager().init();
		pickups = new PickupManager(entities, this);

		renderer = new ShapeRenderer();

		initPlayer();
		ui = new UserInterface(this).init();

	}

	private void initPlayer() {
		player = new Player();

		player.init(0, 4.5f, 10, Constants.PLAYER, animation.getHead(prefs.getInteger(Constants.HEAD_INDEX)),
				animation.getBody(prefs.getInteger(Constants.BODY_INDEX)),
				animation.getFeet(prefs.getInteger(Constants.FEET_INDEX)),
				animation.getTail(prefs.getInteger(Constants.TAIL_INDEX)));
		player.setWidth(1.75f);
		player.setAttackSpeed(22);
		player.setAttackDelay(0.375f);
		player.initCharacterTraits();
		entities.put(Constants.PLAYER, player);
	}

	public void render(SpriteBatch batch, float delta) {
		if (!paused) {
			update(delta);
		}
		batch.begin();

		parallax.render(batch, delta);
		drawEntities(batch);

		if (Constants.DEBUG) {
			// drawDebug(batch, delta);
		}
		batch.end();

		ui.render(delta);

	}

	private void drawDebug(SpriteBatch batch, float delta) {
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setAutoShapeType(true);

		renderer.begin();
		renderer.set(ShapeType.Line);

		for (Entity entity : entities.values()) {
			renderer.rect(entity.getBounds().getX(), entity.getBounds().getY(), entity.getBounds().getWidth(),
					entity.getBounds().getHeight());

		}

		renderer.end();
	}

	private void update(float delta) {

		wave.update(delta);

		Iterator<Entity> it = entities.values().iterator();
		Entity entity;

		while (it.hasNext()) {
			entity = it.next();
			if (entity.destroyed) {
				if (entity instanceof Player) {
					main.setScreen(new GameOverScreen(main));
				}

				if (entity instanceof Enemy01 || entity instanceof Enemy02) {
					ScoreManager.addScore(5);
				}

				if (entity instanceof Boss01) {
					ScoreManager.addScore(100);
				}

				if (entity.dropChance > 0) {
					if (MathUtils.random() < entity.dropChance) {
						if (MathUtils.randomBoolean()) {
							factory.createHealthPack(entity.getPosition(), 20);
						} else {
							factory.createShieldPack(entity.getPosition(), 40);

						}
					}
				}

				it.remove();
			} else {
				entity.update(delta);

				if (entity.getBehavior() != null) {
					entity.getBehavior().update(delta, this);
				}

				collision.handleCollision(entity);
			}
		}

		ScoreManager.update(delta);

	}

	public void drawEntities(SpriteBatch batch) {
		// for (Entity entity : entities.values()) {
		//
		// }

		Iterator<Entity> it = entities.values().iterator();
		Entity entity;

		while (it.hasNext()) {
			entity = it.next();

			if (entity instanceof Player) {
				player = (Player) entity;
				drawPlayer(player, batch);

			} else {
				batch.draw(entity.getReg(), entity.getPosition().x, entity.getPosition().y, 1f, 1f, entity.getWidth(),
						entity.getHeight(), 1, 1, entity.getRotation());
			}
		}
	}

	private void drawPlayer(Player player, SpriteBatch batch) {
		stateTime += Gdx.graphics.getDeltaTime();

		player.setTail(player.getTailAnimation().getKeyFrame(stateTime, true));
		player.setFeet(player.getFeetAnimation().getKeyFrame(stateTime, true));

		batch.draw(player.getTail(), player.getPosition().x, player.getPosition().y, 1f, 1f, player.getWidth(),
				player.getHeight(), 1, 1, player.getRotation());

		batch.draw(player.getBody(), player.getPosition().x, player.getPosition().y, 1f, 1f, player.getWidth(),
				player.getHeight(), 1, 1, player.getRotation());

		batch.draw(player.getFeet(), player.getPosition().x, player.getPosition().y, 1f, 1f, player.getWidth(),
				player.getHeight(), 1, 1, player.getRotation());

		batch.draw(player.getHead(), player.getPosition().x, player.getPosition().y, 1f, 1f, player.getWidth(),
				player.getHeight(), 1, 1, player.getRotation());
	}

	public Player getPlayer() {
		return player;
	}

	public Entity getEntity(String key) {
		return entities.get(key);
	}

	public BulletManager getBulletManager() {
		return bullet;
	}

	public EntityFactory getFactory() {
		return factory;
	}

	public void dispose() {
		ui.dispose();
	}

	public void resize(int width, int height) {
		ui.resize(width, height);
	}

	public void setScreen(Screen screen) {
		main.setScreen(screen);
	}

	public Main getMain() {
		return main;
	}

	public WaveManager getWaveManager() {
		return wave;
	}

	public void pause() {
		paused = !paused;
	}

	public AtlasRegion getPlayerHead() {
		return animation.getHead(prefs.getInteger(Constants.HEAD_INDEX));
	}

	public PickupManager getPickupManager() {
		return pickups;
	}
}
