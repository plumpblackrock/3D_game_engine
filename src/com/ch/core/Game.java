package com.ch.core;


public class Game {
	
	protected Scene currentScene;
	
	public Game() {
	}
	
	public void input(float dt) {
		currentScene.input(dt);
	}

	public void update(float dt) {
		currentScene.update(dt);
	}

	public void render(Renderer renderer) {
		currentScene.render(renderer);
	}
	
	public void setCurrentScene(Scene scene) {
		currentScene = scene;
	}

}
