package com.ch.rendering.components;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.ch.components.GameComponent;
import com.ch.core.CoreEngine;
import com.ch.math.Matrix4f;
import com.ch.math.Vector3f;

public class Camera extends GameComponent {

	private Matrix4f projection;
	private Matrix4f viewProjectionMat4;

	public Camera(Matrix4f projection) {
		this.projection = projection;
	}

	public Matrix4f getViewProjection() {

		if (viewProjectionMat4 == null || getTransform().hasChanged()) {
			Matrix4f cameraRotation = getTransform().getTransformedRot().conjugate().toRotationMatrix();
			Vector3f cameraPos = getTransform().getTransformedPos().mul(-1);
			Matrix4f cameraTranslation = new Matrix4f().initTranslation(cameraPos.getX(), cameraPos.getY(), cameraPos.getZ());

			return (viewProjectionMat4 = projection.mul(cameraRotation.mul(cameraTranslation)));
		}

		return viewProjectionMat4;
	}

	@Override
	public void addToEngine(CoreEngine engine) {
		engine.getRenderer().addCamera(this);
	}

	public void adjustToViewport() {
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
	}

}
