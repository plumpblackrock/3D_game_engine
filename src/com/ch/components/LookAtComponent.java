package com.ch.components;

import com.ch.core.renderer.Renderer;
import com.ch.math.Quaternion;
import com.ch.math.Vector3f;
import com.ch.rendering.light.Shader;

public class LookAtComponent extends GameComponent {
	
	private Renderer renderingEngine;

	@Override
	public void update(float dt) {
		if (renderingEngine != null) {
			Quaternion newRot = getTransform().getLookAtRotation(renderingEngine.getMainCamera().getTransform().getTransformedPos(), new Vector3f(0, 1, 0));
			// GetTransform().GetRot().GetUp());

			getTransform().setRot(getTransform().getRot().NLerp(newRot, dt * 5.0f, true));
			// GetTransform().SetRot(GetTransform().GetRot().SLerp(newRot, delta
			// * 5.0f, true));
		}
	}

	@Override
	public void render(Shader shader, Renderer renderingEngine) {
		this.renderingEngine = renderingEngine;
	}
	
}
