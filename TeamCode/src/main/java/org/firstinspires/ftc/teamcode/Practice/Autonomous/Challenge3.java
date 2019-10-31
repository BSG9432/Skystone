package org.firstinspires.ftc.teamcode.Practice.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@Autonomous(name = "Challenge3")

public class Challenge3 extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    @Override
    public void runOpMode() throws InterruptedException {
    frontLeft = hardwareMap.dcMotor.get("frontLeft");
    frontRight = hardwareMap.dcMotor.get("frontRight");
    backLeft = hardwareMap.dcMotor.get("backLeft");
    backRight = hardwareMap.dcMotor.get("backRight");

    waitForStart();//point turn right for 2.5 seconds at .5 speed
    frontLeft.setPower(.5);
    frontRight.setPower(0);
    backLeft.setPower(.5);
    backRight.setPower(0);
    sleep(2500);

    frontLeft.setPower(0); //stop for 1 second
    frontRight.setPower(0);
    backLeft.setPower(0);
    backRight.setPower(0);
    sleep(1000);

    frontLeft.setPower(0);//point turn left for 4 seconds at .5 speed
    frontRight.setPower(.5);
    backLeft.setPower(0);
    backRight.setPower(.5);
    sleep(4000);

    }
}
