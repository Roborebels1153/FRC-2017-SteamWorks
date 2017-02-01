package com.walpole.frc.team.robot;

import com.walpole.frc.team.robot.lib.RebelTrigger;
import com.walpole.frc.team.robot.commands.ClimbDownCommand;
import com.walpole.frc.team.robot.commands.ClimbUpCommand;
import com.walpole.frc.team.robot.commands.ExampleCommand;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.ShiftLowCommand;
import com.walpole.frc.team.robot.commands.StopClimbCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a
    //// joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    private Joystick driverJoystick = new Joystick(RobotMap.DRIVER_JOYSTICK);

    private Button drLT = new RebelTrigger(driverJoystick, 2);
    private Button drRT = new RebelTrigger(driverJoystick, 3);
    private Button drRB = new JoystickButton(driverJoystick, 6); // the drLb and
								 // drRb are the
								 // left and
								 // rught
								 // bumpers on
								 // the XBOX
								 // controller
    private Button drLB = new JoystickButton(driverJoystick, 5);

    public OI() {
	drLT.whenPressed(new ShiftHighCommand());
	drLT.whenReleased(new ShiftLowCommand());
	drRB.whenPressed(new ClimbUpCommand()); // when right bumper is held,
						// robot motor will spin in one
						// direction
	drRB.whenReleased(new StopClimbCommand()); // when right bumper is
						   // released, robot motor will
						   // stop spinning
	drLB.whenPressed(new ClimbDownCommand()); // when left bumper is held,
						  // robot motor will spin in
						  // the opposite direction
	drLB.whenReleased(new StopClimbCommand()); // when left bumper is
						   // released, robot motor will
						   // stop spinning
    }

    public Joystick getDriverJoystick() {
	return driverJoystick;
    }
}
