package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnWithGyroCommand extends Command {

	private double degreesToTurn;
	private double speedTurn = 0.8;

	public TurnWithGyroCommand(double degreesToTurn) {
		requires(Robot.drive);
		
		this.degreesToTurn = degreesToTurn;
	}

	protected void initialize() {
		Robot.drive.disableDrivePID();
		
		Robot.drive.resetGyro();
		Robot.drive.setTurnPIDSetpoint(degreesToTurn);
		Robot.drive.setMaxGyroOutput(speedTurn);
		
		Robot.drive.enableGyroPID();
	}

	protected void execute() {
		double gyroOutput = Robot.drive.getGyroPIDOutput();
		Robot.drive.turnAtSpeed(gyroOutput);
	}

	protected boolean isFinished() {
		double error = Math.abs(Robot.drive.getGyroPIDError());
		double output = Math.abs(Robot.drive.getGyroPIDOutput());

		return error <= 2 && output <= 1;
	}

	protected void end() {
		Robot.drive.disableGyroPID();
	}

	protected void interrupted() {
		Robot.drive.disableGyroPID();
	}
}