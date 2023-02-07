package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import frc.robot.Constants.DriveConsts;

public class BackwardCommand extends CommandBase{
    private Drive drive; 
    private double desiredEnc; 

    public BackwardCommand(Drive newDrive, double newDesiredEnc) {
        drive = newDrive; 
        desiredEnc = newDesiredEnc; 

        addRequirements(drive);
    }

    @Override
    public void initialize() {
        drive.resetEnc();
    }
  
    @Override
    public void execute() {
        drive.tank(DriveConsts.backwardSpeed, DriveConsts.backwardSpeed);
    }
  
    @Override
    public void end(boolean interrupted) {
        drive.tank(0, 0);
    }
  
    @Override
    public boolean isFinished() {
      return drive.getEnc() <= -desiredEnc; 
    }
}
