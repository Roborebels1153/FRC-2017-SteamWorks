package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RetainGearCommand extends Command {
	
	public RetainGearCommand() {
		requires(Robot.gear);
	}

	protected void initialize() {
//		Robot.gear.retractGearRetainer();
		Robot.gear.keepGear();
    	 
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
//    	Robot.gear.keepGear();
    	        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	

    }

}
