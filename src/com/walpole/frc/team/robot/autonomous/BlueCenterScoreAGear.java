package com.walpole.frc.team.robot.autonomous;

//import com.walpole.frc.team.robot.commands.DriveBackwardsWithEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BlueCenterScoreAGear extends CommandGroup {
    int inchesToAirShip = 76;
    double speedForward = 0.6; 
    int secondsToWait = 3; 
    int inchesBack = 6; 
    double speedBack = 0.6; 
    public BlueCenterScoreAGear() {
	super();
//	addSequential(new ExtendGearPusherCommand()); 
	addSequential(new DriveForwardWithGyroEncoder(inchesToAirShip, speedForward)); 
	addSequential(new WaitCommand(secondsToWait)); 
	addSequential(new ReleaseGearCommand());
	addSequential(new WaitCommand(secondsToWait)); 
	//addSequential(new DriveBackwardsWithEncoder(inchesBack, speedBack));
    }
}