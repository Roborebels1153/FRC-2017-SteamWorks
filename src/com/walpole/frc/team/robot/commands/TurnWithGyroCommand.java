package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnWithGyroCommand extends Command {
    
    private double degreesToTurn;
    private double speedTurn;

    public TurnWithGyroCommand(double degreesToTurn) {
	requires(Robot.drive);
	requires(Robot.floorGear);
	this.degreesToTurn = degreesToTurn;
	this.speedTurn = 0.6;
    }
    
    public TurnWithGyroCommand(double degreesToTurn, double speed) {
  	requires(Robot.drive);
  	requires(Robot.floorGear); 
  	this.degreesToTurn = degreesToTurn; 
  	this.speedTurn = speed;
      }

    protected void initialize() {
	Robot.drive.disableDrivePID();
	Robot.drive.setTurnPIDSetpoint(degreesToTurn);
	Robot.drive.setMaxGyroOutput(speedTurn);
	Robot.drive.enableGyroPID();
	Robot.floorGear.setMotorValue(-0.1);
    }

    protected void execute() {
	double gyroOutput = Robot.drive.getGyroPIDOutput();   
	Robot.drive.setTurnSpeed(gyroOutput);
	//Robot.floorGear.stayInPosition();
    }

    /*public boolean withinTargetValue(double targetValue, double errorTolerance, double actualValue) {
	if ((actualValue >= targetValue - errorTolerance) && (actualValue <= targetValue + errorTolerance)) {
	    return true;
	} else {
	    return false;
	}
    }*/

    protected boolean isFinished() {
	double error = Math.abs(Robot.drive.getGyroPIDError());
	double output = Math.abs(Robot.drive.getGyroPIDOutput());
	
	return error <= 2 && output <= 0.1;
	//return false;
    }

    protected void end() {
	Robot.drive.disableGyroPID();
	Robot.floorGear.setMotorValue(0);
    }

    protected void interrupted() {
	Robot.drive.disableGyroPID();
    }
}
