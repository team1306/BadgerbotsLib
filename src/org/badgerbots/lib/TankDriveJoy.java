/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badgerbots.lib;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

/**
 * Control the robot using TankDrive with two joysticks.
 *
 * @author Finn
 */
public class TankDriveJoy extends TankDrive {

    public TankDriveJoy(Jaguar leftJag, Jaguar rightJag, double exponent, boolean reversed, double deadband, double maxSpeed, double precSpeed, Joystick left, Joystick right) {
        super(leftJag, rightJag, exponent, reversed, deadband, maxSpeed, precSpeed);
        this.left = left;
        this.right = right;
    }

    @Override
    protected double leftJoyPos() {
        return left.getY();
    }

    @Override
    protected double rightJoyPos() {
        return right.getY();
    }

    @Override
    protected boolean precisionLeft() {
        return left.getRawButton(3);
    }

    @Override
    protected boolean precisionRight() {
        return right.getRawButton(3);
    }

    private final Joystick left;
    private final Joystick right;

}
