
package com.walpole.frc.team.robot.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;
import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.ShooterShootCommand;
import com.walpole.frc.team.robot.commands.StopCommand;

public class Shoot extends CommandGroup {
    
    int inchesToAirship = 94; 
   
    public Shoot() { 
	super(); 
	
	addSequential(new ShooterShootCommand(2));
	
//	addSequential(new DriveForwardWithGyroEncoder(inchesToAirship, 3)); 
//	addSequential(new StopCommand()); 
    }

}
