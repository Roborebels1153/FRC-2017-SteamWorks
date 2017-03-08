package com.walpole.frc.team.robot.autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithSeconds;
import com.walpole.frc.team.robot.commands.StopCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossGreenLine extends CommandGroup {
    
    int inchesToAirship = 94; 
    
    
    public CrossGreenLine() { 
	super(); 
	
	addSequential(new DriveForwardWithGyroEncoder(inchesToAirship)); 
	addSequential(new StopCommand()); 
    }

}
