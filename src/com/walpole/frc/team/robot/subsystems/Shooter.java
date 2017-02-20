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
	private Victor agitatorMotor;
//    private Encoder shooterEncoder;
    
    static double shooterP = 0.000004;
    static double shooterI = 0;
    static double shooterD = 0.000001275;
    static double shooterF = 0.000182;
    
	private PIDController shooterPID;
	
	public Shooter() {
		shooterMotor = new Victor(RobotMap.SHOOTER_MOTOR);
		agitatorMotor = new Victor(RobotMap.AGITATOR_MOTOR);

		shooterPID = new PIDController(shooterP, shooterI, shooterD, shooterF, Robot.countRPM, shooterMotor);
		shooterPID.setSetpoint(Constants.originalWantedRPM);
		shooterPID.setContinuous(false);
    	shooterPID.setOutputRange(0, Constants.originalWantedRPM*1.5);
    	shooterPID.disable();

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
    	shooterPID.enable();
//    	if (shooterPID.getError() < 20 && -shooterPID.getError() < 20) {
    		//agitatorMotor.set(-1);
//    	} else {
//    		agitatorMotor.set(0);
//    	}
    }
    public void stopShooting() {
    	shooterPID.disable();
		agitatorMotor.set(0);
    }

    
//    public Value getLight() { return light.get(); }
//    public void turnLightOn() { light.set(Value.kForward); }
//    public void turnLightOff() { light.set(Value.kReverse); }
   
    public double getShooterPIDError() {
    	return shooterPID.getError();
    }
   
}

