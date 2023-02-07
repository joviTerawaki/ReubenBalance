package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class BalanceCommandGroup extends CommandBase{
    
    // SUBSYSTEM
    private final Drive drive;

    // PID
    private final PIDController pid = new PIDController(0.3, 0.0017, 0.028);

    // CLASS CONSTRUCTOR
    public BalanceCommandGroup(Drive drive){
        this.drive = drive;

        addRequirements(drive);
    }

    public double calculateP(){
        double error;
        if (drive.getRoll() < 2.5 && -3.5 < drive.getRoll()){
            error = 0;
        }
        else{
            error = pid.calculate(drive.getRoll(), 0);
        }

        if (error > 0.6){
            return 0.6;
        }
        else if (error < -0.6){
            return -0.6;
        }
        else{
            return error + 0.4;
        }
    }

    public void controlI(){
        double currentPosition = pid.getPositionError();
        double errorPosition = 0;

        if (currentPosition > 0 && errorPosition < 0){
            pid.reset();
        }
        else if (currentPosition < 0 && errorPosition > 0){
            pid.reset();
        }

        errorPosition = pid.getPositionError();

        SmartDashboard.putNumber("error position", errorPosition);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){

        drive.tank(0.95 * calculateP(), calculateP());
        SmartDashboard.putNumber("Output Speed", calculateP());
        SmartDashboard.putNumber("Motor Speed", drive.getSpeed());

    }

    @Override 
    public void end(boolean interrupted){
        drive.tank(0, 0);
    }

    @Override
    public boolean isFinished(){
        return false; 
    }

}
