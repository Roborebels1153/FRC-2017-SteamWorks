package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.subsystems.Drive;

import com.walpole.frc.team.robot.lib.RebelDrive;
import com.walpole.frc.team.robot.lib.DummyPIDOutput;
import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.Robot;
import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

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
	
	private double encoderRateP;
	private double encoderRateI;
	private double encoderRateD;
	private double encoderRateF;
	
	private Encoder leftEncoder;
	private DummyPIDOutput leftEncoderOutput;
	private PIDController leftEncoderPID;
	private PIDController leftEncoderRatePID;
	
	private Encoder rightEncoder;
	private DummyPIDOutput rightEncoderOutput;
	private PIDController rightEncoderPID;
	private PIDController rightEncoderRatePID;
	
	public enum Shifter {
		High, Low
	}
	
	private Shifter currGear = Shifter.Low;
	
	public Drive() {
		leftFrontVictor = new Victor(RobotMap.LEFT_FRONT_MOTOR);
		leftBackVictor = new Victor(RobotMap.LEFT_BACK_MOTOR);
		rightFrontVictor = new Victor(RobotMap.RIGHT_FRONT_MOTOR);
		rightBackVictor = new Victor(RobotMap.RIGHT_BACK_MOTOR);
		leftFrontVictor.setInverted(true);
		leftBackVictor.setInverted(true);
		
		transmission = new DoubleSolenoid(RobotMap.TRANSMISSION_SOLENOID_A, RobotMap.TRANSMISSION_SOLENOID_B);
		
		leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_A, RobotMap.LEFT_ENCODER_B, false, EncodingType.k4X);
		leftEncoder.setReverseDirection(true);
		leftEncoder.setDistancePerPulse(1);
		leftEncoder.setPIDSourceType(PIDSourceType.kRate);
		leftEncoderOutput = new DummyPIDOutput();
		leftEncoderPID = new PIDController(encoderP, encoderI, encoderD, leftEncoder, leftEncoderOutput);
		leftEncoderRatePID = new PIDController(encoderRateP, encoderRateI, encoderRateD, encoderRateF, leftEncoder, leftEncoderOutput);
		
		rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_A, RobotMap.RIGHT_ENCODER_B, false, EncodingType.k4X);
		rightEncoder.setDistancePerPulse(1);
		rightEncoder.setPIDSourceType(PIDSourceType.kRate);
		rightEncoderOutput = new DummyPIDOutput();
		rightEncoderPID = new PIDController(encoderP, encoderI, encoderD, rightEncoder, rightEncoderOutput);
		rightEncoderRatePID = new PIDController(encoderRateP, encoderRateI, encoderRateD, encoderRateF, rightEncoder, rightEncoderOutput);
		
		robotDrive = new RebelDrive(leftFrontVictor, leftBackVictor, rightFrontVictor, rightBackVictor);
	}
	
	/**
	 * Load PID values from preferences and write them to variables
	 */
	private void loadPIDValues() {
		encoderRateP = Robot.prefs.getDouble("encoderRateP", Constants.encoderRateP);
		encoderRateI = Robot.prefs.getDouble("encoderRateI", Constants.encoderRateI);
		encoderRateD = Robot.prefs.getDouble("encoderRateD", Constants.encoderRateD);
		encoderRateF = Robot.prefs.getDouble("encoderRateF", Constants.encoderRateF);
		
		encoderP = Robot.prefs.getDouble("encoderP", Constants.encoderP);
		encoderI = Robot.prefs.getDouble("encoderI", Constants.encoderI);
		encoderD = Robot.prefs.getDouble("encoderD", Constants.encoderD);
	}
	
	/**
	 * Update the proportional, integral, and derivative values
	 */
	public void updatePIDControllers() {
		loadPIDValues();
		
		leftEncoderPID.setPID(encoderP, encoderI, encoderD);
		rightEncoderPID.setPID(encoderP, encoderI, encoderD);
		
		leftEncoderRatePID.setPID(encoderRateP, encoderRateI, encoderRateD, encoderRateF);
		rightEncoderRatePID.setPID(encoderRateP, encoderRateI, encoderRateD, encoderRateF);
	}
	
	@Override
	public void initDefaultCommand() {
	}
	
	// Drive Methods
	
	public void drive(Joystick joystick) {
		double moveValue = 1 * joystick.getRawAxis(RobotMap.JOYSTICK_LEFT_Y);
		double rotateValue = 0.7 * joystick.getRawAxis(RobotMap.JOYSTICK_RIGHT_X);
		robotDrive.arcadeDrive(moveValue, rotateValue, true);
	}
	
	public void arcadeDrive(double driveSpeed, double turnSpeed, boolean squaredInputs) {
		robotDrive.arcadeDrive(driveSpeed, turnSpeed, squaredInputs);
	}
	
	public void tankDrive(double leftDrive, double rightDrive) {
		robotDrive.tankDrive(leftDrive, rightDrive);
	}
	
	public void stopDrive() {
		leftFrontVictor.set(0);
		leftBackVictor.set(0);
		rightFrontVictor.set(0);
		rightBackVictor.set(0);
	}
	
	// Raw Motor Methods
	
	public double getLeftMotorPower() {
		return leftFrontVictor.get();
	}
	
	public double getRightMotorPower() {
		return rightFrontVictor.get();
	}
	
	// Shifting Methods
	
	public Shifter getCurrGear() {
		return currGear;
	}
	
	public void shiftHigh() {
		transmission.set(DoubleSolenoid.Value.kForward);
		currGear = Shifter.High;
	}
	
	public void shiftLow() {
		transmission.set(DoubleSolenoid.Value.kReverse);
		currGear = Shifter.Low;
	}
	
	// Raw Encoder Methods
	
	public int getLeftEncoderCount() {
		return leftEncoder.get();
	}
	
	public int getRightEncoderCount() {
		return rightEncoder.get();
	}
	
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	// PID Enable and Disable Methods
	
	public void enableEncoderPID() {
		leftEncoderPID.enable();
		rightEncoderPID.enable();
	}
	
	public void enableEncoderRatePID() {
		leftEncoderRatePID.enable();
		rightEncoderRatePID.enable();
	}
	
	public void disableEncoderPID() {
		leftEncoderPID.disable();
		rightEncoderPID.disable();
	}
	
	public void disableEncoderRatePID() {
		leftEncoderRatePID.disable();
		rightEncoderRatePID.disable();
	}
	
	// PID Setpoint Methods
	
	public void setEncoderPIDSetpoint(double setPoint) {
		leftEncoderPID.setSetpoint(setPoint);
		rightEncoderPID.setSetpoint(setPoint);
	}
	
	public void setEncoderRatePIDSetpoint(double setPoint) {
		leftEncoderRatePID.setSetpoint(setPoint);
		rightEncoderRatePID.setSetpoint(setPoint);
	}
	
	public double getLeftEncoderSetpoint() {
		return leftEncoderPID.getSetpoint();
	}
	
	public double getRightEncoderSetpoint() {
		return rightEncoderPID.getSetpoint();
	}
	
	public double getLeftEncoderRateSetpoint() {
		return leftEncoderPID.getSetpoint();
	}
	
	public double getRightEncoderRateSetpoint() {
		return rightEncoderPID.getSetpoint();
	}
	
	// PID Output Range Methods
	
	public void setMaxEncoderPIDOutput(double drivingSpeed) {
		leftEncoderPID.setOutputRange(-drivingSpeed, drivingSpeed);
		rightEncoderPID.setOutputRange(-drivingSpeed, drivingSpeed);
	}
	
	public void setMaxEncoderRatePIDOutput(double min, double max) {
		leftEncoderRatePID.setOutputRange(min, max);
		rightEncoderRatePID.setOutputRange(min, max);
	}
	
	// PID Error Methods
	
	public double getLeftPIDError() {
		return leftEncoderPID.getError();
	}
	
	public double getRightPIDError() {
		return rightEncoderPID.getError();
	}
	
	public double getLeftRatePIDError() {
		return leftEncoderRatePID.getError();
	}
	
	public double getRightRatePIDError() {
		return rightEncoderRatePID.getError();
	}
	
	// PID Output Methods
	
	public double getLeftPIDOutput() {
		return leftEncoderPID.get();
	}
	
	public double getRightPIDOutput() {
		return rightEncoderPID.get();
	}
	
	public double getLeftRatePIDOutput() {
		return leftEncoderRatePID.get();
	}
	
	public double getRightRatePIDOutput() {
		return rightEncoderRatePID.get();
	}
}