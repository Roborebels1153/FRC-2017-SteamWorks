package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.RobotMap;
import com.walpole.frc.team.robot.lib.DummyPIDOutput;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FloorGear extends Subsystem {
	
	private Victor gearMotor;
	private Victor collector;
	
	private Encoder gearEncoder;
	//private PIDController gearPID;
	//private DummyPIDOutput gearEncoderOutput;

	public FloorGear() {
		gearMotor = new Victor(RobotMap.GEAR_MOTOR);
		collector = new Victor(RobotMap.GEAR_MOTOR_COLLECTOR);
		gearEncoder = new Encoder(RobotMap.GEAR_ENCODER_A, RobotMap.GEAR_ENCODER_B, false, EncodingType.k4X);
		 //gearEncoderOutput = new DummyPIDOutput();
		//gearPID = new PIDController(0.2, 0, 0, gearEncoder, gearEncoderOutput);
	}
	
	public void gear(Joystick joystick) {
		double speed = Constants.FLOOR_GEAR_LEVER_SPEED * joystick.getRawAxis(RobotMap.JOYSTICK_LEFT_Y);
		gearMotor.set(-speed);
	}
	
	public void setGearMotor (double power) { 
		gearMotor.set(power);
	}
	public void collectorIn() {
	    collector.set(Constants.FLOOR_GEAR_LEVER_SPEED);
	}
	
	public void collectorOut() {
	    collector.set(-Constants.FLOOR_GEAR_LEVER_SPEED);
	}
	
	public void collectorOff() {
	    collector.set(0);
	}
	
	public double getCollectorMotorValue() {
		return collector.get();
	}
	
	public double getGearMotorValue() {
		return gearMotor.get();
	}
	
	@Override
    public void initDefaultCommand() {
		
    }
	
	public void resetGearEncoder() {
		gearEncoder.reset();
	}
	
	public double getGearEncoderCount() {
		return gearEncoder.get();
	}
	
//	public void enableGearPID() {
//		gearPID.enable();
//	}
//	
//	public void disableGearPID() {
//		gearPID.disable();
//	}
//	
//	public void setGearEncoderPIDSetpoint(double setPoint) { 
//		gearPID.setSetpoint(setPoint);
//		
//	}
//	
//	public double getGearPIDError() {
//		return gearPID.getError();
//	}
//	
//	public double getGearPIDOutput() {
//		return gearPID.get();
//	}
//	public double getGearPIDSetPoint () { 
//		return gearPID.getSetpoint(); 
//	}
//	
//	public void setMaxGearCollectorPIDOutput(double speed) { 
//		gearPID.setOutputRange(-speed, speed);
//	}
//	
//	
//	
//	
//	
//	
}