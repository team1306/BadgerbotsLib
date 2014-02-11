/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badgerbots.lib;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;

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

    public PIDMotor(Jaguar jag, Encoder enc, double p, double i, double d) {
        motor = jag;
        encoder = enc;
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

    private double getMotorOutput(double speed) {
        double expected = speed;
        double error = expected - encoder.getRate();
        double pValue = error * p;

        lastSpeed += pValue;
        return lastSpeed;
    }

    public void set(double speed) {

        motor.set(getMotorOutput(speed));
    }

    public void setP(double p) {
        this.p = p;
    }

    public double get() {
        return lastSpeed;
    }

    public void set(double speed, byte syncGroup) {
        motor.set(getMotorOutput(speed), syncGroup);
    }

    public void disable() {
        motor.disable();
    }

    public void pidWrite(double output) {
        motor.pidWrite(output);
    }
}
