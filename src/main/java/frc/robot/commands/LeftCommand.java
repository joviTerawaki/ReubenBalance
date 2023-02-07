package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConsts;
import frc.robot.subsystems.Drive;

public class LeftCommand extends CommandBase{
    private Drive drive; 
    private double desiredAngle; 

    public LeftCommand(Drive newDrive, double newDesiredAngle) {
        drive = newDrive; 
        desiredAngle = drive.getYaw() - newDesiredAngle; 

        addRequirements(drive);
    }

    @Override
    public void initialize() {}
  
    @Override
    public void execute() {
        drive.tank(DriveConsts.backwardSpeed, DriveConsts.forwardSpeed);
    }
      @Override
    public void end(boolean interrupted) {
        drive.tank(0, 0);
    }
  
    @Override
    public boolean isFinished() {
        //ends when yaw returns a value within a 5-degree range of desired angle 
      return (drive.getYaw() <= desiredAngle-2) && (drive.getYaw() >= desiredAngle+2); 
    }
  
}
