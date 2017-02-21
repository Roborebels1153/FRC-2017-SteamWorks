package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithSeconds;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossGreenLine extends CommandGroup {

	private static final int secondsToAirship = 7;

	public CrossGreenLine() {
		super();

		addSequential(new DriveForwardWithSeconds(secondsToAirship));
	}
}