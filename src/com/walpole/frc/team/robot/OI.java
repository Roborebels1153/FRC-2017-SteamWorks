package com.walpole.frc.team.robot;

import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.ShiftLowCommand;
import com.walpole.frc.team.robot.lib.RebelTrigger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

    private Joystick opStick = new Joystick(RobotMap.OPERATOR_STICK);
    private Joystick driverStick = new Joystick(RobotMap.DRIVER_JOYSTICK); 
	
	private Button drLT = new RebelTrigger(driverStick, 2);
	private Button drRT = new RebelTrigger(driverStick, 3);

public OI() {
	drLT.whenPressed(new ShiftHighCommand());
	drLT.whenReleased(new ShiftLowCommand());
	}
	
	public Joystick getDriverJoystick() {
		return driverStick;
	}

	public Joystick getOperatorJoystick() {
		return opStick;
	}
}