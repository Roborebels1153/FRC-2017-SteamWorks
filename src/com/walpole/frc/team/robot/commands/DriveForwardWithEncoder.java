package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives forward a given distance without turn bias compensation
 */
public class DriveForwardWithEncoder extends Command {

	// TODO Consider increasing the speed?
	private double speed = 0.5;
	private double setPoint;

	public DriveForwardWithEncoder(int inchesToDrive) {
		requires(Robot.drive);
		
		setPoint = Constants.ticksPerInch * inchesToDrive;
	}

	@Override
	protected void initialize() {
		Robot.drive.disableGyroPID();

		Robot.drive.resetEncoders();
		Robot.drive.setMaxDrivePIDOutput(speed);
		Robot.drive.setDrivePIDSetPoint(setPoint);

		Robot.drive.enableDrivePID();
	}

	@Override
	protected void execute() {
		double leftOutput = Robot.drive.getLeftPIDOutput();
		double rightOutput = Robot.drive.getRightPIDOutput();
		
		Robot.drive.tankDrive(leftOutput, rightOutput);
	}

	@Override
	protected boolean isFinished() {
		double leftMotorPower = Robot.drive.getLeftMotorPower();
		double error = Robot.drive.getLeftPIDError();

		// TODO Consider using PIDController#setAbsoluteTolerance
		return leftMotorPower <= 0.1 && error <= 50;
	}

	@Override
	protected void end() {
		Robot.drive.disableDrivePID();
	}

	@Override
	protected void interrupted() {
		Robot.drive.disableDrivePID();
	}
}