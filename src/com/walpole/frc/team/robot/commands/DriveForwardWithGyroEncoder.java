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

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.drive.setMaxGyroOutput(0.8);
	Robot.drive.setTurnPIDSetpoint(Robot.drive.getGyroYaw());
	
	Robot.drive.resetEncoders();
	Robot.drive.setMaxDrivePIDOutput(speed);
	Robot.drive.setDrivePIDSetPoint(setPoint);
	
	Robot.drive.enableGyroPID();
	Robot.drive.enableDrivePID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	double leftOutput = Robot.drive.getLeftPIDOutput();
	double rightOutput = Robot.drive.getRightPIDOutput();
	double gyroOutput = Robot.drive.getGyroPIDOutput(); 
	double driveOutput = (leftOutput + rightOutput) / 2;
	
	//You might have to negate the driveOutput to get Robot to drive forward 
	Robot.drive.arcadeDrive(-driveOutput, gyroOutput, false);
	//This makes it goes forward
	//Robot.drive.arcadeDrive(-Robot.drive.getLeftPIDOutput(), 0);
	//Robot.drive.arcadeTankDrive(leftOutput, rightOutput, gyroOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double leftMotorPower = Robot.drive.getLeftMotorPower();
    	double error = (Robot.drive.getLeftPIDError());
    	return leftMotorPower <= 0.1 && error <= 100; 
    }

    // Called once after isFinished returns true
    protected void end() {
	Robot.drive.disableDrivePID();
	Robot.drive.disableGyroPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	Robot.drive.disableDrivePID();
	Robot.drive.disableGyroPID();
    }
}
