//
//
//package com.walpole.frc.team.robot;
//
//import edu.wpi.first.wpilibj.Counter;
//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.PIDSource;
//import edu.wpi.first.wpilibj.PIDSourceType;
//
///**
// *
// */
//public class CountRPM implements PIDSource {
//	
//	private DigitalInput lightSensor;
//	private Counter shooterCounter;
//	private PIDSourceType m_pidSource;	
//
//    public CountRPM() {
//    	lightSensor = new DigitalInput(RobotMap.LIGHT_SENSOR);
//    	shooterCounter = new Counter(lightSensor);
////    	shooterCounter.setUpSource(lightSensor);
////    	shooterCounter.setUpDownCounterMode();
//    	shooterCounter.setDistancePerPulse(1);
//        m_pidSource = PIDSourceType.kRate;
//
//   }
//
//	public double getRate() {
//		return shooterCounter.getRate()*  60;
//	}
//
//	public boolean getLightSensor() {
//		return !lightSensor.get();
//	}			
//	public Counter getCounter() {
//		return shooterCounter;
//	}
//
//	@Override
//	public void setPIDSourceType(PIDSourceType pidSource) {
//		m_pidSource = pidSource;
//	}
//
//	@Override
//	public PIDSourceType getPIDSourceType() {
//	    return m_pidSource;
//	}
//
//	@Override
//	public double pidGet() {
//		 switch (m_pidSource) {
//	      case kRate:
//	        return getRate();
//	      default:
//	        return 0.0;
//	    }
//	}
//}