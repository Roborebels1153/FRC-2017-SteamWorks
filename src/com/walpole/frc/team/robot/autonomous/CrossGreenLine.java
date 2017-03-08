
package com.walpole.frc.team.robot.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;
import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.StopCommand;

public class CrossGreenLine extends CommandGroup {
    
    int inchesToAirship = 94; 
   
    public CrossGreenLine() { 
	super(); 
	
	addSequential(new DriveForwardWithGyroEncoder(inchesToAirship)); 
	addSequential(new StopCommand()); 
    }

}
