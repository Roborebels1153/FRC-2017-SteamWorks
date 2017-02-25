package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BlueLeftKnockDownHopper extends CommandGroup {
    
    private static final int inchesToDrive = 82; 
    private static final int degrees = -90; 
    private static final int secondsToWait = 1;
    private static final int inchesToPushPad = 33;
    private static final int secondsToWaitTwo = 3;

    public BlueLeftKnockDownHopper () {
	super();
	
	//Need command that retracts the top plate of the gear tray 
//	addSequential(new ExtendGearPusherCommand()); 
	addSequential(new DriveForwardWithGyroEncoder(inchesToDrive));
	//This wait command is for testing purposes, once we perfected autonomous, we will no longer need this 
	addSequential(new WaitCommand(secondsToWait)); 
	addSequential(new TurnWithGyroCommand(degrees)); 
	addSequential(new DriveForwardWithEncoder(inchesToPushPad));
	//This wait command is for testing purposes, once we perfected autonomous, we will no longer need this 
	addSequential(new WaitCommand(secondsToWaitTwo)); 
	addSequential(new TurnWithGyroCommand(degrees));
	
    }

    public BlueLeftKnockDownHopper(String name) {
	super(name);
    }
}       