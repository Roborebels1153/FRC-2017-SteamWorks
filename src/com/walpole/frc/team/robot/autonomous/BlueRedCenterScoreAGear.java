package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BlueRedCenterScoreAGear extends CommandGroup {
	
	private static final int inchesToAirShip = 78;
	private static final double speedForward = 0.5;
	private static final int secondsToWait = 3;
	private static final int inchesBack = 6;
	private static final double speedBack = 0.6;

	public BlueRedCenterScoreAGear() {
		super();
		
		// TODO Add in ExtendGearPusherCommand
		// addSequential(new ExtendGearPusherCommand());
		addSequential(new ShiftHighCommand());
		addSequential(new DriveForwardWithGyroEncoder(inchesToAirShip, speedForward));
		addSequential(new WaitCommand(secondsToWait));
		// addSequential(new ReleaseGearCommand());
		// This wait command is just for testing purposes
		addSequential(new WaitCommand(secondsToWait));
	}
}