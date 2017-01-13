package org.usfirst.frc.team1153.robot.subsystems;

import org.usfirst.frc.team1153.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Collector extends Subsystem{
	private Victor collectMotor;
	
	
    public void initDefaultCommand() {
    	collectMotor = new Victor(RobotMap.COLLECT_MOTOR);
    }
    public void collect() {
    	collectMotor.set(1);
    }
    public void stopCollecting() {
    	collectMotor.set(0);
    }
}
