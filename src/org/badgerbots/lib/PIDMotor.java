/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badgerbots.lib;

import edu.wpi.first.wpilibj.*;

/**
 *
 * @author james
 */
public class PIDMotor
{
	private Encoder encoder;
	private Jaguar motor;
	private double p, i, d;
	private double s;
	
	public PIDMotor(int channel, int a, int b) {
		motor = new Jaguar(channel);
		encoder = new Encoder(a, b);
	}
	
	public PIDMotor(Jaguar jag, Encoder enc) {
		motor = jag;
		encoder = enc;
	}
	
	public void setK(double kp, double ki, double kd) {
		p = kp;
		i = ki;
		d = kd;
	}
	
	public void setSpeed(double speed) {
		double e = speed - encoder.getRate();
		s = s + p*e + 
	}
}
