package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveAndTurnRightGear extends CommandGroup {
    
    /*private static final int inchesToDrive = 77; 
    private static final int degrees = 90; 
    private static final int secondsToWait = 1;
    private static final int inchesToAirship = 76;*/

    public DriveAndTurnRightGear() {
	super();
	
	/*addSequential(new DriveForwardWithEncoder(inchesToDrive));
	addSequential(new WaitCommand(secondsToWait)); 
	addSequential(new TurnWithGyroCommand(degrees)); 
	addSequential(new WaitCommand(secondsToWait)); 
	addSequential(new DriveForwardWithEncoder(inchesToAirship));*/
	
	addSequential(new DriveForwardWithEncoder(92));
	addSequential(new WaitCommand(1));
	addSequential(new TurnWithGyroCommand(300));
    }

    public DriveAndTurnRightGear(String name) {
	super(name);
    }
}