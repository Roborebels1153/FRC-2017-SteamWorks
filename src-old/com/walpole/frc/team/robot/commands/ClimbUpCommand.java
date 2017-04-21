package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		// return false;
		// Stop climbing when the limit switch is hit
		return Robot.climb.getLimitSwitchState();
	}

	@Override
	protected void end() {
		Robot.climb.stopClimb();
	}

	@Override
	protected void interrupted() {
	}
}