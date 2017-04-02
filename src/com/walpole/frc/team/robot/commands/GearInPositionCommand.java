package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GearInPositionCommand extends Command {
	
	public GearInPositionCommand() {
		requires(Robot.floorGear);
	}

	
	protected void initialize() {
			Robot.floorGear.stayInPosition();
	}

	protected void execute() {
		
	}

	    // Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
	    return false;
	    
	}

	    // Called once after isFinished returns true
	    protected void end() {
		
	    	
	    }

		protected void interrupted() {
		
		}

	}

