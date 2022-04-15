package frc.robot.subsystems;

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

  public BasePilotable() {
    //Resets initiaux
    //resetEncodeur();
    resetGyro();

    //Ramp et brake
    //setRamp(Constants.kRampTeleOp);
    //setBrake(false);
    
    var frontLeftState = new SwerveModuleState(0, Rotation2d.fromDegrees(0));
    var frontRightState = new SwerveModuleState(0, Rotation2d.fromDegrees(0));
    var backLeftState = new SwerveModuleState(0, Rotation2d.fromDegrees(0));
    var backRightState = new SwerveModuleState(0, Rotation2d.fromDegrees(0));

    ChassisSpeeds chassisSpeeds = kinematics.toChassisSpeeds(
      frontLeftState, frontRightState, backLeftState, backRightState
    );
    
    double forward = chassisSpeeds.vxMetersPerSecond;
    double sideways = chassisSpeeds.vyMetersPerSecond;
    double angular = chassisSpeeds.omegaRadiansPerSecond;

    //Optimisation
    var frontLeftOptimized = SwerveModuleState.optimize(frontLeftState, new Rotation2d(/*encodeur pour angle.getDistance*/getAngle/*c pas le bon*/()));
    var frontRightOptimized = SwerveModuleState.optimize(frontRightState, new Rotation2d(/*encodeur pour angle.getDistance*/getAngle/*c pas le bon*/()));
    var backLeftOptimized = SwerveModuleState.optimize(backLeftState, new Rotation2d(/*encodeur pour angle.getDistance*/getAngle/*c pas le bon*/()));
    var backRightOptimized = SwerveModuleState.optimize(backRightState, new Rotation2d(/*encodeur pour angle.getDistance*/getAngle/*c pas le bon*/()));
  }

  Translation2d frontLeftLocation = new Translation2d(0, 0);// mettre les mesures depuis le centre du robot
  Translation2d frontRightLocation = new Translation2d(0, 0);// mettre les mesures depuis le centre du robot
  Translation2d backLeftLocation = new Translation2d(0, 0);// mettre les mesures depuis le centre du robot
  Translation2d backRightLocation = new Translation2d(0, 0);// mettre les mesures depuis le centre du robot

  SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
    frontLeftLocation, frontRightLocation, backLeftLocation, backRightLocation
  );

  SwerveDriveOdometry odometry = new SwerveDriveOdometry(kinematics, gyro.getRotation2d()/*Pas sur*/, new Pose2d(5.0, 13.5, new Rotation2d()));

  ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(2.0, 2.0, Math.PI/2.0, Rotation2d.fromDegrees(45.0));

  SwerveModuleState[] moduleStates = kinematics.toSwerveModuleStates(speeds);

  SwerveModuleState frontLeftState = moduleStates[0];

  SwerveModuleState frontRightState = moduleStates[1];

  SwerveModuleState backLeftState = moduleStates[2];

  SwerveModuleState backRightState = moduleStates[3];

  @Override
  public void periodic() {
    var gyroAngle = Rotation2d.fromDegrees(-getAngle());

    Pose2d pose = odometry.update(gyroAngle, frontLeftState/*.getState*/);
  }

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

  public Pose2d getPose() {
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
  }

}