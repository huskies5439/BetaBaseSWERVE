package frc.robot.subsystems;

import edu.wpi.first.cscore.VideoEvent.Kind;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BasePilotable extends SubsystemBase {

  private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  
  private SwerveDriveOdometry odometry;

  public BasePilotable() {
    //Resets initiaux
    //resetEncodeur();
    resetGyro();

    //Ramp et brake
    //setRamp(Constants.kRampTeleOp);
    //setBrake(false);
    
    Translation2d frontLeftLocation = new Translation2d(0, 0);// mettre les mesures depuis le centre du robot
    Translation2d frontRightLocation = new Translation2d(0, 0);// mettre les mesures depuis le centre du robot
    Translation2d backLeftLocation = new Translation2d(0, 0);// mettre les mesures depuis le centre du robot
    Translation2d backRightLocation = new Translation2d(0, 0);// mettre les mesures depuis le centre du robot

    SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
      frontLeftLocation, frontRightLocation, backLeftLocation, backRightLocation
    );

    ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(2.0, 2.0, Math.PI/2.0, Rotation2d.fromDegrees(45.0));

    SwerveModuleState[] moduleStates = kinematics.toSwerveModuleStates(speeds);

    SwerveModuleState frontLeft = moduleStates[0];

    SwerveModuleState frontRight = moduleStates[1];

    SwerveModuleState backLeft = moduleStates[2];

    SwerveModuleState backRight = moduleStates[3];

    //Optimisation
    //var frontLeftOptimized = SwerveModuleState.optimize(frontLeft, new Rotation2d(/*encodeur pour angle*/.getDistance()));
    //var frontRightOptimized = SwerveModuleState.optimize(frontRight, new Rotation2d(/*encodeur pour angle*/.getDistance()));
    //var backLeftOptimized = SwerveModuleState.optimize(backLeft, new Rotation2d(/*encodeur pour angle*/.getDistance()));
    //var backRightOptimized = SwerveModuleState.optimize(backRight, new Rotation2d(/*encodeur pour angle*/.getDistance()));
  }

  @Override
  public void periodic() {}

  @Override
  public void simulationPeriodic() {
    
  }

  ////////////////////////////////////////Gyro//////////////////////////////////////////////////////////

  public double getAngle() {
    return -gyro.getAngle();
  }

  public double getAngleSpeed() {
    return -gyro.getRate();
  } 

  public void resetGyro() {
    gyro.reset();
  } 
 
 
 

//////////////////////////////////////// Pose et Odométrie/////////////////////////////////////////////

  /*public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  public double[] getOdometry() {
    double[] position = new double[3];
    double x = getPose().getTranslation().getX();
    double y = getPose().getTranslation().getY();
    double theta = getPose().getRotation().getDegrees();
    position[0] = x;
    position[1] = y;
    position[2] = theta;
    return position;
  }

  public void resetOdometry(Pose2d pose) {
    //resetEncodeur();
    resetGyro();
    odometry.resetPosition(pose, Rotation2d.fromDegrees(getAngle()));
  }*/

}