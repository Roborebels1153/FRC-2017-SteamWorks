package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ReleaseGearCommand extends Command {

	public ReleaseGearCommand() {
		requires(Robot.gear);
	}

	@Override
	protected void initialize() {
		Robot.gear.retractGearRetainer();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}