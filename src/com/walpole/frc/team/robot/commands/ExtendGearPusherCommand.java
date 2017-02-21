package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ExtendGearPusherCommand extends Command {

    public ExtendGearPusherCommand() {
        requires(Robot.gear);
    }

    @Override
    protected void initialize() {
    	Robot.gear.fireGearPusher();
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}