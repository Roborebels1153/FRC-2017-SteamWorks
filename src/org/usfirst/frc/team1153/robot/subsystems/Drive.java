
package org.usfirst.frc.team1153.robot.subsystems;

import org.usfirst.frc.team1153.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {
	
	private RobotDrive robotDrive; 
	private Encoder leftEncoder; 
	private Encoder rightEncoder;
	private Victor leftFront;
	private Victor leftBack;
	private Victor rightFront;
	private Victor rightBack;
	
	public Drive() {
		leftFront = new Victor(RobotMap.LEFT_FRONT_MOTOR);
		leftBack = new Victor(RobotMap.LEFT_BACK_MOTOR);
		rightFront = new Victor (RobotMap.RIGHT_FRONT_MOTOR);
		rightBack = new Victor (RobotMap.RIGHT_BACK_MOTOR);
		
		robotDrive = new RobotDrive(leftFront, leftBack, rightFront, rightBack);
		
	}
	
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

