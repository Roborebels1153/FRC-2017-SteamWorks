package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RetractBallFlapperCommand extends Command {

	public RetractBallFlapperCommand() {
		requires(Robot.floorGear);
	}

	@Override
	protected void initialize() {
		Robot.floorGear.retractBallFlapper();
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
	}

	@Override
	protected void interrupted() {
		// Robot.gear.retractGearPusher();
	}
}