package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterSpeedCommand extends Command {
	
	private double shootSpeed;
    public ShooterSpeedCommand(double speed) {
        // Use requires() here to declare subsystem dependencies
    	shootSpeed = speed;
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.shooter.setShooterSpeed(shootSpeed);
		Robot.shooter.agitatorOff();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
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
