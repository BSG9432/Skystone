package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

//test
@TeleOp (name = "TylaOp")

public class TylaOp extends OpMode {
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

       /* if((gamepad1.dpad_up = true) && (speed < 1)) //makes speed go up when dpad up is pressed
        {
            speed += .25;
        }
        if((gamepad1.dpad_down = true) && (speed > -1))//makes speed go down when dpad down is pressed
        {
            speed -= .25;
        }
*/
        //For Right Motors
        if (Math.abs(gamepad1.right_stick_y) > .1) {
            bsgBot.frontRight.setPower(-gamepad1.right_stick_y);
            bsgBot.backRight.setPower(-gamepad1.right_stick_y);
        } else {
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(0);
        }

        //For Left Motors (KEEP IN MIND THAT LEFT MOTORS ARE SET AT REVERSE DIRECTION IN THE ROBOT OBJECT CLASS)
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            bsgBot.frontLeft.setPower(-gamepad1.left_stick_y);
            bsgBot.backLeft.setPower(-gamepad1.left_stick_y);
        } else {
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(0);
        }

        telemetry.addData("Front Right Value: ", bsgBot.frontRight.getPower());
        telemetry.addData("Back Right Value: ", bsgBot.backRight.getPower());
        telemetry.addData("Front Left Value: ", bsgBot.frontLeft.getPower());
        telemetry.addData("Back  Left Value: ", bsgBot.backLeft.getPower());
        telemetry.update();

        // TO CONTROL THE FOUNDATION SERVOS
        if (gamepad1.a) //Up Position
        {
            bsgBot.rightFoundation.setPosition(1);
            bsgBot.leftFoundation.setPosition(0);
        }
        if (gamepad1.b) //Down Position
        {
            bsgBot.rightFoundation.setPosition(0);
            bsgBot.leftFoundation.setPosition(1);
        }
        //open clamp
        if (gamepad1.y)
        {
            bsgBot.openClamp();

        }
        //close clamp
        if (gamepad1.x)
        {//.35
            bsgBot.closeClamp();

        }

        //side arm down
        if (gamepad1.right_bumper) {
            bsgBot.arm.setPower(.3);
            telemetry.addData("Arm Power",bsgBot.arm.getPower());
            telemetry.addData("bumperValue", gamepad1.left_bumper);
        }
        //side arm up
        else if (gamepad1.left_bumper) {
            bsgBot.arm.setPower(-.5);
            telemetry.addData("Arm Power",bsgBot.arm.getPower());
            telemetry.addData("bumperValue", gamepad1.right_bumper);
        }
        else {
            bsgBot.arm.setPower(0);
        }


        if (gamepad1.right_trigger > .1) // strafe Right
        {
            bsgBot.frontRight.setPower(-gamepad1.right_trigger);
            bsgBot.backRight.setPower(gamepad1.right_trigger);
            bsgBot.frontLeft.setPower(gamepad1.right_trigger);
            bsgBot.backLeft.setPower(-gamepad1.right_trigger);
        } else {
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(0);
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(0);
        }


        if (gamepad1.left_trigger > .1) // strafe Left
        {
            bsgBot.frontRight.setPower(gamepad1.left_trigger);
            bsgBot.backRight.setPower(-gamepad1.left_trigger);
            bsgBot.frontLeft.setPower(-gamepad1.left_trigger);
            bsgBot.backLeft.setPower(gamepad1.left_trigger);
        } else {
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(0);
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(0);
        }


        if (gamepad1.dpad_up){
            bsgBot.lift.setPower(-1);
        }
        else if (gamepad1.dpad_down){
            bsgBot.lift.setPower(1);
        }
        else {
            bsgBot.lift.setPower(0);
        }
        if (gamepad1.right_stick_button) {
            bsgBot.measuringTape.setPower(1);
        }
        else if (gamepad1.left_stick_button) {
            bsgBot.measuringTape.setPower(-1);
        }
        else {
            bsgBot.measuringTape.setPower(0);
        }

        if (gamepad2.a) {
            bsgBot.armStopDown();
        }
        if (gamepad2.b) {
            bsgBot.armStopUp();
        }
    }

}
