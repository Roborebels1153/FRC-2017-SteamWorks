package com.walpole.frc.team.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ShootWithTimer extends CommandGroup {

    public ShootWithTimer() {
       super();
       
       addSequential(new ShooterSpeedIncrement());
       addSequential(new WaitCommand(4));
       addSequential(new ShooterShootCommand());
    }
}
