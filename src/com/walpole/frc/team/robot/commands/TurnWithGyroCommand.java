package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnWithGyroCommand extends Command {
    
    private double degreesToTurn;

    public TurnWithGyroCommand(double degreesToTurn) {
	requires(Robot.drive);
	this.degreesToTurn = degreesToTurn;
    }

    protected void initialize() {
	Robot.drive.resetGyro();
	Robot.drive.setTurnPIDSetpoint(degreesToTurn);
	Robot.drive.disableDrivePID();
	Robot.drive.enableGyroPID();
	// startTimeMillis = System.currentTimeMillis();
	// Robot.driveSubsystem.setMaxGyroOutput(speed);
    }

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

    protected boolean isFinished() {
	double error = Math.abs(Robot.drive.getGyroPIDError());
	double output = Math.abs(Robot.drive.getGyroPIDOutput());
	
	return error <= 2 && output <= 0.01;
    }

    protected void end() {
	Robot.drive.disableGyroPID();
    }

    protected void interrupted() {
	Robot.drive.disableGyroPID();
    }
}
