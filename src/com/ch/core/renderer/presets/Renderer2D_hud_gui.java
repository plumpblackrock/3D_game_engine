package com.ch.core.renderer.presets;

import com.ch.core.GameObject;
import com.ch.core.renderer.Renderer2D;
import static org.lwjgl.opengl.GL11.*;

public class Renderer2D_hud_gui extends Renderer2D {

    @Override
    public void preSceneRender(GameObject object) {
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void clearScreen() {
        // Don't clear because HUD/GUI is an overlay, so previous renderer details should not be cleared.
    }

}
