package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.Robot;
import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{
	private Victor shooterMotor;
	private double speed = 1;

	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		shooterMotor = new Victor(RobotMap.SHOOTER_MOTOR);
    }
    public void incrementSpeed(double incrementNum) {
    	speed = speed + incrementNum;
    	if (speed > 1) {
    		speed = 1;
    	} else if (speed <= 0) {
    		speed = 0.05;
    	}
    }
    public void setSpeed(double shootSpeed) {
    	speed = shootSpeed;
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
}
