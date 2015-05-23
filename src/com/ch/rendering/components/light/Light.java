package com.ch.rendering.components.light;

import com.ch.components.GameComponent;
import com.ch.core.CoreEngine;
import com.ch.math.Vector3f;
import com.ch.rendering.light.Shader;

public class Light extends GameComponent {

	protected Vector3f color;
	protected float intensity;
	protected Shader shader;

	public Light(Vector3f color, float intensity) {
		this.color = color;
		this.intensity = intensity;
	}

	@Override
	public void addToEngine(CoreEngine engine) {
		engine.getRenderer().addLight(this);
	}

	public void setShader(Shader shader) {
		this.shader = shader;
	}

	public Shader getShader() {
		return shader;
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public float getIntensity() {
		return intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}
	
}
