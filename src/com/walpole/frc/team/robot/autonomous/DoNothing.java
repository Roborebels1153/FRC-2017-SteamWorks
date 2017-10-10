
package com.walpole.frc.team.robot.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;
import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.ShooterShootCommand;
import com.walpole.frc.team.robot.commands.StopCommand;

public class DoNothing extends CommandGroup {
    
    int inchesToAirship = 94; 
   
    public DoNothing() { 
	super(); 
	
	//addSequential(new ShooterShootCommand());
	
//	addSequential(new DriveForwardWithGyroEncoder(inchesToAirship, 3)); 
//	addSequential(new StopCommand()); 
    }

}
