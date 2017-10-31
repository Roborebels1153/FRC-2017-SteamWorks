
package com.walpole.frc.team.robot.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;
import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.ShooterShootCommand;
import com.walpole.frc.team.robot.commands.StopCommand;
import com.walpole.frc.team.robot.commands.TurnWithVisionCommand;

public class VisionWorkingTest extends CommandGroup {
    
  
   
    public VisionWorkingTest() { 
	super(); 
	
	//addSequential(new ShooterShootCommand());
	 addSequential(new TurnWithVisionCommand (5,0));
	 
//	addSequential(new DriveForwardWithGyroEncoder(inchesToAirship, 3)); 
//	addSequential(new StopCommand()); 
    }

}
