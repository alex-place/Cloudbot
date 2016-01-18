package com.porkopolis.main.systems;

public class ScoreManager {

	private static float timer = 0f;

	private static int currentScore = 0;

	private static float maxComboTimeout = 5f;

	private static float bodyCount = 0f;

	private static float bodiesPerMultiplier = 4f;

	private static float multiplier = 1f;

	private static float maxMultiplier = 10f;

	public ScoreManager() {
	}

	public static void update(float delta) {
		timer += delta;

		if (timer <= maxComboTimeout) {
			multiplier = 1;
		}

		if (bodyCount >= bodiesPerMultiplier) {
			if (multiplier <= maxMultiplier)
				multiplier++;
		}

	}

	public static void addScore(int score) {
		currentScore += score * multiplier;
		timer = 0;
		bodyCount++;
	}

	public static int getScore() {
		return currentScore;
	}
}
