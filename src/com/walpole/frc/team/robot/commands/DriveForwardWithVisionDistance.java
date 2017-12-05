package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveForwardWithVisionDistance extends Command {
	//Drive
    private double speed;
    private double setPoint;
    private long startTimeMillis;
    private double secondsToDrive;
    
    //Vizzon
    double errorDifference;
	double lastError = 0;
	private int loopCount = 0;
	int withinErrorRange;
	double turnSpeed = 0.6;
	double rightkF = 0.2;
	double leftkF = -0.2;
	long startTime;
    int errorTolerance;
	// kP must be negative so we turn in the correct direction
	double kP = -(0.125/160);
	double kD = 0.001;
	int errorOffset;
	int adjustedError;
	double inchesToDrive;
    
    
    public DriveForwardWithVisionDistance(double inchesToDrive, double speed, int errorTolerance, int errorOffset ) {
	requires(Robot.drive);
	//Drive
	this.speed = speed;
	this.inchesToDrive = inchesToDrive;
	//Vission
	this.errorTolerance = errorTolerance;
	this.errorOffset = errorOffset;
    }
    
    public DriveForwardWithVisionDistance(double inchesToDrive, double speed, double secondsToDrive, int errorTolerance, int errorOffset) {
	requires(Robot.drive);
	//Drive
	this.speed = speed;
	this.setPoint = Constants.ticksPerInch * inchesToDrive;
	this.secondsToDrive = secondsToDrive;
	
	//Vission
	this.errorTolerance = errorTolerance;
	this.errorOffset = errorOffset;
	SmartDashboard.putString("Am I Getting Vision Distance", "ya messed up wrong constructor");

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	if (inchesToDrive != 0.0) {
    		SmartDashboard.putString("Am I Getting Vision Distance", "bad");

    		this.setPoint = Constants.ticksPerInch * inchesToDrive; //this line alone = original
    		SmartDashboard.putNumber("inchesToDrive", inchesToDrive);
    	} else {
    		SmartDashboard.putString("Am I Getting Vision Distance", "yes");
    		Robot.findDist();
    		this.setPoint = Constants.ticksPerInch * (1.0*(Robot.dist));
    	}
    	//this.secondsToDrive = secondsToDrive;
    	
    	
    	
	//startTimeMillis = System.currentTimeMillis();
	//Drive
	Robot.drive.disableDrivePID();
	Robot.drive.resetEncoders();
	Robot.drive.setMaxDrivePIDOutput(speed);
	Robot.drive.setDrivePIDSetPoint(setPoint);
	//Robot.floorGear.setMotorValue(-0.1);
	Robot.drive.enableDrivePID();
	
	//Vission
	withinErrorRange = 0;
	Robot.drive.turnWithVision(0);
	startTime = System.currentTimeMillis();
	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    //drive
	double leftOutput = Robot.drive.getLeftPIDOutput();
	double rightOutput = Robot.drive.getRightPIDOutput(); 
	double driveOutput = (leftOutput + rightOutput) / 2;
	
	//Vission
	adjustedError = Robot.error + errorOffset;
	errorDifference = adjustedError - lastError;
if (loopCount % 1 == 0){
	if(Robot.number_targets == 0) {
		turnSpeed = 0;
		//Robot.drive.turnWithVision(0);
	} else if (adjustedError > errorTolerance) {
		turnSpeed = (adjustedError *kP) + leftkF + (errorDifference * kD);
		//Robot.drive.turnWithVision(-0.6);
	} else if (adjustedError < -(errorTolerance)) {
		turnSpeed = (adjustedError *kP) + rightkF - (errorDifference * kD);
		//Robot.drive.turnWithVision(0.6);
	}	else {
		turnSpeed = 0;
		withinErrorRange++;
	}
}
	
	//Robot.drive.turnWithVision(turnSpeed);
	loopCount++;
	SmartDashboard.putNumber("Turn Speed", turnSpeed);
	SmartDashboard.putNumber("Loop Count", loopCount);
	lastError = adjustedError;
		
	
	//The 
	Robot.drive.arcadeDrive(-leftOutput, turnSpeed, false);
	Robot.floorGear.stayInPosition();
	//This makes it goes forward
	//Robot.drive.arcadeDrive(-Robot.drive.getLeftPIDOutput(), 0);
	//Robot.drive.arcadeTankDrive(leftOutput, rightOutput, gyroOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double leftMotorPower = Robot.drive.getLeftMotorPower();
    	double error = Math.abs(Robot.drive.getLeftPIDError());
//    	double encoderTicks = Robot.drive.getLeftEncoderCount();
////    	if ((leftMotorPower <= 0.1 && error <= 50) || System.currentTimeMillis() - startTimeMillis >= secondsToDrive * 1000) {
////    		return true;
////    	} else {
////    		return false; 
////    	}
    	return (error <=100) && ((System.currentTimeMillis()- startTime) > 1000) && (adjustedError <= errorTolerance) && (Robot.number_targets >=1) && (withinErrorRange >= 3);//leftMotorPower <= 0.1 && error <= 500; 
//    	
    	// Use While Tuning
//    	return false;
    	
    	//return encoderTicks == setPoint;
    }
    

    // Called once after isFinished returns true
    protected void end() {
	Robot.drive.stopDrive();
	Robot.drive.disableDrivePID();
//	Robot.floorGear.setGearMotor(-0.1);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	//Robot.driveSubsystem.stopDrive();
	Robot.drive.disableDrivePID();
    }
}