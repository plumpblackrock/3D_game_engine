package com.ch.components;

import com.ch.input.Input;
import com.ch.math.Vector3f;

public class FreeMove extends GameComponent {
	
	private float speed;
	private int forwardKey;
	private int backKey;
	private int leftKey;
	private int rightKey;

	public FreeMove(float speed) {
		this(speed, Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D);
	}

	public FreeMove(float speed, int forwardKey, int backKey, int leftKey, int rightKey) {
		this.speed = speed;
		this.forwardKey = forwardKey;
		this.backKey = backKey;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
	}

	@Override
	public void input(float dt) {
		float movAmt = speed * dt;

		if (Input.GetKey(forwardKey))
			move(getTransform().getRot().getForward(), movAmt);
		if (Input.GetKey(backKey))
			move(getTransform().getRot().getForward(), -movAmt);
		if (Input.GetKey(leftKey))
			move(getTransform().getRot().getLeft(), movAmt);
		if (Input.GetKey(rightKey))
			move(getTransform().getRot().getRight(), movAmt);
	}

	private void move(Vector3f dir, float amt) {
		getTransform().setPos(getTransform().getPos().add(dir.mul(amt)));
	}
	
}
