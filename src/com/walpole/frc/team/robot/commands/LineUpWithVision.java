package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LineUpWithVision extends Command {
	
	private double neededAngle;
	private double neededDistance;
	private double timeLimit;
	

    public LineUpWithVision(double timeLimit) {
    	requires(Robot.drive);
    	requires(Robot.vision);
    	this.timeLimit = timeLimit;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (!Robot.vision.arduinoString.equalsIgnoreCase("")) {
    		neededAngle = Robot.vision.getVisionAngle();
        	neededDistance = Robot.vision.getDistanceFromCenter();
    	}

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}