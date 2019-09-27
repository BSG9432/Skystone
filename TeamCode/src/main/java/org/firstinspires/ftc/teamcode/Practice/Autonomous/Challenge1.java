package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous (name = "Challenge1")
public class Challenge1 extends LinearOpMode{
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    public Servo servoBoi;

    @Override
    public void runOpMode() throws InterruptedException {
    frontLeft = hardwareMap.dcMotor.get("frontLeft");
    frontRight = hardwareMap.dcMotor.get("frontRight");
    backLeft = hardwareMap.dcMotor.get("backLeft");
    backRight = hardwareMap.dcMotor.get("backRight");
    servoBoi = hardwareMap.servo.get("servoBoi");

    waitForStart();
    //forward
   frontLeft.setPower(-.25);
   backLeft.setPower(-.25);
   frontRight.setPower(.25);
   backRight.setPower(.25);
   sleep(3000);

   frontLeft.setPower(0);
   backLeft.setPower(0);
   frontRight.setPower(0);
   backRight.setPower(0);
   sleep(2000);

   frontLeft.setPower(.5);
   backLeft.setPower(.5);
   frontRight.setPower(-.5);
   backRight.setPower(-.5);
   sleep(3000);

    servoBoi.setPosition(.75);
    sleep(1000);



    }


}
