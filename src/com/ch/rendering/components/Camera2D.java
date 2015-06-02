package com.ch.rendering.components;

import com.ch.core.Scene;
import com.ch.math.Matrix4f;
import org.lwjgl.opengl.GL11;

public class Camera2D extends Camera {

    public Camera2D(float left, float right, float down, float up, float near, float far) {
        super(new Matrix4f());
        this.values = new CameraStruct2D(left, right, down, up, near, far);
        calculateProjectionMatrix(values);
    }

    public Camera2D(int width, int height, float near, float far) {
        this(-width / 2.0f, width / 2.0f, -height / 2.0f, height / 2.0f, near, far);
    }

    public Camera2D(int width, int height) {
        this(-width / 2.0f, width / 2.0f, -height / 2.0f, height / 2.0f, 1, -1);
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
        this.values = new CameraStruct2D(-width / 2.0f, width / 2.0f, -height / 2.0f, height / 2.0f, ((CameraStruct2D) this.values).near, ((CameraStruct2D) this.values).far);
        calculateProjectionMatrix(values);
        try {
            calculateViewMatrix();
        } catch (NullPointerException e) {}
        GL11.glViewport(0, 0, width, height);
    }

    protected class CameraStruct2D extends CameraStruct {

        public float left, right, down, up, near, far;

        public CameraStruct2D(float left, float right, float down, float up, float near, float far) {
            this.left = left;
            this.right = right;
            this.down = down;
            this.up = up;
            this.near = near;
            this.far = far;
        }

        public Matrix4f getAsMatrix4() {
            return new Matrix4f().initOrthographic(left, right, down, up, near, far);
        }

    }

}