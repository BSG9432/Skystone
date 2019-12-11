package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

@TeleOp (name = "ArcadeModeTest")

public class ArcadeModeTest extends OpMode {
    DcMotor frontLeft;
    DcMotor backLeft;
    DcMotor frontRight;
    DcMotor backRight;
    Robot bsgBot = new Robot();


    @Override
    public void init() {

    bsgBot.init(hardwareMap);

    bsgBot.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    bsgBot.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    bsgBot.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    bsgBot.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

    }

    @Override
    public void loop() {
        telemetry.addData("Left Stick X: ",gamepad1.left_stick_x);
        telemetry.addData("Right Stick X: ", gamepad1.right_stick_x);
        telemetry.addData("Left Stick Y:", gamepad1.left_stick_y);
        telemetry.addData("Right Stick Y:", gamepad1.right_stick_y);


        telemetry.update();

    if (Math.abs(gamepad1.left_stick_y)> .1 && gamepad1.left_stick_x != 0) {
            frontLeft.setPower(-gamepad1.left_stick_y);
            backLeft.setPower(-gamepad1.left_stick_y);
            frontRight.setPower(-gamepad1.left_stick_y);
            backRight.setPower(-gamepad1.left_stick_y);
    }
    else {
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }

        //left stick y axis power for going forwards and backwards
    //left strafe
    /*if (gamepad1.left_stick_x > .1) {
        frontLeft.setPower(-gamepad1.left_stick_x);
        backLeft.setPower(gamepad1.left_stick_x);
        frontRight.setPower(gamepad1.left_stick_x);
        backRight.setPower(-gamepad1.left_stick_x);
    }
   else {
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }

   /*if (gamepad1.left_stick_x > .1) {
    frontLeft.setPower(gamepad1.left_stick_x);
    backLeft.setPower(-gamepad1.left_stick_x);
    frontRight.setPower(-gamepad1.left_stick_x);
    backRight.setPower(gamepad1.left_stick_x);
}
   else {
       frontLeft.setPower(0);
       backLeft.setPower(0);
       frontRight.setPower(0);
       backRight.setPower(0);
   }
        //left stick x axis power for strafe left and right

  /* if (gamepad1.right_stick_x < -.1){
        frontLeft.setPower(-gamepad1.right_stick_x);
        backLeft.setPower(-gamepad1.right_stick_x);
        frontRight.setPower(gamepad1.right_stick_x);
        backRight.setPower(gamepad1.right_stick_x);
    }
    else {
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }


    /*if (gamepad1.right_stick_x < .1){
        frontLeft.setPower(gamepad1.right_stick_x);
        backLeft.setPower(gamepad1.right_stick_x);
        frontRight.setPower(-gamepad1.right_stick_x);
        backRight.setPower(-gamepad1.right_stick_x);
    }
    else {
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }

     */


        //right stick x axis power for pivot turns

    }
}
