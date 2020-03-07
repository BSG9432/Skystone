package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

//test
@TeleOp (name = "DiagonalStrafingTest")

public class DiagonalStrafingTest extends OpMode {
    Robot bsgBot = new Robot();

    @Override
    public void init() {
        bsgBot.init(hardwareMap);

        bsgBot.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bsgBot.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bsgBot.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bsgBot.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        bsgBot.rightFoundation.setPosition(1);
        bsgBot.leftFoundation.setPosition(0);

        bsgBot.armStopUp();

        bsgBot.closeClamp();
    }

    @Override
    public void loop() {

        telemetry.addData("Front Right Value: ", bsgBot.frontRight.getPower());
        telemetry.addData("Back Right Value: ", bsgBot.backRight.getPower());
        telemetry.addData("Front Left Value: ", bsgBot.frontLeft.getPower());
        telemetry.addData("Back  Left Value: ", bsgBot.backLeft.getPower());
        telemetry.update();

        //NW
        if (gamepad1.left_stick_y < -.1) {
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(1);
            bsgBot.frontLeft.setPower(1);
            bsgBot.backLeft.setPower(0);
        }
        else {
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(0);
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(0);
        }

        //SW
        if (gamepad1.left_stick_y > .1) {
            bsgBot.frontRight.setPower(-1);
            bsgBot.backRight.setPower(0);
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(-1);
        }
        else {
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(0);
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(0);
        }

        //NE
        if (gamepad1.right_stick_y < -.1) {
            bsgBot.frontRight.setPower(1);
            bsgBot.backRight.setPower(0);
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(1);
        }
        else {
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(-0);
            bsgBot.frontLeft.setPower(01);
            bsgBot.backLeft.setPower(0);
        }

        //SE
        if (gamepad1.left_stick_y > .1) {
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(-1);
            bsgBot.frontLeft.setPower(-1);
            bsgBot.backLeft.setPower(0);
        }
        else {
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(0);
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(0);
        }




    }


}
