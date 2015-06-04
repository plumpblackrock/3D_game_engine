package com.ch.rendering;

import com.ch.util.MappedValues;

import java.util.HashMap;

public class Material extends MappedValues {

	private HashMap<String, Texture> textureHashMap;

	public Material(Texture diffuse, float specularIntensity, float specularPower, Texture normal, Texture dispMap, float dispMapScale, float dispMapOffset) {
		super();
		textureHashMap = new HashMap<String, Texture>();
		addTexture("diffuse", diffuse);
		addFloat("specularIntensity", specularIntensity);
		addFloat("specularPower", specularPower);
		addTexture("normalMap", normal);
		addTexture("dispMap", dispMap);

		float baseBias = dispMapScale / 2.0f;
		addFloat("dispMapScale", dispMapScale);
		addFloat("dispMapBias", -baseBias + baseBias * dispMapOffset);
	}

    public Material(Texture diffuse, float specularIntensity, float specularPower) {
        this(diffuse, specularIntensity, specularPower, new Texture("default_normal.jpg"), new Texture("default_disp.png"), 0, 0);
    }

    public Material(Texture diffuse) {
        this(diffuse, 1, 1);
    }

	public void addTexture(String name, Texture texture) {
		textureHashMap.put(name, texture);
	}

	public Texture getTexture(String name) {
		Texture result = textureHashMap.get(name);
		if (result != null)
			return result;

		return new Texture("test.png");
	}

}
