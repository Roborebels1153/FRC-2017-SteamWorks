package com.walpole.frc.team.robot.autonomous;

//import com.walpole.frc.team.robot.commands.DriveBackwardsWithEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.GearCollectorOut;
import com.walpole.frc.team.robot.commands.MoveGearCollectorOutAutoCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BlueRedCenterScoreAGear extends CommandGroup {
    private static final int inchesToAirShip = 78;
    private static final double speedForward = 0.6; 
    private static final int secondsToWait = 1; 
    private static final int encoderTicksDown = 140; 
    private static final double armSpeed = 0.7; 
    public BlueRedCenterScoreAGear() {
	super();
//	addSequential(new ExtendGearPusherCommand()); 
	addSequential(new ShiftHighCommand()); 
	addSequential(new DriveForwardWithGyroEncoder(inchesToAirShip, speedForward, 3)); 
	addSequential(new WaitCommand(secondsToWait)); 
	//addSequential(new ReleaseGearCommand());
	//addSequential(new GearCollectorOut()); 
	addSequential(new MoveGearCollectorOutAutoCommand(encoderTicksDown, armSpeed)); 
    }
}