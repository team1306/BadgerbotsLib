/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badgerbots.lib.drive;

import edu.wpi.first.wpilibj.SpeedController;
import org.badgerbots.lib.XBoxController;

/**
 * Control the robot with the joysticks on the XBox controller.
 *
 * @author Finn
 */
public class TankDriveXbox extends TankDrive {

    public TankDriveXbox(SpeedController leftMotor, SpeedController rightMotor, double exponent, double deadband, double maxSpeed, double precSpeed, XBoxController control) {
        super(leftMotor, rightMotor, exponent, deadband, maxSpeed, precSpeed);
        this.control = control;
    }

    protected double leftJoyPos() {
        return control.getLeftJoyY();
    }

    protected double rightJoyPos() {
        return control.getRightJoyY();
    }

    protected boolean precisionLeft() {
        return control.getButtonLS();
    }

    protected boolean precisionRight() {
        return control.getButtonRB();
    }

    private final XBoxController control;

}
