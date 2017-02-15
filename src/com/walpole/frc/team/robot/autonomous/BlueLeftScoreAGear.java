package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueLeftScoreAGear extends CommandGroup {
    int inchesToTurn = 72;
    int degreesToTurn = 60;
    int inchesToAirship = 80;
    
    public BlueLeftScoreAGear() {
	super();
	// TODO Auto-generated constructor stub
	
	addSequential (new ExtendGearPusherCommand());
	addSequential (new DriveForwardWithEncoder (inchesToTurn));
	addSequential (new TurnWithGyroCommand (degreesToTurn));
	addSequential (new DriveForwardWithEncoder (inchesToAirship));
	
    }
}
