package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardWithGyroEncoder extends Command {

	private double speed;
	private double setPoint;

	public DriveForwardWithGyroEncoder(int inchesToDrive) {
		requires(Robot.drive);
		this.speed = 0.8;
		this.setPoint = Constants.ticksPerInch * inchesToDrive;
	}

	public DriveForwardWithGyroEncoder(int inchesToDrive, double speed) {
		requires(Robot.drive);
		this.speed = speed;
		this.setPoint = Constants.ticksPerInch * inchesToDrive;
	}

	@Override
	protected void initialize() {
		Robot.drive.setMaxGyroOutput(0.8);
		Robot.drive.setTurnPIDSetpoint(Robot.drive.getGyroYaw());

		Robot.drive.resetEncoders();
		Robot.drive.setMaxDrivePIDOutput(speed);
		Robot.drive.setDrivePIDSetPoint(setPoint);

		Robot.drive.enableGyroPID();
		Robot.drive.enableDrivePID();
	}

	@Override
	protected void execute() {
		double leftOutput = Robot.drive.getLeftPIDOutput();
		double rightOutput = Robot.drive.getRightPIDOutput();
		double gyroOutput = Robot.drive.getGyroPIDOutput();
		double driveOutput = (leftOutput + rightOutput) / 2;

		/*
		 * A negative drive value for arcadeDrive makes the robot
		 * go forward
		 */
		Robot.drive.arcadeDrive(-driveOutput, gyroOutput, false);
	}

	@Override
	protected boolean isFinished() {
		double leftMotorPower = Robot.drive.getLeftMotorPower();
		// We removed the Math abs call from the error
		double error = Robot.drive.getLeftPIDError();

		return leftMotorPower <= 0.1 && error <= 50;
	}

	@Override
	protected void end() {
		Robot.drive.disableDrivePID();
		Robot.drive.disableGyroPID();
	}

	@Override
	protected void interrupted() {
		Robot.drive.disableDrivePID();
		Robot.drive.disableGyroPID();
	}
}