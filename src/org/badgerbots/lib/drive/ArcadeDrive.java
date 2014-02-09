package org.badgerbots.lib.drive;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * An extension of the Drive class that, unlike TankDrive, uses one joystick to control both motors.
 * @author Finn
 */
public abstract class ArcadeDrive extends Drive {

    protected ArcadeDrive(SpeedController leftMotor, SpeedController rightMotor, double exponent, double deadband, double maxSpeed, double precSpeed) {
        super(leftMotor, rightMotor, exponent, deadband, maxSpeed, precSpeed);
    }

    protected abstract double joyX();

    protected abstract double joyY();

    protected abstract boolean precision();

    public void drive1() {

        double rotate = joyX();
        double move = joyY();

        if (Math.abs(rotate) > Math.abs(move)) {
            rotate += rotate - move;
        } else {
            move += move - rotate;
        }

        if (precision()) {
            leftMotor().set(-posToSpeed((move + rotate) / 2, precSpeed));
            rightMotor().set(posToSpeed((move - rotate) / 2, precSpeed));
        } else {
            leftMotor().set(-posToSpeed((move + rotate) / 2, maxSpeed));
            rightMotor().set(posToSpeed((move - rotate) / 2, maxSpeed));
        }
    }

    public void drive() {

        double rotate = joyX();
        double move = joyY();

        double radius = Math.sqrt(rotate * rotate + move * move);

        if (radius > 1) {
            rotate /= radius;
            move /= radius;
        }
        if (precision()) {
            leftMotor().set(-posToSpeed(move + rotate, precSpeed));
            rightMotor().set(posToSpeed(move - rotate, precSpeed));
        } else {
            leftMotor().set(-posToSpeed(move + rotate, maxSpeed));
            rightMotor().set(posToSpeed(move - rotate, maxSpeed));
        }
        leftMotor().set(-posToSpeed(move + rotate, maxSpeed));
        rightMotor().set(posToSpeed(move - rotate, maxSpeed));
    }

}
