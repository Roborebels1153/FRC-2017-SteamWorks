package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.GearCollectorOut;
import com.walpole.frc.team.robot.commands.MoveGearCollectorOutAutoCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.ShiftLowCommand;
import com.walpole.frc.team.robot.commands.StopCommand;
import com.walpole.frc.team.robot.commands.StopGearCollectorCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BlueRedCenterScoreAGear extends CommandGroup {

    private static final double inchesToAirShip = 78.5;
    private static final double speedForward = 0.4; 
    private static final int secondsToWait = 1;
    private static final int encoderTicksDown = 31; 
    private static final double armSpeed = 0.4; 
    public BlueRedCenterScoreAGear() {
	super();
//	addSequential(new ExtendGearPusherCommand()); 
	addSequential(new ShiftHighCommand()); 
	addSequential(new MoveGearCollectorOutAutoCommand(95, 0.5, 2));
	addSequential(new MoveGearCollectorOutAutoCommand(-20, 0.3, 2));
	addSequential(new DriveForwardWithGyroEncoder(inchesToAirShip, speedForward)); 
	addSequential(new WaitCommand(secondsToWait));
	//addSequential(new MoveGearCollectorOutAutoCommand(40, 0.6)); 
	//addSequential(new MoveGearCollectorOutAutoCommand(encoderTicksDown, armSpeed)); 
	addSequential(new GearCollectorOut()); 
	//addSequential(new ReleaseGearCommand());
	//addSequential(new GearCollectorOut()); 
	addSequential(new WaitCommand(3));
	addSequential(new StopGearCollectorCommand());
    }
}                                                                                                   

