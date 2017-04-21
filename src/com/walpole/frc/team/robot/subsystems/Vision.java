package com.walpole.frc.team.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Parity;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.SerialPort.StopBits;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Vision extends Subsystem {
	
	public SerialPort arduinoSerial;
	public String outputString = new String("no target detected");
	public String arduinoString;
	
	private int targetCenter;
	private double distanceFromCenter;
	private double angle;
	
	private boolean targetCenterCheck;
	private boolean distanceFromCenterCheck;
	private boolean angleCheck;

   public Vision() {
	   
		arduinoSerial = new SerialPort(115200,Port.kUSB,8,Parity.kNone,StopBits.kOne);

		arduinoString = arduinoSerial.readString();
		if (!arduinoString.equalsIgnoreCase("")) {
			// process the output
			
			String[] outputArray = arduinoString.trim().split(",");
			int number_targets = Integer.parseInt(outputArray[0]);
			int target1_x = Integer.parseInt(outputArray[1]);
			int target1_y = Integer.parseInt(outputArray[2]);
			int target1_width = Integer.parseInt(outputArray[3]);
			int target1_height = Integer.parseInt(outputArray[4]);
			int target2_x = 0;
			int target2_y = 0;
			int target2_width = 0;
			int target2_height = 0;
			int target_center = 0;
			
			outputString = "target1: " + String.valueOf(target1_x) + "," + String.valueOf(target1_y);
			
			if ((number_targets >= 2) && (outputArray.length == 9)) {
				
				/*
				  for (int c = 5; c < outputArray.length; c++) {
				 
					outputString += "[" + c + "]:" + outputArray[c];
				}
				*/
				
				target2_x = Integer.parseInt(outputArray[5]);
				target2_y = Integer.parseInt(outputArray[6]);
				target2_width = Integer.parseInt(outputArray[7]);
				target2_height = Integer.parseInt(outputArray[8]);
				
				// estimate distance based on average height
				int avg_height = (int) (target1_height + target2_height)/2;
				
				int standard_z_distance = 34;
				int min_avg_height = 25;
				int height_at_std_z = 35;
				int pixy_x_center = 160;
				// at a distance of 34", 1 inch = 4 pixels
				int std_y_pixels = 4;
				
				// distance in inches
				// height = 35 at a distance of 34"
				int z_distance = 0;
				// ratio of actual z_distance / 34"
				double z_distance_ratio = 0.0;
				
				// filter - potentially misleading data
				// depending on tuning of pixycam, it may see the vision targets as multiple small 
				// targets
				if (avg_height > min_avg_height) {
					z_distance = (standard_z_distance * height_at_std_z)/avg_height;
					z_distance_ratio = (double)z_distance/standard_z_distance;
					
					targetCenter = (target1_x + target2_x)/2;
					
					distanceFromCenter = (pixy_x_center - target_center)/(std_y_pixels*z_distance_ratio);
					
					angle = Math.toDegrees(Math.atan2(distanceFromCenter,z_distance));
					
					outputString += " target2: " + String.valueOf(target2_x) + "," + String.valueOf(target2_y) + " center: " 
							+ String.valueOf(target_center);
					outputString += " distance: " + z_distance;
					outputString += " angle: " + String.format("%.2f", angle);
				}
			}
			
				
			
			//outputString = " : " + arduinoString;
		} 
		SmartDashboard.putString("Arduino Output", arduinoString);
		SmartDashboard.putString("Last Pixy Output", outputString);
   }


   /** Basic "Getter Method" for the Calculated Angle 
    * The Logic behind the If-Then Statements is that if by chance the PixyCam is damaged or is not responding, 
    * then the System will put out basic information (returns 0) and notify the SmartDashboard that something is not functioning properly 
    * 
    * @return Boolean Indicator for presence of variable and the variable's definition (or lack thereof if not found).
   */ 
   
   public double getVisionAngle() {
	   
	   if (!arduinoString.equalsIgnoreCase("")) {
		   
		   angleCheck = true;
		   return angle;
		   
	   } else {
		   
		   angleCheck = false;
		   return 0.0;
		   
	   }
   }
   
   /** Basic "Getter Method" for the Vision Target's Center 
    * The Logic behind the If-Then Statements is that if by chance the PixyCam is damaged or is not responding, 
    * then the System will put out basic information (returns 0) and notify the SmartDashboard that something is not functioning properly 
    * 
    * @return Boolean Indicator for presence of variable and the variable's definition (or lack thereof if not found).
   */ 
   public int getTargetCenter() {
	   
	   if (!arduinoString.equalsIgnoreCase("")) {
		   
		   targetCenterCheck = true;
		   return targetCenter;
		   
	   } else {
		   
		   targetCenterCheck = false;
		   return 0;
		   
	   } 
   }
   
   /** Basic "Getter Method" for the Distance from the PixyCam to the Target's Center
    *  The Logic behind the If-Then Statements is that if by chance the PixyCam is damaged or is not responding, 
    *  then the System will put out basic information (returns 0) and notify the SmartDashboard that something is not functioning properly 
    *  
    *  @return Boolean Indicator defined for presence of variable and the variable's definition (or lack thereof if not found).
   */ 
   public double getDistanceFromCenter() {
	   
	   if (!arduinoString.equalsIgnoreCase("")) {
		   
		   distanceFromCenterCheck = true;
		   return distanceFromCenter;
		   
	   } else {
		   
		   distanceFromCenterCheck = false;
		   return 0.0;
		   
	   }
   }
   
   /** Basic getter for the status' of the 3 variable checkers"
    * 
    * @return The Boolean Value for the variable
    */
   public boolean checkVisionAngle() {
	   
	   return angleCheck;
	   
   }
   
   /** Basic getter for the status' of the 3 variable checkers"
    * 
    * @return The Boolean Value for the variable
    */
   public boolean checkTargetCenter() {
	   
	   return targetCenterCheck;
	   
   }
   
   /** Basic getter for the status' of the 3 variable checkers"
    * 
    * @return The Boolean Value for the variable
    */
   public boolean checkDistanceFromCenter() {
	   
	   return distanceFromCenterCheck;
	   
   }
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

