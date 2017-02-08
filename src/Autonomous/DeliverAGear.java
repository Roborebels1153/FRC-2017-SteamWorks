package Autonomous;

import com.walpole.frc.team.robot.commands.DriveBackwardsWithEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DeliverAGear extends CommandGroup {
    int inchesToAirShip = 95; 
    int inchesBack = 42; 
    
    public DeliverAGear() {
   	super();
   	// TODO Auto-generated constructor stub
   	
   	addSequential(new DriveForwardWithEncoder(inchesToAirShip)); 
   	addSequential(new DriveBackwardsWithEncoder(inchesBack));  
       }


}
