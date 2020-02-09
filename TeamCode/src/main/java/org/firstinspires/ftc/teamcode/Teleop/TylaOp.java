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
        bsgBot.clamp.setPosition(.4);
        bsgBot.armStopDown();

       // bsgBot.openClamp();
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
        //close clamp
        if (gamepad1.y)
        {
            bsgBot.clamp.setPosition(.8);

        }
        //open clamp
        if (gamepad1.x)
        {//.35
            bsgBot.clamp.setPosition(0);

        }

        //side arm down
        if (gamepad1.right_bumper) {
            bsgBot.sideArm.setPower(.3);
            telemetry.addData("sideArm Power",bsgBot.sideArm.getPower());
            telemetry.addData("bumperValue", gamepad1.left_bumper);
        }
        //side arm up
        else if (gamepad1.left_bumper) {
            bsgBot.sideArm.setPower(-.5);
            telemetry.addData("sideArm Power",bsgBot.sideArm.getPower());
            telemetry.addData("bumperValue", gamepad1.right_bumper);
        }
        else {
            bsgBot.sideArm.setPower(0);
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
        if (gamepad1.dpad_right) {
            bsgBot.measuringTape.setPower(1);
        }
        else if (gamepad1.dpad_left) {
            bsgBot.measuringTape.setPower(-1);
        }
        else {
            bsgBot.measuringTape.setPower(0);
        }
    }

}
