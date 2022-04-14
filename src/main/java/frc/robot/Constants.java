package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

public final class Constants {
    //Ramsete
    public static final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(0.63); 
    public static final double kSRamsete = 0; //0 à configurer 
    public static final double kVRamsete = 0; //0 à configurer
    public static final double kARamsete = 0; //0 à configurer

    public static final double kPRamsete = 0; //0 à configurer

    //Teleop
    public static final double kRampTeleOp  = 0; //0 à configurer

    //Alignement limelight
    public static final double kToleranceRotation = 3; //à configurer

}
