package org.badgerbots.lib;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author Max Vrany
 */
public class LimitSwitch {
	public LimitSwitch (int port)
	{
		input = new DigitalInput (port);
	}

	public boolean get()
	{
		return input.get();
	}
DigitalInput input;
}
