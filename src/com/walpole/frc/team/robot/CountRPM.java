
package com.walpole.frc.team.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 */
public class CountRPM {
	
	private DigitalInput lightSensor;
	private double timer = 0;
	public ArrayList<Long> rotationTimeList = new ArrayList<Long>();

    public CountRPM() {
		lightSensor = new DigitalInput(RobotMap.LIGHT_SENSOR);

    }

    public void check() {        	
    	if(!lightSensor.get()) {
    		rotationTimeList.add(System.currentTimeMillis());
    	}
    }
    
    public int getRPS() {
    	long currentTime = System.currentTimeMillis();
    	if (rotationTimeList.size() > 0) {
	    	for(int i = 0 ; i < rotationTimeList.size(); i++) {
	    		if ((currentTime - rotationTimeList.get(i)) >= 1000) {
	    			rotationTimeList.remove(i);
	    		}
	    	}
    	}
    	return rotationTimeList.size();
    }

	public boolean getLightSensor() {
		return !lightSensor.get();
	}
	
	public double getTimer() {
		return timer;
	}

	public void setTimer(double timer) {
		this.timer = timer;
	}				
	public ArrayList<Long> getRotTimeList() {
		return rotationTimeList;
	}
}
