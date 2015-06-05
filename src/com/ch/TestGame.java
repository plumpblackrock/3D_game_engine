package com.ch;

import com.ch.components.FreeLook;
import com.ch.components.FreeMove;
import com.ch.components.GameComponent;
import com.ch.core.renderer.Renderer3D;
import com.ch.core.scene.Scene;
import com.ch.core.GameObject;
import com.ch.core.Window;
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
import com.ch.rendering.light.Attenuation;
import com.tp.physics.RigidBody;

public class TestGame extends Scene {

	public void init() {

        setMainRenderer(new Renderer3D());

		Mesh mesh = new Mesh("plane3.obj");

		Material material2 = new Material(new Texture("crate.png"), 1f, 1, new Texture("crate_normal.png"), new Texture("default_disp.png"), 0.03f, -0.5f);

		Material material1 = new Material(new Texture("bricks2.jpg"), .3f, 1, new Texture("bricks2_normal.jpg"), new Texture("bricks2_disp.jpg"), .03f, -.50f);

		Material material = new Material(new Texture("bricks.jpg"), .3f, 1, new Texture("bricks_normal.jpg"), new Texture("bricks_disp.png"), 0.04f, -1.0f);

		Mesh tempMesh = new Mesh("crate.obj");

		MeshRenderer meshRenderer = new MeshRenderer(mesh, material1);

		GameObject planeObject = new GameObject();
		planeObject.addComponent(meshRenderer);
		planeObject.getTransform().getPos().set(0, 0, 0);
		// planeObject.getTransform().getScale().set(3, 3, 3);

		GameObject directionalLightObject = new GameObject();
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(1f, 1f, 1f), 1f);
		directionalLightObject.addComponent(directionalLight);
		directionalLight.getTransform().rotate(new Vector3f(0, 0, 1), (float) Math.toRadians(0));
		 addObject(directionalLightObject);
		
//		GameObject pointLightObject = new GameObject();
//		PointLight l = new PointLight(new Vector3f(1, 1, 0.7f), 400f, new Attenuation(0, 0, 1f)) {
//			
//			@Override
//			public void update(float dt) {
//				this.color = this.color.lerp(new Vector3f((float) Math.random(), (float) Math.random(), (float) Math.random()), .1f);
//				super.update(dt);
//			}
//			
//		};
//		pointLightObject.addComponent(l);
//		addObject(pointLightObject);
//		pointLightObject.getTransform().getPos().set(0, 10, 0);

//		{
			GameObject pointLightObject = new GameObject();
			pointLightObject.addComponent(new PointLight(new Vector3f(0, 0.4f, .9f), .4f, new Attenuation(0, 0, .1f)));
			addObject(pointLightObject);
			pointLightObject.getTransform().getPos().set(0, 2, 0);
//			//
//			GameObject pointLightObject1 = new GameObject();
//			pointLightObject1.addComponent(new PointLight(new Vector3f(1, .7f, 0), .4f, new Attenuation(0, 0, .1f)));
//			addObject(pointLightObject1);
//			pointLightObject1.getTransform().getPos().set(3, 2, 0);
//			//
//			GameObject pointLightObject2 = new GameObject();
//			pointLightObject2.addComponent(new PointLight(new Vector3f(0, 0, 1), .4f, new Attenuation(0, 0, .1f)));
//			addObject(pointLightObject2);
//			pointLightObject2.getTransform().getPos().set(-3, 2f, 0);
//			//
//			GameObject pointLightObject3 = new GameObject();
//			pointLightObject3.addComponent(new PointLight(new Vector3f(1, 0.4f, 0), .4f, new Attenuation(0, 0, .1f)));
//			addObject(pointLightObject3);
//			pointLightObject3.getTransform().getPos().set(6, 2, 0);
//
//			GameObject pointLightObject4 = new GameObject();
//			pointLightObject4.addComponent(new PointLight(new Vector3f(1, 0.1f, 0.9f), .4f, new Attenuation(0, 0, .1f)));
//			addObject(pointLightObject4);
//			pointLightObject4.getTransform().getPos().set(-6, 2, 0);
//		}
//
//		{
//			SpotLight spotLight = new SpotLight(new Vector3f(1, 0.6f, 0.0f), 10f, new Attenuation(0, 0, 1f), .8f);
//
//			GameObject spotLightObject = new GameObject();
//			spotLightObject.addComponent(spotLight);
//
//			spotLightObject.getTransform().getPos().set(0, 2, 5);
//			spotLightObject.getTransform().setRot(new Quaternion(new Vector3f(1, 0, 0), (float) Math.toRadians(90.0f)));
//			spotLightObject.getTransform().rotate(new Vector3f(0, 0, 1), (float) Math.toRadians(-30.0f));
//			spotLightObject.addComponent(new GameComponent() {
//
//				float time = 0;
//
//				@Override
//				public void update(float dt) {
//					// time += dt;
//					getTransform().rotate(new Vector3f(0, 0, 1), 2 * dt);
//				}
//
//			});
//			addObject(spotLightObject);
//
//			SpotLight spotLight1 = new SpotLight(new Vector3f(0, 0.6f, 0.8f), 10f, new Attenuation(0, 0, 1f), .8f);
//
//			GameObject spotLightObject1 = new GameObject();
//			spotLightObject1.addComponent(spotLight1);
//
//			spotLightObject1.getTransform().getPos().set(0, 2, 5);
//			spotLightObject1.getTransform().setRot(new Quaternion(new Vector3f(1, 0, 0), (float) Math.toRadians(90.0f)));
//			spotLightObject1.getTransform().rotate(new Vector3f(0, 0, 1), (float) Math.toRadians(30.0f));
//			spotLightObject1.addComponent(new GameComponent() {
//
//				float time = 0;
//
//				@Override
//				public void update(float dt) {
//					// time += dt;
//					getTransform().rotate(new Vector3f(0, 0, 1), 2f * dt);
//				}
//
//			});
//			addObject(spotLightObject1);
//
//			SpotLight spotLight2 = new SpotLight(new Vector3f(0.9f, 0.3f, 0.2f), 10f, new Attenuation(0, 0, 1f), .8f);
//
//			GameObject spotLightObject2 = new GameObject();
//			spotLightObject2.addComponent(spotLight2);
//
//			spotLightObject2.getTransform().getPos().set(0, 2, 5);
//			spotLightObject2.getTransform().setRot(new Quaternion(new Vector3f(1, 0, 0), (float) Math.toRadians(90.0f)));
//			spotLightObject2.getTransform().rotate(new Vector3f(0, 0, 1), (float) Math.toRadians(-90.0f));
//			spotLightObject2.addComponent(new GameComponent() {
//
//				float time = 0;
//
//				@Override
//				public void update(float dt) {
//					// time += dt;
//					getTransform().rotate(new Vector3f(0, 0, 1), 2f * dt);
//				}
//
//			});
//			addObject(spotLightObject2);
//
//			SpotLight spotLight3 = new SpotLight(new Vector3f(0.2f, 0.9f, 0.3f), 10f, new Attenuation(0, 0, 1f), .8f);
//
//			GameObject spotLightObject3 = new GameObject();
//			spotLightObject3.addComponent(spotLight3);
//
//			spotLightObject3.getTransform().getPos().set(0, 2, 5);
//			spotLightObject3.getTransform().setRot(new Quaternion(new Vector3f(1, 0, 0), (float) Math.toRadians(90.0f)));
//			spotLightObject3.getTransform().rotate(new Vector3f(0, 0, 1), (float) Math.toRadians(-90.0f - 60.0f));
//			spotLightObject3.addComponent(new GameComponent() {
//
//				float time = 0;
//
//				@Override
//				public void update(float dt) {
//					// time += dt;
//					getTransform().rotate(new Vector3f(0, 0, 1), 2f * dt);
//				}
//
//			});
//			addObject(spotLightObject3);
//		}

		addObject(planeObject);

		GameObject testMesh3 = new GameObject()
		// .addComponent(new LookAtComponent())
				.addComponent(new MeshRenderer(tempMesh, material2));


		addObject(new GameObject().addComponent(new FreeLook(0.3f)).addComponent(new FreeMove(6.0f))
				.addComponent(new Camera3D((float) Math.toRadians(70.0f), (float) Window.getWidth() / (float) Window.getHeight(), 0.01f, 1000.0f)));

		cameraObject.getTransform().getPos().set(0.0f, 5.0f, -9.91f);
		// AddObject(


//        addObject(new GameObject().addComponent(new FreeLook(0.3f)).addComponent(new FreeMove(6.0f))
//                        .addComponent(new Camera2D(Window.getWidth() / 100, Window.getHeight() / 100, 100, -100)));

        addObject(testMesh3);

		testMesh3.getTransform().getPos().set(2, 0, 2);
		// testMesh3.getTransform().getScale().set(.3f, .3f, .3f);
		testMesh3.getTransform().setRot(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(-70.0f)));

		// int i;
		// for (i = 0; i < 7; i++) {


		for (int i = 0; i < 1; i++) {

			MeshRenderer r = new MeshRenderer(new Mesh("cube.obj"), material2);
//5629
			GameObject go = new GameObject().addComponent(r);

			go.getTransform().getPos().set(0, 10.0f, 0);
			go.addComponent(new RigidBody());
			
			addObject(go);

		}


		GameComponent c;

		// final int offset = i = 0;
		// final Vector3f posI = new Vector3f(0, -2, 0);

		// go.addComponent(new GameComponent() {
		//
		// float time = 0;
		//
		// @Override
		// public void update(float dt) {
		// time += 3 * dt;
		// getTransform().getPos().set(posI.add((new Vector3f(8 * (float)
		// Math.cos(time + offset), 0, 2 * (float) Math.sin(time +
		// offset))).mul(0.2f)));
		// }
		//
		// });
		//
		// r.getTransform().getScale().set(.1f, .1f, .1f);
		// }

	}
}
