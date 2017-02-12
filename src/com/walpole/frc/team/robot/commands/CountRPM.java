//
//package com.walpole.frc.team.robot.commands;
//
//import com.walpole.frc.team.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class CountRPM extends Command {
//	
//	private double runCount;
//	
//	private double timer;
//
//    public CountRPM( double runCount) {
//        requires(Robot.Counter);
//        this.runCount = runCount * 50;
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	Robot.Counter.reset();
//    	timer = 0;
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	timer++;
//        	
//    	if(Robot.Counter.getLightSensor() == true) {
//    		Robot.Counter.increment(); 
//    	} 
//    }
//    			
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return timer > runCount;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//}
