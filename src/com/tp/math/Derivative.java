package com.tp.math;

import com.ch.math.Vector3f;

/**
 * Created by timplump on 5/27/15.
 */
public class Derivative {

    public Vector3f vel;//Also referred to as ds/dt
    public Vector3f accel;//Also referred to as dv/dt

    public Derivative(Vector3f vel, Vector3f accel) {
        this.vel = vel;
        this.accel = accel;
    }

    public Derivative() {
        this.vel = new Vector3f();
        this.accel = new Vector3f();
    }



}
