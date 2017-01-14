
package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.subsystems.Drive;

import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	
	private RobotDrive robotDrive;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private SpeedController leftFrontVictor;
	private SpeedController leftBackVictor;
	private SpeedController rightFrontVictor;
	private SpeedController rightBackVictor;
	private double currSpeed;
	public enum Shifter {High, Low}
	public enum Speed {Normal, Slow}
	
	public Drive() {
		leftFrontVictor = new Victor(RobotMap.LEFT_FRONT_MOTOR);
		leftBackVictor = new Victor(RobotMap.LEFT_BACK_MOTOR);
		rightFrontVictor = new Victor(RobotMap.RIGHT_FRONT_MOTOR);
		rightBackVictor = new Victor(RobotMap.RIGHT_BACK_MOTOR);
		
		robotDrive = new RobotDrive(leftFrontVictor, leftBackVictor, rightFrontVictor, rightBackVictor);
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void drive(Joystick joystick) {
    	double moveValue = 0.8 * joystick.getY();
    	currSpeed = moveValue;
		double rotateValue = 0.75 * joystick.getX();
		robotDrive.arcadeDrive(moveValue, rotateValue, true);
    }
    
    public double getCurrSpeed () {
		return currSpeed;
	}
    
    
    
}
