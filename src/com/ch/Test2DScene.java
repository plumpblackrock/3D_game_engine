package com.ch;

import com.ch.components.FreeLook;
import com.ch.components.FreeMove;
import com.ch.core.*;
import com.ch.math.Vector2f;
import com.ch.rendering.Material;
import com.ch.rendering.Mesh;
import com.ch.rendering.Texture;
import com.ch.rendering.components.Camera2D;
import com.ch.rendering.components.Camera3D;
import com.ch.rendering.components.MeshRenderer;
import com.ch.rendering.light.Shader;

public class Test2DScene extends Scene {

    public void init() {

        this.setMainRenderer(new Renderer2D());

        GameObject go = new GameObject();

        Mesh quad = Mesh.quad2D(new Vector2f(0, 0), new Vector2f(300, 220));

        go.addComponent(new MeshRenderer(quad, new Material(new Texture("bricks.jpg"), .3f, 1, new Texture("bricks_normal.jpg"), new Texture("bricks_disp.png"), 0.04f, -1.0f)));

        addObject(go);

        addObject(new GameObject().addComponent(new Camera2D(Window.getWidth(), Window.getHeight(), 10, -10)));

    }

}
