package com.walpole.frc.team.robot;

import com.walpole.frc.team.robot.commands.CollecterCollectCommand;
import com.walpole.frc.team.robot.commands.CountRPM;
import com.walpole.frc.team.robot.commands.ShooterShootCommand;
import com.walpole.frc.team.robot.commands.ShooterSpeedCommand;
import com.walpole.frc.team.robot.commands.ShooterSpeedDecrement;
import com.walpole.frc.team.robot.commands.ShooterSpeedIncrement;
import com.walpole.frc.team.robot.commands.TurnLightOffCommand;
import com.walpole.frc.team.robot.commands.TurnLightOnCommand;
import com.walpole.frc.team.robot.lib.RebelTrigger;
import com.walpole.frc.team.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    Joystick stick = new Joystick(0);
    
    Joystick opStick = new Joystick(1);
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



public OI() {
	opTriggerR.whileHeld(new ShooterShootCommand());
//	opA.whileHeld(new CountRPM(60));
	opTriggerL.whileHeld(new CollecterCollectCommand());
	
	opA.whenPressed(new ShooterSpeedCommand(0.25));
	opB.whenPressed(new ShooterSpeedCommand(0.5));
	opX.whenPressed(new ShooterSpeedCommand(0.75));
	opY.whenPressed(new ShooterSpeedCommand(1));
	
	opStart.whileHeld(new TurnLightOnCommand());
	opBack.whileHeld(new TurnLightOffCommand());
	
	
	opBumperL.whenPressed(new ShooterSpeedDecrement());
	opBumperR.whenPressed(new ShooterSpeedIncrement());

}

    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
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
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

