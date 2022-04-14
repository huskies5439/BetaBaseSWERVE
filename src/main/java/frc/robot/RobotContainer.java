package frc.robot;


import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Conduire;
import frc.robot.subsystems.BasePilotable;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  private final BasePilotable basePilotable = new BasePilotable();
  private final Limelight limelight = new Limelight();

  XboxController pilote = new XboxController(0);

  //Trajets
  private final SendableChooser<Command> chooser = new SendableChooser<>();
  private final Command trajetVide = new WaitCommand(14);
  //(example)private final Command AutoTest = new AutoTest(basePilotable, limelight);

    /*public class LeftTrigger extends Trigger {
    @Override
    public boolean get() {
      return pilote.getLeftTriggerAxis()>0.9;
    }
  }*/
  
  /*public class RightTrigger extends Trigger {
    @Override
    public boolean get() {
      return pilote.getRightTriggerAxis()>0.9;
    }
  }*/

  public RobotContainer() {
    //Chooser
    SmartDashboard.putData(chooser);
    chooser.setDefaultOption("Trajet Vide", trajetVide);
    //(example)chooser.addOption("Trajet Test", AutoTest);


    configureButtonBindings();
    //basePilotable.setDefaultCommand(new Conduire(pilote::getLeftY, pilote::getRightX, basePilotable));
  }

///////////////////////////////////////////////////////Buttons Bindings//////////////////////////////////////////////////////////////////////
  private void configureButtonBindings() {

    //A =
    //new JoystickButton(pilote, Button.kA.value).toggleWhenPressed(new /*Mettre Commande ICI*/);

    //B =
    //new JoystickButton(pilote, Button.kB.value).toggleWhenPressed(new /*Mettre Commande ICI*/);

    //X =
    //new JoystickButton(pilote, Button.kX.value).toggleWhenPressed(new /*Mettre Commande ICI*/);

    //Y =
    //new JoystickButton(pilote, Button.kA.value).toggleWhenPressed(new /*Mettre Commande ICI*/);

    //Trigger gauche =
    //new LeftTrigger().whenActiveContinuous(new /*Mettre Commande ICI*);

    //Trigger droit =
    //new RightTrigger().whenActiveContinuous(new /*Mettre Commande ICI*);

    //POV Button 
    //new POVButton(pilote, 0).toggleWhenPressed(new /*Mettre Commande ICI*/);
    //new POVButton(pilote, 90).toggleWhenPressed(new /*Mettre Commande ICI*/);
    //new POVButton(pilote, 180).toggleWhenPressed(new /*Mettre Commande ICI*/);
    //new POVButton(pilote, 360).toggleWhenPressed(new /*Mettre Commande ICI*/);
  }

///////////////////////////////////////////////////////Autonomous Command///////////////////////////////////////////////////////////////
  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}