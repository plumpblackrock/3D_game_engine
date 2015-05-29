package com.tp.math;

import com.ch.math.Vector3f;
import com.tp.physics.RigidBody;

/**
 * Created by timplump on 5/26/15.
 *
 * This class is so that
 */
public class IntegrationHelper {




    public static Derivative evaluateRK4(RigidBody rb, float dt, Derivative derivative) {

        rb.getTransform().addToPos(derivative.vel.mul(dt));//s += v * t
        rb.addToVel(derivative.accel.mul(dt));//v += a * t


        return new Derivative(rb.getVelocity(), rb.calculateAcceleration(dt));
    }
}
