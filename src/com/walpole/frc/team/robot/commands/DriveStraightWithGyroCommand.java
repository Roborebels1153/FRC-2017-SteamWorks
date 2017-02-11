
package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightWithGyroCommand extends Command {

    private double speed;

    public DriveStraightWithGyroCommand() {
	requires(Robot.driveSubsystem);
	this.speed = 0.6;
	Robot.driveSubsystem.setNotFinished();

	// Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.driveSubsystem.resetGyro();
	double gyroStartingAngle = Robot.driveSubsystem.getGyroAngle();
	Robot.driveSubsystem.setTurnPID(gyroStartingAngle);
	Robot.driveSubsystem.enableGyroPID();
	Robot.driveSubsystem.disableDrivePID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	double gyroOutput = Robot.driveSubsystem.getGyroPIDOutput();
	Robot.driveSubsystem.arcadeDrive(0.7, gyroOutput);
	// SmartDashboard.putNumber("Turn Speed", gyroOutput);
    }

    public boolean withinTargetValue(double targetValue, double errorTolerance, double actualValue) {
	if ((actualValue >= targetValue - errorTolerance) && (actualValue <= targetValue + errorTolerance)) {
	    return true;
	} else {
	    return false;
	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	// double currentGyroAngle = Robot.driveSubsystem.getGyroAngle();
	// boolean gyroFinished = currentGyroAngle <=92 && currentGyroAngle >=
	// 88;
	// boolean gyroFinished = Robot.driveSubsystem.getGyroPIDError() < 2;
	/*if ((Math.abs(Robot.driveSubsystem.getGyroPIDError())) < 2
		&& (Math.abs(Robot.driveSubsystem.getGyroPIDOutput()) <= 0.25)) {
	    Robot.driveSubsystem.setIsFinished();
	    // return true;
	    return false;
	} else {
	    return false;
	}*/
	return false;
	// return System.currentTimeMillis() - startTimeMillis >= 2 * 1000;
    }

    // Called once after isFinished returns true
    protected void end() {
	Robot.driveSubsystem.disableGyroPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	Robot.driveSubsystem.disableGyroPID();
    }
}
