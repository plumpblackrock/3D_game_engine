package com.ch.core;

import com.ch.rendering.Material;
import com.ch.rendering.components.Camera;
import com.ch.rendering.components.light.Light;
import com.ch.rendering.light.Shader;
import com.ch.util.MappedValues;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

public abstract class Renderer extends MappedValues {

    protected Camera mainCamera;

    protected ArrayList<Light> lights;
    protected Light activeLight;

    protected HashMap<String, Integer> samplerMap;

    public Renderer() {
        samplerMap = new HashMap<>();
    }

    public abstract void updateUniformStruct(Transform transform, Material material, Shader shader, String uniformName, String uniformType);

    public abstract void validateForRendering();

    public abstract void render(GameObject object);

    public static String getOpenGLVersion() {
        return glGetString(GL_VERSION);
    }

    public void setMainCamera(Camera mainCamera) {
        this.mainCamera = mainCamera;
    }

    public Camera getMainCamera() {
        return mainCamera;
    }

    public void addCamera(Camera camera) {
        mainCamera = camera;
    }

    public int getSamplerSlot(String samplerName) {
        return samplerMap.get(samplerName);
    }

    public void addLight(Light light) {
        lights.add(light);
    }

    public Light getActiveLight() {
        return activeLight;
    }

}
