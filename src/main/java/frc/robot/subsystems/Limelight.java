package frc.robot.subsystems;

import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

  private NetworkTableInstance networkTableInstance = NetworkTableInstance.getDefault();
  private NetworkTable limelight = networkTableInstance.getTable("limelight-huskies");
  private NetworkTableEntry tv = limelight.getEntry("tv");
  private NetworkTableEntry tx = limelight.getEntry("tx");
  private NetworkTableEntry ta = limelight.getEntry("ta");
  private NetworkTableEntry ty = limelight.getEntry("ty");
  private NetworkTableEntry ledMode = limelight.getEntry("ledMode");
  private NetworkTableEntry stream = limelight.getEntry("stream");

  double hLimelight = 0; //à configurer
  double hCible = 0; //à configurer
  double angleLimelight = 0; //à configurer

  LinearFilter filterDistance = LinearFilter.singlePoleIIR(0.15, 0.02); //filtre pour adoucir les variations

  public Limelight() {
    LedOff();
    stream.setNumber(2); //Pour mettre l'image de la limelight en PiP
  }

  public void LedOn() {
    ledMode.setNumber(3);
  }

  
  public void LedOff() {
    ledMode.setNumber(1);
  }

  public double getDistance() {
    return filterDistance.calculate((hCible-hLimelight)/Math.tan(Math.toRadians(angleLimelight+getTx())));
  }

  public double getTa() {
    return ta.getDouble(0);
  }

  
  public boolean getTv() {
    return tv.getDouble(0) == 1;
  }

  
  public double getTx() {
    return tx.getDouble(0);
  }
  
  public double getTy() {
    return ty.getDouble(0);
  }
  
  @Override
  public void periodic() {
  //SmartDashboard.putNumber("tx", getTx());
  //SmartDashboard.putNumber("ty", getTy());
   SmartDashboard.putNumber("distance limelight", getDistance());
  }
}