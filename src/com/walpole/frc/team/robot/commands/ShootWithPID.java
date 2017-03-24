/*package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShootWithPID extends Command {
	
	private final int requiredRPM;
	private double shooterOutput;
	private final double speed;
	
    public ShootWithPID(int requiredRPM) {
    	this.requiredRPM = requiredRPM;
    	this.speed = 0.8;
    }
    
    public ShootWithPID(int requiredRPM, double speed) {
    	this.requiredRPM = requiredRPM;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.setShooterSetpoint(requiredRPM);
    	Robot.shooter.setMaxPIDOutput(speed);
    	Robot.shooter.enablePID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double shooterOutput = Robot.shooter.getShooterPIDOutput();
    	
    	
    	
    	SmartDashboard.putNumber("Shooter Setpoint", requiredRPM);
    	SmartDashboard.putNumber("speed", speed);
    	SmartDashboard.putNumber("Shooter Output", Robot.shooter.getShooterPIDOutput());
    	
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
}*/