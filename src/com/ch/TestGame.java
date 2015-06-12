package com.ch;

import com.ch.components.FreeLook;
import com.ch.components.FreeMove;
import com.ch.components.GameComponent;
import com.ch.core.renderer.Renderer3D;
import com.ch.core.renderer.presets.Renderer3D_debug;
import com.ch.core.scene.Scene;
import com.ch.core.GameObject;
import com.ch.core.Window;
import com.ch.core.scene.templates.Standard3D;
import com.ch.math.Matrix4f;
import com.ch.math.Quaternion;
import com.ch.math.Vector3f;
import com.ch.rendering.Material;
import com.ch.rendering.Mesh;
import com.ch.rendering.Texture;
import com.ch.rendering.components.Camera;
import com.ch.rendering.components.Camera3D;
import com.ch.rendering.components.MeshRenderer;
import com.ch.rendering.components.light.DirectionalLight;
import com.ch.rendering.components.light.PointLight;
import com.ch.rendering.components.light.SpotLight;
import com.ch.rendering.light.Attenuation;
import com.tp.physics.RigidBody;
import org.lwjgl.opengl.Display;

public class TestGame extends Standard3D {


	@Override
	public Camera3D createCamera3D() {
		return new Camera3D((float)Math.toRadians(70.0f), Display.getWidth() / (float) Display.getHeight(), .03f, 100);
	}

	public Renderer3D createRenderer3D() {
		return new Renderer3D_debug();
	}

	@Override
	public void childInit() {

		Mesh mesh = new Mesh("plane3.obj");

		Material material2 = new Material(new Texture("crate.png"), 1f, 1, new Texture("crate_normal.png"), new Texture("default_disp.png"), 0.03f, -0.5f);

		Material material1 = new Material(new Texture("bricks2.jpg"), 1f, 100, new Texture("bricks2_normal.jpg"), new Texture("bricks2_disp.jpg"), .03f, -.50f);

		Material material = new Material(new Texture("bricks.jpg"), 1, 10, new Texture("bricks_normal.jpg"), new Texture("bricks_disp.png"), 0.04f, -1.0f);

		Mesh tempMesh = new Mesh("crate.obj");

		MeshRenderer meshRenderer = new MeshRenderer(mesh, material1);

		GameObject planeObject = new GameObject();
		planeObject.addComponent(meshRenderer);
		planeObject.getTransform().getPos().set(0, 0, 0);
//        planeObject.getTransform().rotate(new Vector3f(0, 1, 0), (float) Math.toRadians(45f));
		addObject(planeObject);

		GameObject pointLightObject = new GameObject();
		pointLightObject.addComponent(new PointLight(new Vector3f(0.7f, 0.9f, 0.5f), 1f, new Attenuation(0, 0, .1f)));
		addObject(pointLightObject);
		pointLightObject.getTransform().getPos().set(0, 2, 0);

		GameObject camO = new GameObject();
		camO.addComponent(new FreeLook(0.3f)).addComponent(new FreeMove(6.0f)).addComponent(mainCam);
		addObject(camO);

	}
}