package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Collector extends Subsystem{
	private Victor collectMotor;
	
	public Collector () {
		//collectMotor = new Victor(RobotMap.COLLECT_MOTOR);  Aayush and Brigham: we are commenting this out because it was not functioning properly (2/21)
	}
	
	
    public void initDefaultCommand() {
    	
    }
    public void collect() {
    	//collectMotor.set(1);
    }
    public void stopCollecting() {
    	//collectMotor.set(0);
    }
}
