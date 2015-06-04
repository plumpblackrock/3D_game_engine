package com.ch.core.renderer;

import com.ch.core.GameObject;
import com.ch.core.Transform;
import com.ch.rendering.Material;
import com.ch.rendering.components.Camera;
import com.ch.rendering.components.light.Light;
import com.ch.rendering.light.Shader;
import com.ch.util.MappedValues;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.glDisable;

public abstract class Renderer extends MappedValues {

    protected Camera mainCamera;

    protected ArrayList<Light> lights;
    protected Light activeLight;

    protected HashMap<String, Integer> samplerMap;

    public Renderer() {
        samplerMap = new HashMap<>();
        lights = new ArrayList<>();

        init();

    }

    public abstract void init();

    public abstract void updateUniformStruct(Transform transform, Material material, Shader shader, String uniformName, String uniformType);

    public abstract void validateForRendering();

    public void render(GameObject object) {

        clearScreen();

        preSceneRender(object);

        renderScene(object);

        preLightingRender(object);

        renderWithLighting(object);

        postLightingRender(object);

    }

    public abstract void clearScreen();

    public abstract void preSceneRender(GameObject object);

    public abstract void enableStatesForRendering();

    public abstract void renderScene(GameObject object);

    public abstract void disableStatesForRendering();

    public abstract void preLightingRender(GameObject object);

    public abstract void enableStatesForLighting();

    public abstract void renderWithLighting(GameObject object);

    public abstract void disableStatesForLighting();

    public abstract void postLightingRender(GameObject object);

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
