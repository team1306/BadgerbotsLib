package org.badgerbots.lib.drive;

import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author Finn
 */
public abstract class ArcadeDrive extends Drive {

    protected ArcadeDrive(Jaguar leftJag, Jaguar rightJag, double exponent, double deadband, double maxSpeed, double precSpeed) {
        super(leftJag, rightJag, exponent, deadband, maxSpeed, precSpeed);
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
                leftJag().set(-posToSpeed((move + rotate) / 2, precSpeed));
                rightJag().set(posToSpeed((move - rotate) / 2, precSpeed));
            } else {
                leftJag().set(-posToSpeed((move + rotate) / 2, maxSpeed));
                rightJag().set(posToSpeed((move - rotate) / 2, maxSpeed));
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
        leftJag().set(-posToSpeed(move + rotate, maxSpeed));
        rightJag().set(posToSpeed(move - rotate, maxSpeed));
    }

}
