package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveBackwardsWithEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BlueCenterScoreAGear extends CommandGroup {
    int inchesToAirShip = 78;
    int inchesBack = 60;
    int degreesToTurn = 60;
    
    double secondsToWait = 1;
    int inchesToAirShipTwo = 5;
    int degreesToTurnTwo = 85;

    public BlueCenterScoreAGear() {
	super();

	addSequential(new ExtendGearPusherCommand());
	addSequential(new DriveForwardWithEncoder(inchesToAirShip));  
	addSequential(new ReleaseGearCommand());
	addSequential(new WaitCommand(secondsToWait));
	addSequential(new DriveBackwardsWithEncoder(inchesBack));     
    }
}