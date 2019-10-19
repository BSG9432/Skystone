package org.firstinspires.ftc.teamcode.Practice.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@Autonomous (name = "Challenge4")
public class Challenge4 extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor backLeft;
    DcMotor frontRight;
    DcMotor backRight;
    Servo servoBoi;

    @Override
    public void runOpMode() throws InterruptedException {
    frontLeft = hardwareMap.dcMotor.get("frontLeft");
    backLeft = hardwareMap.dcMotor.get("backLeft");
    frontRight = hardwareMap.dcMotor.get("frontRight");
    backRight = hardwareMap.dcMotor.get("backRight");
    servoBoi = hardwareMap.servo.get("servoBoi");

    waitForStart();
    frontLeft.setPower(.5);//point
    backLeft.setPower(.5);
    frontRight.setPower(0);
    backRight.setPower(0);
    sleep(2700);

    frontLeft.setPower(0);
    backLeft.setPower(0);
    frontRight.setPower(0);
    backRight.setPower(0);
    sleep(1000);

    frontLeft.setPower(.5);//pivot
    backLeft.setPower(.5);
    frontRight.setPower(-.5);
    backRight.setPower(-.5);
    sleep(2000);

    frontLeft.setPower(0); //point
    backLeft.setPower(0);
    frontRight.setPower(.5);
    backRight.setPower(.5);
    sleep(2000);

    servoBoi.setPosition(.5);
    sleep(1000);




    }
}
