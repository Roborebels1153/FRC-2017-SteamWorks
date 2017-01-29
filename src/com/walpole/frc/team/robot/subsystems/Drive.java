package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.subsystems.Drive;

import com.walpole.frc.team.robot.lib.RebelDrive;
import com.walpole.frc.team.robot.lib.RebelGyro;
import com.walpole.frc.team.robot.lib.SPIGyro;
import com.walpole.frc.team.robot.lib.DualPIDOutput;
import com.walpole.frc.team.robot.lib.DummyPIDOutput;
import com.walpole.frc.team.robot.Constants;
import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
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
	
	private Encoder leftEncoder;
	private DualPIDOutput leftEncoderOutput;
	private PIDController leftEncoderPID;
	private Encoder rightEncoder;
	private DualPIDOutput rightEncoderOutput;
	private PIDController rightEncoderPID;
	
	private RebelGyro gyro;
	private PIDController gyroPID;
	
	private DummyPIDOutput gyroOutput;

	public enum Shifter {
		High, Low
	}

	public enum Speed {
		Normal, Slow
	}

	private Shifter currGear = Shifter.Low;
	private Speed currSpeed = Speed.Normal;

	public Drive() {
		leftFrontVictor = new Victor(RobotMap.LEFT_FRONT_MOTOR);
		leftBackVictor = new Victor(RobotMap.LEFT_BACK_MOTOR);
		rightFrontVictor = new Victor(RobotMap.RIGHT_FRONT_MOTOR);
		rightBackVictor = new Victor(RobotMap.RIGHT_BACK_MOTOR);

		transmission = new DoubleSolenoid(RobotMap.TRANSMISSION_SOLENOID_A, RobotMap.TRANSMISSION_SOLENOID_B);
		
		leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_A, RobotMap.LEFT_ENCODER_B, false, EncodingType.k4X);
		leftEncoderOutput = new DualPIDOutput(leftFrontVictor, leftBackVictor, true);
		leftEncoderPID = new PIDController(Constants.encoderP, Constants.encoderI, Constants.encoderD, leftEncoder, leftEncoderOutput);
		rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_A, RobotMap.RIGHT_ENCODER_B, false, EncodingType.k4X);
		rightEncoderOutput = new DualPIDOutput(rightFrontVictor, rightBackVictor, false);
		rightEncoderPID = new PIDController(Constants.encoderP, Constants.encoderI, Constants.encoderD, rightEncoder, rightEncoderOutput);

		gyro = new RebelGyro();
		gyro.startThread();
		
		
		robotDrive = new RebelDrive(leftFrontVictor, leftBackVictor, rightFrontVictor, rightBackVictor);
		
		gyroOutput = new DummyPIDOutput();
		gyroPID = new PIDController(Constants.gyroP, Constants.gyroI, Constants.gyroD, gyro, gyroOutput);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void drive(Joystick joystick) {
		double moveValue = 0.8 * joystick.getRawAxis(RobotMap.JOYSTICK_LEFT_Y);
		double rotateValue = 0.75 * joystick.getRawAxis(RobotMap.JOYSTICK_RIGHT_X);
		robotDrive.arcadeDrive(moveValue, rotateValue, true);
	}

	public Speed getCurrSpeed() {
		return currSpeed;
	}

	public void shiftHigh() {
//	if (currGear.equals(Shifter.Low)) {
//		transmission.set(DoubleSolenoid.Value.kForward);
//		currGear = Shifter.High;
//	}
		transmission.set(DoubleSolenoid.Value.kForward);
		currGear = Shifter.High;
	}

	public void shiftLow() {
//		if (currGear.equals(Shifter.High)) {
//			transmission.set(DoubleSolenoid.Value.kReverse);
//			currGear = Shifter.Low;
//		}
		transmission.set(DoubleSolenoid.Value.kReverse);
		currGear = Shifter.Low;
	}
	
	public int getLeftEncoderCount() {
		return leftEncoder.get();
	}
	
	public double getGyroAngle() {
		return gyro.getAngle();
	}
	
	public int getRightEncoderCount () {   
	    return rightEncoder.get();        
	}

	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	/**
	 * Used to find how many encoder ticks per given inches
	 * @param Inches
	 * @return Ticks
	 */
	public double convertInchesToTicks(int inches) {
		return Constants.ticksPerInch * inches;
	}

	public void driveAtSpeed(double speed) {
		robotDrive.drive(speed, 0);
	}
	
	/**
	 * Enables both the Left Encoder PID and the Right Encoder PID
	 * by calling enable() on each
	 */
	public void enableDrivePID() {
		leftEncoderPID.enable();
		rightEncoderPID.enable();
		gyroPID.enable();
	}
	
	/**
	 * Disables both the Left Encoder PID and the Right Encoder PID
	 * by calling disable() on each
	 */
	public void disableDrivePID() {
		leftEncoderPID.disable();
		rightEncoderPID.disable();
		gyroPID.disable();
	}
	
	public void setDrivePIDSetPoint(double setPoint) {
		leftEncoderPID.setSetpoint(setPoint);
		rightEncoderPID.setSetpoint(setPoint);
	}
	
	public void setTurnPID (double setPoint) {
		gyroPID.setSetpoint(setPoint);
	}
	
	public void setMaxDrivePIDOutput(double drivingSpeed, double turningSpeed) {
		leftEncoderPID.setOutputRange(-drivingSpeed, drivingSpeed);
		rightEncoderPID.setOutputRange(-drivingSpeed, drivingSpeed);
		gyroPID.setOutputRange(-turningSpeed, turningSpeed);
	}
	
	
	/*public void goSetDistance() {
		if (leftEncoder.get() >= (ticksToDriveALength)) {
			leftFrontVictor.set(0);
			leftBackVictor.set(0);
			rightFrontVictor.set(0);
			rightBackVictor.set(0);
		}
	}*/
	
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
		 * Return a negated value because the left motors 
		 * are backwards
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
		return gyroPID.get();
	}
	
	public double getGyroPIDError() {
		return gyroPID.getError();
	}
	
	}