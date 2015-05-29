package com.tp.physics;

import com.ch.components.GameComponent;
import com.ch.math.Quaternion;
import com.ch.math.Vector3f;
import com.tp.math.Derivative;
import com.tp.math.IntegrationHelper;

public class RigidBody extends GameComponent {

	private Vector3f velocity;
	private Vector3f netForce;
	
	private Quaternion angularVelocity;
	private Quaternion netTorque;
	
	private float inverseMass;

	//This constructor should only be used for tests!
	public RigidBody() {
		
//		this.velocity = new Vector3f(((float) Math.random() * 10.0f) -5.0f, 
//				((float) Math.random() * 10.0f), ((float) Math.random() * 10.0f) -5.0f);
		this.velocity = new Vector3f(((float) Math.random() * 10.0f) - 5.0f, ((float) Math.random() * 5.0f) + 2.5f, ((float) Math.random() * 10.0f) - 5.0f );
		this.netForce = new Vector3f(0,0,0);

		this.angularVelocity = new Quaternion();
		this.netTorque = new Quaternion();
		
		this.inverseMass = 1.0f;//default for now
		this.addGravity();
	}
	
	public RigidBody(Vector3f velocity, Quaternion angularVelocity) {
		this.velocity = velocity;
		this.angularVelocity = angularVelocity;
	}

	/**
	 * This method takes the current velocity, position and acceleration and uses them to estimate where a body will be
	 * at the beginning of the next frame.  It uses RK4 integration.
	 * @param dt - The timedelta between two frames
	 */
	public void integrate(float dt) {
		Derivative a = IntegrationHelper.evaluateRK4(this, 0, new Derivative());
		Derivative b = IntegrationHelper.evaluateRK4(this, dt*0.5f, a);
		Derivative c = IntegrationHelper.evaluateRK4(this, dt * 0.5f, b);
		Derivative d = IntegrationHelper.evaluateRK4(this, dt, c);

		Derivative avg = Derivative.rk4Avg(a, b, c, d);//performs an average of each of the RK4 iterations

		this.getTransform().addToPos(avg.vel.mul(dt));
		this.addToVel(avg.accel.mul(dt));

	}
	
	@Override
	public void update(float dt) {

		this.integrate(dt);
		if (this.getTransform().getPos().getY() < 0) {
			this.getTransform().getPos().setY(0.0f);
			this.velocity.setY(-this.velocity.getY());
		}
	}
		
	public void addForce(Vector3f force) {
		netForce.addSelf(force);
	}

	/**
	 * TODO - Change this to use a forceGenerator or something like that (later on)
	 * 		ALSO: try and make this adjustable (maybe devs want gravity to be different for each object or something.)
	 */
	public void addGravity() {
		if (this.inverseMass != 0.0f)
			netForce.addSelf(new Vector3f(0.0f, PhysicsUtil.EARTH_GRAVITY_ACC, 0.0f).mul(1.0f / this.inverseMass));
//		else System.out.println("You can't add gravity to an object with infinite mass!");
	}
	
	public Vector3f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3f velocity) {
		this.velocity = velocity;
	}

	/**
	 * This method takes a vector 'addVec' and adds it to the velocity vector (this.velocity)
	 * @param addVec - The vector that will be added to the velocity vector.
	 */
	public void addToVel(Vector3f addVec) { this.velocity.addSelf(addVec); }

	public Quaternion getAngularVelocity() {
		return angularVelocity;
	}

	public void setAngularVelocity(Quaternion angularVelocity) {
		this.angularVelocity = angularVelocity;
	}

	public Vector3f getNetForce() {
		return netForce;
	}

	public Vector3f getAcceleration() {
		return netForce.mul(inverseMass);
	}


	/**
	 * This method is used to calculate the acceleration on a body at a given moment in the timespan between two frames.
	 * @param time - we are 'time' seconds into the current frame
	 * @return the acceleration of the rigid body at that particular moment
	 */
	public Vector3f calculateAcceleration(float time) {

		return this.getAcceleration();

		//TODO
		//"it is crucial that you structure your simulation in such a way that it can calculate the acceleration or
		// force derivatives completely from inside this method given the current state and time, otherwise your
		// simulation cannot work with the RK4 integrator."
		//- http://gafferongames.com/game-physics/integration-basics/
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
