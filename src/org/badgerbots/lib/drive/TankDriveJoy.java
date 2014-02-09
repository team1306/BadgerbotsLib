/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badgerbots.lib.drive;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Control the robot using TankDrive with two joysticks.
 *
 * @author Finn
 */
public class TankDriveJoy extends TankDrive {

    public TankDriveJoy(SpeedController leftMotor, SpeedController rightMotor, double exponent, double deadband, double maxSpeed, double precSpeed, Joystick left, Joystick right) {
        super(leftMotor, rightMotor, exponent, deadband, maxSpeed, precSpeed);
        this.left = left;
        this.right = right;
    }

    protected double leftJoyPos() {
        return left.getY();
    }

    protected double rightJoyPos() {
        return right.getY();
    }

    protected boolean precisionLeft() {
        return left.getRawButton(3);
    }

    protected boolean precisionRight() {
        return right.getRawButton(3);
    }

    private final Joystick left;
    private final Joystick right;

}
