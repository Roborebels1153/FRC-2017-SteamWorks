package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveBackwardsWithSeconds extends Command {

	private long startTimeMillis;
	// TODO Consider increasing speed?
	private double speed = 0.6;
	private double secondsToDrive;

	public DriveBackwardsWithSeconds(double secondsToDrive) {
		requires(Robot.drive);
		
		this.secondsToDrive = secondsToDrive;
	}

	@Override
	protected void initialize() {
		startTimeMillis = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		Robot.drive.driveAtSpeed(-speed);
	}

	@Override
	protected boolean isFinished() {
		return System.currentTimeMillis() - startTimeMillis >= secondsToDrive * 1000;
	}

	@Override
	protected void end() {
		Robot.drive.stopDrive();
	}

	@Override
	protected void interrupted() {
		Robot.drive.stopDrive();
	}
}