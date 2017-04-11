package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardWithGyroEncoder extends Command {

    private double speed;
    private double setPoint;
    private long startTimeMillis;
    private double secondsToDrive;
    
    public DriveForwardWithGyroEncoder(double inchesToDrive, double speed) {
	requires(Robot.drive);
	requires(Robot.floorGear);
	this.speed = speed;
	this.setPoint = Constants.ticksPerInch * inchesToDrive;
	//this.secondsToDrive = secondsToDrive;
    }
    
    public DriveForwardWithGyroEncoder(double inchesToDrive, double speed, double secondsToDrive) {
	requires(Robot.drive);
	requires(Robot.floorGear);
	this.speed = speed;
	this.setPoint = Constants.ticksPerInch * inchesToDrive;
	this.secondsToDrive = secondsToDrive;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.drive.setMaxGyroOutput(0.8);
	Robot.drive.setTurnPIDSetpoint(Robot.drive.getGyroYaw());
	//startTimeMillis = System.currentTimeMillis();
	
	Robot.drive.disableDrivePID();
	Robot.drive.disableGyroPID();
	
	Robot.drive.resetEncoders();
	Robot.drive.setMaxDrivePIDOutput(speed);
	Robot.drive.setDrivePIDSetPoint(setPoint);
	
	
	
	//Robot.floorGear.setMotorValue(-0.1);
	
	Robot.drive.enableGyroPID();
	Robot.drive.enableDrivePID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	double leftOutput = Robot.drive.getLeftPIDOutput();
	double rightOutput = Robot.drive.getRightPIDOutput();
	double gyroOutput = Robot.drive.getGyroPIDOutput(); 
	double driveOutput = (leftOutput + rightOutput) / 2;
	
	//The 
	Robot.drive.arcadeDrive(-leftOutput, gyroOutput, false);
	Robot.floorGear.stayInPosition();
	//This makes it goes forward
	//Robot.drive.arcadeDrive(-Robot.drive.getLeftPIDOutput(), 0);
	//Robot.drive.arcadeTankDrive(leftOutput, rightOutput, gyroOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double leftMotorPower = Robot.drive.getLeftMotorPower();
    	double error = Math.abs(Robot.drive.getLeftPIDError());
//    	if ((leftMotorPower <= 0.1 && error <= 50) || System.currentTimeMillis() - startTimeMillis >= secondsToDrive * 1000) {
//    		return true;
//    	} else {
//    		return false; 
//    	}
    	return leftMotorPower <= 0.1 && error <= 75; 
    	
    	// Use While Tuning
//    	return false;
    }
    

    // Called once after isFinished returns true
    protected void end() {
	//Robot.driveSubsystem.stopDrive();
	Robot.drive.disableDrivePID();
	Robot.drive.disableGyroPID();
//	Robot.floorGear.setGearMotor(-0.1);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	//Robot.driveSubsystem.stopDrive();
	Robot.drive.disableDrivePID();
	Robot.drive.disableGyroPID();
    }
}