
package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftLowCommand extends Command {

	public ShiftLowCommand() {
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.drive.shiftLow();
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