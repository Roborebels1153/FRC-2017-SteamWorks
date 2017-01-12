
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
	private Victor  leftFrontVictor;
	private Victor  leftBackVictor;
	private Victor  rightFrontVictor;
	private Victor  rightBackVictor;
	
	public Drive() {
		leftFrontVictor = new Victor(RobotMap.LEFT_FRONT_MOTOR);
		leftBackVictor = new Victor(RobotMap.LEFT_FRONT_MOTOR);
		rightFrontVictor = new Victor(RobotMap.RIGHT_FRONT_MOTOR);
		rightBackVictor = new Victor(RobotMap.RIGHT_BACK_MOTOR);
		
		
		robotDrive = new RobotDrive(leftFrontVictor, leftBackVictor, rightFrontVictor, rightBackVictor);
				
	}
	
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

