package com.ch.core;

import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_POINT;
import static org.lwjgl.opengl.GL11.GL_POLYGON_MODE;
import static org.lwjgl.opengl.GL11.glGetInteger;
import static org.lwjgl.opengl.GL11.glPolygonMode;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.ch.input.Input;
import com.ch.util.Timer;

public class CoreEngine {

	/**
	 * Denotes whether the engine has started to run and is the break condition
	 * of the main loop.
	 */
	private boolean isRunning;
	
	/**
	 * The instance of {@link Scene} that contains all the scenes to be renderer
	 */
	private Scene game;
	private Renderer renderer;

	/**
	 * 
	 * @param game
	 */
	public CoreEngine(Scene game) {
		this.isRunning = false;
		this.game = game;
		game.setEngine(this);
	}

	public void createWindow(int width, int height, String title) {
		Window.createWindow(width, height, title);
		this.renderer = new Renderer();
	}

	public void start() {
		if (isRunning)
			return;

		isRunning = true;

		run();
	}

	public void stop() {
		if (!isRunning)
			return;

		isRunning = false;
	}

	private void run() {

		Timer.init();

		game.init();

		while (isRunning) {

			while (Keyboard.next()) {
				int face = GL_FRONT;
				if (Keyboard.getEventKeyState()) {
					if (Keyboard.getEventKey() == Keyboard.KEY_P) {
						int polygonMode = glGetInteger(GL_POLYGON_MODE);
						if (polygonMode == GL_POINT) {
							glPolygonMode(face, GL_FILL);
						} else if (polygonMode == GL_LINE) {
							glPolygonMode(face, GL_POINT);
						} else if (polygonMode == GL_FILL) {
							glPolygonMode(face, GL_LINE);
						}
					}
				}
			}

			if (Window.isCloseRequested()) {
				stop();
			}

			if (Window.wasResized()) {
				resize();
			}

			Timer.update();

			Display.setTitle(Timer.getFPS() + "");

			game.input(Timer.getDelta());
			Input.Update();

			game.update(Timer.getDelta());

			game.render(renderer);
			Window.render();

		}

		cleanUp();

	}

	private void resize() {
		renderer.getMainCamera().adjustToViewport();
	}

	private void cleanUp() {
		// Window.dispose();
		// could be causing some error in JVM ("invalid
		// drawable" and or deeper error in native code (frame cancellation
		// error)).
		System.exit(0);
	}

	public Renderer getRenderer() {
		return renderer;
	}
}