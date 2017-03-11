package com.walpole.frc.team.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {	
	/** DRIVE RELATED**/
	
	public static int DRIVER_JOYSTICK = 0;
	public static int OPERATOR_STICK = 1;
	
	public static int JOYSTICK_LEFT_Y = 1;
	public static int JOYSTICK_RIGHT_X = 4;
		
	public static int LEFT_FRONT_MOTOR = 0;
	public static int LEFT_BACK_MOTOR = 1;
	public static int RIGHT_FRONT_MOTOR = 2;
	public static int RIGHT_BACK_MOTOR = 3;
	
	public static int LEFT_ENCODER_A = 0;
	public static int LEFT_ENCODER_B = 1;
	public static int RIGHT_ENCODER_A = 2;
	public static int RIGHT_ENCODER_B = 3;
	
	public static int GYRO = 0;
	
	public static int TRANSMISSION_SOLENOID_A = 0;
	public static int TRANSMISSION_SOLENOID_B = 1;
	
	/** SHOOTER/COLLECTOR RELATED**/
	
	public static int SHOOTER_MOTOR = 4;
	public static int AGITATOR_MOTOR = 6;
	
//	public static int LIGHT = 0;
	
	public static int LIGHT_SENSOR = 7;

//	public static int CONVEYER_MOTORS = 6;   //Aayush and Brigham: we are commenting this out because it was not functioning properly (2/21)

	
//	public static int AGITATOR = 8;
	
	/**CLIMBER RELATED**/
	
	public static int CLIMB_MOTOR = 7;
	
	public static int CLIMB_LIMIT_SWITCH = 8;
	
	public static int CLIMB_LIMIT_SWITCH_CLOSER = 9;
	
	/**GEAR RELATED**/
	
	public static int GEAR_SOLENOID_A = 3;
	//public static int GEAR_SOLENOID_B = 3;
	public static int GEAR_PUSHER_SOLENOID_A = 2;
	//public static int GEAR_PUSHER_SOLENOID_B = 5;
	public static int BALL_INTAKE_SOLENOID = 4;
	
	public static int GEAR_MOTOR_LEFT = 8;
	public static int GEAR_MOTOR_RIGHT = 9;
	public static int GEAR_MOTOR_COLLECTOR = 5;
	
	public static int GEAR_ENCODER_A = 4;
	public static int GEAR_ENCODER_B = 5;
	
	
}