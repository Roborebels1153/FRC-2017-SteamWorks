
package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnWithGyroCommand extends Command {
    private double degreesToTurn;
    private double speed;
    private long startTimeMillis;

    public TurnWithGyroCommand(double degreesToTurn) {
	requires(Robot.drive);
	this.degreesToTurn = degreesToTurn;
	this.speed = 0.6;
	Robot.drive.setNotFinished();

	// Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.drive.resetGyro();
	Robot.drive.setTurnPID(degreesToTurn);
	Robot.drive.enableGyroPID();
	Robot.drive.disableDrivePID();
	// startTimeMillis = System.currentTimeMillis();
	// Robot.driveSubsystem.setMaxGyroOutput(speed);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	double gyroOutput = Robot.drive.getGyroPIDOutput();
	Robot.drive.setTurnSpeed(gyroOutput);
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
	if ((Math.abs(Robot.drive.getGyroPIDError())) < 2
		&& (Math.abs(Robot.drive.getGyroPIDOutput()) <= 0.25)) {
	    Robot.drive.setIsFinished();
	    // return true;
	    return false;
	} else {
	    return false;
	}
	// return System.currentTimeMillis() - startTimeMillis >= 2 * 1000;
    }

    // Called once after isFinished returns true
    protected void end() {
	Robot.drive.disableGyroPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	Robot.drive.disableGyroPID();
    }
}
