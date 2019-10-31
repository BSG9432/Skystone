package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

@Autonomous (name = "BackupRedBuilding")
public class BackupRedBuilding extends LinearOpMode {
    Robot bsgRobot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {
        bsgRobot.init(hardwareMap);


        waitForStart();

    }
}
