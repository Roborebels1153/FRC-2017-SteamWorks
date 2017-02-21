package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveBackwardsWithEncoder extends Command {

    private double speed;
    private double setPoint;
    private int inchesToDrive; 
    public DriveBackwardsWithEncoder(int inchesToDrive) {
	requires(Robot.drive);
	this.speed = 0.85;
	this.inchesToDrive = inchesToDrive;
	this.setPoint = Constants.ticksPerInch * inchesToDrive;
    }
    public DriveBackwardsWithEncoder(int inchesToDrive, double speed) {
	requires(Robot.drive);
	this.speed = speed;
	this.setPoint = Constants.ticksPerInch * inchesToDrive;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.drive.resetEncoders();
	Robot.drive.enableDrivePID();
	Robot.drive.setMaxDrivePIDOutput(speed);
	Robot.drive.setDrivePIDSetPoint(setPoint);
	Robot.drive.setTurnPIDSetpoint(Robot.drive.getGyroAngle());

	// Robot.drive.convertInchesToTicks(2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	double leftOutput = Robot.drive.getLeftPIDOutput();
	double rightOutput = Robot.drive.getRightPIDOutput();

	Robot.drive.driveAtSpeed(speed);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	// double leftMotorPower = Robot.drive.getLeftMotorPower();
	// double leftError = Robot.drive.getLeftPIDError();
	// boolean leftMotorFinished = leftMotorPower <= 0.1 && leftError <= 50;
	//
	// double rightMotorPower = Robot.drive.getRightMotorPower();
	// double rightError = Robot.drive.getRightPIDError();
	// boolean rightMotorFinished = rightMotorPower <= 0.1 && rightError <=
	// 50;
	//
	// return leftMotorFinished && rightMotorFinished;
	return Robot.drive.isOnTarget();

    }

    // Called once after isFinished returns true
    protected void end() {
	Robot.drive.stopDrive();
	Robot.drive.disableDrivePID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
}
