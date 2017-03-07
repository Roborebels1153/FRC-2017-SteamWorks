
package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RetainGearCommand extends Command {

	public RetainGearCommand() {
		requires(Robot.gear);
	}

	@Override
	protected void initialize() {
		// Robot.gear.retractGearRetainer();
		Robot.gear.keepGear();
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
		// Robot.gear.keepGear();
	}

	@Override
	protected void interrupted() {
	}
}
