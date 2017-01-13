package org.usfirst.frc.team1153.robot.subsystems;

import org.usfirst.frc.team1153.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{
	private Victor shooterMotor1;
	private Victor shooterMotor2;

	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		shooterMotor1 = new Victor(RobotMap.SHOOTER_MOTOR1);
		shooterMotor2 = new Victor(RobotMap.SHOOTER_MOTOR2);
    }
    
    public void shoot() {
    	shooterMotor1.set(1);
    	shooterMotor2.set(-1);
    }
    public void stopShooting() {
    	shooterMotor1.set(0);
    	shooterMotor2.set(0);
    }
}
