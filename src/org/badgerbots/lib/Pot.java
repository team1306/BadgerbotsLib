/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badgerbots.lib;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author Badge
 */
public class Pot
{
	public Pot (int channel)
	{
		aChannel = new AnalogChannel(channel);
		DEGREESPERVOLT = 1.0;
	}
	
	public Pot(int channel, double dpv)
	{
		aChannel = new AnalogChannel(channel);
		DEGREESPERVOLT = dpv;
	}
	
	public double get()
	{
		return aChannel.getVoltage();
	}
	
	public double getAngle()
	{
		return aChannel.getVoltage() * DEGREESPERVOLT;
	}
	
AnalogChannel aChannel;
final double DEGREESPERVOLT;

}
