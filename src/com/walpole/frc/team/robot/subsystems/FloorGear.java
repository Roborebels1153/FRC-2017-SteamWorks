package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;
import com.walpole.frc.team.robot.RobotMap;
import com.walpole.frc.team.robot.lib.DummyPIDOutput;
import com.walpole.frc.team.robot.lib.RebelDrive;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FloorGear extends Subsystem {
	
	private Victor gearMotor;
	private Victor collector;
	
	
	private double gearEncoderP; 
	private double gearEncoderI; 
	private double gearEncoderD; 
	
	private Encoder gearEncoder;
	private PIDController gearPID;
	private DummyPIDOutput gearEncoderOutput;
	
	private DigitalInput gearLimitSwitch;
	private DigitalInput gearLimitSwitchTwo; 
	
	private Solenoid ballIntakeFlapper;

	private DigitalInput gearLightSensor;
	
	private Relay gearLED;

	public FloorGear() {
		gearMotor = new Victor(RobotMap.GEAR_MOTOR);
		collector = new Victor(RobotMap.GEAR_MOTOR_COLLECTOR);
		gearEncoder = new Encoder(RobotMap.GEAR_ENCODER_A, RobotMap.GEAR_ENCODER_B, false, EncodingType.k4X);
		
		gearEncoderOutput = new DummyPIDOutput();
		gearPID = new PIDController(Constants.gearEncoderP, Constants.gearEncoderI,Constants.gearEncoderD, gearEncoder, gearEncoderOutput);
		gearLimitSwitch = new DigitalInput (RobotMap.GEAR_LIMIT_SWITCH);
		gearLimitSwitchTwo = new DigitalInput (RobotMap.GEAR_LIMIT_SWITCH_TWO); 
		
		ballIntakeFlapper = new Solenoid(RobotMap.BALL_INTAKE_SOLENOID);

		gearLightSensor = new DigitalInput (RobotMap.GEAR_LIGHT_SENSOR);
		
		gearLED = new Relay(RobotMap.LIGHT);
		
		init();
		
	}
	
	private void init() {
		ballIntakeFlapper.set(false);
		

	}
	
	private void loadGearPIDValues() { 
		gearEncoderP = Robot.prefs.getDouble("gearEncoderP", gearEncoderP); 
		gearEncoderI = Robot.prefs.getDouble("gearEncoderI", gearEncoderI); 
		gearEncoderD = Robot.prefs.getDouble("gearEncoderD", gearEncoderD);
	}
	
	public void updateGearPIDControllers() { 
		loadGearPIDValues();
		//gearPID.setPID(gearEncoderP, gearEncoderI, gearEncoderD); 
	}
	
	
	public void gear(Joystick joystick) {
		double speed = Constants.FLOOR_GEAR_LEVER_SPEED * joystick.getRawAxis(RobotMap.JOYSTICK_LEFT_Y);
		if(Math.abs(joystick.getRawAxis(RobotMap.JOYSTICK_LEFT_Y)) < 0.1) { 
			gearMotor.set(-0.1);
		} else { 
		gearMotor.set(speed);		
		}
	
			
	}
	
	public boolean getGearLightSensorState () {
		return gearLightSensor.get();
	}
	
	public void pickedUpGearLED() {
		if (getGearLightSensorState()) {
			gearLED.set(Value.kReverse);
		} else if (Robot.oi.getOperatorJoystick().getRawButton(4) == true) {
			gearLED.set(Value.kReverse);
		} else {
			gearLED.set(Value.kForward);
		}
}                                                                       
	
	public void gearLEDOff() { 
		gearLED.set(Value.kForward);
	}
	public void gearLEDOn() {
		gearLED.set(Value.kReverse);
	}
	
//	public void gearLEDState() {
//		gearLED.get();
//	}
	
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
	
	public void stayInPosition() {
		gearMotor.set(-0.1);
		
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
	
	public void enableGearPID() {
		gearPID.enable();
	}
	
	public void disableGearPID() {
		gearPID.disable();
	}
	
	public void setGearEncoderPIDSetpoint(double setPoint) { 
		gearPID.setSetpoint(setPoint);
		
	}
	
	public double getGearPIDError() {
		return gearPID.getError();
	}
	
	public double getGearPIDOutput() {
		return gearPID.get();
	}
	public double getGearPIDSetPoint () { 
		return gearPID.getSetpoint(); 
	}
	
	public boolean getGearLimitSwitchState() { 
		return gearLimitSwitch.get(); 
	}
	
	public boolean getGearLimitSwitchTwoState () { 
		return gearLimitSwitchTwo.get(); 
	}
	
	public void setMaxGearCollectorPIDOutput(double speed) { 
		gearPID.setOutputRange(-speed, speed);
	}
	
	public void ArmDown () { 
		if (getGearLimitSwitchState() == true) { 
			gearMotor.set(0.5);
		} else if (getGearLimitSwitchState() == false) { 
			gearMotor.set(0);
			
		}
		
	}
	
	public void ArmUp () { 
		if (getGearLimitSwitchTwoState() == true) { 
			gearMotor.set(-0.5);
		} else if (getGearLimitSwitchTwoState() == false) { 
			gearMotor.set(0);
			
		}
		
	}
	
public void retractBallFlapper() {
		
		ballIntakeFlapper.set(false);
		
	}
	
	public void fireBallFlapper() {
		
		ballIntakeFlapper.set(true);
	}
	
	public void setMotorValue(double speed) {
		gearMotor.set(speed);
	}
	
}
	
