package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Collector extends Subsystem{
	private Victor collectMotor = new Victor(RobotMap.COLLECT_MOTOR);
	private Victor internalMotor = new Victor(RobotMap.AGITATOR_MOTOR);
	private boolean internalOn = false;
	
	public Collector () {
		internalOn = false;
	}
	
	
    public void initDefaultCommand() {
    	
    }
    public void collect() {
    	collectMotor.set(0.8);
    }
    public void stopCollecting() {
    	collectMotor.set(0);
    }
    public void internal() {
    	internalMotor.set(0.8);
    }
    public void endInternal() {
    	internalMotor.set(0);
    }
    public void internalToggle() {
    	internalOn = !internalOn;
    }
    public boolean internalOnOrOff() {
    	return internalOn;
    }
}
