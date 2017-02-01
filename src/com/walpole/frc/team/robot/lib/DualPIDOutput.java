package com.walpole.frc.team.robot.lib;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class DualPIDOutput implements PIDOutput {

    private SpeedController victorA;
    private SpeedController victorB;
    private boolean backwards;

    public DualPIDOutput(SpeedController victorA, SpeedController victorB, boolean backwards) {
	this.victorA = victorA;
	this.victorB = victorB;
	this.backwards = backwards;
    }

    public void pidWrite(double output) {
	if (backwards) {
	    output = -output;
	}

	victorA.set(output);
	victorB.set(output);
    }
}