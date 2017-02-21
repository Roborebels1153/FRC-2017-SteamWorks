package com.walpole.frc.team.robot;

public class Constants {
	
	// Speeds used to control the robot in Tele-Op mode (Brigham)
	public static final double TELE_DRIVE_SPEED = 0.8;
	public static final double TELE_TURN_SPEED = 0.75;

    // Gear Ratios for 2017 Prototype (1154)
    public static final int WHEEL_DIAMETER = 4;
    public static final double SECOND_STAGE_REVOLUTIONS = 3;
    public static final double OUTPUT_REVOLUTIONS = 2.5;
    public static final double ENCODER_COUNTS_PER_REV = 7.5;
    public static final int TICKS_PER_ENCODER_REV = 128;
    public static final double inchesPerTick = WHEEL_DIAMETER * Math.PI / (ENCODER_COUNTS_PER_REV * TICKS_PER_ENCODER_REV);
    public static final double ticksPerInch = (ENCODER_COUNTS_PER_REV * TICKS_PER_ENCODER_REV) / (WHEEL_DIAMETER * Math.PI);
    
    // Gear Ratios for 2016 Final Bot (1156)
    // public static final double ticksPerInch = 32.158; 

    // PID Constants for 2017 Prototype (1154)
    public static final double encoderP = 0.2025;
    public static final double encoderI = 0;
    public static final double encoderD = 0.5075;
    // Box Gyro
    public static final double gyroP = -0.0172997;
    public static final double gyroI = 0;
    public static final double gyroD = 0;
    
    // PID Constants for 2016 Final Bot (1156)
    /*public static final double encoderP = 0.0015;
    public static final double encoderI = 0;
    public static final double encoderD = 0;*/
    // SPI Gyro
    /*public static final double gyroP = 0.14;
    public static final double gyroI = 0;
    public static final double gyroD = 0;*/
    
    // PID Constants for Kit Bot (1156)
    /*public static final double gyroP = -0.15;
    public static final double gyroI = 0;
    public static final double gyroD = -0.16;*/
    
    //public static final double TURN_SPEED = 0.25;
    //public static final double originalWantedRPM = 3000;
}