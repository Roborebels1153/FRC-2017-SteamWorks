package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnLightOnCommand extends Command {
	
	public TurnLightOnCommand() {
		requires(Robot.shooter);
	}
	
	protected void initialize() {
//		Robot.shooter.turnLightOn();
		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.turnLightOn();
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
    	Robot.shooter.turnLightOff();
    	
    }

}
