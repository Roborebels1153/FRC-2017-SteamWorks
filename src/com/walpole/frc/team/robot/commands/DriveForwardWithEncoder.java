
package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardWithEncoder extends Command {
	
	private double speed;
	
	private double inchesToDrive;
	
	

    public DriveForwardWithEncoder() {
    	requires (Robot.driveSubsystem);
    	this.speed = 1;
    	this.inchesToDrive = 10;
    			
        // Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.resetEncoders();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.driveAtSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Robot.driveSubsystem.getLeftEncoderCount() >= (Constants.ticksPerInch * inchesToDrive));   
    	//if the encoder tick count is above the target tick count, the motors will stop

    	 
    
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.stopDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
   
    }
}
