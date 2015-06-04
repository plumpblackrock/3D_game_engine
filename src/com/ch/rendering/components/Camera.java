package com.ch.rendering.components;

import com.ch.components.GameComponent;
import com.ch.math.Matrix4f;
import com.ch.math.Vector3f;
import com.ch.util.Legacy;

public abstract class Camera extends GameComponent {

    protected Matrix4f projection;
    protected Matrix4f viewProjectionMat4;
    protected CameraStruct values;

    protected Camera(Matrix4f projection) {
        this.projection = projection;
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

    public abstract Matrix4f calculateProjectionMatrix(CameraStruct data);

    public abstract void adjustToViewport(int width, int height);

    protected abstract class CameraStruct {

        protected abstract Matrix4f getAsMatrix4();

    }

}
