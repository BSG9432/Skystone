package org.firstinspires.ftc.teamcode.Practice.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "autoprac")
public class autoprac extends LinearOpMode {
   DcMotor frontLeft;
   DcMotor frontRight;
   DcMotor backLeft;
   DcMotor backRight;
   Servo servoBoi;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        servoBoi = hardwareMap.servo.get("servoBoi");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //servo set position .25
        waitForStart();
        servoBoi.setPosition(.25);
        sleep(5000);

        //forward for 5 seconds
        frontLeft.setPower(.5);
        frontRight.setPower(.5);
        backLeft.setPower(.5);
        backRight.setPower(.5);
        sleep(5000);

        //backwards for 5 seconds
        frontLeft.setPower(-.5);
        frontRight.setPower(-.5);
        backLeft.setPower(-.5);
        backRight.setPower(-.5);
        sleep(5000);

        //pivot turn right for 5 seconds
        frontLeft.setPower(.5);
        frontRight.setPower(-.5);
        backLeft.setPower(.5);
        backRight.setPower(-.5);
        sleep(5000);
    }
}
