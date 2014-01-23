package org.badgerbots.lib;

import edu.wpi.first.wpilibj.Encoder;
import org.badgerbots.lib.drive.TankDrive;

public class EncoderSys {

    private final Encoder _leftEnc;
    private final Encoder _rightEnc;
    private final TankDrive drive;

    private static final double FEET_PER_COUNT = 0.0020138414446088418195273355021;

    public EncoderSys(int leftAChannel, int leftBChannel, int rightAChannel, int rightBChannel, TankDrive driver) {
        _leftEnc = new Encoder(leftAChannel, leftBChannel, true);
        _rightEnc = new Encoder(rightAChannel, rightBChannel, false);
        _leftEnc.setDistancePerPulse(FEET_PER_COUNT);
        _rightEnc.setDistancePerPulse(FEET_PER_COUNT);
        drive = driver;
    }

    public void start() {
        _leftEnc.start();
        _rightEnc.start();
    }

    public void stop() {
        _leftEnc.stop();
        _rightEnc.stop();
    }

    public void reset() {
        _leftEnc.reset();
        _rightEnc.reset();
    }

}
