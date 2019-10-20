package org.firstinspires.ftc.teamcode.Practice.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
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
    //forward at .25 speed for 3 seconds
   frontLeft.setPower(-.25);
   backLeft.setPower(-.25);
   frontRight.setPower(.25);
   backRight.setPower(.25);
   sleep(3000);

   //no movement for 2 seconds
   frontLeft.setPower(0);
   backLeft.setPower(0);
   frontRight.setPower(0);
   backRight.setPower(0);
   sleep(2000);

   //forward at half speed for 3 seconds
   frontLeft.setPower(.5);
   backLeft.setPower(.5);
   frontRight.setPower(-.5);
   backRight.setPower(-.5);
   sleep(3000);

    //set servo position to .75 for 1 second
    servoBoi.setPosition(.75);
    sleep(1000);



    }


}
