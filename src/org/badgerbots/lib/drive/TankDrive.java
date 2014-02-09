/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badgerbots.lib.drive;

import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author Finn
 */
public abstract class TankDrive extends Drive {

    public TankDrive(SpeedController leftMotor, SpeedController rightMotor, double exponent, double deadband, double maxSpeed, double precSpeed) {
        super(leftMotor, rightMotor, exponent, deadband, maxSpeed, precSpeed);
    }

    /**
     * Gets the position of the left joystick. Overridden by subclasses to allow
     * for control by large joysticks or XBox joysticks.
     *
     * @return position of the left joystick, from -1.0 to 1.0
     */
    protected abstract double leftJoyPos();

    protected abstract double rightJoyPos();

    /**
     * Finds whether the left joystick is in precision mode
     *
     * @return true if precision mode is on
     */
    protected abstract boolean precisionLeft();

    protected abstract boolean precisionRight();

    /**
     * Drives the robot. It sets motor speeds based on calculations from
     * posToSpeed(). The left motor is reversed so that one motor spins
     * clockwise while the other spins counterclockwise, making the robot drive
     * straight. If "reversed" is set to true, it reverses both motors.
     */
    public void drive() {

        if (precisionLeft()) {

            leftMotor().set(posToSpeed(-leftJoyPos(), precSpeed));
        } else {

            leftMotor().set(posToSpeed(-leftJoyPos(), maxSpeed));
        }
        if (precisionRight()) {

            rightMotor().set(posToSpeed(rightJoyPos(), precSpeed));

        } else {

            rightMotor().set(posToSpeed(rightJoyPos(), maxSpeed));
        }
    }
}
