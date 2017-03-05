package com.walpole.frc.team.robot;


import com.walpole.frc.team.robot.autonomous.BlueCenterScoreAGearWithSeconds;
import com.walpole.frc.team.robot.autonomous.BlueLeftKnockDownHopper;
import com.walpole.frc.team.robot.autonomous.BlueLeftScoreAGear;
import com.walpole.frc.team.robot.autonomous.BlueRedCenterScoreAGear;
import com.walpole.frc.team.robot.autonomous.BlueRightScoreAGear;
import com.walpole.frc.team.robot.autonomous.CrossGreenLine;
import com.walpole.frc.team.robot.autonomous.DeliverAGearLeft;
import com.walpole.frc.team.robot.autonomous.DeliverAGearRight;
import com.walpole.frc.team.robot.autonomous.Drive10FeetShiftLow;
import com.walpole.frc.team.robot.autonomous.RedLeftScoreAGear;
import com.walpole.frc.team.robot.autonomous.RedRightKnockDownHopper;
import com.walpole.frc.team.robot.autonomous.RedRightScoreAGear;
import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithSeconds;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;
import com.walpole.frc.team.robot.subsystems.Climb;
import com.walpole.frc.team.robot.subsystems.Collector;
import com.walpole.frc.team.robot.subsystems.Drive;
import com.walpole.frc.team.robot.subsystems.Gear;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static Preferences prefs;
	public static DriverStation ds;
	
	public static final Collector collector = new Collector();
	public static final Drive drive = new Drive();
	public static final Climb climb = new Climb();
	public static final Gear gear = new Gear();
	
	public static OI oi = new OI();
	
	private DriverStation.Alliance alliance = DriverStation.Alliance.Invalid;

	private Command autonomousCommand;
	private SendableChooser<Command> chooser;
	
	// Removed unused variable (Brigham)
	// static NetworkTable table;

	@Override
	public void robotInit() {
		prefs = Preferences.getInstance();
		ds = DriverStation.getInstance();
		
		oi = new OI();
		
		chooser = new SendableChooser<Command>();
		addChooserCommands();
		SmartDashboard.putData("Auto mode", chooser);
	}
	
	private void addChooserCommands() {
		// Autonomous Commands
		chooser.addObject("Center Deliver A Gear", new BlueRedCenterScoreAGear());
		chooser.addObject("Blue Left Deliver A Gear", new BlueLeftScoreAGear());
		chooser.addObject("Blue Right Deliver A Gear", new BlueRightScoreAGear()); 
		chooser.addObject("Red Right Deliver A Gear", new RedRightScoreAGear());
		chooser.addObject("Red Left Deliver A Gear", new RedLeftScoreAGear());
		chooser.addObject("Cross The Green Line", new CrossGreenLine()); 
		chooser.addObject("Red Right Knock Down Hopper", new RedRightKnockDownHopper());
		chooser.addObject("Blue Left Knock Down Hopper", new BlueLeftKnockDownHopper());
		chooser.addObject("Score A Gear With Seconds Center", new BlueCenterScoreAGearWithSeconds());

		// Testing Commands
		chooser.addObject("Drive 10 Feet", new DriveForwardWithEncoder(120));
		chooser.addObject("Drive stright 10 feet", new DriveForwardWithGyroEncoder(120));
		chooser.addObject("Turn Right With Gyro", new TurnWithGyroCommand(90));
		chooser.addObject("Center With Gyro", new TurnWithGyroCommand(0));
		chooser.addObject("Drive Forward With Seconds", new DriveForwardWithSeconds(5));
		//chooser.addObject("Drive And Turn", new DriveAndTurn());
		chooser.addObject("Drive 10 feet ShiftLow Forward", new Drive10FeetShiftLow());
		chooser.addObject("Shift High", new ShiftHighCommand()); 
	}

	private void updateDashboard() {
		SmartDashboard.putBoolean("Limit Switch", climb.getLimitSwitchState());
		
		// Motor Values
		SmartDashboard.putNumber("Right Motor Power Value", drive.getRightMotorPower());
		SmartDashboard.putNumber("Left Motor Power Value", drive.getLeftMotorPower());
		
		// Gyro Values
		SmartDashboard.putNumber("Gyro Error", drive.getGyroPIDError());
		SmartDashboard.putNumber("Gyro PID Output", drive.getGyroPIDOutput());
		SmartDashboard.putBoolean("Gyro Is Finished", drive.turnIsFinished());
		SmartDashboard.putNumber("Gyro Angle", drive.getGyroYaw());
		SmartDashboard.putNumber("Gyro Setpoint", drive.getTurnPIDSetpoint()); 
		SmartDashboard.putBoolean("Gyro Calibration", drive.checkGyroCalibration());
		
		// General Encoder Values
		SmartDashboard.putNumber("Target Tick Count", Constants.ticksPerInch * 120);
		
		// Left Encoder Values
		SmartDashboard.putNumber("Left Encoder Value", drive.getLeftEncoderCount());
		SmartDashboard.putNumber("Left Encoder PID Error", drive.getLeftPIDError());
		SmartDashboard.putNumber("Left Encoder PID Output", drive.getLeftPIDOutput());
		SmartDashboard.putNumber("Left Encoder Setpoint", drive.getLeftEncoderSetpoint());
		
		// Right Encoder Values
		SmartDashboard.putNumber("Right Encoder Value", drive.getRightEncoderCount());
		SmartDashboard.putNumber("Right Encoder PID Error", drive.getRightPIDError());
		SmartDashboard.putNumber("Right Encoder PID Output", drive.getRightPIDOutput());
		SmartDashboard.putNumber("Right Encoder Setpoint", drive.getRightEncoderSetpoint());
	}
	
	private void updateAllianceColor() {
		DriverStation.Alliance newAlliance = driverStation.getAlliance();
		if (alliance == DriverStation.Alliance.Invalid && newAlliance != DriverStation.Alliance.Invalid) {
			alliance = newAlliance;
			if (alliance == DriverStation.Alliance.Red) {
				// We are Red
				Robot.drive.setLEDred();
			} else if (alliance == DriverStation.Alliance.Blue) {
				// We are Blue
				Robot.drive.setLEDBlue();
			}
		}
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
		drive.updatePIDControllers();
		autonomousCommand = (Command) chooser.getSelected();

		if (autonomousCommand != null) {
			autonomousCommand.start();
			Robot.gear.fireGearPusher();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		updateDashboard();
	}

	@Override
	public void teleopInit() {
		Robot.gear.fireGearPusher();
		Robot.drive.resetEncoders();

		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		drive.drive(oi.getDriverJoystick());
		
		updateDashboard();
		// Robot.shooter.turnLightOn();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}