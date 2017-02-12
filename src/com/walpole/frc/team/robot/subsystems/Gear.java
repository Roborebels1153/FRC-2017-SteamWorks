package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gear extends Subsystem {
	
	private DoubleSolenoid gearSolenoidRetainer;
	private DoubleSolenoid gearPusherSolenoid;
	
	public Gear() {
		
		gearSolenoidRetainer = new DoubleSolenoid(RobotMap.GEAR_SOLENOID_A, RobotMap.GEAR_SOLENOID_B);
		
		
		gearPusherSolenoid = new DoubleSolenoid(RobotMap.GEAR_PUSHER_SOLENOID_A, RobotMap.GEAR_PUSHER_SOLENOID_B);
		
		init();
	}
	
	private void init() {
		
		gearSolenoidRetainer.set(DoubleSolenoid.Value.kForward);
		gearPusherSolenoid.set(DoubleSolenoid.Value.kForward);
		
	}
	
	public void retractGearRetainer() {
		
		gearSolenoidRetainer.set(DoubleSolenoid.Value.kReverse);
		
	}
	
	public void keepGear() {
		
		gearSolenoidRetainer.set(DoubleSolenoid.Value.kForward);
		
		
	}
	
	public void fireGearPusher() {
		
		gearPusherSolenoid.set(DoubleSolenoid.Value.kReverse);
		
	}
	
	public void retractGearPusher() {
		
		gearPusherSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	@Override
	protected void initDefaultCommand() {
		
		// TODO Auto-generated method stub
		
	}
	

}
