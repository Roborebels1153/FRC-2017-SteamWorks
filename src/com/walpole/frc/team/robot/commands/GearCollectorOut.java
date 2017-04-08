package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GearCollectorOut extends Command {

    public GearCollectorOut() {
        requires(Robot.floorGear);
    }

    @Override
    protected void initialize() {
	Robot.floorGear.collectorOut();
	Robot.floorGear.gearLEDOff();
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}