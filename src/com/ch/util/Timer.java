package com.ch.util;

import org.lwjgl.Sys;

public class Timer {

	private static float fps;
	private static long lastFPS;
	private static long lastFrame;
	public static float delta;
	public static float currentFPS;

	private static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public static void init() {
		lastFPS = getTime();
	}

	private static float calculateDelta() {
		long time = getTime();
		float delta = (int) (time - lastFrame);
		lastFrame = getTime();
		return delta;
	}

	private static void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			currentFPS = fps;
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public static float getDelta() {
		return delta;
	}

	public static float getFPS() {
		return currentFPS;
	}

	public static void update() {
		updateFPS();
		delta = ((calculateDelta() / 1000));
		delta = delta < 0 || delta > 1 ? 0 : delta;
	}

}
