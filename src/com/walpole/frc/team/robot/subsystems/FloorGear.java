package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.RobotMap;
import com.walpole.frc.team.robot.lib.DualPIDOutput;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FloorGear extends Subsystem {
	
	private Victor gearMotorLeft;
	private Victor gearMotorRight;
	private DualPIDOutput dualPIDOutput;
	
	private Encoder gearEncoder;
	
	private PIDController gearPID;

	public FloorGear() {
		gearMotorLeft = new Victor(RobotMap.GEAR_MOTOR_LEFT);
		gearMotorRight = new Victor(RobotMap.GEAR_MOTOR_RIGHT);
		dualPIDOutput = new DualPIDOutput(gearMotorLeft, gearMotorRight);
		
		gearEncoder = new Encoder(RobotMap.GEAR_ENCODER_A, RobotMap.GEAR_ENCODER_B, false, EncodingType.k4X);
		
		gearPID = new PIDController(0.2, 0, 0, gearEncoder, dualPIDOutput);
	}
	
	public void disableGearPID() {
		gearPID.disable();
	}
	
	public void enableGearPID() {
		gearPID.enable();
	}
	
	public void gear(Joystick joystick) {
		double speed = Constants.FLOOR_GEAR_COLLECTOR_SPEED * joystick.getRawAxis(RobotMap.JOYSTICK_LEFT_Y);
		gearMotorLeft.set(speed);
		gearMotorRight.set(-speed);
	}
	
	@Override
    public void initDefaultCommand() {
		
    }
}