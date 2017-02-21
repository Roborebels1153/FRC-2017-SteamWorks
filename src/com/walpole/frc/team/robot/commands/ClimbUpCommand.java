
package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

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

	@Override
	protected boolean isFinished() {
		// Stop when the limit switch is enabled
		return Robot.climb.getLimitSwitch().get();
	}

	@Override
	protected void end() {
		Robot.climb.stopClimb();
	}

	@Override
	protected void interrupted() {
		Robot.climb.stopClimb();
	}
}