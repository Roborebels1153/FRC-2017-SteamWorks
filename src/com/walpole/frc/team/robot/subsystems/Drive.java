package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.subsystems.Drive;

import com.walpole.frc.team.robot.lib.RebelDrive;
import com.walpole.frc.team.robot.lib.RebelGyro;
import com.walpole.frc.team.robot.lib.DummyPIDOutput;
import com.kauailabs.navx.frc.AHRS;
import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;
import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem {

    private RebelDrive robotDrive;

    private SpeedController leftFrontVictor;
    private SpeedController leftBackVictor;
    private SpeedController rightFrontVictor;
    private SpeedController rightBackVictor;

    private DoubleSolenoid transmission;

    private double encoderP;
    private double encoderI;
    private double encoderD;
    
    private double gyroP;
    private double gyroI;
    private double gyroD;

    private Encoder leftEncoder;
    private DummyPIDOutput leftEncoderOutput;
    private PIDController leftEncoderPID; 
    private Encoder rightEncoder;
    private DummyPIDOutput rightEncoderOutput;
    private PIDController rightEncoderPID;

    //private RebelGyro gyro;
    //private AnalogGyro gyro;
    private AHRS gyro;
    private PIDController gyroPID;
    private DummyPIDOutput gyroOutput;
    private boolean turnIsFinished;
    private double driveTolerance = 15;
    
    private double previousJoystickValue = 0;

    private double joystickChangeLimiter = .08;
    
    private boolean turboMode = false;
   

    public enum Shifter {
	High, Low
    }

    public enum Speed {
	Normal, Slow
    }

    private Shifter currGear = Shifter.Low;
    private Speed currSpeed = Speed.Normal;

    //private double desiredRotationDegrees;

    public Drive() {
	leftFrontVictor = new Victor(RobotMap.LEFT_FRONT_MOTOR);
	leftBackVictor = new Victor(RobotMap.LEFT_BACK_MOTOR);
	rightFrontVictor = new Victor(RobotMap.RIGHT_FRONT_MOTOR);
	rightBackVictor = new Victor(RobotMap.RIGHT_BACK_MOTOR);

	transmission = new DoubleSolenoid(RobotMap.TRANSMISSION_SOLENOID_A, RobotMap.TRANSMISSION_SOLENOID_B);

	leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_A, RobotMap.LEFT_ENCODER_B, false, EncodingType.k4X);
	leftEncoderOutput = new DummyPIDOutput();
	//Add Constants here if you want to load PID values from constants class
	leftEncoderPID = new PIDController(Constants.encoderP, Constants.encoderI, Constants.encoderD, leftEncoder, leftEncoderOutput);
	rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_A, RobotMap.RIGHT_ENCODER_B, false, EncodingType.k4X);
	rightEncoder.setReverseDirection(true);
	leftEncoder.setReverseDirection(true); // TODO:This is set FALSE to work on Prototype Robot, change TRUE on FINAL
	rightEncoderOutput = new DummyPIDOutput();
	//Add Constants here if you want to load PID values from constants class
	rightEncoderPID = new PIDController(Constants.encoderP, Constants.encoderI, Constants.encoderD, rightEncoder, rightEncoderOutput); 


	//gyro = new RebelGyro();
	//gyro.startThread();
	//gyro = new AnalogGyro(new AnalogInput(RobotMap.GYRO));
	try {
	    gyro = new AHRS(SerialPort.Port.kUSB);
	} catch (RuntimeException ex) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
	    // TODO Add global variable
	}
	gyroOutput = new DummyPIDOutput();
	//Add Constants here if you want to load PID values from constants class
	gyroPID = new PIDController(gyroP, gyroI, gyroD, gyro, gyroOutput);
	gyroPID.setOutputRange(-1, 1);
	gyroPID.setInputRange(-180, 180);
	gyroPID.setContinuous();

	robotDrive = new RebelDrive(leftFrontVictor, leftBackVictor, rightFrontVictor, rightBackVictor);
    }

    /**
     * Load PID values from preferences and write them to variables
     */
    private void loadPIDValues() {
	encoderP = Robot.prefs.getDouble("encoderP", encoderP);
	encoderI = Robot.prefs.getDouble("encoderI", encoderI);
	encoderD = Robot.prefs.getDouble("encoderD", encoderD);

	gyroP = Robot.prefs.getDouble("gyroP", gyroP);
	gyroI = Robot.prefs.getDouble("gyroI", gyroI);
	gyroD = Robot.prefs.getDouble("gyroD", gyroD);

    }

    /**
     * Update the proportional, integral, and derivative values
     */
    public void updatePIDControllers() {
	loadPIDValues();

	leftEncoderPID.setPID(encoderP, encoderI, encoderD); 
	rightEncoderPID.setPID(encoderP, encoderI, encoderD); 
	gyroPID.setPID(gyroP, gyroI, gyroD);
    }

    @Override
    public void initDefaultCommand() {
    }

    public void drive(Joystick joystick) {
		double moveValue = 1 * joystick.getRawAxis(RobotMap.JOYSTICK_LEFT_Y);
		double rotateValue = 0.7 * joystick.getRawAxis(RobotMap.JOYSTICK_RIGHT_X);
		robotDrive.arcadeDrive(moveValue, rotateValue, true);
    }
    
    public void driveWithInertia(Joystick joystick) {
    	
    	double currentJoystick = joystick.getRawAxis(RobotMap.JOYSTICK_LEFT_Y);
    	double changeInJoystick = currentJoystick - previousJoystickValue;
    	
    	if (changeInJoystick > joystickChangeLimiter) {
    		changeInJoystick = joystickChangeLimiter;
    	} else if (changeInJoystick < -joystickChangeLimiter) {
    		changeInJoystick = -joystickChangeLimiter;
    	}
    	double speedMultiplyer;
  	
    	if (turboMode == true) {
    		//speedMultiplyer = 1;
        	 speedMultiplyer = 1;
    	} else {
    		//speedMultiplyer = 0.8;
        	 speedMultiplyer = 0.8; 
    	}
   	
    	previousJoystickValue = previousJoystickValue + changeInJoystick;
    	
		double moveValue = speedMultiplyer * previousJoystickValue;
		double rotateValue = 0.75 * joystick.getRawAxis(RobotMap.JOYSTICK_RIGHT_X);
		robotDrive.arcadeDrive(moveValue, rotateValue, true);
    	
    }
    
    public Shifter getCurrGear() {
    	return currGear;
    }
    
    public void turboOn() {
    	turboMode = true;
    }
    
    public void turboOff() {
    	turboMode = false;
    }
    
    public void Nitro(Joystick joystick) {
    	//double moveValue = speed;
    	double moveValue = 1 * joystick.getRawAxis(RobotMap.JOYSTICK_LEFT_Y);
    	double rotateValue = 0.7 * joystick.getRawAxis(RobotMap.JOYSTICK_RIGHT_X);
    	robotDrive.arcadeDrive(moveValue, rotateValue, true); 
    }
    
    public Speed getCurrSpeed() {
	return currSpeed;
    }

    public void shiftHigh() {
	/*
	 * if (currGear.equals(Shifter.Low)) {
	 * transmission.set(DoubleSolenoid.Value.kForward); currGear =
	 * Shifter.High;
	 */
	transmission.set(DoubleSolenoid.Value.kForward);
	currGear = Shifter.High;
    }

    public void shiftLow() {
	/*
	 * if (currGear.equals(Shifter.High)) {
	 * transmission.set(DoubleSolenoid.Value.kReverse); currGear =
	 * Shifter.Low; }
	 */
	transmission.set(DoubleSolenoid.Value.kReverse);
	currGear = Shifter.Low;
    }
    
   /* public rampUpCode() {
    	
    }*/

    public int getLeftEncoderCount() {
	//we are negating this as it shows up as a negative on the SmartDashboard
	return leftEncoder.get();
    }

    public double getGyroAngle() {
	return gyro.getAngle();
    }
    
    public void calibrateGyro() {
    	gyro.reset();
    }

    public int getRightEncoderCount() {
	return rightEncoder.get();
    }

    public void resetEncoders() {
	leftEncoder.reset();
	rightEncoder.reset();
    }

    /**
     * Used to find how many encoder ticks per given inches
     * 
     * @param Inches
     * @return Ticks
     */
    public double convertInchesToTicks(int inches) {
	return Constants.ticksPerInch * inches;
    }

    public void driveAtSpeed(double speed) {
	robotDrive.arcadeDrive(speed, 0);
    }
    
    public void arcadeDrive(double driveSpeed, double turnSpeed) {
	robotDrive.arcadeDrive(driveSpeed, turnSpeed);
    }
    
    public void arcadeDrive(double driveSpeed, double turnSpeed, boolean squaredInputs) {
	robotDrive.arcadeDrive(driveSpeed, turnSpeed, squaredInputs);
    }
    
    public void tankDrive(double leftDrive, double rightDrive) {
	robotDrive.tankDrive(leftDrive, rightDrive);
    }
    
    public void arcadeTankDrive(double driveLeftSpeed, double driveRightSpeed, double turnSpeed) { 
	robotDrive.arcadeTankDrive(driveLeftSpeed, driveRightSpeed, turnSpeed); 
    }
//    public void arcadeTankDrive(double leftOutput, double rightOutput, double gyroOutput) {
//	robotDrive.arcadeTankDrive(leftOutput, rightOutput, gyroOutput);
//    }

    public boolean isOnTarget() {
	return Math.abs(leftEncoderPID.getError()) < driveTolerance
		|| Math.abs(rightEncoderPID.getError()) < driveTolerance;
    }

    public void setTurnSpeed(double speed) {
	// the 3rd argument is set to false because we do not need squared inputs for autonomous
	robotDrive.arcadeDrive(0, speed, false);
    }

    /**
     * Enables both the Left Encoder PID and the Right Encoder PID by calling
     * enable() on each
     */
    public void enableDrivePID() {
	leftEncoderPID.enable();
	rightEncoderPID.enable();
    }

    public void enableGyroPID() {
	gyroPID.enable();
    }

//    public void resetGyro() {
//	gyro.reset();
//    }
    
    public double getGyroYaw() {
	return gyro.getYaw();
    }

    /**
     * Disables both the Left Encoder PID and the Right Encoder PID by calling
     * disable() on each
     */
    public void disableDrivePID() {
	leftEncoderPID.disable();
	rightEncoderPID.disable();
    }

    public void disableGyroPID() {
	gyroPID.disable();
    }

    public void setDrivePIDSetPoint(double setPoint) {
	leftEncoderPID.setSetpoint(setPoint);
	rightEncoderPID.setSetpoint(setPoint);
    }
    
    public double getRightEncoderSetpoint () {
	return rightEncoderPID.getSetpoint();
    }
 
    
    public double getLeftEncoderSetpoint () {
	return leftEncoderPID.getSetpoint();
    }

    public void setTurnPIDSetpoint(double setPoint) {
	gyroPID.setSetpoint(setPoint);
    }
   
    public double getTurnPIDSetpoint() { 
	return gyroPID.getSetpoint(); 
    }

    public void setMaxDrivePIDOutput(double drivingSpeed) {
	leftEncoderPID.setOutputRange(-drivingSpeed, drivingSpeed);
	rightEncoderPID.setOutputRange(-drivingSpeed, drivingSpeed);
    }

    public void setMaxGyroOutput(double turningSpeed) {
	gyroPID.setOutputRange(-turningSpeed, turningSpeed);
	
    }
    
    public boolean checkGyroCalibration() {
	return gyro.isCalibrating();
    }
    	

    /*
     * public void goSetDistance() { if (leftEncoder.get() >=
     * (ticksToDriveALength)) { leftFrontVictor.set(0); leftBackVictor.set(0);
     * rightFrontVictor.set(0); rightBackVictor.set(0); } }
     */

    /**
     * Used to make the robot stop
     */
 
    public void stopDrive() {
	leftFrontVictor.set(0);
	leftBackVictor.set(0);
	rightFrontVictor.set(0);
	rightBackVictor.set(0);
    }

    public double getLeftMotorPower() {
	/*
	 * Return a negated value because the left motors are backwards
	 */
	return -leftBackVictor.get();
    }

    public double getRightMotorPower() {
	return rightBackVictor.get();
    }

    public double getLeftPIDError() {
	return leftEncoderPID.getError();
    }

    public double getRightPIDError() {
	return rightEncoderPID.getError();
    }

    public double getGyroPIDOutput() {
	// return gyroOutput.getOutput(); //
	return gyroPID.get();
    }

    public double getLeftPIDOutput() {
	return leftEncoderPID.get();
    }

    public double getRightPIDOutput() {
	return rightEncoderPID.get();
    }

    public double getGyroPIDError() {
	return gyroPID.getError();
    }

    public boolean turnIsFinished() {
	return this.turnIsFinished;
    }
    
}