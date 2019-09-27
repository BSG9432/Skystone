package org.firstinspires.ftc.teamcode.Practice;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Briop")
public class Briop extends OpMode {
    DcMotor frontLeft;
    DcMotor backLeft;
    DcMotor frontRight;
    DcMotor backRight;
    Servo servoBoi;

    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");
        servoBoi = hardwareMap.servo.get("servoBoi");

        //positive value on left side goes forward
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        servoBoi.setPosition(.25);
    }

    @Override
    public void loop() {
        //controlling left stick
        if (Math.abs(-gamepad1.left_stick_y) > .1) {
            frontLeft.setPower(-gamepad1.left_stick_y);
            backLeft.setPower(-gamepad1.left_stick_y);
        }
        else { // Safety
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }
        //controlling right stick
        if (Math.abs(gamepad1.right_stick_y) > .1) {
            frontRight.setPower(gamepad1.right_stick_y);
            backRight.setPower(gamepad1.right_stick_y);
        }
        else {
                frontRight.setPower(0);
                backRight.setPower(0);
        }

        //set Servo position to .75
        if (gamepad1.a){
            servoBoi.setPosition(.75);
        }
        //sets Servo position to .2
        if (gamepad1.y){
            servoBoi.setPosition(.2);
        }
    }
}
