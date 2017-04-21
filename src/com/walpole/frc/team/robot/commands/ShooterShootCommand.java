package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterShootCommand extends Command {

	public ShooterShootCommand() { 
		requires(Robot.shooter); 
    }

    	
	protected void initialize() {
		
	}

   

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.shooter.shoot();
    	Robot.shooter.shoot();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot.shooter.stopShooting();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

    