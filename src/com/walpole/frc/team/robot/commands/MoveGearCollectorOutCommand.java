package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveGearCollectorOutCommand extends Command {
	
	private double speed;
	private double setPoint;
	
public MoveGearCollectorOutCommand(int setPoint, int speed) {
	requires(Robot.floorGear); 
	this.speed = speed; 
	this.setPoint = setPoint; 
	
}

	@Override
	protected void initialize() {
//		Robot.floorGear.resetGearEncoder();
//		Robot.floorGear.enableGearPID();
//		Robot.floorGear.getGearPIDSetPoint(); 
//		Robot.floorGear.setGearEncoderPIDSetpoint(setPoint);
//		Robot.floorGear.setMaxGearCollectorPIDOutput(speed);
//		
		
	}

	@Override
	protected void execute() {
		//double output = Robot.floorGear.getGearPIDOutput(); 
		//Robot.floorGear.setGearMotor(output);
		
		
	}

	@Override
	protected boolean isFinished() {
		return false;
		//double error = Math.abs(Robot.floorGear.getGearPIDError()); 
		//return error < 100; 
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
	

}
