package com.walpole.frc.team.robot;

public class Constants {
    
    public static double FLOOR_GEAR_LEVER_SPEED = 0.7;
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
    public static double ticksPerInch = 73.825;
    // Gear Ratios for 2016 Final Bot (1156)
    /*public static int WHEEL_DIAMETER = 10;
    public static double ENCODER_COUNTS_PER_REV = 8.5;
    public static int TICKS_PER_ENCODER_REV = 128;
    public static double inchesPerTick = WHEEL_DIAMETER * Math.PI / (ENCODER_COUNTS_PER_REV * TICKS_PER_ENCODER_REV);
    public static double ticksPerInch = (ENCODER_COUNTS_PER_REV * TICKS_PER_ENCODER_REV) / (WHEEL_DIAMETER * Math.PI);*/
   // public static double ticksPerInch = 32.158; 

    /*// PID Constants for 2017 FINAL Robot (1153)
    public static double encoderP = 0.012;
    public static double encoderI = 0;
    public static double encoderD = 0.06882; */
    
    //PID Constants for 2017 PROTOTYPE Robot(1154)
    public static double encoderP = 0.017;
    public static double encoderI = 0;
    public static double encoderD = 0.06882; 
    public static double encoderRateP = 0;
    public static double encoderRateI = 0;
    public static double encoderRateD = 0;
    public static double encoderRateF = 0;
    // Navx-Micro Gyro
    public static double gyroP = 0.05;
    public static double gyroI = 0;
    public static double gyroD = 0.025;
    // Box Gyro
   /* public static double gyroP = -0.0172997;
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