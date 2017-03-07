package com.walpole.frc.team.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {	
	
	// DRIVE RELATED
	
	public static final int DRIVER_JOYSTICK = 0;
	public static final int OPERATOR_STICK = 1;
	
	public static final int JOYSTICK_LEFT_Y = 1;
	public static final int JOYSTICK_RIGHT_X = 4;
	
	public static final int LIGHT_SENSOR = 7;
	
	public static final int LEFT_FRONT_MOTOR = 0;
	public static final int LEFT_BACK_MOTOR = 1;
	public static final int RIGHT_FRONT_MOTOR = 2;
	public static final int RIGHT_BACK_MOTOR = 3;
	
	public static final int LEFT_ENCODER_A = 0;
	public static final int LEFT_ENCODER_B = 1;
	public static final int RIGHT_ENCODER_A = 2;
	public static final int RIGHT_ENCODER_B = 3;
	
	public static final int GYRO = 0;
	
	public static final int HOPPER_LED_STRIP = 0;
	
	public static final int TRANSMISSION_SOLENOID_A = 0;
	public static final int TRANSMISSION_SOLENOID_B = 1;

	// SHOOTER/COLLECTOR RELATED
	

	public static int SHOOTER_MOTOR = 4;
	
	public static int AGITATOR_MOTOR = 5;
	
	public static int INTERNAL_MOTOR = 9;
	
	public static int COLLECT_MOTOR = 8;
	
	public static int INDEXER_PISTON = 6;
	
	public static int LIGHT = 0;

	// CLIMBER RELATED
	
	public static final int CLIMB_MOTOR = 7;

	public static final int CLIMB_LIMIT_SWITCH = 8;
	
	// GEAR RELATED

	
	public static final int GEAR_SOLENOID_A = 2;
	public static final int GEAR_PUSHER_SOLENOID_A = 3;
	public static final int BALL_INTAKE_SOLENOID = 4;
}