
package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbUpCommand extends Command {

    public ClimbUpCommand() {
    	requires(Robot.climbSubsystem);    //for this command, we require the climb subsystem
        // Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!Robot.climbSubsystem.getLimitSwitch().get()){
    		Robot.climbSubsystem.climbUp();                       //to execute, we need the climbUp method, and we need to make sure that the limit switch is not true
    	} else {
    		Robot.climbSubsystem.stopClimb();
    	}
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
