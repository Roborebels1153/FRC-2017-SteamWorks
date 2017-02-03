package com.walpole.frc.team.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {  
	
	public static int LEFT_FRONT_MOTOR = 0;
	public static int LEFT_BACK_MOTOR = 1;
	public static int RIGHT_FRONT_MOTOR = 2;
	public static int RIGHT_BACK_MOTOR = 3;
	
	public static int SHOOTER_MOTOR_1 = 4;
	public static int SHOOTER_MOTOR_2 = 5;
	public static int AGITATOR = 6;
	
	public static int COLLECTOR_MOTOR = 7;
	
	//SENSORS
	public static int SHOOTER_ENCODER1 = 0;
	public static int SHOOTER_ENCODER2 = 1;
	public static int LIGHT_SENSOR = 9;
	

    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
}
