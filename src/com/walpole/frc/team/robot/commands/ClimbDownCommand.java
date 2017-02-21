package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbDownCommand extends Command {

	public ClimbDownCommand() {
		requires(Robot.climb);
	}

	@Override
	protected void initialize() {
		Robot.climb.climbDown();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.climb.stopClimb();
	}

	@Override
	protected void interrupted() {
	}
}