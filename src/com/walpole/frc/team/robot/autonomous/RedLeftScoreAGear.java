package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.ShiftLowCommand;
import com.walpole.frc.team.robot.commands.StopCommand;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand; 
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
/**
 * Red Alliance Wall, Retrieval Zone Side
 * 
 * @author Aayush and Avi
 */
public class RedLeftScoreAGear extends CommandGroup {
    private static final int inchesForward = 72;
    private static final int degreesToTurn = 60;
    private static final int inchesToAirship = 78;
   // private static final int secondsToWait = 2; 
   // private static final int inchesBack = 60; 
    private static final  double speedToAirShip = 0.5; 
    private static final double speedForward = 0.6; 
    private static final double speedToTurn = 0.5; 

    public RedLeftScoreAGear() {
	super();
	
	//addSequential(new ExtendGearPusherCommand());
	addSequential(new ShiftLowCommand()); 
	addSequential(new DriveForwardWithGyroEncoder(inchesForward, speedForward));
	addSequential(new TurnWithGyroCommand(degreesToTurn, speedToTurn));
	addSequential(new DriveForwardWithGyroEncoder(inchesToAirship, speedToAirShip));  
	addSequential(new StopCommand()); 
	addSequential(new ReleaseGearCommand());
	addSequential(new ShiftHighCommand()); 
	addSequential(new DriveForwardWithGyroEncoder(inchesForward, speedForward, 3));
	addSequential(new TurnWithGyroCommand(degreesToTurn));
	addSequential(new DriveForwardWithGyroEncoder(inchesToAirship, speedToAirShip, 2));  
	addSequential(new WaitCommand(1)); 
	addSequential(new ReleaseGearCommand());
    }
}