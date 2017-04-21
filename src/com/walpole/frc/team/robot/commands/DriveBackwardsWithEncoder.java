package com.walpole.frc.team.robot.commands;

/**
 * Drives backwards a given distance without turn bias compensation
 */
public class DriveBackwardsWithEncoder extends DriveForwardWithEncoder {

	public DriveBackwardsWithEncoder(int inchesToDrive) {
		// Use the DriveForwardWithEncoder command, with a negative setpoint
		super(-inchesToDrive);
	}
}