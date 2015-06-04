package com.ch;

import com.ch.components.Free2DMove;
import com.ch.core.*;
import com.ch.core.renderer.Renderer2D;
import com.ch.core.scene.Scene;
import com.ch.math.Quaternion;
import com.ch.math.Vector2f;
import com.ch.math.Vector3f;
import com.ch.rendering.Material;
import com.ch.rendering.Mesh;
import com.ch.rendering.Texture;
import com.ch.rendering.components.Camera2D;
import com.ch.rendering.components.MeshRenderer;

public class Test2DScene extends Scene {

    public void init() {

        setMainRenderer(new Renderer2D());

        Material material = new Material(new Texture("bricks.jpg"));

        GameObject testMesh3 = new GameObject().addComponent(new Free2DMove(3))
                .addComponent(new MeshRenderer(Mesh.quad2D(new Vector2f(0, 0), new Vector2f(1, 1)), material));

        addObject(new GameObject().addComponent(new Camera2D(Window.getWidth(), Window.getHeight(), 1, -1, 100)));

        addObject(testMesh3);

        testMesh3.getTransform().getPos().set(0, 0, 0);
        testMesh3.getTransform().setRot(new Quaternion(new Vector3f(1, 0, 0), (float) Math.toRadians(0.0f)));
    }

}
