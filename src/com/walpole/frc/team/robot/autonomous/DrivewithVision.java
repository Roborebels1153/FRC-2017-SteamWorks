package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithVision;
import com.walpole.frc.team.robot.commands.GearCollectorOut;
import com.walpole.frc.team.robot.commands.MoveGearCollectorOutAutoCommand;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.StopCommand;
import com.walpole.frc.team.robot.commands.StopGearCollectorCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DrivewithVision extends CommandGroup {
    
	  private static final double inchesForward = 62;
	   // private static final int inchesForwardProto = 65; //Distance manipulated for PROTOTYPE Robot
	    private static final int degreesToTurn = -59;
	    private static final int inchesToAirship = 66;
	    private static final int secondsToWait = 1; 
	   // private static final int inchesBack = 60;
	    private static final  double speedForward = 0.3; 
	    private static final  double speedToAirShip = 0.4; 
	    private static final int encoderTicksDown = 140;            
	    private static final double armSpeed = 0.3; 
	    private static final double speedToTurn = 0.35; 
    public DrivewithVision() {
	super();
//	addSequential(new ShiftHighCommand());
//	addSequential(new DriveForwardWithGyroEncoder(-66, speedToAirShip, 3));
//	addSequential(new TurnWithGyroCommand(59, speedToTurn));
//	addSequential(new TurnWithGyroCommand(59, speedToTurn));
//	addSequential(new DriveForwardWithGyroEncoder(-62, speedForward, 5));

	
	
	//addSequential(new MoveGearCollectorOutAutoCommand(65, 0.4, 2));
	//addSequential(new MoveGearCollectorOutAutoCommand(-10, 0.5, 2));

	
	addSequential(new DriveForwardWithVision(85, 0.32, 5, 5, -25));
	
	
	
	
	
//	addSequential(new ShiftHighCommand());
//	//addSequential(new MoveGearCollectorOutAutoCommand(35, 0.5)); 
//	addSequential(new DriveForwardWithGyroEncoder(inchesForward, speedForward, 5));
//	addSequential(new WaitCommand(0.5)); 
//	addSequential(new TurnWithGyroCommand(degreesToTurn, speedToTurn));
//	addSequential(new TurnWithGyroCommand(degreesToTurn, speedToTurn));
////	addSequential(new MoveGearCollectorOutAutoCommand(0, -0.5, 1.5));
//	//addSequential(new WaitCommand(1));
//	addSequential(new MoveGearCollectorOutAutoCommand(30, 0.4, 2));
//	//addSequential(new MoveGearCollectorOutAutoCommand(-20, 0.3, 2));
//	//addSequential(new MoveGearCollectorOutAutoCommand(35, 0.5, 2)); 
//	//addSequential(new WaitCommand(secondsToWait));
//	//addSequential(new WaitCommand(2));
//	addSequential(new GearCollectorOut()); 
//	addSequential(new WaitCommand(3));
//	addSequential(new StopGearCollectorCommand());
//	//addSequential(new MoveGearCollectorOutAutoCommand(encoderTicksDown, armSpeed)); 

    }

    public DrivewithVision(String name) {
	super(name);
    }
}          
