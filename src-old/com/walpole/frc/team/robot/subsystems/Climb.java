package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climb extends Subsystem {

	private SpeedController climberVictor;
	private DigitalInput limitSwitch;

	public Climb() {
		// We only need one motor to climb as of now (we might have to add things
		climberVictor = new Victor(RobotMap.CLIMB_MOTOR);
		limitSwitch = new DigitalInput(RobotMap.CLIMB_LIMIT_SWITCH);
	}

	@Override
	protected void initDefaultCommand() {
	}

	public void climbUp() {
		climberVictor.set(1);
	}

	public void stopClimb() {
		climberVictor.set(0);
	}

	public void climbDown() {
		climberVictor.set(-0.7);
	}

	public boolean getLimitSwitchState() {
		return limitSwitch.get();
	}
}