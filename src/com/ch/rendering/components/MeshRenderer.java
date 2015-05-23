package com.ch.rendering.components;

import com.ch.components.GameComponent;
import com.ch.core.Renderer;
import com.ch.rendering.Material;
import com.ch.rendering.Mesh;
import com.ch.rendering.light.Shader;

public class MeshRenderer extends GameComponent {
	
	private Mesh m_mesh;
	private Material m_material;

	public MeshRenderer(Mesh mesh, Material material) {
		this.m_mesh = mesh;
		this.m_material = material;
	}

	@Override
	public void render(Shader shader, Renderer renderingEngine) {
		shader.bind();
		shader.updateUniforms(getTransform(), m_material, renderingEngine);
		m_mesh.draw();
	}
	
}
