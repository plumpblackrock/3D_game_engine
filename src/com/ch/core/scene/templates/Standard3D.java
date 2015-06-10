package com.ch.core.scene.templates;


import com.ch.core.renderer.Renderer3D;
import com.ch.core.scene.Scene;
import com.ch.rendering.components.Camera3D;

public abstract class Standard3D extends Scene {

    protected Camera3D mainCam;

    @Override
    public final void init() {

        mainCam = createCamera3D();

        setMainRenderer(createRenderer3D());

        childInit();

    }

    public abstract Camera3D createCamera3D();

    public abstract Renderer3D createRenderer3D();

    public abstract void childInit();

}
