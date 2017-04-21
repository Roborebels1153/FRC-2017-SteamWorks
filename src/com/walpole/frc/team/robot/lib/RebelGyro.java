package com.walpole.frc.team.robot.lib;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class RebelGyro extends SPIGyro implements PIDSource {

    private PIDSourceType pidSource;

    public RebelGyro() {
	super();
	pidSource = PIDSourceType.kDisplacement;
    }

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

    @Override
    public double getAngle() {
	return super.getAngle() % 360;
    }

}