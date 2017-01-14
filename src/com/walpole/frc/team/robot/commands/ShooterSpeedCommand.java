package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterSpeedCommand extends Command {
	private int buttonNum;
    public ShooterSpeedCommand(int buttonNumber) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
        buttonNum = buttonNumber;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (buttonNum == 0) {
    		Robot.shooter.speed = 0.25;
    	} else if (buttonNum == 1) {
    		Robot.shooter.speed = 0.5;
    	} else if (buttonNum == 2) {
    		Robot.shooter.speed = 0.75;
    	} else if (buttonNum == 3) {
    		Robot.shooter.speed = 1;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
