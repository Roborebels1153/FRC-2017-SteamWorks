package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterShootCommand extends Command {
	
	private double secondsTillAgitator;
	private long startTimeMillis;



        // Use requires() here to declare subsystem dependencies
	
	public ShooterShootCommand(double secondsTillAgitator) { 
		requires(Robot.shooter); 
    	this.secondsTillAgitator = secondsTillAgitator;
    }

    // Called just before this Command runs the first time
    
    	
	protected void initialize() {
		startTimeMillis = System.currentTimeMillis();

		
	}

   

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.shooter.shoot();
    	Robot.shooter.shootWhenWeDontHaveALightSensor();  
    	if (System.currentTimeMillis() - startTimeMillis >= secondsTillAgitator * 1000) {
    		Robot.shooter.agitatorOn();
    		
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

    