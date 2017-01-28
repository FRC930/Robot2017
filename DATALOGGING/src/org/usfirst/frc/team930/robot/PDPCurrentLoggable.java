package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;


public class PDPCurrentLoggable implements Loggable{

	private final PowerDistributionPanel PDP;
	public PDPCurrentLoggable(PowerDistributionPanel PDP) {
		this.PDP = PDP;
	}
	@Override
	public String log(int channel) {
		return Double.toString(PDP.getCurrent(channel));
	}

}


