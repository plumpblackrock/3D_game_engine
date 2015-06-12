package com.ch.core.scene.templates;

import com.ch.core.renderer.Renderer2D;
import com.ch.core.scene.Scene;
import com.ch.rendering.components.Camera2D;

public abstract class Standard2D extends Scene {

    protected Camera2D mainCam;

    @Override
    public final void init() {

        mainCam = createCamera2D();

        setMainRenderer(createRenderer2D());

        childInit();

    }

    public abstract Camera2D createCamera2D();

    public abstract Renderer2D createRenderer2D();

    public abstract void childInit();

}
