package com.tp.math;

import com.ch.math.Vector3f;
import com.tp.physics.RigidBody;

/**
 * Created by timplump on 5/26/15.
 *
 */
public class IntegrationHelper {

    /**
     * For speed purposes, 1/6th was hard-coded instead of having the program recalculate it
     * every time it needs it.
     */
    public static final float oneSixth = 0.16666667f;


    /**
     *
     * @param rb A {@link RigidBody} passed into the method in order to calculate the
     * @param dt The timedelta
     * @param derivative The previously evaluated RK4 {@link Derivative}.  See
     *      {@link com.tp.physics.RigidBody#integrate(float)} for more information
     * @return the evaluated, relevant RK4 derivative
     */
    public static Derivative evaluateRK4(RigidBody rb, float dt, Derivative derivative) {

        Vector3f acceleration = rb.calculateAcceleration(dt);//, rb.getTransform().getPos().add(derivative.vel.mul(dt)));
        return new Derivative(rb.getVelocity().add(derivative.accel.mul(dt)), acceleration);
    }

    /**
     * The 4 {@link Derivative} - a,b,c,d have both <b>vel</b> and <b>accel</b> vector attributes
     * @param a The first RK4 Derivative
     * @param b The second RK4 Derivative
     * @param c The third RK4 Derivative
     * @param d The fourth RK4 Derivative
     * @return The weighted average (with weights: 1,2,2,1) of the four Derivatives for both the <b>vel</b> and <b>accel</b> components.
     */
    public static Derivative rk4Avg(Derivative a, Derivative b, Derivative c, Derivative d) {
        Derivative avg = new Derivative();
        avg.vel = (a.vel.add( (b.vel.add(c.vel)).mul(2.0f) ).add(d.vel)).mul(oneSixth);//(a + 2b + 2c + d) / 6
        avg.accel = (a.accel.add( (b.accel.add(c.accel)).mul(2.0f) ).add(d.accel)).mul(oneSixth);//(a + 2b + 2c + d) / 6
        return avg;
    }
}
