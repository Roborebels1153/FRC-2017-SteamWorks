
package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.subsystems.Drive;

import com.walpole.frc.team.lib.RebelDrive;
import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {

	private RobotDrive robotDrive;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private SpeedController leftFrontVictor;
	private SpeedController leftBackVictor;
	private SpeedController rightFrontVictor;
	private SpeedController rightBackVictor;
	private DoubleSolenoid transmission;

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

		robotDrive = new RebelDrive(leftFrontVictor, leftBackVictor, rightFrontVictor, rightBackVictor);
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

}
