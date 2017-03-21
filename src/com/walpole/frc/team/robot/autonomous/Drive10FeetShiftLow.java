package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.GearCollectorOut;
import com.walpole.frc.team.robot.commands.MoveGearCollectorOutAutoCommand;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.ShiftLowCommand;
import com.walpole.frc.team.robot.commands.StopGearCollectorCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Drive10FeetShiftLow extends CommandGroup {
    
    private static final int distance = 120; 
    private static final double secondsToWait = 0.5; 
    
    
    public Drive10FeetShiftLow() { 
	super(); 
	//ShiftHigh is actually ShiftLow due to pneumatics wiring
	addSequential(new ShiftHighCommand()); 
	//addSequential(new WaitCommand(secondsToWait)); 
	addSequential(new DriveForwardWithGyroEncoder(distance, 0.5, 5)); 
	addSequential(new MoveGearCollectorOutAutoCommand(140, 0.7));
	addSequential(new GearCollectorOut()); 
	addSequential(new WaitCommand(3));
	addSequential(new StopGearCollectorCommand());
    }

}