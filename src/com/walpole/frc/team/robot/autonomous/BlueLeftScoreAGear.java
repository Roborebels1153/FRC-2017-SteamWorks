package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveBackwardsWithEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand; 
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BlueLeftScoreAGear extends CommandGroup {
    int inchesForward = 72;
    int degreesToTurn = 300;
    int inchesToAirship = 80;
    int secondsToWait = 2; 
    int inchesBack = 60; 
    double speedToAirShip = 0.6; 
    
    public BlueLeftScoreAGear() {
	super();
	
	//addSequential(new ExtendGearPusherCommand());
	addSequential(new DriveForwardWithGyroEncoder(inchesForward));
	addSequential(new WaitCommand(secondsToWait)); 
	addSequential(new TurnWithGyroCommand(degreesToTurn));
	addSequential(new WaitCommand(secondsToWait)); 
	addSequential(new DriveForwardWithGyroEncoder(inchesToAirship, speedToAirShip));  
	addSequential(new WaitCommand(secondsToWait)); 
	addSequential(new ReleaseGearCommand()); 
	
	//addSequential(new DriveBackwardsWithEncoder(inchesBack));     
    }
}
