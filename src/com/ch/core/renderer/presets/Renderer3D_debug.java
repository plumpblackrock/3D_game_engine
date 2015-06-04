package com.ch.core.renderer.presets;

import com.ch.core.GameObject;
import com.ch.core.renderer.Renderer3D;
import com.ch.math.Vector3f;
import com.ch.rendering.light.Shader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT;

public class Renderer3D_debug extends Renderer3D {

    private Shader debugShder;

    @Override
    public void init() {
        super.init();

        debugShder = new Shader("debug-color");

    }

    @Override
    public void preSceneRender(GameObject object) {
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
    }

    public void setDebugColor(Vector3f debugColor) {
        addVector3f("debug_color", debugColor);
    }

}
