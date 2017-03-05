package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveBackwardsWithGyroEncoder extends DriveForwardWithGyroEncoder {

	public DriveBackwardsWithGyroEncoder(int inchesToDrive) {
		// Run DriveForwardWithGyroEncoder with a negative setpoint
		super(-inchesToDrive);
	}

	public DriveBackwardsWithGyroEncoder(int inchesToDrive, double speed) {
		// Run DriveForwardWithGyroEncoder with a negative setpoint
		super(-inchesToDrive, speed);
	}
}