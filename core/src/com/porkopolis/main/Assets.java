package com.porkopolis.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;
import com.porkopolis.main.utils.Constants;

public class Assets implements Disposable {

	public static final String TAG = Assets.class.getSimpleName();

	public static boolean rebuildAtlas = true;
	public static boolean drawDebugOutline = false;

	public static boolean loaded = false;

	public static AssetManager manager;
	public static AssetEntities ships;
	public static AssetBackground background;
	public static AssetBullets bullets;
	public static AssetCharacter character;
	public static AssetUserInterface ui;
	public static AssetBosses bosses;

	private static TextureAtlas atlas;

	private static Preferences prefs = Gdx.app.getPreferences(Constants.CHARACTER);

	public static AssetManager getManager() {
		if (manager == null) {
			manager = new AssetManager();
		}
		return manager;
	}

	public static final String TEXTURE_ATLAS_OBJECTS = "assets.atlas";
	public static final String SKIN = "skin/uiskin.json";

	public static Skin skin;

	public static void load() {
		getManager(); // Insure the manager exists
		manager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		manager.load(SKIN, Skin.class);
		manager.finishLoading();
		loaded = true;
	}

	public static void create() {
		atlas = manager.get(TEXTURE_ATLAS_OBJECTS);
		ships = new AssetEntities(atlas);
		background = new AssetBackground(atlas);
		bullets = new AssetBullets(atlas);
		character = new AssetCharacter(atlas);
		ui = new AssetUserInterface(atlas);
		bosses = new AssetBosses(atlas);

		skin = manager.get(SKIN);

	}

	public static class AssetEntities {
		public AtlasRegion ship01;

		public AtlasRegion enemy01;
		public AtlasRegion enemy011;
		public AtlasRegion enemy012;

		public AtlasRegion enemy02;
		public AtlasRegion enemy021;

		public AtlasRegion healthPack;
		public AtlasRegion shieldPack;

		public AssetEntities(TextureAtlas atlas) {
			ship01 = atlas.findRegion("ship01");

			enemy01 = atlas.findRegion("enemy01");
			enemy011 = atlas.findRegion("enemy011");
			enemy012 = atlas.findRegion("enemy012");

			enemy02 = atlas.findRegion("enemy021");
			enemy021 = atlas.findRegion("enemy02");

			healthPack = atlas.findRegion("healthpack");
			shieldPack = atlas.findRegion("shieldpack");

		}
	}

	public static class AssetBackground {
		public AtlasRegion bg1;
		public AtlasRegion bg2;
		public AtlasRegion bg3;

		public AssetBackground(TextureAtlas atlas) {
			bg1 = atlas.findRegion("backdrop1");
			bg2 = atlas.findRegion("backdrop2");
			bg3 = atlas.findRegion("backdrop3");

		}
	}

	public static class AssetBullets {
		public AtlasRegion blue01;
		public AtlasRegion red01;

		public AtlasRegion pepe;
		public AtlasRegion nyan;
		public AtlasRegion skrillex;
		public AtlasRegion mouse;

		public AssetBullets(TextureAtlas atlas) {
			blue01 = atlas.findRegion("laserblue01");
			red01 = atlas.findRegion("laserred01");
			pepe = atlas.findRegion("bullet01");
			nyan = atlas.findRegion("bullet02");
			skrillex = atlas.findRegion("bullet03");
			mouse = atlas.findRegion("bullet04");

		}

		public TextureRegion getPlayerBullet() {
			return blue01;
		}

	}

	public static class AssetCharacter {
		public AtlasRegion head01;
		public AtlasRegion head02;
		public AtlasRegion head03;
		public AtlasRegion head04;

		public AtlasRegion body01;
		public AtlasRegion body02;
		public AtlasRegion body03;

		public AtlasRegion feet01;
		public AtlasRegion feet02;
		public AtlasRegion feet03;

		public AtlasRegion feet04;
		public AtlasRegion feet05;
		public AtlasRegion feet06;

		public AtlasRegion tail01;
		public AtlasRegion tail02;

		public AtlasRegion tail03;
		public AtlasRegion tail04;

		public AssetCharacter(TextureAtlas atlas) {
			head01 = atlas.findRegion("nyan1");
			head02 = atlas.findRegion("nyan2");
			head03 = atlas.findRegion("nyan11");
			head04 = atlas.findRegion("nyan12");

			body01 = atlas.findRegion("nyan3");
			body02 = atlas.findRegion("nyan4");
			body03 = atlas.findRegion("nyan13");

			feet01 = atlas.findRegion("nyan5");
			feet02 = atlas.findRegion("nyan6");
			feet03 = atlas.findRegion("nyan7");

			feet04 = atlas.findRegion("nyan14");
			feet05 = atlas.findRegion("nyan15");
			feet06 = atlas.findRegion("nyan16");

			tail01 = atlas.findRegion("nyan8");
			tail02 = atlas.findRegion("nyan9");

			tail03 = atlas.findRegion("nyan17");
			tail04 = atlas.findRegion("nyan18");

		}

	}

	public static class AssetUserInterface {
		public AtlasRegion upArrow;
		public AtlasRegion downArrow;
		public AtlasRegion leftArrow;
		public AtlasRegion rightArrow;
		public AtlasRegion blueButton;
		public AtlasRegion blueButtonPressed;

		public AtlasRegion greenLeft;
		public AtlasRegion greenMiddle;
		public AtlasRegion greenRight;

		public AtlasRegion blueLeft;
		public AtlasRegion blueMiddle;
		public AtlasRegion blueRight;

		public AtlasRegion redLeft;
		public AtlasRegion redMiddle;
		public AtlasRegion redRight;

		public AtlasRegion metalPanel;

		public AssetUserInterface(TextureAtlas atlas) {
			upArrow = atlas.findRegion("uparrow");
			downArrow = atlas.findRegion("downarrow");
			leftArrow = atlas.findRegion("leftarrow");
			rightArrow = atlas.findRegion("rightarrow");

			blueButton = atlas.findRegion("bluebutton");
			blueButtonPressed = atlas.findRegion("bluebuttonpressed");

			greenLeft = atlas.findRegion("barHorizontalgreenleft");
			greenMiddle = atlas.findRegion("barHorizontalgreenmid");
			greenRight = atlas.findRegion("barHorizontalgreenright");

			blueLeft = atlas.findRegion("barHorizontalblueleft");
			blueMiddle = atlas.findRegion("barHorizontalbluemid");
			blueRight = atlas.findRegion("barHorizontalblueright");

			redLeft = atlas.findRegion("barHorizontalredleft");
			redMiddle = atlas.findRegion("barHorizontalredmid");
			redRight = atlas.findRegion("barHorizontalredright");

			metalPanel = atlas.findRegion("metalPanel");

		}

	}

	public static class AssetBosses {
		public AtlasRegion gnome;
		public AtlasRegion mrt;
		public AtlasRegion javert;

		public AssetBosses(TextureAtlas atlas) {
			gnome = atlas.findRegion("spacegnome01");
			mrt = atlas.findRegion("mrt");
			javert = atlas.findRegion("javert");
		}

	}

	@Override
	public void dispose() {
		manager.dispose();
	}

	public static TextureAtlas getAtlas() {
		return atlas;
	}
}