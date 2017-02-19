package com.walpole.frc.team.robot;

public class Constants {

    public static int WHEEL_DIAMETER = 4;
    public static double SECOND_STAGE_REVOLUTIONS = 3;
    public static double OUTPUT_REVOLUTIONS = 2.5;
    public static double ENCODER_COUNTS_PER_REV = 7.5;
    public static int TICKS_PER_ENCODER_REV = 128;
    public static double inchesPerTick = WHEEL_DIAMETER * Math.PI / (ENCODER_COUNTS_PER_REV * TICKS_PER_ENCODER_REV);
    public static double ticksPerInch = (ENCODER_COUNTS_PER_REV * TICKS_PER_ENCODER_REV) / (WHEEL_DIAMETER * Math.PI);
    
    //Constants for 2016 robot (1153)
    /*public static int WHEEL_DIAMETER = 10;
    public static double ENCODER_COUNTS_PER_REV = 8.5;
    public static int TICKS_PER_ENCODER_REV = 128;
    public static double inchesPerTick = WHEEL_DIAMETER * Math.PI / (ENCODER_COUNTS_PER_REV * TICKS_PER_ENCODER_REV);
    public static double ticksPerInch = (ENCODER_COUNTS_PER_REV * TICKS_PER_ENCODER_REV) / (WHEEL_DIAMETER * Math.PI);*/
    //public static double ticksPerInch = 32.158; 

    // PID Constants for 1154 (THIS YEARS ROBOT (2017))
    public static double encoderP = 0.2025;
    public static double encoderI = 0;
    public static double encoderD = 0.5075;
    
    // PID Constants for 1153 (2016 robot)
    /*public static double encoderP = 0.01;
    public static double encoderI = 0;
    public static double encoderD = 0;*/
    
    // PID Constants for 1153 (2016 robot)
   // public static double encoderP = 0.0015;
   // public static double encoderI = 0;
   // public static double encoderD = 0;
    
    // PID Constants for 1156
    /*public static double gyroP = -0.15; // These Values Work!
    public static double gyroI = 0;
    public static double gyroD = -0.16; // These Values Work!*/
    
    /*// PID Constants for 1153 (SPI Gyro) (2016 robot)
    public static double gyroP = 0.14;
    public static double gyroI = 0;
    public static double gyroD = 0.1;*/
    
    //PID Constants for 1154   (THIS YEARS ROBOT (2017))   (Box Gyro)
    public static double gyroP = -0.0172997;
    public static double gyroI = 0;
    public static double gyroD = 0;
    
    //public static double TURN_SPEED = 0.25;
    //public static double originalWantedRPM = 3000;
}
//    public static double encoderD = 0.02;
//    
//    
//     
//    public static double gyroP = 0.35;
//    public static double gyroI = 0;
//    public static double gyroD = 0.015;
//    
//	
//}