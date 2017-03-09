package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand; 
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.RetainGearCommand;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BlueLeftScoreAGear extends CommandGroup {
    private static final int inchesForward = 72;
    private static final int degreesToTurn = 60;
    private static final int inchesToAirship = 80;
    private static final int secondsToWait = 1; 
   // private static final int inchesBack = 60;
    private static final  double speedForward = 0.7; 
    private static final  double speedToAirShip = 0.6; 
    
    public BlueLeftScoreAGear() {
	super();
	
	//addSequential(new ExtendGearPusherCommand());
	addSequential(new ShiftHighCommand()); 
	addSequential(new DriveForwardWithGyroEncoder(inchesForward, speedForward));
	//addSequential(new WaitCommand(secondsToWait)); 
	addSequential(new TurnWithGyroCommand(degreesToTurn));
	//addSequential(new WaitCommand(secondsToWait)); 
	addSequential(new DriveForwardWithGyroEncoder(inchesToAirship, speedToAirShip));  
	addSequential(new WaitCommand(secondsToWait)); 
	addSequential(new ReleaseGearCommand()); 
	
	//addSequential(new DriveBackwardsWithEncoder(inchesBack));     
    }
}
