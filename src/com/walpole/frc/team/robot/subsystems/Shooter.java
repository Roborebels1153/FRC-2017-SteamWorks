package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.Robot;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Shooter extends PIDSubsystem {
	
	private Victor shooterMotor;
	private Victor shooterMotor2;
	private Victor agitator;
	private double powerPercent = 1;
	private double agitatorPower = 1;
	private int lightCount = 0;
    private Encoder shooterEncoder;
    private double neededPower;
    private DigitalInput lightSensor;
    
    //private Encoder shooterEncoder2;
    //private PIDController shooterPID2;
	
	public Shooter() {
		super(Constants.encoderP, Constants.encoderI, Constants.encoderD);
		getPIDController().setSetpoint(Constants.originalWantedRPM);
		setAbsoluteTolerance(10);
		getPIDController().setContinuous(false);
	
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		shooterMotor = new Victor(RobotMap.SHOOTER_MOTOR_1);
		shooterMotor2 = new Victor(RobotMap.SHOOTER_MOTOR_2);
		agitator = new Victor(RobotMap.AGITATOR);
		lightSensor = new DigitalInput(RobotMap.LIGHT_SENSOR);
		shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER1, RobotMap.SHOOTER_ENCODER2, false, Encoder.EncodingType.k4X);
		getPIDController().disable();
    }
   
	
    public Encoder getEncoder() {
    	return shooterEncoder;
    }
    
	public boolean getLightSensor() {	
		return lightSensor.get(); 
	}
	
    public int getLightCount() {
    	return lightCount;
    }
    
    public void lightSensorCount() {
    	lightCount += 1;
    }

    
    public void incrementWantedRPM() {
//    	powerPercent += 0.05;
//    	if (powerPercent >= 0.75) {
//    		powerPercent = 0.8;
//    	}
//    	
    }
	public void decrementWantedRPM() {
//    	powerPercent -= 0.05;
//    	if (powerPercent <= 0) {
//    		powerPercent = 0.05;
	}
//    	getPIDController().setSetpoint(powerPercent*Constants.originalWantedRPM);
//    }
	public void setPower(double shootPower) {
//    	powerPercent = shootPower;
//    	getPIDController().setSetpoint(powerPercent*Constants.originalWantedRPM);
	}  
	public void setAgitatorPower(double powerAgitator) {
//    	agitatorPower = powerAgitator;
	}
    public double getNeededPower() {
    	return neededPower;
    }
    public void shoot() {
		getPIDController().enable();
    	shooterMotor.set(1);//neededPower);
    	//if (shooterEncoder.getRate() >= getPIDController().getSetpoint() - 50 && shooterEncoder.getRate() <= getPIDController().getSetpoint() + 50) {
        //	agitator.set(1);
    	//} else {
    	//	agitator.set(0);
    	//}
    }
    public void stopShooting() {
 //   	agitatorPower = 0;
    	agitator.set(0);
    	shooterMotor.set(0);
    	shooterMotor2.set(0);
    }

	@Override
	protected double returnPIDInput() {
		return shooterEncoder.getRate();
	}

	@Override
	protected void usePIDOutput(double output) {
		neededPower = output;
	}
}
