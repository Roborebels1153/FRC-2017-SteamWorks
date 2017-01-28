package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Shooter extends PIDSubsystem {
	
	private Victor shooterMotor;
	private Victor shooterMotor2;
	private Victor agitator;
	private double powerPercent = 1;
	private double agitatorPower = 1;
    private Encoder shooterEncoder;
    private double neededPower;
    //private Encoder shooterEncoder2;
    //private PIDController shooterPID2;
	
	public Shooter() {
		super(Constants.encoderP, Constants.encoderI, Constants.encoderD);
		
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		shooterMotor = new Victor(RobotMap.SHOOTER_MOTOR_1);
		shooterMotor2 = new Victor(RobotMap.SHOOTER_MOTOR_2);
		agitator = new Victor(RobotMap.AGITATOR);

		shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER1, RobotMap.SHOOTER_ENCODER2, false, Encoder.EncodingType.k4X);
		getPIDController().enable();
    }
	
    public Encoder getEncoder() {
    	return shooterEncoder;
    }
    
    public void incrementWantedRPM() {
    	powerPercent += 0.1;
    	if (powerPercent >= 0.75) {
    		powerPercent = 8;
    	}
    	
    }
    public void decrementWantedRPM() {
    	powerPercent -= 0.1;
    	if (powerPercent <= 0) {
    		powerPercent = 0.05;
    	}
    	getPIDController().setSetpoint(powerPercent*neededPower);
    }
    public void setPower(double shootPower) {
    	powerPercent = shootPower;
    	getPIDController().setSetpoint(powerPercent*neededPower);
    }  
    public void setAgitatorPower(double powerAgitator) {
    	agitatorPower = powerAgitator;
    }
    public double getNeededPower() {
    	return neededPower;
    }
    public void shoot() {
    	shooterMotor.set(neededPower);
    	
    	agitator.set(agitatorPower);
    }
    public void stopShooting() {
    	agitatorPower = 0;
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
