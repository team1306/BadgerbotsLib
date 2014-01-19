/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badgerbots.lib;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import org.badgerbots.lib.Drive;

/**
 *
 * @author Finn
 */
class TankDrive implements Drive {

    /**
     * Creates a new TankDrive object.
     *
     * @param leftJag left Jaguar
     * @param rightJag right Jaguar
     * @param leftJoy left Joystick
     * @param rightJoy right Joystick
     * @param mode linear or quadratic
     * @param reversed if true, forwards and backwards will be switched.
     * @param deadband the size of the joystick zone in which the speed is 0
     * @param maxSpeed the maximum speed the robot can go, from 0.0 to 1.0.
     * Default: 1.0
     * @param precSpeed the maximum speed while the robot is in precision mode.
     * Default: 0.2
     */
    TankDrive(Jaguar leftJag, Jaguar rightJag, Joystick leftJoy, Joystick rightJoy, double exponent, boolean reversed, double deadband, double maxSpeed, double precSpeed) {
        this.leftJag = leftJag;
        this.rightJag = rightJag;
        this.leftJoy = leftJoy;
        this.rightJoy = rightJoy;

        this.exponent = exponent;
        this.reversed = reversed;

        this.deadband = deadband;
        this.maxSpeed = maxSpeed;
        this.precSpeed = precSpeed;
    }

    void setPower(double exponent) {
        this.exponent = exponent;
    }

    /**
     * Drives the robot. It then calculates and sets motor speeds. The left
     * motor is reversed so that one motor spins clockwise while the other spins
     * counterclockwise, making the robot drive straight
     */
    @Override
    public void drive() {

        int rev = reversed ? -1 : 1;
        if (leftJoy.getRawButton(3) && rightJoy.getRawButton(3)) {
            leftJag.set(rev * posToSpeed(-leftJoy.getY(), precSpeed));
            rightJag.set(rev * posToSpeed(rightJoy.getX(), precSpeed));
        } else {
            leftJag.set(rev * posToSpeed(-leftJoy.getY(), maxSpeed));
            rightJag.set(rev * posToSpeed(rightJoy.getY(), maxSpeed));
        }
    }

    /**
     * Finds the velocity for the motor in terms of leftJoyPos, deadband, and
     * maxSpeed.
     *
     * @param joyPos position of the joystick
     * @param max the maximum speed that the motor can go, usually maxSpeed or precSpeed
     * @return the speed to set the Jaguar to
     */
    private double posToSpeed(double joyPos, double max) {
        if (Math.abs(joyPos) <= deadband) {
            return 0.0;
        } else if (joyPos > deadband) {
            return max * Math.pow(joyPos - deadband, exponent) / Math.pow(1 - deadband, exponent);
        } else {
            return -max * Math.pow(-joyPos - deadband, exponent) / Math.pow(1 - deadband, exponent);
        }

    }

    private double exponent;

    private final Jaguar leftJag;
    private final Jaguar rightJag;
    private final Joystick leftJoy;
    private final Joystick rightJoy;

    private final boolean reversed;

    private final double deadband;
    private final double maxSpeed;
    private final double precSpeed;

}
