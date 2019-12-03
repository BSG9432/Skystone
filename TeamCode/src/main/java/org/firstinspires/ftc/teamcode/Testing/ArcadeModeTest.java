package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "ArcadeModeTest")

public class ArcadeModeTest extends OpMode {
    DcMotor frontLeft;
    DcMotor backLeft;
    DcMotor frontRight;
    DcMotor backRight;
    @Override
    public void init() {
    frontLeft = hardwareMap.dcMotor.get("frontLeft");
    backLeft = hardwareMap.dcMotor.get("backLeft");
    frontRight = hardwareMap.dcMotor.get("frontRight");
    backRight = hardwareMap.dcMotor.get("backRight");

    frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
    if (Math.abs(gamepad1.left_stick_y)> .1) {
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
    if (gamepad1.left_stick_x > .1) {
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

   if (gamepad1.left_stick_x > .1) {
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

   if (gamepad1.right_stick_x < -.1){
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


    if (gamepad1.right_stick_x < .1){
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


        //right stick x axis power for pivot turns

    }
}
