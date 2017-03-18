package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardWithEncoder extends Command {
	
	private double speed;
	private double distance;
	
	public DriveForwardWithEncoder(int inchesToDrive) {
		requires(Robot.drive);
		this.speed = 0.8;
		this.distance = Constants.ticksPerInch * inchesToDrive;
	}
	
	public DriveForwardWithEncoder(int inchesToDrive, double speed) {
		requires(Robot.drive);
		this.speed = speed;
		this.distance = Constants.ticksPerInch * inchesToDrive;
	}
	
	@Override
	protected void initialize() {
		Robot.drive.resetEncoders();
		
		Robot.drive.setMaxEncoderRatePIDOutput(0, speed);
		Robot.drive.setEncoderRatePIDSetpoint(112);
		
		/*Robot.drive.setMaxEncoderPIDOutput(112);
		Robot.drive.setEncoderPIDSetPoint(distance);*/
		
		Robot.drive.enableEncoderRatePID();
		//Robot.drive.enableEncoderPID();
	}
	
	@Override
	protected void execute() {
		double encoderRateOutputLeft = Robot.drive.getLeftRatePIDOutput();
		double encoderRateOutputRight = Robot.drive.getLeftRatePIDOutput();
		
		/*double encoderOutputLeft = Robot.drive.getLeftPIDOutput();
		double encoderOutputRight = Robot.drive.getLeftPIDOutput();
		double avg = (encoderOutputLeft + encoderOutputRight) / 2;
		Robot.drive.setEncoderRatePIDSetPoint(avg);*/
		
		Robot.drive.tankDrive(encoderRateOutputLeft, encoderRateOutputRight);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.drive.disableEncoderRatePID();
		Robot.drive.disableEncoderPID();
	}
	
	@Override
	protected void interrupted() {
		Robot.drive.disableEncoderRatePID();
		Robot.drive.disableEncoderPID();
	}
}