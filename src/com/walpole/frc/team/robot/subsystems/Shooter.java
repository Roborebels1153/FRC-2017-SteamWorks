package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.Constants;

import com.walpole.frc.team.robot.Robot;
import com.walpole.frc.team.robot.RobotMap;
import com.walpole.frc.team.robot.lib.DummyPIDOutput;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {
	
	private Victor shooterMotor;
	//private Victor shooterMotor2;
	//private Victor agitator;
    private Encoder shooterEncoder;
    
    public static double shooterP = 0.0001;
    public static double shooterI = 0.0001;
    public static double shooterD = 0.0015;
    public static double shooterF = 0;
    
	private PIDController shooterPID;
	private double speed = 1;
    //private Relay light;
    
    //private Encoder shooterEncoder2;
    //
	
	public Shooter() {
		shooterMotor = new Victor(RobotMap.SHOOTER_MOTOR);
//		shooterMotor2 = new Victor(RobotMap.SHOOTER_MOTOR_2);
//		agitator = new Victor(RobotMap.AGITATOR);
		shooterPID = new PIDController(shooterP, shooterI, shooterD, shooterF, Robot.countRPM, shooterMotor);
		shooterPID.setSetpoint(Constants.originalWantedRPM);
		shooterPID.setContinuous(false);
    	shooterPID.setOutputRange(0, 5000);
    	shooterPID.disable();

	}
	
	public void init() {
		
	}
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        
    }
   
	
    public Encoder getEncoder() {
    	return shooterEncoder;
    }
    public double getSpeed() {
    	return speed;
    }
    public void shoot() {
    	shooterPID.enable();
    }
    public void stopShooting() {
    	shooterPID.disable();
    	//shooterMotor.set(0);
    }

    
//    public Value getLight() { return light.get(); }
//    public void turnLightOn() { light.set(Value.kForward); }
//    public void turnLightOff() { light.set(Value.kReverse); }
   
    public double getShooterPIDError() {
    	return shooterPID.getError();
    }
   
}

