package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.MoveGearCollectorOutAutoCommand;
import com.walpole.frc.team.robot.commands.StopCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveAndTurn extends CommandGroup {
    
    private static final int inchesToDrive = 77; 
    private static final int degrees = 90; 
    private static final int degreesNegative = -90; 

    private static final int secondsToWait = 1;
    private static final int inchesToAirship = 76;
    private static final double speed = 0.6; 

    public DriveAndTurn() {
	super();
	
	//addSequential(new DriveForwardWithGyroEncoder(inchesToDrive, speed));
	//addSequential(new WaitCommand(secondsToWait)); 
//	addSequential(new TurnWithGyroCommand(degrees, 0.5)); 
//	addSequential(new WaitCommand(secondsToWait)); 
//	addSequential(new TurnWithGyroCommand(degreesNegative, 0.5)); 
	//addSequential(new DriveForwardWithGyroEncoder(inchesToAirship, speed));
	//addSequential(new StopCommand()); 
//	addSequential(new MoveGearCollectorOutAutoCommand(90, 0.5, 2));
//	addSequential(new MoveGearCollectorOutAutoCommand(-20, 0.3, 2));
	addSequential (new TurnWithGyroCommand (90, 0.4));
	addSequential (new TurnWithGyroCommand (90, 0.4));
	

    }

    public DriveAndTurn(String name) {
	super(name);
    }
}          
