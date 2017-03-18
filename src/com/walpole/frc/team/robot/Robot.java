package com.walpole.frc.team.robot;

import com.walpole.frc.team.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	public static Preferences prefs;
	public static final Drive drive = new Drive();
	public static OI oi = new OI();
	
	private Command autonomousCommand;
	private SendableChooser<Command> chooser;
	
	@Override
	public void robotInit() {
		prefs = Preferences.getInstance();
		oi = new OI();
		chooser = new SendableChooser<Command>();
		SmartDashboard.putData("Auto mode", chooser);
	}
	
	public static void updateDashboard() {
		SmartDashboard.putNumber("Left Encoder Value", drive.getLeftEncoderCount());
		SmartDashboard.putNumber("Right Motor Power Value", drive.getRightMotorPower());
		SmartDashboard.putNumber("Left Motor Power Value", drive.getLeftMotorPower());
		SmartDashboard.putNumber("Right Encoder Value", drive.getRightEncoderCount());
		SmartDashboard.putNumber("Left Encoder PID Error", drive.getLeftPIDError());
		SmartDashboard.putNumber("Left Encoder PID Output", drive.getLeftPIDOutput());
		SmartDashboard.putNumber("Right Encoder PID Error", drive.getRightPIDError());
		SmartDashboard.putNumber("Right Encoder PID Output", drive.getRightPIDOutput());
		SmartDashboard.putNumber("Right Encoder Setpoint", drive.getRightEncoderSetpoint());
		SmartDashboard.putNumber("Left Encoder Setpoint", drive.getLeftEncoderSetpoint());
	}
	
	@Override
	public void disabledInit() {
	}
	
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateDashboard();
	}
	
	@Override
	public void autonomousInit() {
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateDashboard();
	}
	
	@Override
	public void teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		drive.drive(oi.getDriverJoystick());
		updateDashboard();
	}
	
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}