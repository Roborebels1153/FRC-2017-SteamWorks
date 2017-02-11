package Autonomous;

import com.walpole.frc.team.robot.commands.DriveBackwardsWithEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;
import com.walpole.frc.team.robot.commands.WaitInbetweenCommandsCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DeliverAGear extends CommandGroup {
    int inchesToAirShip = 95;
    int inchesBack = 42;
    int degreesToTurn = 60;
    int secondsToDrive = 3;
    int inchesToAirShipTwo = 5;
    int degreesToTurnTwo = 85;

    public DeliverAGear() {
	super();
	// TODO Auto-generated constructor stub

	addSequential(new DriveForwardWithEncoder(inchesToAirShip));
	addSequential(new ReleaseGearCommand());
	addSequential(new WaitInbetweenCommandsCommand(secondsToDrive));
	addSequential(new DriveBackwardsWithEncoder(inchesBack));
	addSequential(new TurnWithGyroCommand(degreesToTurn));
	addSequential(new DriveForwardWithEncoder(inchesToAirShipTwo));
	addSequential(new TurnWithGyroCommand(degreesToTurnTwo));
    }

}
