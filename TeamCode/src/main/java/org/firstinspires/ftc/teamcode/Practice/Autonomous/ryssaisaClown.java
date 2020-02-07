package org.firstinspires.ftc.teamcode.Practice.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
@Autonomous (name = "ryssaisaClown")
public class ryssaisaClown extends LinearOpMode {


    Robot bsgRobot = new Robot();


    @Override
    public void runOpMode() throws InterruptedException {
        bsgRobot.init(hardwareMap);


        waitForStart();
        //forward at .25 speed for 3 seconds
        bsgRobot.strafeRight(1000);
        sleep(500);
        bsgRobot.strafeLeft(1500);



        bsgRobot.frontLeft.setPower(-.5);
        bsgRobot.backLeft.setPower(-.5);
        bsgRobot.frontRight.setPower(-.5);
        bsgRobot.backRight.setPower(-.5);
        sleep(750);

        bsgRobot.foundationDown();
        sleep(500);
        bsgRobot.moveForward(.5);
        sleep(2000);
        bsgRobot.foundationUp();
        sleep(500);
        bsgRobot.strafeLeft(1000);
        sleep(2000);




    }
}