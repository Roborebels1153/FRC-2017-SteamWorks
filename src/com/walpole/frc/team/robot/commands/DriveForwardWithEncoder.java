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
    	this.speed = 0.85;
    	this.inchesToDrive = inchesToDrive;
    	this.setPoint = Constants.ticksPerInch * inchesToDrive;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.resetEncoders();
    	Robot.drive.enablePID();
    	Robot.drive.setMaxDrivePIDOutput(speed);
    	Robot.drive.setDriveEncoderSetPoint(setPoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.driveSubsystem.driveAtSpeed(Robot.driveSubsystem.getLeftPIDOutput());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double leftMotorPower = Robot.drive.getLeftMotorPower();
    	double error = Robot.drive.getLeftPIDError();
    	return leftMotorPower <= 0.1 && error <= 50;   
    	//if the encoder tick count is above the target tick count, the motors will stop
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.stopDrive();
    	Robot.drive.disablePID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
   
    }
}