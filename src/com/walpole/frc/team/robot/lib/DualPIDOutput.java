package com.walpole.frc.team.robot.lib;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class DualPIDOutput implements PIDOutput {
	
	private SpeedController victorA;
	private SpeedController victorB;
	
	public DualPIDOutput(SpeedController victorA, SpeedController victorB) {
		this.victorA = victorA;
		this.victorB = victorB;
	}
	
	public void pidWrite(double output) {
		victorA.set(output);
		victorB.set(output);
	}
}