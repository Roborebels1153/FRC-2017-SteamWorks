package Autonomous;

import com.walpole.frc.team.robot.commands.DriveForwardWithSeconds;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAndTurn extends CommandGroup {

    /**
     * 
     */
    public DriveAndTurn(double seconds, double degrees) {
	super();
	// TODO Auto-generated constructor stub

	addSequential(new TurnWithGyroCommand(degrees));
	addSequential(new DriveForwardWithSeconds(seconds));
    }

    /**
     * @param name
     */
    public DriveAndTurn(String name) {
	super(name);
	// TODO Auto-generated constructor stub
    }

}
