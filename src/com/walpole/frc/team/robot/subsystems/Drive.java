
package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.subsystems.Drive;

import com.walpole.frc.team.robot.lib.RebelDrive;
import com.walpole.frc.team.robot.lib.DualPIDOutput;

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
	private Encoder leftEncoder;
	//private Encoder rightEncoder;
	private SpeedController leftFrontVictor;
	private SpeedController leftBackVictor;
	private SpeedController rightFrontVictor;
	private SpeedController rightBackVictor;
	private DoubleSolenoid transmission;
	private AnalogGyro gyro;
	private PIDController leftEncoderPID;
	//private PIDController rightEncoderPID;
	//private PIDController gyroPID;
	private DualPIDOutput leftEncoderOutput;
	//private DualPIDOutput rightEncoderOutput;
	//private DummyPIDOutput gyroOutput;

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
		//rightEncoder = new Encoder(RobotMap.LEFT_ENCODER_A, RobotMap.LEFT_ENCODER_B, false, EncodingType.k4X);  

		gyro = new AnalogGyro(RobotMap.GYRO);
		
		robotDrive = new RebelDrive(leftFrontVictor, leftBackVictor, rightFrontVictor, rightBackVictor);
		
		leftEncoderOutput = new DualPIDOutput(leftFrontVictor, leftBackVictor);
		leftEncoderPID = new PIDController(Constants.encoderP, Constants.encoderI, Constants.encoderD, leftEncoder, leftEncoderOutput);
		//rightEncoderPID = new PIDController(encoderP, encoderI, encoderD, rightEncoder, rightEncoderOutput);
		//gyroPID = new PIDController(Constants.gyroP, Constants.gyroI, Constants.gyroD, gyro, gyroOutput); 

		//rightEncoderOutput = new DummyPIDOutput();
		//gyroOutput = new DummyPIDOutput();
		
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
	
	public double getGyroCount() {
		return gyro.getAngle();
	}
	
	/*public int getRightEncoderCount () {   
	    //return rightEncoder.get();        
	}*/

	public void resetEncoders() {
		leftEncoder.reset();
		//rightEncoder.reset();
	}
	
	public double convertInchesToTicks(int inches) {     //used to find how many encoder ticks per inches
		return Constants.ticksPerInch * inches;
	}
	
	public void driveAtSpeed(double speed) {            //used to convert inches to encoder ticks  
		robotDrive.drive(speed, 0);
	}
	
	public void enablePID() {
		leftEncoderPID.enable();
//		rightEncoderPID.enable() {
//		gyroPID.enable();
	}
	
	public void disablePID() {
		leftEncoderPID.disable();
//		rightEncoderPID.enable() {
//		gyroPID.enable();
	}
	
	public void setDriveEncoderSetPoint(double setPoint) {
		leftEncoderPID.setSetpoint(setPoint);
//		rightEncoderPID.setSetpoint(setPoint);
	}
	
	public void setMaxDrivePIDOutput(double speed) {
		leftEncoderPID.setOutputRange(-speed, speed);
		//rightEncoderPID.setOutputRange(-speed, speed);
	}
	
//	public void goSetDistance() {
//		if (leftEncoder.get() >= (ticksToDriveALength)) {
//			leftFrontVictor.set(0);
//			leftBackVictor.set(0);
//			rightFrontVictor.set(0);
//			rightBackVictor.set(0);
//		}
//	}
	public void stopDrive() {         //used to make the robot stop
		leftFrontVictor.set(0);		
		leftBackVictor.set(0);
		rightFrontVictor.set(0);
		rightBackVictor.set(0);
	}
	
	public double getLeftMotorPower() {
		return -leftBackVictor.get(); 
	}
	
	public double getRightMotorPower() {
		return rightBackVictor.get();
	}
	
	public double getLeftPIDError() {
		return leftEncoderPID.getError();
	}
}
