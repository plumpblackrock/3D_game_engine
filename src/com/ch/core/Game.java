package com.ch.core;


import com.ch.core.scene.Scene;

public class Game {

	protected Scene currentScene;
    protected CoreEngine engine;

	public Game() {
	}

    //TODO: temp
    public void init() {
        currentScene.init();
    }

	public void input(float dt) {
		currentScene.input(dt);
	}

	public void update(float dt) {
		currentScene.update(dt);
	}

	public void render() {
		currentScene.render();
	}

	public void setCurrentScene(Scene scene) {
		currentScene = scene;
        currentScene.setParentApplication(this);
	}

    public void setEngine(CoreEngine engine) {
        this.engine = engine;
    }

    public void resize() {
        currentScene.resize();
    }

    public void validate() {
        currentScene.validate();
    }

}
