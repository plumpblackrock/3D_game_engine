package com.ch.core.renderer;

import com.ch.core.GameObject;
import com.ch.core.Transform;
import com.ch.math.Vector3f;
import com.ch.rendering.Material;
import com.ch.rendering.components.light.Light;
import com.ch.rendering.light.Shader;
import com.ch.util.OptionalOverride;

import static org.lwjgl.opengl.GL11.*;

public class Renderer2D extends Renderer {

    private Shader forwardAmbient;

    public Renderer2D() {
        super();
    }

    @Override
    public void init() {

        samplerMap.put("diffuse", 0);
        samplerMap.put("normalMap", 1);
        samplerMap.put("dispMap", 2);

        addVector3f("ambient", new Vector3f(1.0f, 1.0f, 1.0f));

        forwardAmbient = new Shader("forward-ambient");

        glClearColor(0.0f, 0.0f, .2f, 1f);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_TEXTURE_2D);

    }

    @Override
    @OptionalOverride
    public void updateUniformStruct(Transform transform, Material material, Shader shader, String uniformName, String uniformType) {
        throw new IllegalArgumentException(uniformType + " is not a supported type in Renderer");
    }

    @Override
    public void validateForRendering() {
        if (getMainCamera() == null) {
            new RuntimeException("There is no main camera").printStackTrace();
            System.exit(1);
        }
    }

    public void clearScreen() {
        glClear(GL_COLOR_BUFFER_BIT);
    }

    public void preSceneRender(GameObject object) {
    }

    public void enableStatesForRendering() {
    }

    public void renderScene(GameObject object) {

        enableStatesForRendering();

        object.renderAll(forwardAmbient, this);

        disableStatesForRendering();

    }

    public void disableStatesForRendering() {
    }

    public void preLightingRender(GameObject object) {
    }

    public void enableStatesForLighting() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);
        glDepthFunc(GL_EQUAL);
    }

    public void renderWithLighting(GameObject object) {

        enableStatesForLighting();

        for (Light light : lights) {
            activeLight = light;
            object.renderAll(light.getShader(), this);
        }

        disableStatesForLighting();

    }

    public void disableStatesForLighting() {
        glDepthFunc(GL_LESS);
        glDisable(GL_BLEND);
    }

    public void postLightingRender(GameObject object) {
    }

}
