package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardWithEncoder extends Command {

    private double speed;
    private double inchesToDrive;
    private double setPoint;

    public DriveForwardWithEncoder(int inchesToDrive) {
	requires(Robot.drive);
	this.speed = 0.5;
	this.inchesToDrive = inchesToDrive;
	this.setPoint = Constants.ticksPerInch * inchesToDrive;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.drive.disableGyroPID();
	Robot.drive.resetEncoders();	
	Robot.drive.setMaxDrivePIDOutput(speed, speed);
	Robot.drive.setDrivePIDSetPoint(setPoint);
	Robot.drive.enableDrivePID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	// double leftMotorPower = Robot.driveSubsystem.getLeftMotorPower();
	// double leftError = Robot.driveSubsystem.getLeftPIDError();
	// boolean leftMotorFinished = leftMotorPower <= 0.1 && leftError <= 50;
	//
	// double rightMotorPower = Robot.driveSubsystem.getRightMotorPower();
	// double rightError = Robot.driveSubsystem.getRightPIDError();
	// boolean rightMotorFinished = rightMotorPower <= 0.1 && rightError <=
	// 50;
	//
	// return leftMotorFinished && rightMotorFinished;
	//return Robot.driveSubsystem.isOnTarget();
	// This is a new command that finishes DriveForwardWithEncoder when the
	// robot is on target
    	double leftMotorPower = Robot.drive.getLeftMotorPower();
    	double error = Robot.drive.getLeftPIDError();
    	return leftMotorPower <= 0.1 && error <= 50;   
    	//if the encoder tick count is above the target tick count, the motors will stop

    }

    // Called once after isFinished returns true
    protected void end() {

	//Robot.driveSubsystem.stopDrive();
	Robot.drive.disableDrivePID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	//Robot.driveSubsystem.stopDrive();
	Robot.drive.disableDrivePID();
    }
}