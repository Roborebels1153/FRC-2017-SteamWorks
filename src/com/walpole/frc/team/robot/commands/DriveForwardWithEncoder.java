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
	this.setPoint = -(Constants.ticksPerInch * inchesToDrive);
    }

    protected void initialize() {
	Robot.drive.resetEncoders();
	double gyroStartingAngle = Robot.drive.getGyroAngle();
	Robot.drive.setTurnPIDSetpoint(gyroStartingAngle);
	Robot.drive.setMaxDrivePIDOutput(speed);
	Robot.drive.setDrivePIDSetPoint(setPoint);
	Robot.drive.enableDrivePID();
	Robot.drive.enableGyroPID();
    }

    protected void execute() {
	double gyroOutput = Robot.drive.getGyroPIDOutput();
	double leftOutput = Robot.drive.getLeftPIDOutput();
	double rightOutput = Robot.drive.getRightPIDOutput();
	
	Robot.drive.arcadeTankDrive(leftOutput, rightOutput, gyroOutput);
    }

    protected boolean isFinished() {
    	double leftMotorPower = Robot.drive.getLeftMotorPower();
    	double error = Math.abs(Robot.drive.getLeftPIDError());
    	//return leftMotorPower < 0.1 && error <= 280;
    	return false;
    }

    protected void end() {
	Robot.drive.disableGyroPID();
	Robot.drive.disableDrivePID();
    }

    protected void interrupted() {
	Robot.drive.disableGyroPID();
	Robot.drive.disableDrivePID();
    }
}