package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveBackwardsWithEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BlueLeftScoreAGear extends CommandGroup {
    int inchesToTurn = 72;
    int degreesToTurn = 60;
    int inchesToAirship = 80;
    int secondsToWait = 1; 
    int inchesBack = 60; 
    
    public BlueLeftScoreAGear() {
	super();
	
	addSequential(new ExtendGearPusherCommand());
	addSequential(new DriveForwardWithEncoder(inchesToTurn));
	addSequential(new TurnWithGyroCommand(degreesToTurn));
	addSequential(new DriveForwardWithEncoder(inchesToAirship));  
	addSequential(new ReleaseGearCommand()); 
	addSequential(new WaitCommand(secondsToWait)); 
	addSequential(new DriveBackwardsWithEncoder(inchesBack));     
    }
}
