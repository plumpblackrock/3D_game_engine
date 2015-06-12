package com.ch.rendering;

import com.ch.IO.IndexedModel;
import com.ch.IO.OBJModel;
import com.ch.math.Vector2f;
import com.ch.math.Vector3f;
import com.ch.resource.MeshResource;
import com.ch.util.Util;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Mesh {

	private static HashMap<String, MeshResource> loadedModels = new HashMap<String, MeshResource>();
	private MeshResource resource;
	private String name;

	public Mesh(String fileName) {
		this.name = fileName;
		MeshResource oldResource = loadedModels.get(fileName);

		if (oldResource != null) {
			resource = oldResource;
			resource.addReference();
		} else {
			loadMesh(fileName);
			loadedModels.put(fileName, resource);
		}
	}

	public Mesh(Vertex[] vertices, int[] indices, String name) {
		this(vertices, indices, false, name);
	}

	public Mesh(Vertex[] vertices, int[] indices, boolean calcNormals, String name) {
		this.name = name;
		addVertices(vertices, indices, calcNormals);
	}

    public static Mesh quad2D(Vector2f center, Vector2f dimensions) {

        Vector2f halfSides = dimensions.mul(.5f);

        Vertex[] vertices = {
                new Vertex(center.add(halfSides).as3DVector(), new Vector2f(1, 1)),
                new Vertex(center.add(-halfSides.getX(), halfSides.getY()).as3DVector(), new Vector2f(0, 1)),
                new Vertex(center.sub(halfSides).as3DVector(), new Vector2f(0, 0)),
                new Vertex(center.add(halfSides.getX(), -halfSides.getY()).as3DVector(), new Vector2f(1, 0)),
        };

        int[] indices = { 2, 0, 3, 2, 1, 0 };

        return new Mesh(vertices, indices, true, "quad2 " + center + " " + dimensions);

    }

	@Override
	protected void finalize() {
		if (resource.removeReference() && !name.isEmpty()) {
			loadedModels.remove(name);
		}
	}

	private void addVertices(Vertex[] vertices, int[] indices, boolean calcNormals) {
		if (calcNormals) {
			calcNormals(vertices, indices);
		}

		resource = new MeshResource(indices.length);

		glBindBuffer(GL_ARRAY_BUFFER, resource.getVbo());
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, resource.getIbo());
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
	}

	public void draw() {
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		glEnableVertexAttribArray(3);

		glBindBuffer(GL_ARRAY_BUFFER, resource.getVbo());
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 12);
		glVertexAttribPointer(2, 3, GL_FLOAT, false, Vertex.SIZE * 4, 20);
		glVertexAttribPointer(3, 3, GL_FLOAT, false, Vertex.SIZE * 4, 32);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, resource.getIbo());
		glDrawElements(GL_TRIANGLES, resource.getSize(), GL_UNSIGNED_INT, 0);

		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
		glDisableVertexAttribArray(3);
	}

	private void calcNormals(Vertex[] vertices, int[] indices) {
		for (int i = 0; i < indices.length; i += 3) {
			int i0 = indices[i];
			int i1 = indices[i + 1];
			int i2 = indices[i + 2];

			Vector3f v1 = vertices[i1].getPos().sub(vertices[i0].getPos());
			Vector3f v2 = vertices[i2].getPos().sub(vertices[i0].getPos());

			Vector3f normal = v1.cross(v2).normalized();

			vertices[i0].setNormal(vertices[i0].getNormal().add(normal));
			vertices[i1].setNormal(vertices[i1].getNormal().add(normal));
			vertices[i2].setNormal(vertices[i2].getNormal().add(normal));
		}

		for (int i = 0; i < vertices.length; i++)
			vertices[i].setNormal(vertices[i].getNormal().normalized());
	}

	private Mesh loadMesh(String fileName) {
		String[] splitArray = fileName.split("\\.");
		String ext = splitArray[splitArray.length - 1];

		if (!ext.equals("obj")) {
			System.err.println("Error: '" + ext + "' file format not supported for mesh data.");
			new Exception().printStackTrace();
			System.exit(1);
		}

		OBJModel test = new OBJModel("./res/models/" + fileName);
		IndexedModel model = test.toIndexedModel();

		ArrayList<Vertex> vertices = new ArrayList<Vertex>();

		for (int i = 0; i < model.getPositions().size(); i++) {
			vertices.add(new Vertex(model.getPositions().get(i), model.getTexCoords().get(i), model.getNormals().get(i), model.getTangents().get(i)));
		}

		Vertex[] vertexData = new Vertex[vertices.size()];
		vertices.toArray(vertexData);

		Integer[] indexData = new Integer[model.getIndices().size()];
		model.getIndices().toArray(indexData);

		addVertices(vertexData, Util.toIntArray(indexData), false);

		return this;
	}
}
