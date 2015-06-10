package com.ch;

import com.ch.components.FreeLook;
import com.ch.components.FreeMove;
import com.ch.core.GameObject;
import com.ch.core.renderer.Renderer3D;
import com.ch.core.scene.templates.Standard3D;
import com.ch.math.Vector3f;
import com.ch.rendering.Material;
import com.ch.rendering.Mesh;
import com.ch.rendering.Texture;
import com.ch.rendering.components.Camera3D;
import com.ch.rendering.components.MeshRenderer;
import com.ch.rendering.components.light.PointLight;
import com.ch.rendering.light.Attenuation;
import org.lwjgl.opengl.Display;

public class TemplateTest extends Standard3D {


    @Override
    public Camera3D createCamera3D() {
        return new Camera3D((float)Math.toRadians(70.0f), Display.getWidth() / (float) Display.getHeight(), .03f, 100);
    }

    public Renderer3D createRenderer3D() {
        return new Renderer3D();
    }

    @Override
    public void childInit() {

        Mesh mesh = new Mesh("plane3.obj");

        Material material2 = new Material(new Texture("crate.png"), 1f, 1, new Texture("crate_normal.png"), new Texture("default_disp.png"), 0.03f, -0.5f);

        Material material1 = new Material(new Texture("bricks2.jpg"), 10f, 1000, new Texture("bricks2_normal.jpg"), new Texture("bricks2_disp.jpg"), .03f, -.50f);

        Material material = new Material(new Texture("bricks.jpg"), .3f, 1, new Texture("bricks_normal.jpg"), new Texture("bricks_disp.png"), 0.04f, -1.0f);

        Mesh tempMesh = new Mesh("crate.obj");

        MeshRenderer meshRenderer = new MeshRenderer(mesh, material);

        GameObject planeObject = new GameObject();
        planeObject.addComponent(meshRenderer);
        planeObject.getTransform().getPos().set(0, 0, 0);
        addObject(planeObject);

        GameObject pointLightObject = new GameObject();
        pointLightObject.addComponent(new PointLight(new Vector3f(0, 0.4f, .9f), .4f, new Attenuation(0, 0, .1f)));
        addObject(pointLightObject);
        pointLightObject.getTransform().getPos().set(0, 2, 0);

        GameObject camO = new GameObject();
        camO.addComponent(new FreeLook(0.3f)).addComponent(new FreeMove(6.0f)).addComponent(mainCam);
        addObject(camO);

    }
}
