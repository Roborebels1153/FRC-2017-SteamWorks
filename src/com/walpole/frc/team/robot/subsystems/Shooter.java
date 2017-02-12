package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	
	private Victor shooterMotor;
	private Victor shooterMotor2;
//	private Victor agitator;
	private double powerPercent = 1;
//    private Encoder shooterEncoder;
    private double neededPower;
    
//    public static double shooterP = 0.78;
//    public static double shooterI = 0;
//    public static double shooterD = 0;
//    
//	private PIDController shooterPID;
	private double speed = 1;
//    private Relay light;

	
	public Shooter() {
//		shooterPID.setSetpoint(Constants.originalWantedRPM);
//		shooterPID.setContinuous(false);
//    	shooterPID.disable();
    	
 //   	shooterPID = new PIDController(shooterP, shooterI, shooterD, Robot.countRPM.getCounter() , agitator);
		shooterMotor = new Victor(RobotMap.SHOOTER_MOTOR);
		shooterMotor2 = new Victor(RobotMap.SHOOTER_MOTOR_TWO);
//		agitator = new Victor(RobotMap.AGITATOR);

//		shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER1, RobotMap.SHOOTER_ENCODER2, false, Encoder.EncodingType.k4X);
		
//		light = new Relay(RobotMap.LIGHT);
		
		init();
	}
	
	private void init() {
		
		// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
//		shooterMotor = new Victor(RobotMap.SHOOTER_MOTOR);
//		shooterMotor2 = new Victor(RobotMap.SHOOTER_MOTOR_2);
//		agitator = new Victor(RobotMap.AGITATOR);
//		shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER1, RobotMap.SHOOTER_ENCODER2, false, Encoder.EncodingType.k4X);
//		shooterPID.disable();
		
	}
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        
    }
   
	
//    public Encoder getEncoder() {
//    	return shooterEncoder;
//    }
   
//    public void enablePID() {
//    	shooterPID.enable();
//    }
//    
//    public void disablePID() {
//    	shooterPID.disable();
//    }
//    
//    public void getError() {
//    	shooterPID.getError();
//    }
//    
//    public void setShooterSetpoint( double setpoint) {
//    	shooterPID.setSetpoint(setpoint);	
//    }
//    
//    public void setMaxPIDOutput(double speed) {
//    	shooterPID.setOutputRange(-speed, speed);
//    }
//    
//    public double getShooterPIDOutput() {
//    	return shooterPID.get();
//    }

    public void incrementWantedRPM() {
    	powerPercent += 0.05;
    	if (powerPercent >= 0.75) {
    		powerPercent = 0.8;
    	}
    	
    }
	public void decrementWantedRPM() {
    	powerPercent -= 0.05;
    	if (powerPercent <= 0) {
    		powerPercent = 0.05;
    	}
	}
	public void setPower(double shootPower) {
		powerPercent = shootPower;
//    	getPIDController().setSetpoint(powerPercent*Constants.originalWantedRPM);
	}  
    public double getNeededPower() {
    	return neededPower;

    }
    
    public void setSpeed(double shootSpeed) {
    	speed = shootSpeed;
    }
    
    public double getSpeed() {
    	return speed;
    }
    public void shoot() {
    	shooterMotor.set(neededPower);
    	//shooterMotor.set(1);//Max RPM = 4574
    	//if (shooterEncoder.getRate() >= getPIDController().getSetpoint() - 50 && shooterEncoder.getRate() <= getPIDController().getSetpoint() + 50) {
        //	agitator.set(1);
    	//} else {
    	//	agitator.set(0);
    	//}
    	shooterMotor.set(speed);
    	shooterMotor2.set(speed*-1);
    }
    public void stopShooting() {
//    	shooterPID.disable();
//    	agitator.set(0);
//    	shooterMotor.set(0);
//<<<<<<< HEAD
//    	shooterMotor2.set(0);
    }

    
//    public Value getLight() {
//    	return light.get();
//    }
//    
//    public void turnLightOn() {
//    	 light.set(Value.kForward);
//    }
//    
//    public void turnLightOff() {
//    	light.set(Value.kReverse);
//    }
}

