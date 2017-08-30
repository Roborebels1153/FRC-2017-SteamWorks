package com.walpole.frc.team.robot;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kauailabs.navx.frc.AHRS;
import com.walpole.frc.team.robot.autonomous.BlueCenterScoreAGearWithSeconds;
import com.walpole.frc.team.robot.autonomous.BlueLeftKnockDownHopper;
import com.walpole.frc.team.robot.autonomous.BlueLeftScoreAGear;
import com.walpole.frc.team.robot.autonomous.BlueLeftScoreAGearShoot;
import com.walpole.frc.team.robot.autonomous.BlueRedCenterScoreAGear;
import com.walpole.frc.team.robot.autonomous.BlueRightScoreAGear;
import com.walpole.frc.team.robot.autonomous.Shoot;
import com.walpole.frc.team.robot.autonomous.Drive10FeetShiftLow;
import com.walpole.frc.team.robot.autonomous.DriveAndTurn;
import com.walpole.frc.team.robot.autonomous.RedLeftScoreAGear;
import com.walpole.frc.team.robot.autonomous.RedRightHopperShoot;
import com.walpole.frc.team.robot.autonomous.RedRightScoreAGear;
import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithGyroEncoder;
import com.walpole.frc.team.robot.commands.DriveForwardWithSeconds;
import com.walpole.frc.team.robot.commands.GearCollectorIn;
import com.walpole.frc.team.robot.commands.GearTrayWithJoysticks;
import com.walpole.frc.team.robot.commands.ShiftHighCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;
import com.walpole.frc.team.robot.commands.TurnWithVisionCommand;
import com.walpole.frc.team.robot.subsystems.Climb;
import com.walpole.frc.team.robot.subsystems.Collector;
import com.walpole.frc.team.robot.subsystems.Drive;
import com.walpole.frc.team.robot.autonomous.BlueRedCenterScoreAGear;
import com.walpole.frc.team.robot.autonomous.BlueLeftScoreAGear;
import com.walpole.frc.team.robot.autonomous.BlueRightScoreAGear;
import com.walpole.frc.team.robot.autonomous.Drive10FeetShiftLow;
import com.walpole.frc.team.robot.autonomous.RedLeftScoreAGear;
import com.walpole.frc.team.robot.autonomous.RedRightScoreAGear;
import com.walpole.frc.team.robot.commands.CalibrateGyro;
import com.walpole.frc.team.robot.commands.DriveForwardWithEncoder;
import com.walpole.frc.team.robot.commands.MoveGearCollectorOutAutoCommand;
import com.walpole.frc.team.robot.commands.RetractGearPusherCommand;
import com.walpole.frc.team.robot.commands.TurnWithGyroCommand;
import com.walpole.frc.team.robot.subsystems.Climb;
import com.walpole.frc.team.robot.subsystems.Drive;
import com.walpole.frc.team.robot.subsystems.FloorGear;
import com.walpole.frc.team.robot.subsystems.Gear;
import com.walpole.frc.team.robot.subsystems.Shooter;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Parity;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.SerialPort.StopBits;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

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
	public static final Shooter shooter = new Shooter();
	public static OI oi = new OI();
	
	private final Object imgLock = new Object();  

    private Command autonomousCommand;
    private SendableChooser<Command> chooser;
    
    private DriverStation.Alliance alliance = DriverStation.Alliance.Invalid;
	private VisionThread visionThread;
	
	public static AHRS gyro;
	
	public static int number_targets = 0;
	public static int target1_x = 0;
	public static int target1_y = 0;
	public static int target1_width = 0;
	public static int target1_height = 0;
	public static int target2_x = 0;
	public static int target2_y = 0;
	public static int target2_width = 0;
	public static int target2_height = 0;
	public static int target_center = 0;
	public int center_x = 115; //120;
	public static int error = 0;
	public static int loopCount = 0;

	public SerialPort arduinoSerial;
	public String outputString = new String("no target detected");
	public String arduinoString = new String("");
	public String packetPattern = "B,([0-9.,]*),[EN]$";
	public Pattern r;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	prefs = Preferences.getInstance();
    	oi = new OI();
    	chooser = new SendableChooser<Command>();
		arduinoSerial = new SerialPort(9600,Port.kMXP,8,Parity.kNone,StopBits.kOne);
	
//	new Thread(() -> {
//		
//		try {
//		    gyro = new AHRS(SerialPort.Port.kUSB);
//		} catch (RuntimeException ex) {
//	            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
//		    // TODO Add global variable
//		}
//	}).start();
	
	// chooser.addObject("My Auto", new MyAutoCommand());

	chooser.addObject("Blue Red Center Deliver A Gear", new BlueRedCenterScoreAGear());
	chooser.addObject("Blue Left Deliver A Gear", new BlueLeftScoreAGear());
	chooser.addObject("Blue Right Deliver A Gear", new BlueRightScoreAGear()); 
	chooser.addObject("Red Right Deliver A Gear", new RedRightScoreAGear());
	chooser.addObject("Red Left Deliver A Gear", new RedLeftScoreAGear());
	//chooser.addObject("Drive 10 Feet", new DriveForwardWithEncoder(120));
//	chooser.addObject("Drive 10 ft with gyro", new DriveForwardWithGyroEncoder(120));
	chooser.addObject("Turn Right With Gyro", new TurnWithGyroCommand(90, 0.35));
	chooser.addObject("Center With Gyro", new TurnWithGyroCommand(0, 0.5));
	//chooser.addObject("Drive Forward With Seconds", new DriveForwardWithSeconds(5));
	chooser.addObject("Drive And Turn", new DriveAndTurn());
	//chooser.addObject("Cross The Green Line", new CrossGreenLine()); 
	//chooser.addObject("Score A Gear With Seconds Center", new BlueCenterScoreAGearWithSeconds());
	chooser.addObject("Drive 10 feet ShiftLow Forward", new Drive10FeetShiftLow()); 
	chooser.addObject("Move Gear Collector Down", new MoveGearCollectorOutAutoCommand(40, 0.6, 2));
	chooser.addObject("Turn With Gyro Slow", new TurnWithGyroCommand(90, 0.3));
	chooser.addObject("Blue Left Deliver A Gear Shoot", new BlueLeftScoreAGearShoot());
	//chooser.addObject("Turn With Gyro Normal", new TurnWithGyroCommand(90));


	//chooser.addObject("Move Gear Up", new MoveGearCollectorOutAutoCommand(0, 0.7));
	//Shift high is actually shift low, due to the change in wiring for 2017 PROTOTYPE robot 
	//chooser.addObject("Shift Low", new ShiftHighCommand()); 
	
		
// AxisCamera cameraTwo = CameraServer.getInstance().addAxisCamera("axis-camera-vision","10.11.53.4");
// 	camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
	SmartDashboard.putData("Auto mode", chooser);
	
	new Thread(() -> {
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setVideoMode(VideoMode.PixelFormat.kMJPEG, 200, 160, 10);
		
	}).start();
	
    }   	
    
    public static void updateDashboard() {
    	
    // Climber Values
//	SmartDashboard.putBoolean("Limit Switch", climb.getLimitSwitchState());
//	SmartDashboard.putBoolean("Other Limit Switch", climb.getOtherLimitSwitchState());
//	
	//Drive Values
	
		//Left Encoder Values
	SmartDashboard.putNumber("Left Encoder Value", drive.getLeftEncoderCount());
	SmartDashboard.putNumber("Left Motor Power Value", drive.getLeftMotorPower());
	SmartDashboard.putNumber("Left Encoder PID Error", drive.getLeftPIDError());
	SmartDashboard.putNumber("Left Encoder PID Output", drive.getLeftPIDOutput());
	SmartDashboard.putNumber("Left Encoder Setpoint", drive.getLeftEncoderSetpoint());

		//Right Encoder Values
	SmartDashboard.putNumber("Right Motor Power Value", drive.getRightMotorPower());
	SmartDashboard.putNumber("Right Encoder Value", drive.getRightEncoderCount());
	SmartDashboard.putNumber("Right Encoder PID Error", drive.getRightPIDError());
	SmartDashboard.putNumber("Right Encoder PID Output", drive.getRightPIDOutput());
	SmartDashboard.putNumber("Right Encoder Setpoint", drive.getRightEncoderSetpoint());

	//Gyro Values
	SmartDashboard.putNumber("Gyro Error", drive.getGyroPIDError());
	SmartDashboard.putNumber("Gyro PID Output", drive.getGyroPIDOutput());
	SmartDashboard.putBoolean("Gyro Is Finished", drive.turnIsFinished());
	//SmartDashboard.putNumber("Gyro Angle", drive.getGyroAngle());
	SmartDashboard.putNumber("Gyro Angle", drive.getGyroYaw());
	SmartDashboard.putNumber("Gyro Setpoint", drive.getTurnPIDSetpoint()); 
	SmartDashboard.putBoolean("Gyro Calibration", drive.checkGyroCalibration());
	
	//General Values
	SmartDashboard.putNumber("Target Tick Count(10ft)", Constants.ticksPerInch * 120);

	//Gear Values
	SmartDashboard.putNumber("Motor Power", floorGear.getGearMotorValue());
	SmartDashboard.putNumber("Gear Encoder Value", floorGear.getGearEncoderCount());
	SmartDashboard.putNumber("Gear PID Output", floorGear.getGearPIDOutput()); 
	SmartDashboard.putNumber("Gear PID Error", floorGear.getGearPIDError()); 
	SmartDashboard.putNumber("Gear Encoder Setpoint", floorGear.getGearPIDSetPoint());
	SmartDashboard.putData("Calibrate Gyro", new CalibrateGyro());
	SmartDashboard.putBoolean("Is Gear In", floorGear.getGearLightSensorState());

	
	//Shooter Values
	SmartDashboard.putNumber("RPS", Robot.shooter.getRPS());
	SmartDashboard.putNumber("RPM", Robot.shooter.getRPS() * 60);
	SmartDashboard.putNumber("Shooter Error", shooter.shooterPIDError());
	SmartDashboard.putNumber("Shooter Motor Power", shooter.getShooterMotorPower());
	//SmartDashboard.putNumber("RPM", Robot.Counter.getRPMCount());
	

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
		
		
		updateDashboard();
//		
//		updateAllianceColor();

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
    	r = Pattern.compile(packetPattern); 
    	//Robot.gear.keepGear();
    	autonomousCommand = new TurnWithVisionCommand(5);

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
        boolean scan = true; 
        if(scan) { 
        	//if (loopCount % 7 == 0){
			if (arduinoSerial.getBytesReceived() > 0) {
				arduinoString += arduinoSerial.readString();
			if (!arduinoString.equalsIgnoreCase("")) {
				// process the output
				
				arduinoString = arduinoString.trim();
				SmartDashboard.putString("Arduino Output", arduinoString);
				
				// first split on return characters
				String[] outputLines = arduinoString.trim().split("\n");

				SmartDashboard.putNumber("Number of lines of arduino data", outputLines.length);
				
				String[] outputArray;
				int c = 0;
				boolean keepLooping = true;
				int number_of_targets_check = 0;
				// loop through all of the lines, look for BEGIN/END pairs to make sure we have a full packet of data
				while ((keepLooping) && (c < outputLines.length)) {
					Matcher m = r.matcher(outputLines[c]);
					if (m.find()) {
						outputArray = m.group(1).trim().split(",");
						SmartDashboard.putString("Regex matched", m.group(1));
						SmartDashboard.putNumber("Length Of Output Array", outputArray.length);
						
						// check first field
						if (outputArray.length > 1) {
							number_of_targets_check = Integer.parseInt(outputArray[0]);
							SmartDashboard.putNumber("Number of targets", number_targets);
							
							// only process if we got a packet and if the number of targets > 0
							if (number_of_targets_check > 0) {
								// stop checking for data
								keepLooping = false;
								// process the last line
								//String[] outputArray = m.group(1).trim().split(",");
							
								// clear out the arduinoString
								arduinoString="";
								
								//String[] outputArray = arduinoString.trim().split(",");
								//String targets = outputArray[0].trim().isEmpty() ? "0" : outputArray[0];
								//number_targets = Integer.parseInt(targets);
								
								if (outputArray.length >= 5) {
									String target1x = outputArray[1].trim().isEmpty() ? "0" : outputArray[1];
									String target1y = outputArray[2].trim().isEmpty() ? "0" : outputArray[2];
								
									number_targets = number_of_targets_check;
									target1_x = Integer.parseInt(target1x);
									target1_y = Integer.parseInt(target1y);
									//target1_width = Integer.parseInt(outputArray[3]);
									//target1_height = Integer.parseInt(outputArray[4]);
									target2_x = 0;
									target2_y = 0;
									target2_width = 0;
									target2_height = 0;
									target_center = 0;
								 
									 error = center_x - target1_x;
									
									outputString = "target1: " + String.valueOf(target1_x) + "," + String.valueOf(target1_y);
									
									if ((number_targets >= 2) && (outputArray.length == 9)) {
									
										
										/*
										  for (int c = 5; c < outputArray.length; c++) {
										 
											outputString += "[" + c + "]:" + outputArray[c];
										}
										*/
										String target2x = outputArray[5].trim().isEmpty() ? "0" : outputArray[5];
										String target2y = outputArray[6].trim().isEmpty() ? "0" : outputArray[6];
										target2_x = Integer.parseInt(target2x);
										target2_y = Integer.parseInt(target2y);
										 error = center_x - ((target1_x + target2_x)/2);
					//					target2_width = Integer.parseInt(outputArray[7]);
					//					target2_height = Integer.parseInt(outputArray[8]);
										
										//// estimate distance based on average height
										//int avg_height = (int) (target1_height + target2_height)/2;
										
										//int standard_z_distance = 34;
										//int min_avg_height = 25;
										//int height_at_std_z = 35;
										//int pixy_x_center = 160;
										//// at a distance of 34", 1 inch = 4 pixels
										//int std_y_pixels = 4;
										
										//// distance in inches
										//// height = 35 at a distance of 34"
										//int z_distance = 0;
										//// ratio of actual z_distance / 34"
										//double z_distance_ratio = 0.0;
										
										//// filter - potentially misleading data
										//// depending on tuning of pixycam, it may see the vision targets as multiple small 
										//// targets
										//if (avg_height > min_avg_height) {
										//	z_distance = (standard_z_distance * height_at_std_z)/avg_height;
										//	z_distance_ratio = (double)z_distance/standard_z_distance;
											
										//	target_center = (int) (target1_x + target2_x)/2;
											
										//	double distance_from_center = (pixy_x_center - target_center)/(std_y_pixels*z_distance_ratio);
											
										//	double angle = Math.toDegrees(Math.atan2(distance_from_center,z_distance));
											
										//	outputString += " target2: " + String.valueOf(target2_x) + "," + String.valueOf(target2_y) + " center: " 
										//			+ String.valueOf(target_center);
										//	outputString += " distance: " + z_distance;
										//	outputString += " angle: " + String.format("%.2f", angle);
										//}
									}
								}
							} else {
								outputString = "no target found";
							}
						}
					}
					c++;
					if ((keepLooping) && (c == outputLines.length)) {
						// clear the arduinoString	
						arduinoString = "";
						// only determine we have no targets if we have looped through all of the lines
						// and didn't find a target
						number_targets = 0;
						error = 0;
					}
				}
				SmartDashboard.putNumber("Error", error);	
				SmartDashboard.putString("Last Pixy Output", outputString); 
			}		
				//outputString = " : " + arduinoString;
			//}
				

		}
		
		loopCount++;
        }
        
    }

    public void teleopInit() {
    	//Robot.gear.fireGearPusher();
    	//Robot.gear.keepGear();
    	Robot.drive.resetEncoders(); 
    	Robot.floorGear.resetGearEncoder();
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
//	floorGear.gearLEDOn();
	floorGear.pickedUpGearLED();
	updateDashboard();
  //      Robot.shooter.turnLightOn();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
	LiveWindow.run();
    }
    
    public AHRS getGyro() {
    	return gyro;
    }

	
}
