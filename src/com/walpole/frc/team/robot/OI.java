package com.walpole.frc.team.robot;


import com.walpole.frc.team.robot.commands.ConveyerOffCommand;
import com.walpole.frc.team.robot.commands.ConveyerOnCommand;

import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.FireBallFlapperCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.RetainGearCommand;
import com.walpole.frc.team.robot.commands.RetractBallFlapperCommand;
import com.walpole.frc.team.robot.commands.RetractGearPusherCommand;

import com.walpole.frc.team.robot.commands.SetShooterDistance;
import com.walpole.frc.team.robot.commands.ShooterShootCommand;
import com.walpole.frc.team.robot.commands.TurnLightOffCommand;
import com.walpole.frc.team.robot.commands.TurnLightOnCommand;
import com.walpole.frc.team.robot.lib.RebelTrigger;
import com.walpole.frc.team.robot.commands.ClimbDownCommand;
import com.walpole.frc.team.robot.commands.ClimbUpCommand;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.ShiftLowCommand;
import com.walpole.frc.team.robot.commands.StopClimbCommand;
import com.walpole.frc.team.robot.commands.ToggleInternalMotors;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private Joystick opStick = new Joystick(RobotMap.OPERATOR_STICK);
    private Joystick driverJoystick = new Joystick(RobotMap.DRIVER_JOYSTICK);
    
    Button opTriggerL = new RebelTrigger(opStick, 2);
    Button opTriggerR = new RebelTrigger(opStick, 3);
    
    Button opA = new JoystickButton(opStick, 1);
    Button opB = new JoystickButton(opStick, 2);
    Button opX = new JoystickButton(opStick, 3);
    Button opY = new JoystickButton(opStick, 4);
    
    Button opBumperL = new JoystickButton(opStick, 5);
    Button opBumperR = new JoystickButton(opStick, 6);
    
    Button opStart = new JoystickButton(opStick, 8);
    Button opBack = new JoystickButton(opStick, 9);
    
	
	private Button drLT = new RebelTrigger(driverJoystick, 2);
	private Button drRT = new RebelTrigger(driverJoystick, 3);
	private Button drRB = new JoystickButton(driverJoystick, 6);     //the drLb and drRb are the left and right bumpers on the XBOX controller
	private Button drLB = new JoystickButton(driverJoystick, 5 );


	public OI() {
		opBumperL.whileHeld(new ReleaseGearCommand());
		opBumperL.whenReleased(new RetainGearCommand());
		opTriggerL.whenReleased(new ExtendGearPusherCommand());
		opTriggerL.whileHeld(new RetractGearPusherCommand());
	
		opTriggerR.whileHeld(new ShooterShootCommand());
		
		opStart.whileHeld(new ConveyerOnCommand()); // This is a test
		opStart.whenReleased(new ConveyerOffCommand());
		
		opX.toggleWhenPressed(new ToggleInternalMotors());
		opA.whenPressed(new SetShooterDistance(true));
		opB.whenReleased(new SetShooterDistance(false));
//		opY.whenPressed(new ShooterRPMChange(4000));
//		
//		opStart.whileHeld(new TurnLightOnCommand());
//		opBack.whileHeld(new TurnLightOffCommand());
	
	
		drLT.whenPressed(new ShiftHighCommand());
		drLT.whenReleased(new ShiftLowCommand());

		drRB.whenPressed(new ClimbUpCommand());
		drRB.whenReleased(new StopClimbCommand());

		drLB.whileHeld(new ClimbDownCommand());
		drLB.whenReleased(new StopClimbCommand());
	}

	public Joystick getDriverJoystick() {
		return driverJoystick;
	}
}