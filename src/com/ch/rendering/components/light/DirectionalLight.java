package com.ch.rendering.components.light;

import com.ch.math.Vector3f;
import com.ch.rendering.light.Shader;

public class DirectionalLight extends Light {
	
	public DirectionalLight(Vector3f color, float intensity) {
		super(color, intensity);

		setShader(new Shader("forward-directional"));
	}

	public Vector3f getDirection() {
		return getTransform().getTransformedRot().getForward();
	}
	
}
