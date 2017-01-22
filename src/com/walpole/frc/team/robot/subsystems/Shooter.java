package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.Robot;
import com.walpole.frc.team.robot.RobotMap;
import com.walpole.frc.team.robot.commands.ShooterShootCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem{
	private Victor shooterMotor;
	private Victor shooterMotor2;
	private double power = 1;
    private Encoder shooterEncoder;


	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Shooter() {
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		shooterMotor = new Victor(RobotMap.SHOOTER_MOTOR);
		shooterMotor2 = new Victor(RobotMap.COLLECT_MOTOR);

		shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER1, RobotMap.SHOOTER_ENCODER2, false, Encoder.EncodingType.k4X);
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
    }
    public void stopShooting() {
    	shooterMotor.set(0);
    	shooterMotor2.set(0);
    }
}
