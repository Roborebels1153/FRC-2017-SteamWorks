package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.MoveGearCollectorOutAutoCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.ShiftLowCommand;
import com.walpole.frc.team.robot.commands.StopCommand;
import com.walpole.frc.team.robot.commands.StopGearCollectorCommand;
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
    private static final double speedForward = 0.5; 
    private static final double speedToTurn = 0.5; 
    private static final  double speedToAirShip = 0.4; 
    private static final int encoderTicksDown = 140; 
    private static final double armSpeed = 0.5;

    public RedLeftScoreAGear() {
	super();
	
	//addSequential(new ExtendGearPusherCommand());
	addSequential(new ShiftHighCommand()); 
	addSequential(new DriveForwardWithGyroEncoder(inchesForward, speedForward, 5));
	addSequential(new TurnWithGyroCommand(degreesToTurn, speedToTurn));
	addSequential(new DriveForwardWithGyroEncoder(inchesToAirship, speedToAirShip, 3));  
	//addSequential(new ReleaseGearCommand()); 
	addSequential(new MoveGearCollectorOutAutoCommand(encoderTicksDown, armSpeed));
	addSequential(new WaitCommand(3)); 
	addSequential(new StopGearCollectorCommand());
    }
}