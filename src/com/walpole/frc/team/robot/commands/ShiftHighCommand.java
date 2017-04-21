package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftHighCommand extends Command {


	public ShiftHighCommand() {
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		Robot.drive.shiftHigh();
		Robot.floorGear.setMotorValue(-0.1);

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
