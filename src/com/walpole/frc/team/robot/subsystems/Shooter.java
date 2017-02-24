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
//    private Encoder shooterEncoder;
	
//    1500 RPM
//    static double shooterP = 0.001;
//    static double shooterI = 0;
//    static double shooterD = 0.102;
//    static double shooterF = 0.0001;
    
//	  2500 RPM
    static double shooterP = 0.002;
    static double shooterI = 0;
    static double shooterD = 0.12;
    static double shooterF = 0.0001;
    
	private PIDController shooterPID;
	
	public Shooter() {
		shooterMotor = new Victor(RobotMap.SHOOTER_MOTOR);
		agitatorMotor = new Victor(RobotMap.AGITATOR_MOTOR);

		shooterPID = new PIDController(shooterP, shooterI, shooterD, shooterF, Robot.countRPM, shooterMotor);
		shooterPID.setSetpoint(4000);
		shooterPID.setContinuous(false);
    	shooterPID.setOutputRange(0, 0.6);
    	shooterPID.disable();
    	
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
//    	shooterMotor.set(0.8);
//    }
    	shooterPID.enable();
    	agitatorMotor.set(-1);
    	if (shooterPID.getError() < 100 && -shooterPID.getError() < 100) {
//    		agitatorMotor.set(-1);
        	indexer.set(true);

    	} else {
//    		agitatorMotor.set(0);
//        	indexer.set(false);

    	} 
    }
    public void stopShooting() {
    	shooterPID.disable();
		agitatorMotor.set(0);
//    	shooterMotor.set(0);
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
    	return shooterPID.getError();
    }
    
}

