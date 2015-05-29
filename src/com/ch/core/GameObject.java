package com.ch.core;

import com.ch.components.GameComponent;
import com.ch.rendering.light.Shader;

import java.util.ArrayList;

public final class GameObject {

	private ArrayList<GameObject> children;
	private ArrayList<GameComponent> components;
	private Transform transform;
//	private CoreEngine engine;
    private Scene parentScene;

	/**
	 * 
	 */
	public GameObject() {
		children = new ArrayList<GameObject>();
		components = new ArrayList<GameComponent>();
		transform = new Transform();
        parentScene = null;
	}

	public GameObject addChild(GameObject child) {
		children.add(child);
		child.setParentScene(parentScene);
		child.getTransform().setParent(transform);

		return this;
	}

	public GameObject addComponent(GameComponent component) {
		components.add(component);
		component.setParent(this);

		return this;
	}

	public void inputAll(float dt) {
		input(dt);

		for (GameObject child : children)
			child.inputAll(dt);
	}

	public void updateAll(float dt) {
		update(dt);

		for (GameObject child : children)
			child.updateAll(dt);
	}

	public void renderAll(Shader shader, Renderer renderingEngine) {
		render(shader, renderingEngine);

		for (GameObject child : children)
			child.renderAll(shader, renderingEngine);
	}

	public void input(float dt) {
		transform.update();

		for (GameComponent component : components)
			component.input(dt);
	}

	public void update(float dt) {
		for (GameComponent component : components)
			component.update(dt);
	}

	public void render(Shader shader, Renderer renderingEngine) {
		for (GameComponent component : components)
			component.render(shader, renderingEngine);
	}

	public ArrayList<GameObject> getAllAttached() {
		ArrayList<GameObject> result = new ArrayList<GameObject>();

		for (GameObject child : children)
			result.addAll(child.getAllAttached());

		result.add(this);
		return result;
	}

	public Transform getTransform() {
		return transform;
	}

	public void setParentScene(Scene parentScene) {
		if (this.parentScene == null || !this.parentScene.equals(parentScene)) {
			this.parentScene = parentScene;

			for (GameComponent component : components)
				component.addToScene(parentScene);

			for (GameObject child : children)
				child.setParentScene(parentScene);
		}
	}
}
