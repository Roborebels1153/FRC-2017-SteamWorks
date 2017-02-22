package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveBackwardsWithSeconds;
import com.walpole.frc.team.robot.commands.DriveForwardWithSeconds;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BlueCenterScoreAGearWithSeconds extends CommandGroup {
    int secondsToAirShip = 5;
    int secondsBack = 3;
    
    double secondsToWait = 1;

    public BlueCenterScoreAGearWithSeconds() {
	super();

	//addSequential(new ExtendGearPusherCommand());
	addSequential(new DriveForwardWithSeconds(secondsToAirShip));  
	addSequential(new ReleaseGearCommand());
	addSequential(new WaitCommand(secondsToWait));
	addSequential(new DriveBackwardsWithSeconds(secondsBack));     
    }
}