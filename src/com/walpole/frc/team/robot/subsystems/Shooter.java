package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.RobotMap;
import com.walpole.frc.team.robot.commands.ConveyerOffCommand;
import com.walpole.frc.team.robot.commands.ConveyerOnCommand;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	
	private Victor shooterMotor;
	private Victor agitatorMotor;
		
    static double shooterShortP = 0.2;
    static double shooterShortI = 0;
    static double shooterShortD = 0;
    static double shooterShortF = 0.0001;
	    
    static double shooterFarP = 0.3;
    static double shooterFarI = 0;
    static double shooterFarD = 0;
    static double shooterFarF = 0.00005;
    
	private PIDController shooterPID;
	
	private Counter shooterCounter = new Counter(RobotMap.LIGHT_SENSOR);
	
	public Shooter() {
	
		shooterMotor = new Victor(RobotMap.SHOOTER_MOTOR);
		agitatorMotor = new Victor(RobotMap.AGITATOR_MOTOR);
		
		shooterCounter.setUpDownCounterMode();
		shooterCounter.setDistancePerPulse(1);
		shooterCounter.setMaxPeriod(0.01);
		shooterCounter.setPIDSourceType(PIDSourceType.kRate);

		shooterPID = new PIDController(shooterFarP, shooterFarI, shooterFarD, shooterFarF, shooterCounter, shooterMotor);
		shooterPID.setSetpoint(3500/60);
		shooterPID.setContinuous(false);
    	shooterPID.setOutputRange(0, 0.8);
    	shooterPID.disable();
	}
	
	public double getRPS() {
		return shooterCounter.getRate();
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        
    }
    public void shoot() {
    	shooterPID.enable();
    	if (shooterPID.getError() < 15 && shooterPID.getError() > -15) {
    		agitatorOn();
    	} else {
    		agitatorOff();
    	} 
    }
    
    public double shooterPIDError() {
    	return shooterPID.getError();
    }
    
    public void shootWhenWeDontHaveALightSensor() {
    	shooterMotor.set(.8);
    }
    
    public void stopShooting() {
    	shooterPID.disable();
    	agitatorOff();
    }
    
    public void agitatorOn() {
    	agitatorMotor.set(-1);
    }
    
    public void agitatorOff() {
    	agitatorMotor.set(0);
    }
    
    public void setShooterSpeed(double speed) {
    	shooterMotor.set(speed);
    }

    
//    public Value getLight() { return light.get(); }
//    public void turnLightOn() { light.set(Value.kForward); }
//    public void turnLightOff() { light.set(Value.kReverse); }
   
//    public double getShooterPIDError() {
//    	return getPID().getError();
//    }
    
}

