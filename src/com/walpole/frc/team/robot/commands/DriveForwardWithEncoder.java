package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardWithEncoder extends Command {

    private double speed;
    private double setPoint;

    public DriveForwardWithEncoder(int inchesToDrive) {
	requires(Robot.drive);
	this.speed = 0.5;
	this.setPoint = Constants.ticksPerInch * inchesToDrive;
    }

    protected void initialize() {
	Robot.drive.disableGyroPID();
	Robot.drive.resetEncoders();	
	Robot.drive.setMaxDrivePIDOutput(speed);
	Robot.drive.setDrivePIDSetPoint(setPoint);
	Robot.drive.enableDrivePID();
    }

    protected void execute() {
	Robot.drive.arcadeDrive(-Robot.drive.getLeftPIDOutput(), 0);
    }

    protected boolean isFinished() {
    	double leftMotorPower = Robot.drive.getLeftMotorPower();
    	double error = Robot.drive.getLeftPIDError();
    	
    	// If the encoder tick count is above the target tick count, the motors will stop
    	return leftMotorPower <= 0.1 && error <= 50;
    }

    protected void end() {
	Robot.drive.disableDrivePID();
    }

    protected void interrupted() {
	Robot.drive.disableDrivePID();
    }
}