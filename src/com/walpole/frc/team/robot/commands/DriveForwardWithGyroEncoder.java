package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives forward a given distance with turn bias compensation
 */
public class DriveForwardWithGyroEncoder extends Command {

	// Default speed is 0.8
	private double speed = 0.8;
	private double turnSpeed = 0.8;
	private double setPoint;

	public DriveForwardWithGyroEncoder(int inchesToDrive) {
		requires(Robot.drive);
		
		setPoint = Constants.ticksPerInch * inchesToDrive;
	}

	public DriveForwardWithGyroEncoder(int inchesToDrive, double speed) {
		requires(Robot.drive);
		
		this.speed = speed;
		setPoint = Constants.ticksPerInch * inchesToDrive;
	}

	
	@Override
	protected void initialize() {
		Robot.drive.setMaxGyroOutput(turnSpeed);
		Robot.drive.setTurnPIDSetpoint(0);

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
		 * A negative drive power will make the robot go forward, false
		 * removes squared inputs which are bad for autonomous
		 */
		Robot.drive.arcadeDrive(-driveOutput, gyroOutput, false);
	}

	@Override
	protected boolean isFinished() {
		double leftMotorPower = Robot.drive.getLeftMotorPower();
		double rightMotorPower = Robot.drive.getRightMotorPower();
		double motorPower = (leftMotorPower + rightMotorPower) / 2;
		double error = Math.abs(Robot.drive.getLeftPIDError());
		
		return motorPower <= 0.1 && error <= 50;
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