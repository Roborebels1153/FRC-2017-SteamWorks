package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveGearCollectorOutAutoCommand extends Command {
	
	private double speed;
	private double setPoint;
	private double secondsArm;

public MoveGearCollectorOutAutoCommand(int setPoint, double speed) {
	requires(Robot.floorGear); 
	this.speed = speed; 
	this.setPoint = setPoint; 
	
}


	@Override
	protected void initialize() {

		Robot.floorGear.resetGearEncoder();
//		Robot.floorGear.getGearPIDSetPoint(); 
		Robot.floorGear.setGearEncoderPIDSetpoint(setPoint);
		Robot.floorGear.setMaxGearCollectorPIDOutput(speed);
		Robot.floorGear.enableGearPID();
//		Robot.floorGear.ArmDown();
		
	}

	@Override
	protected void execute() {
		double output = Robot.floorGear.getGearPIDOutput(); 
		//double output = Robot.floorGear.getGearPIDSetPoint();
		Robot.floorGear.setGearMotor(output);
		      
		Robot.floorGear.collectorOut();
		
		
	}

	@Override
	protected boolean isFinished() {
		
		double error = Math.abs(Robot.floorGear.getGearPIDError()); 
		
		//Encoders Only:
		return error < 10;
				
		
		
		/*//LimitSwitch only:
		 if (!Robot.floorGear.getGearLimitSwitchState() == true) {
    		return true;
    	} else {
    		return false;
    	}*/
		 
		/*//Encoders and Limit Switch:
		return (error < 100 | Robot.floorGear.getGearLimitSwitchState());  */
		 
		//Testing Purposes:
		//return false;
		
		//return true; 
		
	}

	@Override
	protected void end() {
		Robot.floorGear.disableGearPID();
		
	}

	@Override
	protected void interrupted() {
		
	}
	

}
