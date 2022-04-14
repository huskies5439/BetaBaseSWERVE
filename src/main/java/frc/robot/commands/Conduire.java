package frc.robot.commands;

import frc.robot.subsystems.BasePilotable;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Conduire extends CommandBase {
  BasePilotable basePilotable;

  public Conduire(BasePilotable basePilotable) {
    this.basePilotable = basePilotable;
    addRequirements(basePilotable);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {return false;}
}
