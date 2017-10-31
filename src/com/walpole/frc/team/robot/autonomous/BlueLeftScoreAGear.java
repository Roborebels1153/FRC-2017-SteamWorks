package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.Robot;
import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithVision;
import com.walpole.frc.team.robot.commands.DriveForwardWithVisionDistance;
import com.walpole.frc.team.robot.commands.DriveStraightWithVisionDistance;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.GearCollectorOut;
import com.walpole.frc.team.robot.commands.MoveGearCollectorOutAutoCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.RetainGearCommand;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.StopGearCollectorCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;
import com.walpole.frc.team.robot.commands.TurnWithVisionCommand;
import com.walpole.frc.team.robot.commands.VisionGetAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BlueLeftScoreAGear extends CommandGroup {
    private static final int inchesForward = 60;
   // private static final int inchesForwardProto = 65; //Distance manipulated for PROTOTYPE Robot
    private static final int degreesToTurn = 60;
    private static final double inchesToAirship = 66;
    private static final int secondsToWait = 1; 
   // private static final int inchesBack = 60;
    private static final  double speedForward = 0.35; 
    private static final  double speedToAirShip = 0.4; 
    private static final int encoderTicksDown = 140;            
    private static final double armSpeed = 0.3; 
    private static final double speedToTurn = 0.35; 
    //private static final double dist = Robot.dist; 

    public BlueLeftScoreAGear() {
	super();
	
//	addSequential(new ShiftHighCommand());
//	//addSequential(new MoveGearCollectorOutAutoCommand(35, 0.5)); 
//	addSequential(new DriveForwardWithGyroEncoder(inchesForward, speedForward, 5));
//	addSequential(new WaitCommand(0.5)); 
//	addSequential(new TurnWithGyroCommand(degreesToTurn, speedToTurn));
//	addSequential(new TurnWithGyroCommand(degreesToTurn, speedToTurn));
//	addSequential(new WaitCommand(2)); 
////	addSequential(new MoveGearCollectorOutAutoCommand(0, -0.5, 1.5));
//	//addSequential(new WaitCommand(1));
//	//addSequential(new MoveGearCollectorOutAutoCommand(65, 0.4, 2));
//	//addSequential(new MoveGearCollectorOutAutoCommand(-10, 0.5, 2));
//	addSequential(new MoveGearCollectorOutAutoCommand(31, 0.6, 2));
////	addSequential(new MoveGearCollectorOutAutoCommand(35, 0.5, 2));
//	addSequential(new DriveForwardWithGyroEncoder(inchesToAirship, speedToAirShip, 3));  
//	//addSequential(new MoveGearCollectorOutAutoCommand(37, 0.5, 2)); 
//	//addSequential(new WaitCommand(secondsToWait));
//	//addSequential(new WaitCommand(1)); 
//	addSequential(new GearCollectorOut()); 
//	addSequential(new WaitCommand(3));
//	addSequential(new StopGearCollectorCommand());
//	//addSequential(new MoveGearCollectorOutAutoCommand(encoderTicksDown, armSpeed)); 
	
//	addSequential(new DriveForwardWithGyroEncoder(59, speedForward, 5));
//	addSequential(new TurnWithGyroCommand(45, 0.4));
//	addSequential(new WaitCommand(0.5)); 
//	addSequential(new TurnWithVisionCommand(5,-15));
//	addSequential(new DriveStraightWithVisionDistance(0, speedForward, 0.50));
//	addSequential(new VisionGetAngle());
	
//	addSequential(new DriveForwardWithVision(85, 0.32, 5, 5, -25));
	
	 //Summer Vision Sequence- Driving with vision=works
	/*addSequential(new DriveForwardWithGyroEncoder(59, speedForward, 5));
	addSequential(new TurnWithGyroCommand(45, 0.4));
	addSequential(new WaitCommand(0.5)); 
	addSequential(new TurnWithVisionCommand(5,-15)); //parameter is error Tolerance
	addSequential(new WaitCommand(0.5)); 
	addSequential(new DriveForwardWithVision(85, 0.32, 5, 5, -25));*/
	
	addSequential(new DriveForwardWithVisionDistance(0, 0.32, 5, -20));
	
	
	
	
	//addSequential(new DriveForwardWithGyroEncoder(24, 0.32, 5));
	//addSequential(new TurnWithVisionCommand(5, -25));
	//addSequential(new DriveForwardWithGyroEncoder(38, 0.32, 5));
	//addSequential (new MoveGearCollectorOutAutoCommand(27, 0.4));
	
    }
    
}