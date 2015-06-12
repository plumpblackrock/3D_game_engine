package com.ch.core;

import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_POINT;
import static org.lwjgl.opengl.GL11.GL_POLYGON_MODE;
import static org.lwjgl.opengl.GL11.glGetInteger;
import static org.lwjgl.opengl.GL11.glPolygonMode;

import com.ch.TestGame;
import com.ch.core.scene.Scene;
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
	private Game game;

	/**
	 * 
	 * @param game
	 */
	public CoreEngine(Game game) {
		this.isRunning = false;
		this.game = game;
		this.game.setEngine(this);
	}

	public void createWindow(int width, int height, String title) {
		Window.createWindow(width, height, title);
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
        game.validate();

		while (isRunning) {

            if (Input.GetKeyDown(Input.KEY_O)) {
                this.game.setCurrentScene(new TestGame());
                game.init();
                game.validate();
            }

			if (Window.isCloseRequested()) {
				stop();
			}

			if (Window.wasResized()) {
				resize();
			}

			Timer.update();

            // TODO: remove ~only temp~
			Display.setTitle(Timer.getFPS() + "");

			game.input(Timer.getDelta());
			Input.Update();

			game.update(Timer.getDelta());

			game.render();
			Window.render();

		}

		cleanUp();

	}

	private void resize() {
		game.resize();
	}

	private void cleanUp() {
		// Window.dispose();
		// could be causing some error in JVM ("invalid
		// drawable" and or deeper error in native code (frame cancellation
		// error)).
		System.exit(0);
	}

}
