package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

@Autonomous (name = "BackupBlueBuilding")
public class BackupBlueBuilding extends LinearOpMode {
    Robot bsgRobot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {
        bsgRobot.init(hardwareMap);

        waitForStart();
        goForwards(.75,2000);

    }
    public void goForwards(double power, int sleep){
        bsgRobot.frontLeft.setPower(-power);
        bsgRobot.backLeft.setPower(-power);
        bsgRobot.frontRight.setPower(power);
        bsgRobot.backRight.setPower(power);
        sleep(sleep);
    }
}

