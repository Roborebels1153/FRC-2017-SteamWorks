package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopGearCollectorCommand extends Command {

    public StopGearCollectorCommand() {

	requires(Robot.floorGear); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.floorGear.collectorOff();  
    }
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    
    protected void interrupted() { 

    }
}
