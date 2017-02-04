
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

    public TurnWithGyroCommand(double degreesToTurn) {
	requires(Robot.driveSubsystem);
	this.degreesToTurn = degreesToTurn;
	this.speed = 1;

	// Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.driveSubsystem.resetGyro();
	Robot.driveSubsystem.setTurnPID(degreesToTurn);
	Robot.driveSubsystem.enableGyroPID();
	// Robot.driveSubsystem.setMaxGyroOutput(speed);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	double gyroOutput = Robot.driveSubsystem.getGyroPIDOutput();
	Robot.driveSubsystem.setTurnSpeed(gyroOutput);
	SmartDashboard.putNumber("Turn Speed", gyroOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	double currentGyroAngle = Robot.driveSubsystem.getGyroAngle();
	// boolean gyroFinished = currentGyroAngle <=2 && currentGyroAngle >=
	// -2;
	boolean gyroFinished = Robot.driveSubsystem.getGyroPIDError() < 2;
	return gyroFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
	Robot.driveSubsystem.disableGyroPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
