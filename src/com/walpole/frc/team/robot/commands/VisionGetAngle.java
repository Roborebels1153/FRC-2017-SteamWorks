package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionGetAngle extends Command {

    public VisionGetAngle() {
        
    }

    @Override
    protected void initialize() {
    	SmartDashboard.putNumber("Target 1 Height", Robot.target1_height);
		SmartDashboard.putNumber("Target 2 Height", Robot.target2_height);
		SmartDashboard.putNumber("Target Right Height", Robot.target_right_height);
		SmartDashboard.putNumber("Target Left Height", Robot.target_left_height);


    }

    @Override
    protected void execute() {
    	if(Robot.number_targets == 2.0) {
    		SmartDashboard.putNumber("Target 1 Height", Robot.target1_height);
    		SmartDashboard.putNumber("Target 2 Height", Robot.target2_height);
    		SmartDashboard.putNumber("Target Right Height", Robot.target_right_height);
    		SmartDashboard.putNumber("Target Left Height", Robot.target_left_height);
    	//TODO:Compare heights
    		
    	}
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}