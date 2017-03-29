package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.MoveGearCollectorOutAutoCommand;
//import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand; 
//import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
//import com.walpole.frc.team.robot.commands.RetainGearCommand;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.ShooterShootCommand;
import com.walpole.frc.team.robot.commands.ShooterStopCommand;
import com.walpole.frc.team.robot.commands.StopGearCollectorCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BlueLeftScoreAGearShoot extends CommandGroup {
    private static final int inchesForward = 72;
    private static final int degreesToTurn = 60;
    private static final int inchesToAirship = 80;
    private static final int secondsToWait = 1; 
   // private static final int inchesBack = 60;
    private static final  double speedForward = 0.7; 
    private static final  double speedToAirShip = 0.6; 
    private static final int encoderTicksDown = 140; 
    private static final double armSpeed = 0.5;
    private static final int inchesBack = -5;
    private static final double speedBack = 0.7; 
    private static final double degreesToTurnShoot = -165;
    private static final int inchesToBoiler = 5;
    private static final double speedToBoiler = 0.7;
    private static final double speedToTurn = 0.5; 
    private static final double degreesToTurnBack = 170; 
    
    public BlueLeftScoreAGearShoot() {
	super();
	
	addSequential(new ShiftHighCommand()); 
	addSequential(new DriveForwardWithGyroEncoder(inchesForward, speedForward, 5));
	addSequential(new TurnWithGyroCommand(degreesToTurn, speedToTurn));
//	addSequential(new MoveGearCollectorOutAutoCommand(0, -0.5, 1.5));
	addSequential(new WaitCommand(1));
	addSequential(new DriveForwardWithGyroEncoder(inchesToAirship, speedToAirShip, 3));  
	addSequential(new WaitCommand(secondsToWait));
	addSequential(new MoveGearCollectorOutAutoCommand(encoderTicksDown, armSpeed));
	addSequential(new WaitCommand(1));
	addSequential(new StopGearCollectorCommand());
	addSequential(new DriveForwardWithGyroEncoder(inchesBack, speedBack));
	addSequential(new TurnWithGyroCommand(degreesToTurnBack));
	addSequential(new DriveForwardWithGyroEncoder(inchesToBoiler, speedToBoiler)); 
	addSequential(new ShooterShootCommand());
	addSequential(new WaitCommand(8));
	addSequential(new ShooterStopCommand());
	
	
    }
}
              