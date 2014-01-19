/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badgerbots.lib;

import edu.wpi.first.wpilibj.Jaguar;

/**
 * Control the robot with the joysticks on the XBox controller.
 *
 * @author Finn
 */
public class TankDriveXbox extends TankDrive {

    public TankDriveXbox(Jaguar leftJag, Jaguar rightJag, double exponent, boolean reversed, double deadband, double maxSpeed, double precSpeed, XBoxController control) {
        super(leftJag, rightJag, exponent, reversed, deadband, maxSpeed, precSpeed);
        this.control = control;
    }

    @Override
    protected double leftJoyPos() {
        return control.getLeftJoyY();
    }

    @Override
    protected double rightJoyPos() {
        return control.getRightJoyY();
    }

    @Override
    protected boolean precisionLeft() {
        return control.getLeftTrigger() > 0.5;
    }

    @Override
    protected boolean precisionRight() {
        return control.getRightTrigger() > 0.5;
    }

    private final XBoxController control;

}
