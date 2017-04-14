  package com.walpole.frc.team.robot;

public class Constants {

	// Speeds used to control the robot in Tele-Op mode (Brigham)
	public static final double TELE_DRIVE_SPEED = 0.8;
	public static final double TELE_TURN_SPEED = 0.75;
    public static double FLOOR_GEAR_LEVER_SPEED = 1;
    public static double FLOOR_GEAR_COLLECTOR_SPEED = 1;
    public static double FLOOR_GEAR_COLLECTOR_PASSIVE_SPEED = 0.275;

    // Gear Ratios for 2017 Prototype (1154)
    /* public static int WHEEL_DIAMETER = 4;
    public static double SECOND_STAGE_REVOLUTIONS = 3;
    public static double OUTPUT_REVOLUTIONS = 2.5;
    public static double ENCODER_COUNTS_PER_REV = 7.5;
    public static int TICKS_PER_ENCODER_REV = 128;
    public static double inchesPerTick = WHEEL_DIAMETER * Math.PI / (ENCODER_COUNTS_PER_REV * TICKS_PER_ENCODER_REV);
    public static double ticksPerInch = (ENCODER_COUNTS_PER_REV * TICKS_PER_ENCODER_REV) / (WHEEL_DIAMETER * Math.PI);
    */

   // public static double ticksPerInch = 73.825; //73.825 is the ticksPerInch for the Prototype before the gear box changes
    public static double ticksPerInch = 60.7; //ticksPerInch after gear ratio changes
    // Gear Ratios for 2016 Final Bot (1156)
    /*public static int WHEEL_DIAMETER = 10;
    public static double ENCODER_COUNTS_PER_REV = 8.5;
    public static int TICKS_PER_ENCODER_REV = 128;
    public static double inchesPerTick = WHEEL_DIAMETER * Math.PI / (ENCODER_COUNTS_PER_REV * TICKS_PER_ENCODER_REV);
    public static double ticksPerInch = (ENCODER_COUNTS_PER_REV * TICKS_PER_ENCODER_REV) / (WHEEL_DIAMETER * Math.PI);*/
    // public static double ticksPerInch = 32.158; 

    // PID Constants for 2017 FINAL Robot (1153)
//    public static double encoderP = 0.05;
//    public static double encoderI = 0;
//    public static double encoderD = 0.0055;
    
    //PID CONSTANTS 2017 WORKED ON PROTOTYPE(UNH) 
    public static double encoderP = 0.36; 
    public static double encoderI = 0;
    public static double encoderD = 0.448;
    
//    public static double encoderP = 0.017; 
//    public static double encoderI = 0; 
//    public static double encoderD = 0.1;
   
    
    public static double gearEncoderP = 0.01; 
    public static double gearEncoderI = 0; 
    public static double gearEncoderD = 0; 
    
    
   
    // Navx-Micro Gyro
    public static double gyroP = 0.041;
    public static double gyroI = 0;
    public static double gyroD = 0.026;
    // Box Gyro
    /*public static double gyroP = -0.0172997;
    public static double gyroI = 0;
    public static double gyroD = 0; */
    
    /*// PID Constants for 2016 Final Bot (1156)
    public static double encoderP = 0.0015;
    public static double encoderI = 0;
    public static double encoderD = 0;*/
  
    // PID Constants for Kit Bot (1156)
    /*public static double gyroP = -0.15;
    public static double gyroI = 0;
    public static double gyroD = -0.16;*/
    
    //public static double TURN_SPEED = 0.25;
    //public static double originalWantedRPM = 3000;
}