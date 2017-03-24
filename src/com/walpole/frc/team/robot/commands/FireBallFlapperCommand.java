package com.walpole.frc.team.robot.commands;

import com.walpole.frc.team.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FireBallFlapperCommand extends Command {

    public FireBallFlapperCommand() {
//        requires(Robot.gear);
    }

    @Override
    protected void initialize() {
//    	Robot.gear.fireBallFlapper();
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