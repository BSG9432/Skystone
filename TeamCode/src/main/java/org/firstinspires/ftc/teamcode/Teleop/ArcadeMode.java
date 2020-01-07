package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

@TeleOp (name = "ArcadeModeTest")

public class ArcadeMode extends OpMode {

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

        //telemetry to show output values for sticks (for testing purposes)
        telemetry.addData("Left Stick X: ",gamepad1.left_stick_x);
        telemetry.addData("Right Stick X: ", gamepad1.right_stick_x);
        telemetry.addData("Left Stick Y:", gamepad1.left_stick_y);
        telemetry.addData("Right Stick Y:", gamepad1.right_stick_y);


        telemetry.update();


        // D R I V E  T R A I N

        //Driving forwards and backwards using left_stick_y
        if (Math.abs(gamepad1.left_stick_y)> .1 && Math.abs(gamepad1.left_stick_x)< .3){
                bsgBot.frontLeft.setPower(-gamepad1.left_stick_y);
                bsgBot.backLeft.setPower(-gamepad1.left_stick_y);
                bsgBot.frontRight.setPower(-gamepad1.left_stick_y);
                bsgBot.backRight.setPower(-gamepad1.left_stick_y);
        }
        else {
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(0);
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(0);
        }


        //Left strafe when left_stick_x is negative (left)
        if (gamepad1.left_stick_x < -.1 && Math.abs(gamepad1.left_stick_y) < .3) {
            bsgBot.frontLeft.setPower(-gamepad1.left_stick_x);
            bsgBot.backLeft.setPower(gamepad1.left_stick_x);
            bsgBot.frontRight.setPower(gamepad1.left_stick_x);
            bsgBot.backRight.setPower(-gamepad1.left_stick_x);
        }
       else {
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(0);
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(0);
        }


        //Right strafe when left_stick_x is positive (right)
       if (gamepad1.left_stick_x > .1 && Math.abs(gamepad1.left_stick_y) < .3) {
           bsgBot.frontLeft.setPower(-gamepad1.left_stick_x);
           bsgBot.backLeft.setPower(gamepad1.left_stick_x);
           bsgBot.frontRight.setPower(gamepad1.left_stick_x);
           bsgBot.backRight.setPower(-gamepad1.left_stick_x);
    }
       else {
           bsgBot.frontLeft.setPower(0);
           bsgBot.backLeft.setPower(0);
           bsgBot.frontRight.setPower(0);
           bsgBot.backRight.setPower(0);
       }

       //Rotate counterclockwise (pivot turn left) when right stick is pressed to the left
       if (gamepad1.right_stick_x < -.1){
           bsgBot.frontLeft.setPower(gamepad1.right_stick_x);
           bsgBot.backLeft.setPower(gamepad1.right_stick_x);
           bsgBot.frontRight.setPower(-gamepad1.right_stick_x);
           bsgBot.backRight.setPower(-gamepad1.right_stick_x);
        }
       else {
           bsgBot.frontLeft.setPower(0);
           bsgBot.backLeft.setPower(0);
           bsgBot.frontRight.setPower(0);
           bsgBot.backRight.setPower(0);
        }

       //Rotate clockwise (pivot turn right) when right stick is pressed to the right
       if (gamepad1.right_stick_x > .1){
           bsgBot.frontLeft.setPower(gamepad1.right_stick_x);
           bsgBot.backLeft.setPower(gamepad1.right_stick_x);
           bsgBot.frontRight.setPower(-gamepad1.right_stick_x);
           bsgBot.backRight.setPower(-gamepad1.right_stick_x);
        }
        else {
           bsgBot.frontLeft.setPower(0);
           bsgBot.backLeft.setPower(0);
           bsgBot.frontRight.setPower(0);
           bsgBot.backRight.setPower(0);
        }



    }
}
