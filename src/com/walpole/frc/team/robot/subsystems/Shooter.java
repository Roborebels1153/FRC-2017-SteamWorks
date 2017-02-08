package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{
	
	private Victor shooterMotor;
	//private Victor shooterMotor2;
	private double speed = 1;
    public static Encoder shooterEncoder;
    //private Relay light;

	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Shooter() {
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
		shooterMotor = new Victor(RobotMap.SHOOTER_MOTOR);

		//shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER1, RobotMap.SHOOTER_ENCODER2, false, Encoder.EncodingType.k4X);
		
//		light = new Relay(RobotMap.LIGHT);
    }
    
    
    public void incrementSpeed() {
    	speed += 0.1;
    	if (speed >= 0.95) {
    		speed = 1;
    	}
    }
    
    public void decrementSpeed() {
    	speed -= 0.1;
    	if (speed <= 0) {
    		speed = 0.05;
    	}
    }
    
    public void setSpeed(double shootSpeed) {
    	this.speed = shootSpeed;
    }
    
    public double getSpeed() {
    	return speed;
    }
    
    public void shoot() {
    	shooterMotor.set(speed);
    }
    
    public void stopShooting() {
    	shooterMotor.set(0);
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
