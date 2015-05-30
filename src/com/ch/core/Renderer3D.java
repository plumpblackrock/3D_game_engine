package com.ch.core;

import com.ch.math.Vector3f;
import com.ch.rendering.Material;
import com.ch.rendering.components.light.Light;
import com.ch.rendering.light.Shader;
import com.ch.util.OptionalOverride;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Renderer3D extends Renderer {

    private Shader forwardAmbient;
    private Shader debugShder;
    private Vector3f debugColor;

    public Renderer3D() {
        super();
        lights = new ArrayList<>();
        samplerMap.put("diffuse", 0);
        samplerMap.put("normalMap", 1);
        samplerMap.put("dispMap", 2);

        addVector3f("ambient", new Vector3f(0.2f, 0.2f, 0.2f));

        forwardAmbient = new Shader("forward-ambient");
        debugShder = new Shader("debug-color");

        // night
        glClearColor(0.0f, 0.0f, .2f, 1f);
        // day
//		 glClearColor(0.0f, 0.8f, 1f, 1f);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
    }

    @Override
    @OptionalOverride
    public void updateUniformStruct(Transform transform, Material material, Shader shader, String uniformName, String uniformType) {
        throw new IllegalArgumentException(uniformType + " is not a supported type in Renderer");
    }

    public void validateForRendering() {
        if (getMainCamera() == null) {
            new RuntimeException("There is no main camera").printStackTrace();
            System.exit(1);
        }
    }

    public void render(GameObject object) {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glPolygonMode(GL_FRONT, GL_LINE);
        glLineWidth(2);
        setDebugColor(new Vector3f(1, 0, 0));
        object.renderAll(debugShder, this);
        glLineWidth(1);
        glPolygonMode(GL_FRONT, GL_POINT);
        glPointSize(4);
        setDebugColor(new Vector3f(0, 1, 0));
        object.renderAll(debugShder, this);
        glPointSize(1);
        glPolygonMode(GL_FRONT, GL_FILL);

        object.renderAll(forwardAmbient, this);

        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);
        glDepthFunc(GL_EQUAL);

        for (Light light : lights) {
            activeLight = light;
            object.renderAll(light.getShader(), this);
        }

        glDepthFunc(GL_LESS);
        glDisable(GL_BLEND);

    }

    public void setDebugColor(Vector3f debugColor) {
        addVector3f("debug_color", debugColor);
    }

}