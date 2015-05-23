package com.tp.physics;

import com.ch.components.GameComponent;
import com.ch.math.Quaternion;
import com.ch.math.Vector3f;

public class RigidBody extends GameComponent {
	
	public float gameTime = 0.0f;//TEST
	public static float staticVar = 0;//TEST
	/**
	 * if you dont know what velocity is well you probably shouldn't be using this engine 
	 * <p>
	 * TODO write legitimate Javadocs <p>
	 * TODO change this
	 */
	private Vector3f velocity;
	private Vector3f netForce;
	
	private Quaternion angularVelocity;
	private Quaternion netTorque;
	
	private float inverseMass;
	
	public RigidBody() {
		
//		this.velocity = new Vector3f(((float) Math.random() * 10.0f) -5.0f, 
//				((float) Math.random() * 10.0f), ((float) Math.random() * 10.0f) -5.0f);
		this.velocity = new Vector3f(((float) Math.random() * 100.0f) - 50.0f, ((float) Math.random() * 30.0f) + 30.0f, ((float) Math.random() * 100.0f) - 0.0f );
		this.netForce = new Vector3f(0,0,0);
		gameTime += staticVar;
		staticVar++;
		this.angularVelocity = new Quaternion();
		this.netTorque = new Quaternion();
		
		this.inverseMass = 1.0f;//default for now
		this.addGravity();
	}
	
	public RigidBody(Vector3f velocity, Quaternion angularVelocity) {
		this.velocity = velocity;
		this.angularVelocity = angularVelocity;
	}
	 
	
	@Override
	public void update(float dt) {
//		System.out.println(gameTime);
		gameTime += dt;
		
		if (gameTime >= 15)
			this.updatePosition(dt);
		
		this.updateVelocity(dt);
				
//		this.getTransform().getPos().addSelf(velocity.mul(dt));
		
		//TEST \/
		//X
//		if (this.getTransform().getPos().getX() > 7) {
//			this.velocity.setX(- this.velocity.getX());
//			this.getTransform().getPos().setX(7.0f);
//
//		}
//		if (this.getTransform().getPos().getX() < -7) {
//			this.velocity.setX(- this.velocity.getX());
//			this.getTransform().getPos().setX(-7.0f);
//		}
		
		//Y
		if (this.getTransform().getPos().getY() < 0.0f ) {
			this.velocity.setY(- this.velocity.getY());
			this.getTransform().getPos().setY(0.0f);
		}
		
		//Z
//		if (this.getTransform().getPos().getZ() > 7) {
//			this.velocity.setZ(- this.velocity.getZ());
//			this.getTransform().getPos().setZ(7.0f);
//
//		}
//		if (this.getTransform().getPos().getZ() < -7) {
//			this.velocity.setZ(- this.velocity.getZ());
//			this.getTransform().getPos().setZ(-7.0f);
//		}
//		
			
			
	}
	
	/**
	 * Updates the position after a time interval dt.  This is using Euler Integration and should be improved later
	 * @param dt
	 */
	public void updatePosition(float dt) {
		this.getTransform().getPos().addSelfScaledVector(this.velocity, dt);;
	}
	
	/**
	 * 
	 * @param dt
	 */
	public void updateVelocity(float dt) {
		this.velocity.addSelfScaledVector(this.netForce, dt * this.inverseMass);
	}
		
	public void addForce(Vector3f force) {
		netForce.addSelf(force);
	}
	
	public void addGravity() {
		if (this.inverseMass != 0.0f)
			netForce.addSelf(new Vector3f(0.0f, -9.8f, 0.0f).mul(1.0f / this.inverseMass));
		else
			System.out.println("you can't add gravity to an object with infinite mass!");
	}
	
	public Vector3f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3f velocity) {
		this.velocity = velocity;
	}

	public Quaternion getAngularVelocity() {
		return angularVelocity;
	}

	public void setAngularVelocity(Quaternion angularVelocity) {
		this.angularVelocity = angularVelocity;
	}

	public Vector3f getNetForce() {
		return netForce;
	}

	public void setNetForce(Vector3f netForce) {
		this.netForce = netForce;
	}

	public Quaternion getNetTorque() {
		return netTorque;
	}

	public void setNetTorque(Quaternion netTorque) {
		this.netTorque = netTorque;
	}
	
}
