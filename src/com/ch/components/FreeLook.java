package com.ch.components;

import com.ch.core.Window;
import com.ch.input.Input;
import com.ch.math.Vector2f;
import com.ch.math.Vector3f;

public class FreeLook extends GameComponent {
	
	private static final Vector3f Y_AXIS = new Vector3f(0, 1, 0);

	private boolean mouseLocked = false;
	private float sensitivity;
	private int unlockMouseKey;

	public FreeLook(float sensitivity) {
		this(sensitivity, Input.KEY_ESCAPE);
	}

	public FreeLook(float sensitivity, int unlockMouseKey) {
		this.sensitivity = sensitivity;
		this.unlockMouseKey = unlockMouseKey;
	}

	@Override
	public void input(float dt) {
		Vector2f centerPosition = new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2);

		if (Input.GetKey(unlockMouseKey)) {
			Input.SetCursor(true);
			mouseLocked = false;
		}
		if (Input.GetMouseDown(0)) {
			Input.SetMousePosition(centerPosition);
			Input.SetCursor(false);
			mouseLocked = true;
		}

		if (mouseLocked) {
			Vector2f deltaPos = Input.GetMousePosition().sub(centerPosition);

			boolean rotY = deltaPos.getX() != 0;
			boolean rotX = deltaPos.getY() != 0;

			if (rotY)
				getTransform().rotate(Y_AXIS, (float) Math.toRadians(deltaPos.getX() * sensitivity));
			if (rotX)
				getTransform().rotate(getTransform().getRot().getRight(), (float) Math.toRadians(-deltaPos.getY() * sensitivity));

			if (rotY || rotX)
				Input.SetMousePosition(centerPosition);
		}
	}
	
}
