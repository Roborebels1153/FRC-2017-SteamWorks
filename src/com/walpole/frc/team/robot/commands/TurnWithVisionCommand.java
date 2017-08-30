package com.walpole.frc.team.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.walpole.frc.team.robot.Robot;

/**
 *
 */
public class TurnWithVisionCommand extends Command {
	double errorDifference;
	double lastError = 0;
	private int loopCount = 0;
	double turnSpeed = 0.6;
	double rightkF = 0.46;
	double leftkF = -0.46;
	long startTime;
    int errorTolerance;
	// kP must be negative so we turn in the correct direction
	double kP = -(0.088/160);
	double kD = 0.001;
	
			
	public TurnWithVisionCommand(int errorTolerance) {
		
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
		this.errorTolerance = errorTolerance;
		
		
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.drive.turnWithVision(0);
		startTime = System.currentTimeMillis();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		errorDifference = Robot.error - lastError;
	if (loopCount % 1 == 0){
		if(Robot.number_targets == 0) {
			turnSpeed = 0;
			//Robot.drive.turnWithVision(0);
		} else if (Robot.error > errorTolerance) {
			turnSpeed = (Robot.error *kP) + leftkF + (errorDifference * kD);
			//Robot.drive.turnWithVision(-0.6);
		} else if (Robot.error < -(errorTolerance)) {
			turnSpeed = (Robot.error *kP) + rightkF - (errorDifference * kD);
			//Robot.drive.turnWithVision(0.6);
		}	else {
			turnSpeed = 0;
		}
	}
		
		Robot.drive.turnWithVision(turnSpeed);
		loopCount++;
		SmartDashboard.putNumber("Turn Speed", turnSpeed);
		SmartDashboard.putNumber("Loop Count", loopCount);
		lastError = Robot.error;
			
		}
	

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return  (System.currentTimeMillis()- startTime) > 1000 && (Robot.error <= errorTolerance);		
		
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}