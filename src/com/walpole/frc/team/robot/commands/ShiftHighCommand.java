package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftHighCommand extends Command {

	public ShiftHighCommand() {
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
	}
	
	@Override
	protected void execute() {
		Robot.drive.shiftHigh();
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