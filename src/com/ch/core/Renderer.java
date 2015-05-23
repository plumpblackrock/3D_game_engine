package com.ch.core;

import com.ch.math.Vector3f;
import com.ch.rendering.Material;
import com.ch.rendering.components.Camera;
import com.ch.rendering.components.light.Light;
import com.ch.rendering.light.Shader;
import com.ch.util.MappedValues;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;

public class Renderer extends MappedValues {

	private HashMap<String, Integer> samplerMap;
	private ArrayList<Light> lights;
	private Light activeLight;

	private Shader forwardAmbient;
	private Camera mainCamera;

	public Renderer() {
		super();
		lights = new ArrayList<Light>();
		samplerMap = new HashMap<String, Integer>();
		samplerMap.put("diffuse", 0);
		samplerMap.put("normalMap", 1);
		samplerMap.put("dispMap", 2);

		addVector3f("ambient", new Vector3f(0.2f, 0.2f, 0.2f));

		forwardAmbient = new Shader("forward-ambient");

		// night
		glClearColor(0.0f, 0.0f, .2f, 1f);
		// day
		// glClearColor(0.0f, 0.8f, 1f, 1f);

		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);

		// glPolygonMode(GL_FRONT, GL_LINE);

		//
		// glEnable(GL_DEPTH_CLAMP);

		glEnable(GL_TEXTURE_2D);
	}

	public void updateUniformStruct(Transform transform, Material material, Shader shader, String uniformName, String uniformType) {
		throw new IllegalArgumentException(uniformType + " is not a supported type in Renderer");
	}

	public void render(GameObject object) {
		
		if (getMainCamera() == null) {
			new RuntimeException("There is no main camera").printStackTrace();
			System.exit(1);
		}

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		object.renderAll(forwardAmbient, this);

		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE);
		glDepthMask(false);
		glDepthFunc(GL_EQUAL);

		for (Light light : lights) {
			activeLight = light;
			object.renderAll(light.getShader(), this);
		}

		glDepthFunc(GL_LESS);
		glDepthMask(true);
		glDisable(GL_BLEND);
	}

	public static String getOpenGLVersion() {
		return glGetString(GL_VERSION);
	}

	public void addLight(Light light) {
		lights.add(light);
	}

	public void addCamera(Camera camera) {
		mainCamera = camera;
	}

	public int getSamplerSlot(String samplerName) {
		return samplerMap.get(samplerName);
	}

	public Light getActiveLight() {
		return activeLight;
	}

	public Camera getMainCamera() {
		return mainCamera;
	}

	public void setMainCamera(Camera mainCamera) {
		this.mainCamera = mainCamera;
	}

}
