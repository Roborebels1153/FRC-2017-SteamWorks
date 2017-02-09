package Autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DeliverAGearLeft extends CommandGroup {
    int inchesToAirShip = 5;
    int degreesToTurn = 30;
    
    
    public DeliverAGearLeft() {
   	super();
   	// TODO Auto-generated constructor stub
   	
   	addSequential (new DriveForwardWithEncoder(inchesToAirShip));
   	addSequential (new TurnWithGyroCommand (degreesToTurn));
    	}
}
