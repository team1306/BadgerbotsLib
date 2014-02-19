/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badgerbots.lib.drive;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import org.badgerbots.lib.PIDMotor;

/**
 *
 * @author Finn
 */
public abstract class Drive {

    /**
     * Creates a new TankDrive object.
     *
     * @param leftMotor left Jaguar
     * @param rightMotor right Jaguar
     * @param exponent This is the exponent used in the calculation of motor
     * speeds. If this is 1.0, then there is a linear relationship between the
     * joystick position and the motor speed. If it is 2.0, it's parabolic, etc.
     * @param deadband the size of the joystick zone in which the speed is 0
     * @param maxSpeed the maximum speed the robot can go, from 0.0 to 1.0.
     * Default: 1.0
     * @param precSpeed the maximum speed while the robot is in precision mode.
     * Default: 0.2
     */
    protected Drive(SpeedController leftMotor, SpeedController rightMotor, double exponent, double deadband, double maxSpeed, double precSpeed) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;

        this.exponent = exponent;
        reverse = 1;
        invert = 1;

        this.deadband = deadband;
        this.maxSpeed = maxSpeed;
        this.precSpeed = precSpeed;
    }


    public abstract void drive();

    public void invert() {
        SpeedController dummyMotor = leftMotor;
        leftMotor = rightMotor;
        rightMotor = dummyMotor;
        invert = -invert;
    }
    
    public void reverse() {
        reverse = -reverse;
    }

    /**
     * Changes the exponent.
     *
     * @param exponent the new exponent
     */
    void setExponent(double exponent) {
        this.exponent = exponent;
    }

    protected double exponent() {
        return exponent;
    }

    protected SpeedController leftMotor() {
        return leftMotor;
    }

    protected SpeedController rightMotor() {
        return rightMotor;
    }

    /**
     * Finds the velocity for the motor in terms of joyPos, deadband, maxSpeed,
     * and exponent. The math here is a little confusing, and it is designed so
     * that the motors don't start turning until the joystick leaves the
     * deadband. At full throttle, the speed is "max." Between those points, the
     * relationship is a curve, and the degree is "exponent."
     *
     * @param joyPos position of the joystick
     * @param max the maximum speed that the motor can go, usually maxSpeed or
     * precSpeed
     * @return the speed to set the Jaguar to
     */
    protected double posToSpeed(double joyPos, double max) {
        double speed;
        if (Math.abs(joyPos) <= deadband) {
            speed = 0.0;
        } else if (joyPos > deadband) {
            speed = max * MathUtils.pow(joyPos - deadband, exponent) / MathUtils.pow(1 - deadband, exponent);
        } else {
            speed = -max * MathUtils.pow(-joyPos - deadband, exponent) / MathUtils.pow(1 - deadband, exponent);
        }

        return reverse*speed;

    }

    protected void setRight(double joyPos, double max) {
        rightMotor.set(posToSpeed(joyPos, max));
    }

    protected void setLeft(double joyPos, double max) {
        leftMotor.set(posToSpeed(joyPos, max));
    }

    private double exponent;

    private SpeedController leftMotor, rightMotor;
    private int reverse, invert;
    protected final double deadband, maxSpeed, precSpeed;
}