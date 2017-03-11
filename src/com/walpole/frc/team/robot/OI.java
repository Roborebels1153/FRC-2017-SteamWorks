package com.walpole.frc.team.robot;

import com.walpole.frc.team.robot.commands.ClimbDownCommand;
import com.walpole.frc.team.robot.commands.ClimbUpCommand;
import com.walpole.frc.team.robot.commands.ClimbWithoutLimitSwitch;
import com.walpole.frc.team.robot.commands.ConveyerOffCommand;
import com.walpole.frc.team.robot.commands.ConveyerOnCommand;
import com.walpole.frc.team.robot.commands.ExtendGearPusherCommand;
import com.walpole.frc.team.robot.commands.ReleaseGearCommand;
import com.walpole.frc.team.robot.commands.RetainGearCommand;
import com.walpole.frc.team.robot.commands.RetractGearPusherCommand;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.ShiftLowCommand;
import com.walpole.frc.team.robot.commands.ShooterShootCommand;
import com.walpole.frc.team.robot.commands.ShooterStopCommand;
import com.walpole.frc.team.robot.commands.StopClimbCommand;
import com.walpole.frc.team.robot.lib.RebelTrigger;

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
	opBumperR.whenReleased(new ExtendGearPusherCommand());
	opBumperR.whileHeld(new RetractGearPusherCommand());
	
	opTriggerL.whileHeld(new ConveyerOnCommand()); // This is a test
	opTriggerL.whenReleased(new ConveyerOffCommand());
	
	opTriggerR.whenPressed(new ShooterShootCommand());
	opTriggerR.whenReleased(new ShooterStopCommand());

	drLT.whenPressed(new ShiftHighCommand());
	drLT.whenReleased(new ShiftLowCommand());

	drRB.whenPressed(new ClimbWithoutLimitSwitch());        // when right bumper is held, robot motor will spin in one direction
	drRB.whenReleased(new StopClimbCommand());   // when right bumper is released, robot motor will stop spinning

	drLB.whileHeld(new ClimbDownCommand());      // when left bumper is held, robot motor will spin in the opposite direction
	drLB.whenReleased(new StopClimbCommand());   // when left bumper is released, robot motor will stop spinning
	}
	
	public Joystick getDriverJoystick() {
		return driverJoystick;
	}

}
