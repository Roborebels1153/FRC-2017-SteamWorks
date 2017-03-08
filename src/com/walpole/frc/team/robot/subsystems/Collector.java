package com.walpole.frc.team.robot.subsystems;

import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Collector extends Subsystem{
	//private Victor collectMotor = new Victor(RobotMap.COLLECT_MOTOR);
	private Victor internalMotor = new Victor(RobotMap.INTERNAL_MOTOR);
	private Relay collector = new Relay(RobotMap.COLLECTOR);
	
	public Collector () {
	}
	
	
    public void initDefaultCommand() {
    	
    }
    public void collect() {
    	//collectMotor.set(1);
    	collector.set(Value.kForward);
    }
    public void stopCollecting() {
    	//collectMotor.set(0);
    	collector.set(Value.kOff);
    }
    public void internal() {
    	internalMotor.set(0.8);
    }
    public void endInternal() {
    	internalMotor.set(0);
    }
}
