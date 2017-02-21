package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnWithGyroCommand extends Command {
    
    private double degreesToTurn;
    private double speedDrive; 
    private double speedTurn;

    public TurnWithGyroCommand(double degreesToTurn) {
	requires(Robot.drive);
	this.degreesToTurn = degreesToTurn;
	this.speedTurn = 0.8;
	
    }

    protected void initialize() {
	Robot.drive.resetGyro();
	Robot.drive.setTurnPIDSetpoint(degreesToTurn);
	Robot.drive.enableGyroPID();
	Robot.drive.disableDrivePID();
	//Robot.drive.setMaxDrivePIDOutput(speedDrive); 
	// startTimeMillis = System.currentTimeMillis();
	Robot.drive.setMaxGyroOutput(speedTurn);
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
	
	return error <= 2 && output <= 1;
	//return false;
    }

    protected void end() {
	Robot.drive.disableGyroPID();
    }

    protected void interrupted() {
	Robot.drive.disableGyroPID();
    }
}
