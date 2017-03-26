package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;

import com.walpole.frc.team.robot.commands.MoveGearCollectorOutAutoCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.ShiftLowCommand;
import com.walpole.frc.team.robot.commands.StopCommand;
import com.walpole.frc.team.robot.commands.StopGearCollectorCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Blue Alliance Wall, Retrieval Zone side
 * 
 * @author Aayush and Avi
 */
public class BlueRightScoreAGear extends CommandGroup {

    private static final int inchesForward = 72;
    private static final int degreesToTurn = -60;
    private static final int inchesToAirship = 78;
    private static final int secondsToWait = 2; 
   // private static final int inchesBack = 60; 

    private static final  double speedToAirShip = 0.5; 
    private static final  double speedForward = 0.4; 
    private static final int encoderTicksDown = 140;
    private static final double armSpeed = 0.5; 
    public BlueRightScoreAGear() {
	super();
	//addSequential(new ExtendGearPusherCommand());
	addSequential(new ShiftHighCommand());
	addSequential(new DriveForwardWithGyroEncoder(inchesForward, speedForward, 3));
	addSequential(new TurnWithGyroCommand(degreesToTurn));
	addSequential(new DriveForwardWithGyroEncoder(inchesToAirship, speedToAirShip, 2));  
	addSequential(new WaitCommand(secondsToWait)); 
	addSequential(new MoveGearCollectorOutAutoCommand(encoderTicksDown, armSpeed));
	addSequential(new WaitCommand(3)); 
	addSequential(new StopGearCollectorCommand());
	    }
}