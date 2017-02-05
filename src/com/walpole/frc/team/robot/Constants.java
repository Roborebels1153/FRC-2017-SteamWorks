package com.walpole.frc.team.robot;

public class Constants {

    public static int WHEEL_DIAMETER = 4;
    public static double SECOND_STAGE_REVOLUTIONS = 3;
    public static double OUTPUT_REVOLUTIONS = 2.5;
    public static double ENCODER_COUNTS_PER_REV = 7.5;
    public static int TICKS_PER_ENCODER_REV = 128;
    public static double inchesPerTick = WHEEL_DIAMETER * Math.PI / (ENCODER_COUNTS_PER_REV * TICKS_PER_ENCODER_REV);
    public static double ticksPerInch = (ENCODER_COUNTS_PER_REV * TICKS_PER_ENCODER_REV) / (WHEEL_DIAMETER * Math.PI);

    // PID Constants
    public static double encoderP = 0.78;
    public static double encoderI = 0;
    public static double encoderD = 0;

    public static double gyroP = -0.15; //These Values Work!
    public static double gyroI = 0;
    public static double gyroD = -0.16; //These Values Work!

    public static double TURN_SPEED = 0.25;
}