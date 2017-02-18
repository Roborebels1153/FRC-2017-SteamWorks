package com.walpole.frc.team.robot.lib;

import com.walpole.frc.team.robot.Robot;
import com.walpole.frc.team.robot.subsystems.Drive.Shifter;
import com.walpole.frc.team.robot.subsystems.Drive.Speed;

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

    public void arcadeDrive(GenericHID stick, Shifter gear) {

	if (Shifter.Low.equals(gear)) {
	    arcadeDrive(stick.getY(), stick.getRawAxis(4));
	} else {
	    arcadeDrive(stick.getY(), highSpeedNic.calculate(stick.getRawAxis(4)), true);
	}
    }

    public void arcadeDrive(double driveSpeed, double turnSpeed) {
	if (Speed.Slow.equals(Robot.drive.getCurrSpeed())) {
	    arcadeDrive(driveSpeed * SLOW, turnSpeed, true);
	} else {
	    arcadeDrive(driveSpeed * STRAIGHT_THROTTLE, lowSpeedNic.calculate(turnSpeed * TURN_THROTTLE), true);

	}
    }
   
    public void arcadeTankDrive(double driveLeftSpeed, double driveRightSpeed, double turnSpeed) {
	double moveValue;
	
	// Use the average of the two drive speeds to find the direction of the robot
	if (avg(driveLeftSpeed, driveRightSpeed) > 0) {
	    moveValue = 1;
	} else {
	    moveValue = -1;
	}
	
	// Find the absolute value of the drive speeds so we can use them to scale
	driveLeftSpeed = Math.abs(driveLeftSpeed);
	driveRightSpeed = Math.abs(driveRightSpeed);

	double leftMotorPower;
	double rightMotorPower;

	// Scale each motor power by multiplying full motor power by the motor speed
        /*if (moveValue > 0.0) {
	    if (turnSpeed > 0.0) {
		// We are going forward and turning right
		leftMotorPower = driveLeftSpeed * (moveValue - turnSpeed);
		rightMotorPower = Math.max((driveRightSpeed * moveValue), turnSpeed);
	    } else {
		// We are going forward and turning left
		leftMotorPower = Math.max((driveLeftSpeed * moveValue), -turnSpeed);
		rightMotorPower = driveRightSpeed * (moveValue + turnSpeed);
	    }
	} else {
	    if (turnSpeed > 0.0) {
		// We are going backwards and turning left
		leftMotorPower = -Math.max(-(driveLeftSpeed * moveValue), turnSpeed);
		rightMotorPower = driveRightSpeed * (moveValue + turnSpeed);
	    } else {
		// We are going backwards and turning right
		leftMotorPower = driveLeftSpeed * (moveValue - turnSpeed);
		rightMotorPower = -Math.max(-(driveRightSpeed * moveValue), -turnSpeed);
	    }
	}*/
	
	// Scale each motor power by multiplying full motor power by the motor speed
	if (moveValue > 0.0) {
	    if (turnSpeed > 0.0) {
		// We are going forward and turning right
		leftMotorPower = driveLeftSpeed * (moveValue - turnSpeed);
		rightMotorPower = driveRightSpeed * (Math.max(moveValue, turnSpeed));
	    } else {
		// We are going forward and turning left
		leftMotorPower = driveLeftSpeed * (Math.max(moveValue, -turnSpeed));
		rightMotorPower = driveRightSpeed * (moveValue + turnSpeed);
	    }
	} else {
	    if (turnSpeed > 0.0) {
		// We are going backwards and turning left
		leftMotorPower = driveLeftSpeed * (-Math.max(-moveValue, turnSpeed));
		rightMotorPower = driveRightSpeed * (moveValue + turnSpeed);
	    } else {
		// We are going backwards and turning right
		leftMotorPower = driveLeftSpeed * (moveValue - turnSpeed);
		rightMotorPower = driveRightSpeed * (-Math.max(-moveValue, -turnSpeed));
	    }
	}
	
	setLeftRightMotorOutputs(leftMotorPower, rightMotorPower);
    }
    
    private double avg(double a, double b) {
	return (a + b) / 2;
    }
}