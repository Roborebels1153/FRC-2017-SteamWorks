
package com.walpole.frc.team.robot;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class CountRPM {
	
	private DigitalInput lightSensor;
	private Counter shooterCounter;
//	public ArrayList<Long> rotationTimeList = new ArrayList<Long>();
	

    public CountRPM() {
    	lightSensor = new DigitalInput(RobotMap.LIGHT_SENSOR);
    	shooterCounter = new Counter();
    	shooterCounter.setUpSource(lightSensor);
    	shooterCounter.setUpDownCounterMode();
    	shooterCounter.setDistancePerPulse(1);

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

	public double getShooterRate() {
		return shooterCounter.getRate();
//		shooterCounter.reset();
	}

	public boolean getLightSensor() {
		return !lightSensor.get();
	}			
	public Counter getCounter() {
		return shooterCounter;
	}
}