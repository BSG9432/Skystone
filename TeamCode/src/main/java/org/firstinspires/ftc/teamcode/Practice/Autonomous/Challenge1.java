package org.firstinspires.ftc.teamcode.Practice.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.Robot;
@Autonomous (name = "Challenge1")
public class Challenge1 extends LinearOpMode{


    Robot bsgRobot = new Robot();



    @Override
    public void runOpMode() throws InterruptedException {
        bsgRobot.init(hardwareMap);;

    waitForStart();
    //forward at .25 speed for 3 seconds
        bsgRobot.frontLeft.setPower(-.25);
        bsgRobot. backLeft.setPower(-.25);
        bsgRobot.frontRight.setPower(.25);
        bsgRobot.backRight.setPower(.25);
   sleep(1500);





    }


}
