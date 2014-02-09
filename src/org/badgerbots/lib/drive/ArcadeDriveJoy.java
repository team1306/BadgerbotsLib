/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badgerbots.lib.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author Finn
 */
public class ArcadeDriveJoy extends ArcadeDrive {

    public ArcadeDriveJoy(SpeedController leftMotor, SpeedController rightMotor, double exponent, double deadband, double maxSpeed, double precSpeed, Joystick joystick) {
        super(leftMotor, rightMotor, exponent, deadband, maxSpeed, precSpeed);
        this.joystick = joystick;
    }

    protected double joyX() {
        return joystick.getX();
    }

    protected double joyY() {
        return joystick.getY();
    }

    protected boolean precision() {
        return joystick.getRawButton(3);
    }

    private final Joystick joystick;
}
