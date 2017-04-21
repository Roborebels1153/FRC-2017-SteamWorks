
package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopClimbCommand extends Command {

    public StopClimbCommand() {

	requires(Robot.climb); // for this command, we require the
					// climb subsystem
	// Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.climb.stopClimb();  // to execute, we need the stopClimb
	  // method
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
    protected void interrupted() { // no interruption because this is the state
				   // when no bumper is pressed

    }
}
