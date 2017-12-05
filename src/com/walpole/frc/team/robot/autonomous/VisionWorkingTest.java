
package com.walpole.frc.team.robot.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;
import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithVisionDistance;
import com.walpole.frc.team.robot.commands.ShooterShootCommand;
import com.walpole.frc.team.robot.commands.StopCommand;
import com.walpole.frc.team.robot.commands.TurnWithVisionCommand;

public class VisionWorkingTest extends CommandGroup {
    
  
   
    public VisionWorkingTest() { 
	super(); 
	
	
 addSequential(new TurnWithVisionCommand (5,0));
	
//	addSequential(new DriveForwardWithVisionDistance(0, 0.32, 5, -20));

    }

}
