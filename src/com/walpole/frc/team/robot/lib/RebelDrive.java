
package com.walpole.frc.team.robot.lib;

import com.walpole.frc.team.robot.Robot;
import com.walpole.frc.team.robot.subsystems.Drive.Shifter;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class RebelDrive extends RobotDrive {

    NegInertiaCalc lowSpeedNic;
    NegInertiaCalc highSpeedNic;
    private static double STRAIGHT_THROTTLE = 1;
    private static double TURN_THROTTLE = 0.85;
    private static double SLOW = 0.7;

    public RebelDrive(SpeedController frontLeftMotor, SpeedController rearLeftMotor, SpeedController frontRightMotor,
	    SpeedController rearRightMotor) {
	super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
	lowSpeedNic = new NegInertiaCalc(2);
	highSpeedNic = new NegInertiaCalc(4);
    }
    /**
    *Allegedly Uses the NegitiveInertiaClass to control the Robot's Speed
    */

    public void arcadeDrive(GenericHID stick, Shifter gear) {

	if (Shifter.Low.equals(gear)) {
	    arcadeDrive(stick.getY(), stick.getRawAxis(4));
	} else {
	    arcadeDrive(highSpeedNic.calculate(stick.getRawAxis(1)), highSpeedNic.calculate(stick.getRawAxis(4)), true);
	}
    }

    // Remove overridden implementation of arcade drive
    /*@Override
    public void arcadeDrive(double driveSpeed, double turnSpeed) {
	if (Speed.Slow.equals(Robot.drive.getCurrSpeed())) {
	    arcadeDrive(driveSpeed * SLOW, turnSpeed, true);
	} else {
	    arcadeDrive(driveSpeed * STRAIGHT_THROTTLE, lowSpeedNic.calculate(turnSpeed * TURN_THROTTLE), true);

	}
    }*/
    
    
    public void arcadeTankDrive(double driveLeftSpeed, double driveRightSpeed, double turnSpeed) {
	double moveValue;
	
	// Use the average of the two drive speeds to find the direction of the robot
	if (driveLeftSpeed > 0) {
	    moveValue = 1;
	} else {
	    moveValue = -1;
	}
    }
}
