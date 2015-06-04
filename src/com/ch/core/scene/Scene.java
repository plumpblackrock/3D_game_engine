package com.ch.core.scene;

import com.ch.core.Game;
import com.ch.core.GameObject;
import com.ch.core.renderer.Renderer;
import com.ch.core.Window;
import com.ch.util.OptionalOverride;

public abstract class Scene {

	protected GameObject root;
    protected Game parentApplication;
    protected Renderer mainRenderer;

    @OptionalOverride
	public void init() {
    }

    @OptionalOverride
    public void exitScene() {
    }

	public void input(float delta) {
		getRootObject().inputAll(delta);
	}

	public void update(float delta) {
		getRootObject().updateAll(delta);
	}

	public void render() {
		mainRenderer.render(getRootObject());
	}

	public void addObject(GameObject object) {
		getRootObject().addChild(object);
	}

	private GameObject getRootObject() {
		if (root == null) {
            root = new GameObject();
            root.setParentScene(this);
        }
		return root;
	}

    public void setParentApplication(Game parentApplication) {
        this.parentApplication = parentApplication;
    }

    public void setMainRenderer(Renderer renderer) {
        this.mainRenderer = renderer;
    }

    public void resize() {
        mainRenderer.getMainCamera().adjustToViewport(Window.getWidth(), Window.getHeight());
    }

    public void validate() {
        mainRenderer.validateForRendering();
    }

    public Renderer getMainRenderer() {
        return mainRenderer;
    }

}
