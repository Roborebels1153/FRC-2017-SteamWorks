package com.walpole.frc.team.robot.subsystems;

import java.util.concurrent.TimeUnit;

import com.walpole.frc.team.robot.Robot;
import com.walpole.frc.team.robot.RobotMap;
import com.walpole.frc.team.robot.commands.ShooterShootCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends PIDSubsystem{
	private Victor shooterMotor;
	private Victor shooterMotor2;
	private Victor agitator;
	private double power = 1;
	private double agitatorPower;
    private Encoder shooterEncoder;
    private PIDController shooterPID;
    private Encoder shooterEncoder2;
    private PIDController shooterPID2;
   	private double encoderP;
	private double encoderI;
	private double encoderD;
	
	public Shooter(double p, double i, double d) {
		super(p, i, d);
		encoderP = p;
		encoderI = i;
		encoderD = d;
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
		
		shooterPID = new PIDController(encoderP, encoderI, encoderD, shooterEncoder, shooterMotor);
    }
	
    public Encoder getEncoder() {
    	return shooterEncoder;
    }
    
    public void incrementPower() {
    	power += 0.1;
    	if (power >= 0.95) {
    		power = 1;
    	}
    	
    }
    public void decrementPower() {
    	power -= 0.1;
    	if (power <= 0) {
    		power = 0.05;
    	}
    }
    public void setPower(double shootPower) {
    	power = shootPower;
    }
    public double getPower() {
    	return power;
    }
    public void shoot() {
    	shooterMotor.set(power);
    	shooterMotor2.set(power*-1);
    	
    	agitator.set(agitatorPower);
    	if (shooterEncoder.getRate() > 100*power) {
    		agitatorPower = 1;
    	} else {
    		agitatorPower = 0;
    	}
    }
    public void stopShooting() {
		agitatorPower = 0;
    	agitator.set(0);
    	shooterMotor.set(0);
    	shooterMotor2.set(0);
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}
}
