package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopClimbCommand extends Command {

	public StopClimbCommand() {
		requires(Robot.climb);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.climb.stopClimb();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}