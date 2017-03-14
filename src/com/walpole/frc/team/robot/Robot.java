package com.walpole.frc.team.robot;

import com.walpole.frc.team.robot.autonomous.BlueRedCenterScoreAGear;
import com.walpole.frc.team.robot.autonomous.BlueLeftScoreAGear;
import com.walpole.frc.team.robot.autonomous.BlueRightScoreAGear;
import com.walpole.frc.team.robot.autonomous.Drive10FeetShiftLow;
import com.walpole.frc.team.robot.autonomous.RedLeftScoreAGear;
import com.walpole.frc.team.robot.autonomous.RedRightScoreAGear;
import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.RetractGearPusherCommand;
import com.walpole.frc.team.robot.subsystems.Climb;
import com.walpole.frc.team.robot.subsystems.Drive;
import com.walpole.frc.team.robot.subsystems.FloorGear;
import com.walpole.frc.team.robot.subsystems.Gear;

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
	public static final Drive drive = new Drive();
	public static final Climb climb = new Climb();
	//public static final Gear gear = new Gear();
	public static final FloorGear floorGear = new FloorGear();
	public static OI oi = new OI();
	
	private final Object imgLock = new Object();  

    private Command autonomousCommand;
    private SendableChooser<Command> chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	prefs = Preferences.getInstance();;
	oi = new OI();
	chooser = new SendableChooser<Command>();
	// chooser.addObject("My Auto", new MyAutoCommand());
	SmartDashboard.putData("Auto mode", chooser);

	chooser.addObject("Blue Red Center Deliver A Gear", new BlueRedCenterScoreAGear());
	chooser.addObject("Blue Left Deliver A Gear", new BlueLeftScoreAGear());
	chooser.addObject("Blue Right Deliver A Gear", new BlueRightScoreAGear()); 
	chooser.addObject("Red Right Deliver A Gear", new RedRightScoreAGear());
	chooser.addObject("Red Left Deliver A Gear", new RedLeftScoreAGear());
	//chooser.addObject("Drive 10 Feet", new DriveForwardWithEncoder(120));
	//chooser.addObject("Drive 10 ft with gyro", new DriveForwardWithGyroEncoder(120));
	//chooser.addObject("Turn Right With Gyro", new TurnWithGyroCommand(90));
	//chooser.addObject("Center With Gyro", new TurnWithGyroCommand(0));
	//chooser.addObject("Drive Forward With Seconds", new DriveForwardWithSeconds(5));
	//chooser.addObject("Drive And Turn", new DriveAndTurn());
	//chooser.addObject("Cross The Green Line", new CrossGreenLine()); 
	//chooser.addObject("Score A Gear With Seconds Center", new BlueCenterScoreAGearWithSeconds());
	chooser.addObject("Drive 10 feet ShiftLow Forward", new Drive10FeetShiftLow()); 
	//Shift high is actually shift low, due to the change in wiring for 2017 PROTOTYPE robot 
	//chooser.addObject("Shift Low", new ShiftHighCommand()); 
    }    
	
    
    public static void updateDashboard() {
	SmartDashboard.putBoolean("Limit Switch", climb.getLimitSwitchState());
	SmartDashboard.putNumber("Left Encoder Value", drive.getLeftEncoderCount());
	SmartDashboard.putNumber("Right Motor Power Value", drive.getRightMotorPower());
	SmartDashboard.putNumber("Left Motor Power Value", drive.getLeftMotorPower());
	SmartDashboard.putNumber("Right Encoder Value", drive.getRightEncoderCount());
	//SmartDashboard.putNumber("Gyro Angle", drive.getGyroAngle());
	SmartDashboard.putNumber("Target Tick Count", Constants.ticksPerInch * 120);
	SmartDashboard.putNumber("Gyro Error", drive.getGyroPIDError());
	SmartDashboard.putNumber("Gyro PID Output", drive.getGyroPIDOutput());
	SmartDashboard.putBoolean("Gyro Is Finished", drive.turnIsFinished());
	SmartDashboard.putNumber("Left Encoder PID Error", drive.getLeftPIDError());
	SmartDashboard.putNumber("Left Encoder PID Output", drive.getLeftPIDOutput());
	SmartDashboard.putNumber("Right Encoder PID Error", drive.getRightPIDError());
	SmartDashboard.putNumber("Right Encoder PID Output", drive.getRightPIDOutput());
	SmartDashboard.putNumber("Right Encoder Setpoint", drive.getRightEncoderSetpoint());
	SmartDashboard.putNumber("Left Encoder Setpoint", drive.getLeftEncoderSetpoint());
	SmartDashboard.putNumber("Gyro Angle", drive.getGyroYaw());
	SmartDashboard.putNumber("Gyro Setpoint", drive.getTurnPIDSetpoint()); 
	SmartDashboard.putBoolean("Gyro Calibration", drive.checkGyroCalibration());
//	SmartDashboard.putNumber("RPM", Robot.Counter.getRPMCount());
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    public void disabledInit(){
	
    	
//    	new RetractGearPusherCommand();

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		new RetractGearPusherCommand();
		updateDashboard();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	//Robot.gear.keepGear();
        autonomousCommand = (Command) chooser.getSelected();
        drive.updatePIDControllers();  //the prefs are not working so this is commented (Sunday 2/12)
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
        //Robot.gear.fireGearPusher();
//        Robot.gear.retractGearPusher();
        
        // autonomousCommand = new ShootWithTimer();

          //AUTONOMOUS MODES 
//        autonomousCommand = new BlueRedCenterScoreAGear(); 
//        autonomousCommand = new BlueLeftScoreAGear(); 
       // autonomousCommand = new BlueRightScoreAGear();
       // autonomousCommand = new RedRightScoreAGear(); 
      //  autonomousCommand = new RedLeftScoreAGear(); 
        
         // autonomousCommand = new Drive10FeetShiftLow(); 
        //autonomousCommand = new TurnWithGyroCommand(90);
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) {
            autonomousCommand.start();
        	//Robot.gear.fireGearPusher();
        } else {
            autonomousCommand = new DriveForwardWithEncoder(120);
            autonomousCommand.start();
        	//Robot.gear.fireGearPusher();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
	Scheduler.getInstance().run();
        
        updateDashboard();
    }

    public void teleopInit() {
    	//Robot.gear.fireGearPusher();
    	//Robot.gear.keepGear();
    	Robot.drive.resetEncoders(); 
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
	Scheduler.getInstance().run();
	//drive.drive(oi.getDriverJoystick());
	drive.driveWithInertia(oi.getDriverJoystick());
	floorGear.gear(oi.getOperatorJoystick());
	updateDashboard();
  //      Robot.shooter.turnLightOn();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
	LiveWindow.run();
    }

}
