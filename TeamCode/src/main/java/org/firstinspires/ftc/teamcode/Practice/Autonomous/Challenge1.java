package org.firstinspires.ftc.teamcode.Practice.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
@Autonomous (name = "Challenge1")
public class Challenge1 extends LinearOpMode{


    Robot bsgRobot = new Robot();



    @Override
    public void runOpMode() throws InterruptedException {
        bsgRobot.init(hardwareMap);;

    waitForStart();
    //forward at .25 speed for 3 seconds
        bsgRobot.strafeRight(1000);
        sleep(500);
        bsgRobot.strafeLeft(1500);

        bsgRobot.frontLeft.setPower(-1);
        bsgRobot. backLeft.setPower(-1);
        bsgRobot.frontRight.setPower(-1);
        bsgRobot.backRight.setPower(-1);
        sleep(1500);


        bsgRobot.rightFoundation.setPosition(1);
        bsgRobot.leftFoundation.setPosition(0);


    }


}
