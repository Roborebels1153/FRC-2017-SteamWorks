package com.walpole.frc.team.robot.subsystems;

import java.util.Arrays;

import com.walpole.frc.team.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Vision {
    
    
	public NetworkTable table; 
//			.getTable("GRIP/myContoursReport");

    public Vision(NetworkTable table) {
		
		this.table = table;
		updateVisionDashboard();
    }
    
    public void updateVisionDashboard() {
  
    	}
//    	Timer.delay(1);
    
    
    private String arrayToString(double[] array) {
    	String string = "[";
    	
    	for (double number : array) {
    		string += String.valueOf(number);
    		string += ", ";
    	}
    	
    	string += "]";
    	
    	return string;
    }
    
   

}

