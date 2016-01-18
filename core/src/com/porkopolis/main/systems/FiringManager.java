package com.porkopolis.main.systems;

import com.badlogic.gdx.math.Vector2;
import com.porkopolis.main.model.Player;
import com.porkopolis.main.utils.Constants;

public class FiringManager {

	private Player player;
	private Vector2 tmp;
	private float x;
	private float y;
	private long id;

	public FiringManager(Player player) {
		this.player = player;
	}

	public void straightFire(WorldManager manager) {
		if (player.getHealth() > 0) {
			x = player.getPosition().x + (player.getWidth() / 2);
			y = player.getPosition().y + (player.getHeight() / 2);
			tmp = new Vector2(x, y);
			manager.getBulletManager().createBullet(tmp,
					new Vector2(player.getAttackSpeed(), 0),
					player.getDamage(), Constants.FRIENDLY);
			id = SoundManager.getShoot01().play();
			SoundManager.getShoot01().setVolume(id, 0.1f);
		}
	}

	public void doubleStraightFire(WorldManager manager) {
		if (player.getHealth() > 0) {
			x = player.getPosition().x + player.getWidth();
			y = player.getPosition().y + 0.2f;
			tmp = new Vector2(x, y);
			manager.getBulletManager().createBullet(tmp,
					new Vector2(player.getAttackSpeed(), 0),
					player.getDamage(), Constants.FRIENDLY);
			id = SoundManager.getShoot01().play();
			SoundManager.getShoot01().setVolume(id, 0.1f);

			x = player.getPosition().x + (player.getWidth() - 0.2f);
			y = player.getPosition().y + (player.getHeight() - 0.2f);
			tmp = new Vector2(x, y);
			manager.getBulletManager().createBullet(tmp,
					new Vector2(player.getAttackSpeed(), 0),
					player.getDamage(), Constants.FRIENDLY);
			id = SoundManager.getShoot01().play();
			SoundManager.getShoot01().setVolume(id, 0.1f);
		}
	}

	public void sinFire(WorldManager manager) {
		if (player.getHealth() > 0) {
			x = player.getPosition().x + (player.getWidth() / 2);
			y = player.getPosition().y + (player.getHeight() / 2);
			tmp = new Vector2(x, y);
			manager.getBulletManager().createSinBullet(tmp,
					new Vector2(player.getAttackSpeed(), 0),
					player.getDamage(), Constants.FRIENDLY);
			id = SoundManager.getShoot01().play();
			SoundManager.getShoot01().setVolume(id, 0.1f);
		}
	}

	public void doubleSinFire(WorldManager manager) {
		if (player.getHealth() > 0) {
			x = player.getPosition().x + (player.getWidth() / 2);
			y = player.getPosition().y + (player.getHeight() / 2);
			tmp = new Vector2(x, y);
			manager.getBulletManager().createSinBullet(tmp,
					new Vector2(player.getAttackSpeed(), 0),
					player.getDamage(), Constants.FRIENDLY, true);
			id = SoundManager.getShoot01().play();
			SoundManager.getShoot01().setVolume(id, 0.1f);

			x = player.getPosition().x + (player.getWidth() / 2);
			y = player.getPosition().y + (player.getHeight() / 2);
			tmp = new Vector2(x, y);
			manager.getBulletManager().createSinBullet(tmp,
					new Vector2(player.getAttackSpeed(), 0),
					player.getDamage(), Constants.FRIENDLY, false);
			id = SoundManager.getShoot01().play();
			SoundManager.getShoot01().setVolume(id, 0.1f);
		}
	}

}
