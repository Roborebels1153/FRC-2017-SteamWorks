package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{
	private Victor shooterMotor;
	public double speed;

	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		shooterMotor = new Victor(RobotMap.SHOOTER_MOTOR);
    }
    
    public void shoot() {
    	shooterMotor.set(speed);
    }
    public void stopShooting() {
    	shooterMotor.set(0);
    }
}
