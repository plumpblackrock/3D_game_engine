package com.ch.IO;

public class OBJIndex {
	
	private int vertexIndex;
	private int texCoordIndex;
	private int normalIndex;

	public int getVertexIndex() {
		return vertexIndex;
	}

	public int getTexCoordIndex() {
		return texCoordIndex;
	}

	public int getNormalIndex() {
		return normalIndex;
	}

	public void setVertexIndex(int val) {
		vertexIndex = val;
	}

	public void setTexCoordIndex(int val) {
		texCoordIndex = val;
	}

	public void setNormalIndex(int val) {
		normalIndex = val;
	}

	@Override
	public boolean equals(Object obj) {
		OBJIndex index = (OBJIndex) obj;

		return vertexIndex == index.vertexIndex && texCoordIndex == index.texCoordIndex && normalIndex == index.normalIndex;
	}
	
}
