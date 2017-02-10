package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;
import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	
	private Victor shooterMotor;
	private Victor shooterMotor2;
	private Victor agitator;
	private double powerPercent = 1;
    private Encoder shooterEncoder;
    private double neededPower;
    
    public static double shooterP = 0.78;
    public static double shooterI = 0;
    public static double shooterD = 0;
    
	private PIDController shooterPID;

    
    //private Encoder shooterEncoder2;
    //private PIDController shooterPID2;
	
	public Shooter() {
		shooterPID.setSetpoint(Constants.originalWantedRPM);
		shooterPID.setContinuous(false);
    	shooterPID.disable();
    	
    	shooterPID = new PIDController(shooterP, shooterI, shooterD, Robot.countRPM. , agitator);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		shooterMotor = new Victor(RobotMap.SHOOTER_MOTOR);
//		shooterMotor2 = new Victor(RobotMap.SHOOTER_MOTOR_2);
//		agitator = new Victor(RobotMap.AGITATOR);
		shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER1, RobotMap.SHOOTER_ENCODER2, false, Encoder.EncodingType.k4X);
		shooterPID.disable();
    }
   
	
    public Encoder getEncoder() {
    	return shooterEncoder;
    }

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
    public void shoot() {
    	shooterMotor.set(neededPower);
    	//shooterMotor.set(1);//Max RPM = 4574
    	//if (shooterEncoder.getRate() >= getPIDController().getSetpoint() - 50 && shooterEncoder.getRate() <= getPIDController().getSetpoint() + 50) {
        //	agitator.set(1);
    	//} else {
    	//	agitator.set(0);
    	//}
    }
    public void stopShooting() {
    	shooterPID.disable();
//    	agitator.set(0);
    	shooterMotor.set(0);
//    	shooterMotor2.set(0);
    }