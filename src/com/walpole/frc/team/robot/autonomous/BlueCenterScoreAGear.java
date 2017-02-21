package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BlueCenterScoreAGear extends CommandGroup {
	
	private static final int inchesToAirShip = 76;
	private static final double speedForward = 0.6;
	private static final int secondsToWait = 3;
	/*private static final int inchesBack = 6;
	private static final double speedBack = 0.6;*/

	public BlueCenterScoreAGear() {
		super();
		// addSequential(new ExtendGearPusherCommand());
		addSequential(new DriveForwardWithGyroEncoder(inchesToAirShip, speedForward));
		addSequential(new WaitCommand(secondsToWait));
		addSequential(new ReleaseGearCommand());
		addSequential(new WaitCommand(secondsToWait));
		// addSequential(new DriveBackwardsWithEncoder(inchesBack, speedBack));
	}
}