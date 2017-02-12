
package com.walpole.frc.team.robot;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 *
 */
public class CountRPM implements PIDSource {
	
	private DigitalInput lightSensor;
	private Counter shooterCounter;
	private PIDSourceType m_pidSource;

//	public ArrayList<Long> rotationTimeList = new ArrayList<Long>();
	

    public CountRPM() {
    	lightSensor = new DigitalInput(RobotMap.LIGHT_SENSOR);
    	shooterCounter = new Counter();
    	shooterCounter.setUpSource(lightSensor);
    	shooterCounter.setUpDownCounterMode();
    	shooterCounter.setDistancePerPulse(1);
        m_pidSource = PIDSourceType.kRate;

   }
//
//    public void check() {        	
//    	if(!lightSensor.get()) {
//    		rotationTimeList.add(System.currentTimeMillis());
//    	}
//    }
//    
//    public int getRPS() {
//    	long currentTime = System.currentTimeMillis();
//    	if (rotationTimeList.size() > 0) {
//	    	for(int i = 0 ; i < rotationTimeList.size(); i++) {
//	    		if ((currentTime - rotationTimeList.get(i)) >= 1000) {
//	    			rotationTimeList.remove(i);
//	    		}
//	    	}
//    	}
//    	return rotationTimeList.size();
//    }

	public double getRate() {
		return shooterCounter.getRate()*60;
//		shooterCounter.reset();
	}

	public boolean getLightSensor() {
		return !lightSensor.get();
	}			
	public Counter getCounter() {
		return shooterCounter;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		m_pidSource = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
	    return m_pidSource;
	}

	@Override
	public double pidGet() {
		 switch (m_pidSource) {
	      case kRate:
	        return getRate();
	      default:
	        return 0.0;
	    }
	}
}
