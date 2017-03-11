package com.walpole.frc.team.robot.lib;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Victor;

/**
 * A PIDOutput that will write the output of a PID to two different
 * motors, taking into consideration the appropriate negation and
 * setting motorA and motorB to opposite motor power values
 * 
 * @author Brigham
 */
public class DualPIDOutput implements PIDOutput {
	
	private Victor motorA;
	private Victor motorB;
	
	public DualPIDOutput(Victor motorA, Victor motorB) {
		this.motorA = motorA;
		this.motorB = motorB;
	}

	@Override
	public void pidWrite(double output) {
		motorA.set(output);
		motorB.set(-output);
	}
}