package com.ch.rendering.components.light;

import com.ch.math.Vector3f;
import com.ch.rendering.light.Attenuation;
import com.ch.rendering.light.Shader;

public class SpotLight extends PointLight {

	private float cutoff;

	public SpotLight(Vector3f color, float intensity, Attenuation attenuation, float cutoff) {
		super(color, intensity, attenuation);
		this.cutoff = cutoff;

		setShader(new Shader("forward-spot"));
	}

	public Vector3f GetDirection() {
		return getTransform().getTransformedRot().getForward();
	}

	public float GetCutoff() {
		return cutoff;
	}

	public void SetCutoff(float cutoff) {
		this.cutoff = cutoff;
	}

}
