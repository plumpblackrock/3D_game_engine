package com.tp.math;

import com.ch.math.Vector3f;

/**
 * Created by timplump on 5/27/15.
 */
public class Derivative {

    public Vector3f vel;
    public Vector3f accel;

    /**
     * For speed purposes, I hard coded the value of 1/6th instead of having the program recalculate it
     * every time it needs it.
     */
    public static final float oneSixth = 0.16666667f;

    public Derivative(Vector3f vel, Vector3f accel) {
        this.vel = vel;
        this.accel = accel;
    }

    public Derivative() {
        this.vel = new Vector3f();
        this.accel = new Vector3f();
    }

    /**
     * @param a - The first RK4 vector
     * @param b - The second RK4 vector
     * @param c - The third RK4 vector
     * @param d - The fourh RK4 vector
     * @return The weighted average (with weights: 1,2,2,1) of the four vectors
     */
    public static Derivative rk4Avg(Derivative a, Derivative b, Derivative c, Derivative d) {
        Derivative avg = new Derivative();
        avg.vel = (a.vel.add( (b.vel.add(c.vel)).mul(2.0f) ).add(d.vel)).mul(oneSixth);//(a + 2b + 2c + d) / 6
        avg.accel = (a.accel.add( (b.accel.add(c.accel)).mul(2.0f) ).add(d.accel)).mul(oneSixth);//(a + 2b + 2c + d) / 6
        return avg;
    }
}
