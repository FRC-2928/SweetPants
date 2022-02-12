// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class TriggerShootBall extends CommandBase {
  
  Intake m_intake;
  private int m_counter = 0;

  /** Creates a new CheckAndShootBall. */
  public TriggerShootBall(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
    m_intake = intake;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_intake.readyToShoot()) {
      // Override all brakes
      System.out.println("Ready to shoot...");
      m_intake.setFeederBrakeDisabled();
      m_intake.setIntakeBrakeDisabled();

      // Start feeder motor at high power
      m_intake.startFeederMotor(0.8);
      m_counter = 0;
    } else {
      m_counter = 99;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // to check: there is a ball (of right color), ramp is fully closed
    m_counter =+ 1;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.setFeederBrakeEnabled();
    m_intake.startFeederMotor(0.2);
    m_intake.startIntakeMotor(0.2);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_intake.intakeCleared() && m_intake.feederCleared();
  }
}
