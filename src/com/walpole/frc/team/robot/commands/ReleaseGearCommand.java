
package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ReleaseGearCommand extends Command {
	
	public ReleaseGearCommand() {
//		requires(Robot.gear);
	}

	protected void initialize() {
			
//		Robot.gear.retractGearRetainer();
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

    	// Called when another command which requires one or more of the same
    	// subsystems is scheduled to run
    	protected void interrupted() {
    
    	}
}
