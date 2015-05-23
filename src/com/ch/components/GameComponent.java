package com.ch.components;

import com.ch.core.CoreEngine;
import com.ch.core.GameObject;
import com.ch.core.Renderer;
import com.ch.core.Transform;
import com.ch.rendering.light.Shader;

/**
 * 
 * @author Claude-Henry S. Rochenoir
 *
 */
public abstract class GameComponent {

	/**
	 * A reference of (pointer to) the object to witch this component has been
	 * added to. The function {@link GameObject#addComponent(GameComponent)} is
	 * required to call {@link GameComponent#setParent(GameObject)}
	 * <p>
	 * @see GameComponent#setParent(GameObject)
	 */
	protected GameObject parent;

	/**
	 * This function is to be overridden if necessary by children of this class
	 * to be able to handle input. This is called in the parent object's (
	 * {@link GameComponent#parent}) {@link GameObject#input(float)}
	 * <p>
	 * @param dt
	 *            the "time step": how much time has elapsed since the last game
	 *            cycle has been processed (in seconds).
	 */
	public void input(float dt) {
	};

	/**
	 * This function is to be overridden if necessary by children of this class
	 * to be able to handle general updates such as motion calculations or other
	 * tick calls. This is called in the parent object's (
	 * {@link GameComponent#parent}) {@link GameObject#update(float)}
	 * <p>
	 * @param dt
	 *            the "time step": how much time has elapsed since the last game
	 *            cycle has been processed (in seconds).
	 */
	public void update(float dt) {
	};

	/**
	 * This function is to be overridden if necessary by children of this class
	 * to be able to handle rendering. This is called in the parent object's (
	 * {@link GameComponent#parent}) {@link GameObject#render(Shader, Renderer)}
	 * <p>
	 * @param shader
	 *            the {@link Shader} that is to be used to render this object.
	 *            For example, if the object is to be renderer with a specific
	 *            <code>Light l;</code>, then {@link Light#getShader()} would be
	 *            passed to this function
	 * @param renderer
	 *            the current {@link Renderer} that contains the current OpenGL
	 *            rendering states as well as critical variables. See the
	 *            {@link Renderer} class for more information.
	 */
	public void render(Shader shader, Renderer renderer) {
	};

	/**
	 * This method should not be used outside of the {@link GameObjet} class,
	 * more specifically called in the
	 * {@link GameObject#addComponent(GameComponent)} function. I has for
	 * purpose to give the {@link GameComponent} object a reference to the
	 * Object it has been added to.
	 * <p>
	 * @param parent
	 *            a reference of the {@link GameObject} this component has been
	 *            added to. Sets {@link GameComponent#parent} equal to this
	 *            parameter.
	 */
	public void setParent(GameObject parent) {
		this.parent = parent;
	}

	/**
	 * This method gives access to the parent object's transform. This is due to
	 * the fact that components are are transform-less and world abstract.
	 * <p>
	 * @return The transform of the parent object.
	 */
	public Transform getTransform() {
		return parent.getTransform();
	}

	/**
	 * This method should not be used outside of the core libraries. The purpose
	 * of this method is to handle special case components that need to
	 * referenced by the {@link CoreEngine} class (or any of its critical
	 * variables like the {@link Renderer} class), such as the
	 * {@link com.ch.rendering.components.Camera} class.
	 * <p>
	 * @param engine
	 *            a reference to the current instance of the {@link CoreEngine}
	 *            class.
	 */
	public void addToEngine(CoreEngine engine) {
	};

}
