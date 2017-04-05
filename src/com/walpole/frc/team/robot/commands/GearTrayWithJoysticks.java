package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;
import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearTrayWithJoysticks extends Command {

    public GearTrayWithJoysticks() {
       requires(Robot.floorGear);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Joystick stick = Robot.oi.getOperatorJoystick();
    	Robot.floorGear.gear(stick);
    	
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
