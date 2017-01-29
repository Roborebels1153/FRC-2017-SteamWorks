package com.walpole.frc.team.robot.lib;

import org.team2168.utils.BNO055;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class RebelGyro extends SPIGyro implements PIDSource {
	
	private PIDSourceType pidSource;

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.pidSource = pidSource; 
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidSource;
	}

	@Override
	public double pidGet() {
		return getAngle();
	}

	
	
	

}