package com.porkopolis.main.systems;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.porkopolis.main.Assets;
import com.porkopolis.main.utils.Constants;

public class AnimationManager {

	private WorldManager manager;

	private Animation feetAnimation1;
	private Animation feetAnimation2;
	private Animation tailAnimation1;
	private Animation tailAnimation2;

	public AnimationManager init() {
		feetAnimation1 = new Animation(Constants.FEET_ANIMATION_DURATION, Assets.character.feet01,
				Assets.character.feet02, Assets.character.feet03);

		feetAnimation2 = new Animation(Constants.FEET_ANIMATION_DURATION, Assets.character.feet04,
				Assets.character.feet05, Assets.character.feet06);

		tailAnimation1 = new Animation(Constants.TAIL_ANIMATION_DURATION, Assets.character.tail01,
				Assets.character.tail02);

		tailAnimation2 = new Animation(Constants.TAIL_ANIMATION_DURATION, Assets.character.tail03,
				Assets.character.tail04);

		return this;
	}

	public Animation getFeet(int index) {
		if (index == 0) {
			return feetAnimation1;
		} else if (index == 1) {
			return feetAnimation2;
		} else {
			return feetAnimation1;
		}
	}

	public Animation getTail(int index) {
		if (index == 0) {
			return tailAnimation1;
		} else if (index == 1) {
			return tailAnimation2;
		} else {
			return tailAnimation1;
		}
	}

	public AtlasRegion getHead(int index) {
		if (index == 0) {
			return Assets.character.head01;
		} else if (index == 1) {
			return Assets.character.head02;
		} else if (index == 2) {
			return Assets.character.head03;
		} else if (index == 3) {
			return Assets.character.head04;
		} else {
			return Assets.character.head01;
		}
	}

	public AtlasRegion getBody(int index) {
		if (index == 0) {
			return Assets.character.body01;
		} else if (index == 1) {
			return Assets.character.body02;
		} else if (index == 2) {
			return Assets.character.body03;
		} else {
			return Assets.character.body01;
		}
	}

}
