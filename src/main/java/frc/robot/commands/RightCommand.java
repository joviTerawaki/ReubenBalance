package frc.robot.commands;

import java.nio.channels.GatheringByteChannel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import frc.robot.Constants.DriveConsts;

public class RightCommand extends CommandBase{
    private Drive drive; 
    private double desiredAngle; 

    public RightCommand(Drive newDrive, double newDesiredAngle) {
        drive = newDrive; 
        desiredAngle = drive.getYaw() + newDesiredAngle; 

        addRequirements(drive);
    }

    @Override
    public void initialize() {}
  
    @Override
    public void execute() {
        drive.tank(DriveConsts.forwardSpeed, DriveConsts.backwardSpeed);
    }
      @Override
    public void end(boolean interrupted) {
        drive.tank(0, 0);
    }
  
    @Override
    public boolean isFinished() {
        //ends when yaw returns a value within a 5-degree range of desired angle 
      return (drive.getYaw() >= desiredAngle-2) && (drive.getYaw() <= desiredAngle+2); 
    }

}
