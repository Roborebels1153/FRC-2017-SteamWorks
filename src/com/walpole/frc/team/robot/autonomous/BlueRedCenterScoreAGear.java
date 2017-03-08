package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.ShiftLowCommand;
import com.walpole.frc.team.robot.commands.StopCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BlueRedCenterScoreAGear extends CommandGroup {

    int inchesToAirShip = 78;
    double speedForward = 0.6; 
    int secondsToWait = 3; 
    int inchesBack = 6; 
    
    public BlueRedCenterScoreAGear() {
	super();
	//addSequential(new ExtendGearPusherCommand()); 
	//addSequential(new ShiftHighCommand()); 
	addSequential(new ShiftLowCommand()); 
	addSequential(new DriveForwardWithGyroEncoder(inchesToAirShip, speedForward)); 
	addSequential(new StopCommand()); 
	//addSequential(new WaitCommand(secondsToWait));  
	addSequential(new ReleaseGearCommand());
	//This wait command is just for testing purposes
    }
}                                                                                                   

