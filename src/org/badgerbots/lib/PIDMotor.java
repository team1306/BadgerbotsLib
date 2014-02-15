/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badgerbots.lib;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;

/**
 * Represents a motor controlled with PID. It contains a Jaguar motor and an
 * encoder. Because it implements SpeedController (as does Jaguar), it contains
 * the same methods as Jaguar. Jaguar can be effectively replaced with PIDMotor.
 * Currently, we are only implementing the P (proportional) part, not I or D.
 *
 * @author Finn
 */
public class PIDMotor implements SpeedController {

    private final Encoder encoder;
    private final Jaguar motor;
    private double p, i, d;

    private double lastSpeed;

    /**
     * Creates a new PIDMotor with the given motor, encoder, and PID constants.
     *
     * @param jag The Jaguar motor to control
     * @param enc The encoder that's associated with the motor
     * @param p The proportional constant
     * @param i The integral constant. Currently unsupported.
     * @param d The derivative constant. Currently unsupported.
     */
    public PIDMotor(Jaguar jag, Encoder enc, double p, double i, double d) {
        motor = jag;
        encoder = enc;
        encoder.start();
        lastTime = Timer.getFPGATimestamp();
        this.p = p;
        this.i = 0.0;
        this.d = 0.0;
        lastSpeed = 0.0;
        if (i != 0) {
            throw new IllegalArgumentException("i is not yet supported");
        }
        if (d != 0) {
            throw new IllegalArgumentException("d is not yet supported");
        }
    }

    /**
     * Gets the current encoder rate
     *
     * @return the average rate of the encoder since this method was last called
     */
    public double getEncoderRate() {
        double distance = encoder.getDistance();
        double time = lastTime - Timer.getFPGATimestamp();

        lastTime = Timer.getFPGATimestamp();
        encoder.reset();

        return distance / time;
    }
    private double lastTime;

    /**
     * Sets the speed of the motor, using PID calculation.
     *
     * @param speed
     */
    public void set(double speed) {

        double expected = speed;
        double error = getEncoderRate() - expected;
        double pValue = error * p;

        lastSpeed += pValue;
        motor.set(lastSpeed);
    }

    public void setP(double p) {
        this.p = p;
    }

    public double get() {
        return lastSpeed;
    }

    public void set(double speed, byte syncGroup) {
        throw new java.lang.IllegalArgumentException("Do not use set(double, byte). Use set(double) instead.");
    }

    public void disable() {
        motor.disable();
    }

    public void pidWrite(double output) {
        motor.pidWrite(output);
    }
}
