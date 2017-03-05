package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DeliverAGearRight extends CommandGroup {
	
	private static final int inchesToAirShip = 5;
	private static final int degreesToTurn = -30;

	public DeliverAGearRight() {
		super();

		addSequential(new ExtendGearPusherCommand());
		addSequential(new DriveForwardWithEncoder(inchesToAirShip));
		addSequential(new TurnWithGyroCommand(degreesToTurn));
	}
}