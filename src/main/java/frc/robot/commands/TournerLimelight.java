package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BasePilotable;
import frc.robot.subsystems.Limelight;

public class TournerLimelight extends CommandBase {
  BasePilotable basePilotable;
  Limelight limelight;
  double voltage;

  public TournerLimelight(BasePilotable basePilotable, Limelight limelight) {
    this.basePilotable = basePilotable;
    this.limelight = limelight;
    addRequirements(basePilotable);
    addRequirements(limelight);
  }

  @Override
  public void initialize() {
    limelight.LedOn();
    //basePilotable.setBrake(true); //Pour ne pas se faire pousser
  }

  @Override
  public void execute() {

    if (limelight.getTv()) {
      /*voltage = basePilotable.getVoltagePIDF(0, limelight::getTx);
      basePilotable.autoConduire(-voltage, voltage);*/
    }

    else {
      //basePilotable.stop;
    }
  }

  @Override
  public void end(boolean interrupted) {
    //basePilotable.stop;
    limelight.LedOff();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
