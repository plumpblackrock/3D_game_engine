package com.ch.rendering.components;

import com.ch.core.Scene;
import com.ch.util.Legacy;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.ch.components.GameComponent;
import com.ch.core.CoreEngine;
import com.ch.math.Matrix4f;
import com.ch.math.Vector3f;

import java.io.IOException;

public class Camera extends GameComponent {

	private Matrix4f projection;
	private Matrix4f viewProjectionMat4;
    private CameraStruct values;

    public Camera(float fov, float aspect, float zNear, float zFar) {
        this.values = new CameraStruct(fov, aspect, zNear, zFar);
        calculateProjectionMatrix(values);
    }

    @Legacy
	protected Camera(Matrix4f projection, CameraStruct struct) {
		this.projection = projection;
        this.values = struct;
	}

	public Matrix4f getViewProjection() {

		if (viewProjectionMat4 == null || getTransform().hasChanged()) {

            calculateViewMatrix();

		}

		return viewProjectionMat4;
	}

    public Matrix4f calculateViewMatrix() {

        Matrix4f cameraRotation = getTransform().getTransformedRot().conjugate().toRotationMatrix();
        Vector3f cameraPos = getTransform().getTransformedPos().mul(-1);
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(cameraPos.getX(), cameraPos.getY(), cameraPos.getZ());

        return (viewProjectionMat4 = projection.mul(cameraRotation.mul(cameraTranslation)));

    }

    public Matrix4f calculateProjectionMatrix(CameraStruct data) {

        return (projection = data.getAsMatrix4());

    }

	@Override
	public void addToScene(Scene scene) {
        scene.getMainRenderer().setMainCamera(this);
	}

	public void adjustToViewport(int width, int height) {
        this.values.aspect = (float) width / height;
        calculateProjectionMatrix(values);
        try {
            calculateViewMatrix();
        } catch (NullPointerException e) {}
        GL11.glViewport(0, 0, width, height);
	}

    protected class CameraStruct {

        public float fov, aspect, zNear, zFar;

        public CameraStruct(float fov, float aspect, float zNear, float zFar) {
            this.fov = fov;
            this.aspect = aspect;
            this.zNear = zNear;
            this.zFar = zFar;
        }

        public Matrix4f getAsMatrix4() {
            return new Matrix4f().initPerspective(fov, aspect, zNear, zFar);
        }

    }

}
