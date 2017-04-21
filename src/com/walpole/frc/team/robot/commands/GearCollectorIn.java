package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GearCollectorIn extends Command {

    public GearCollectorIn() {
        requires(Robot.floorGear);
    }

    @Override
    protected void initialize() {
	Robot.floorGear.collectorIn();
	Robot.floorGear.setGearMotor(0);
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