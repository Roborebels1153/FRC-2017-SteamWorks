package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveBackwardsWithEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;
import com.walpole.frc.team.robot.commands.WaitInbetweenCommandsCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueCenterScoreAGear extends CommandGroup {
    int inchesToAirShip = 78;
    int inchesBack = 60;
    int degreesToTurn = 60;
    
    double secondsToWait = 1;
    int inchesToAirShipTwo = 5;
    int degreesToTurnTwo = 85;

    public BlueCenterScoreAGear() {
	super();
	// TODO Auto-generated constructor stub

	addSequential(new ExtendGearPusherCommand());
	addSequential(new DriveForwardWithEncoder(inchesToAirShip));  //the speed for this command is set in the command itself
	addSequential(new ReleaseGearCommand());
	addSequential(new WaitInbetweenCommandsCommand(secondsToWait));
	addSequential(new DriveBackwardsWithEncoder(inchesBack));     //the speed for this command is set in the command itself
    }

}
