package org.firstinspires.ftc.teamcode.Autonomous.AckWaitTimePrograms;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
@Autonomous (name = "BLUEryssaisaclown")
public class BLUEryssaisaclown extends LinearOpMode {


    Robot bsgRobot = new Robot();


    @Override
    public void runOpMode() throws InterruptedException {
        bsgRobot.init(hardwareMap);


        waitForStart();

        bsgRobot.strafeLeft(1000);
        sleep(500);

        //move backwards
        bsgRobot.moveForward(-.5);
        sleep(500);

        bsgRobot.foundationDown();
        sleep(2000);

        bsgRobot.stopWheels();
        sleep(500);

        //moveforward
        bsgRobot.moveForward(.5);
        sleep(500);

        bsgRobot.foundationUp();
        sleep(2000);

        bsgRobot.strafeRight(1000);
        sleep(2500);


    }
}