package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.GearCollectorOut;
import com.walpole.frc.team.robot.commands.MoveGearCollectorOutAutoCommand;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.ShiftLowCommand;
import com.walpole.frc.team.robot.commands.StopGearCollectorCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Drive10FeetShiftLow extends CommandGroup {

    
    private static final int distance = 120; 
    private static final double secondsToWait = 2; 
    private static final double speed = 0.5;
    //Testing
    private double armSpeed = 0.5;
    
    
    public Drive10FeetShiftLow() { 
	super();
	//ShiftHigh is actually ShiftLow due to pneumatics wiring
	//addSequential(new ShiftHighCommand()); 
	//addSequential(new WaitCommand(secondsToWait)); 
	//addSequential(new MoveGearCollectorOutAutoCommand(31, 0.4, 2));
//	addSequential(new MoveGearCollectorOutAutoCommand(95, 0.5, 2));
//	addSequential(new MoveGearCollectorOutAutoCommand(-20, 0.3, 2));
	addSequential(new DriveForwardWithGyroEncoder(distance, 0.35, 15)); 
	//addSequential(new TurnWithGyroCommand(90, 0.2));
	//addSequential(new WaitCommand(2));
//	addSequential(new MoveGearCollectorOutAutoCommand(0, armSpeed, 1.5));
	//addSequential(new MoveGearCollectorOutAutoCommand(50, 0.4));
//	addSequential(new GearCollectorOut()); 
//	addSequential(new WaitCommand(3));
//	addSequential(new StopGearCollectorCommand());
    }

}