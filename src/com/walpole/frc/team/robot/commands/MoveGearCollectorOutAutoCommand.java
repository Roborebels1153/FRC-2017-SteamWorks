package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveGearCollectorOutAutoCommand extends Command {
	
	private double speed;
	private double setPoint;
	private double secondsArm;
	private long startTimeMillis;

	public MoveGearCollectorOutAutoCommand(int setPoint, double speed ) {
		requires(Robot.floorGear); 
		this.speed = speed; 
		this.setPoint = setPoint;
	}
	
	public MoveGearCollectorOutAutoCommand(int setPoint, double speed, double secondsArm) {
		requires(Robot.floorGear); 
		this.speed = speed; 
		this.setPoint = setPoint; 
		this.secondsArm = secondsArm;

	
}


	@Override
	protected void initialize() {

		Robot.floorGear.resetGearEncoder();
//		Robot.floorGear.getGearPIDSetPoint(); 
		Robot.floorGear.setGearEncoderPIDSetpoint(setPoint);
		Robot.floorGear.setMaxGearCollectorPIDOutput(speed);
		Robot.floorGear.enableGearPID();
		startTimeMillis = System.currentTimeMillis();
//		Robot.floorGear.ArmDown();
		
	}

	@Override
	protected void execute() {
		double output = Robot.floorGear.getGearPIDOutput(); 
		//double output = Robot.floorGear.getGearPIDSetPoint();
		Robot.floorGear.setGearMotor(output);
		//Robot.floorGear.setGearMotor(-0.1);
		      
		//Robot.floorGear.collectorOut();
		
		
	}

	@Override
	protected boolean isFinished() {
		
		double error = Math.abs(Robot.floorGear.getGearPIDError()); 
		return System.currentTimeMillis() - startTimeMillis >= secondsArm * 1000 || error < 10; 
		// return error < 10; 

		
		//Encoders Only:
//		if(error < 10) { 
//			Robot.floorGear.setGearMotor(-0.1);
		
//			//Robot.floorGear.collectorOut();
//		}
		//return false;  
				
		
	}

	@Override
	protected void end() {
		Robot.floorGear.disableGearPID();
		Robot.floorGear.setGearMotor(-0.1);
		
	}

	@Override
	protected void interrupted() {
		
		
		
	}
	

}
