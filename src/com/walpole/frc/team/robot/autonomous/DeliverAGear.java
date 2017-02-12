package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveBackwardsWithEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;
import com.walpole.frc.team.robot.commands.WaitInbetweenCommandsCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DeliverAGear extends CommandGroup {
    int inchesToAirShip = 95;
    int inchesBack = 42;
    int degreesToTurn = 60;
    int secondsToWait = 3;
    int inchesToAirShipTwo = 5;
    int degreesToTurnTwo = 85;

    public DeliverAGear() {
	super();
	// TODO Auto-generated constructor stub

	addSequential(new ExtendGearPusherCommand());
	addSequential(new DriveForwardWithEncoder(inchesToAirShip));  //the speed for this command is set in the command itself
	addSequential(new ReleaseGearCommand());
	addSequential(new WaitInbetweenCommandsCommand(secondsToWait));
	addSequential(new DriveBackwardsWithEncoder(inchesBack));     //the speed for this command is set in the command itself
	addSequential(new TurnWithGyroCommand(degreesToTurn));
	addSequential(new DriveForwardWithEncoder(inchesToAirShipTwo));     //the speed for this command is set in the command itself
	addSequential(new TurnWithGyroCommand(degreesToTurnTwo));
    }

}
