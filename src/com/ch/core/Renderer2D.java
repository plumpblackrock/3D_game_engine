package com.ch.core;

import com.ch.math.Vector3f;
import com.ch.rendering.Material;
import com.ch.rendering.components.Camera2D;
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
//        glEnable(GL_DEPTH_TEST);
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

    @Override
    public void render(GameObject object) {

        glClear(GL_COLOR_BUFFER_BIT);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        object.renderAll(forwardAmbient, this);

//        glEnable(GL_BLEND);
//        glBlendFunc(GL_ONE, GL_ONE);
//        glDepthFunc(GL_EQUAL);
//
//        for (Light light : lights) {
//            activeLight = light;
//            object.renderAll(light.getShader(), this);
//        }
//
//        glDepthFunc(GL_LESS);
//        glDisable(GL_BLEND);

    }

}
