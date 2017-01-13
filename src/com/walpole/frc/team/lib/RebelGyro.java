package com.walpole.frc.team.lib;

import org.team2168.utils.BNO055;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class RebelGyro implements PIDSource {

	private PIDSourceType m_pidSource;
	private static BNO055 imu;
	private double initialAngle;
	
	public RebelGyro() {
		m_pidSource = PIDSourceType.kDisplacement;
		imu = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER);
	}
	
	public boolean isInitialized() {
		return imu.isInitialized();
	}
	
	public boolean isCalibrated() {
		return imu.isCalibrated();
	}
	
	public boolean isSensorPresant() {
		return imu.isSensorPresent();
	}
	
	public BNO055.CalData getCalibration() {
		return imu.getCalibration();
	}
	
	public double getInitialAngle() {
		return initialAngle;
	}
	
	public void setInitialAngle(double initialAngle) {
		this.initialAngle = initialAngle;
	}

	/**
	 * Gets a Vector representing the sensors position (heading, roll, pitch).
	 * heading:		0 to 360 degrees
	 * roll:	   -90 to +90 degrees
	 * pitch:     -180 to +180 degrees
	 * 
	 * @return a vector [heading, roll, pitch]
	 */
	public double[] getVector() {
		return imu.getVector();
	}
	
	public double getHeading() {
		return getVector() [0];
	}
	
	
	/**
	 * Set which parameter of the encoder you are using as a process control
	 * variable. The encoder class supports the rate and distance parameters.
	 *
	 * @param pidSource An enum to select the parameter.
	 */
	public void setPIDSourceType(PIDSourceType pidSource) {
		m_pidSource = pidSource;
	}

	  /**
	   * {@inheritDoc}
	   */
	  public PIDSourceType getPIDSourceType() {
	    return m_pidSource;
	  }

	@Override
	public double pidGet() {
		return getHeading();
	}
	
	

}