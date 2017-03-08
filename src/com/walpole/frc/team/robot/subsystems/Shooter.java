package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.Constants;

import com.walpole.frc.team.robot.Robot;
import com.walpole.frc.team.robot.RobotMap;
import com.walpole.frc.team.robot.lib.DummyPIDOutput;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {
	
	private Victor shooterMotor;
	private Victor agitatorMotor;
	
	 private Solenoid indexer;
	
    static double shooterShortP = 0.002;
    static double shooterShortI = 0;
    static double shooterShortD = 0.2;
    static double shooterShortF = 0.0001;
	    
    static double shooterFarP = 0.003;
    static double shooterFarI = 0;
    static double shooterFarD = 0.15;
    static double shooterFarF = 0.00015;
    
	private PIDController shooterFarPID;
	private PIDController shooterShortPID;
	private boolean shootFar = true;
	
	public void setShooterFar(boolean far) {
		if (far) {
			shootFar = true;
		} else {
			shootFar = false;
		}
	}
	
	public PIDController getPID() {
		if (shootFar) {
			return shooterFarPID;
		} else {
			return shooterShortPID;
		}
	}
	
	public Shooter() {
		shooterMotor = new Victor(RobotMap.SHOOTER_MOTOR);
		agitatorMotor = new Victor(RobotMap.AGITATOR_MOTOR);

		shooterFarPID = new PIDController(shooterFarP, shooterFarI, shooterFarD, shooterFarF, Robot.countRPM, shooterMotor);
		shooterFarPID.setSetpoint(4100);
		shooterFarPID.setContinuous(false);
    	shooterFarPID.setOutputRange(0, 1);
    	shooterFarPID.disable();
    	
		shooterShortPID = new PIDController(shooterShortP, shooterShortI, shooterShortD, shooterShortF, Robot.countRPM, shooterMotor);
		shooterShortPID.setSetpoint(2500);
		shooterShortPID.setContinuous(false);
		shooterShortPID.setOutputRange(0, 0.6);
		shooterShortPID.disable();
    	
    	indexer = new Solenoid(RobotMap.INDEXER_PISTON);
	}
	
	public void init() {
	}
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        
    }
//    public Encoder getEncoder() {
//    	return shooterEncoder;
//    }
    public void shoot() {
    	getPID().enable();
//    	agitatorMotor.set(-1);
    	if (getPID().getError() < 125 && -getPID().getError() < 125) {
    		agitatorMotor.set(-1);
        	indexer.set(true);
    	} else {
    		agitatorMotor.set(0);
        	indexer.set(false);

    	} 
    }
    
    public void shootWhenWeDontHaveALightSensor() {
    	shooterMotor.set(1);
    	agitatorMotor.set(-1);
    }
    
    public void stopShooting() {
    	shooterShortPID.disable();
    	shooterFarPID.disable();

		agitatorMotor.set(0);
    }
    
    public void agitatorOn() {
    	agitatorMotor.set(-1);
    }
    
    public void agitatorOff() {
    	agitatorMotor.set(0);
    }

    
//    public Value getLight() { return light.get(); }
//    public void turnLightOn() { light.set(Value.kForward); }
//    public void turnLightOff() { light.set(Value.kReverse); }
   
    public double getShooterPIDError() {
    	return getPID().getError();
    }
    
}

