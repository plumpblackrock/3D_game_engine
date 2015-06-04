package com.ch.rendering.components;

import com.ch.core.scene.Scene;
import org.lwjgl.opengl.GL11;

import com.ch.math.Matrix4f;

public class Camera3D extends Camera {

    public Camera3D(float fov, float aspect, float zNear, float zFar) {
        super(new Matrix4f());
        this.values = new CameraStruct3D(fov, aspect, zNear, zFar);
        calculateProjectionMatrix(values);
    }

    @Override
    public Matrix4f calculateProjectionMatrix(CameraStruct data) {
        return (projection = data.getAsMatrix4());
    }

	@Override
	public void addToScene(Scene scene) {
        scene.getMainRenderer().setMainCamera(this);
	}

    @Override
	public void adjustToViewport(int width, int height) {
        ((CameraStruct3D)this.values).aspect = (float) width / height;
        calculateProjectionMatrix(values);
        try {
            calculateViewMatrix();
        } catch (NullPointerException e) {}
        GL11.glViewport(0, 0, width, height);
	}

    protected class CameraStruct3D extends CameraStruct {

        public float fov, aspect, zNear, zFar;

        public CameraStruct3D(float fov, float aspect, float zNear, float zFar) {
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
