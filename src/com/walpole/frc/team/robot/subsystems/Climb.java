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
		climberVictor = new Victor(RobotMap.CLIMB_MOTOR); // we only need one
		// motor to climb (as of now, we might have to add things)
		limitSwitch = new DigitalInput(RobotMap.CLIMB_LIMIT_SWITCH);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void climbUp() { // this method is for when the climber is climbing
		if (getLimitSwitchState()  == false) {
			climberVictor.set(1);
		} else if (getLimitSwitchState() == true) {
			climberVictor.set(0);
		}
	}

	public void stopClimb() { // this method is to tell the robot when to stop
		// climbing
		climberVictor.set(0);
	}

	public void climbDown() { // this method is for de-climbing the robot,
		climberVictor.set(-0.65);

	}

	public boolean getLimitSwitchState() {
		return !limitSwitch.get();
	}

}

