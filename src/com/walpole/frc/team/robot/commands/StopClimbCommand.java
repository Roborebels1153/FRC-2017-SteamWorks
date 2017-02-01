
package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopClimbCommand extends Command {

    public StopClimbCommand() {
	requires(Robot.climbSubsystem); // for this command, we require the
					// climb subsystem
	// Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	Robot.climbSubsystem.stopClimb(); // to execute, we need the stopClimb
					  // method
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
    protected void interrupted() { // no interruption because this is the state
				   // when no bumper is pressed

    }
}
