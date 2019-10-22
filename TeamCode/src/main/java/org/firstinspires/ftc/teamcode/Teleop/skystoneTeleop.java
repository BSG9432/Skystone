package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

//test
@TeleOp (name = "skystoneTeleop")

public class skystoneTeleop extends OpMode {
    Robot bsgRobot = new Robot();
    public int speed = 1;


    @Override
    public void init() {
        bsgRobot.init(hardwareMap);

    }

    @Override
    public void loop() {

        if((gamepad1.dpad_up = true) && (speed < 1)) //makes speed go up when dpad up is pressed
        {
            speed += .25;
        }
        if((gamepad1.dpad_down = true) && (speed > -1))//makes speed go down when dpad down is pressed
        {
            speed -= .25;
        }

        //For Right motors
        if (-gamepad1.right_stick_y > .1)
        {
            bsgRobot.frontRight.setPower(speed);
            bsgRobot.backRight.setPower(speed);
        }
        else
        {
            bsgRobot.frontRight.setPower(0);
            bsgRobot.backRight.setPower(0);
        }

        if (-gamepad1.right_stick_y < -.1) {
            bsgRobot.frontRight.setPower(-speed);
            bsgRobot.backRight.setPower(-speed);
        }
        else {
            bsgRobot.frontRight.setPower(0);
            bsgRobot.backRight.setPower(0);
        }



        //For Left Side
        if (-gamepad1.left_stick_y > .1)
        {
            bsgRobot.frontLeft.setPower(-speed);
            bsgRobot.backLeft.setPower(-speed);
        }
        else
        {
            bsgRobot.frontLeft.setPower(0);
            bsgRobot.backLeft.setPower(0);
        }

        if (-gamepad1.left_stick_y < -.1)
        {
            bsgRobot.frontLeft.setPower(speed);
            bsgRobot.backLeft.setPower(speed);
        }
        else
        {
            bsgRobot.frontLeft.setPower(0);
            bsgRobot.backLeft.setPower(0);
        }

//left trigger lift up


        // left bumper servos close
        /*
        if (gamepad1.left_bumper) {
        bsgRobot.rightClaw.setPosition(.4);
        bsgRobot.leftClaw.setPosition(.6);
        }
        if(gamepad1.right_bumper)
        {
            bsgRobot.rightClaw.setPosition(.9);
            bsgRobot.leftClaw.setPosition(.1);
        }
*/
        if (gamepad1.left_trigger > .1)
        {
            //bsgRobot.lift.setPower(-.5);
            //bsgRobot.lift.setPower(.5)
        }

        //lift intake - triggers ; intake intake - bumpers

        /*if (gamepad1.left_stick_x > .1) // strafe RIGHT
        {
            frontRight.setPower(-1);
            backRight.setPower(1);
            frontLeft.setPower(1);
            backLeft.setPower(-1);
        }
        
        else
        {
            frontRight.setPower(0);
            backRight.setPower(0);
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }
        
        if (gamepad1.left_stick_x < -.1) // strafe LEFT
        {
            frontRight.setPower(1);
            backRight.setPower(-1);
            frontLeft.setPower(-1);
            backLeft.setPower(1);
        }
        else
        {
            frontRight.setPower(0);
            backRight.setPower(0);
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }

         */
    }
}
