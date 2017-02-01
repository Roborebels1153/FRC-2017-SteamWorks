package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardWithEncoder extends Command {

    private double speed;
    private double inchesToDrive;
    private double setPoint;

    public DriveForwardWithEncoder(int inchesToDrive) {
	requires(Robot.driveSubsystem);
	this.speed = 0.85;
	this.inchesToDrive = inchesToDrive;
	this.setPoint = Constants.ticksPerInch * inchesToDrive;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.driveSubsystem.resetEncoders();
	Robot.driveSubsystem.enableDrivePID();
	Robot.driveSubsystem.setMaxDrivePIDOutput(speed, speed);
	Robot.driveSubsystem.setDrivePIDSetPoint(setPoint);
	Robot.driveSubsystem.setTurnPID(Robot.driveSubsystem.getGyroAngle());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	double leftMotorPower = Robot.driveSubsystem.getLeftMotorPower();
	double leftError = Robot.driveSubsystem.getLeftPIDError();
	boolean leftMotorFinished = leftMotorPower <= 0.1 && leftError <= 50;

	double rightMotorPower = Robot.driveSubsystem.getRightMotorPower();
	double rightError = Robot.driveSubsystem.getRightPIDError();
	boolean rightMotorFinished = rightMotorPower <= 0.1 && rightError <= 50;

	return leftMotorFinished && rightMotorFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
	Robot.driveSubsystem.stopDrive();
	Robot.driveSubsystem.disableDrivePID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
}