package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClimbUpCommand extends Command {

	public ClimbUpCommand() {
		requires(Robot.climb);
	}

	@Override
	protected void initialize() {
		Robot.climb.climbUp();
	}

	@Override
	protected void execute() {
	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(!Robot.climb.getLimitSwitchState() | !Robot.climb.getOtherLimitSwitchState() == true) {
    		return true;
    	} else {
    		return false;
    	}
//    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
	Robot.climb.stopClimb(); // calling the stopClimb method
    	
    }

	@Override
	protected void interrupted() {
	}

}