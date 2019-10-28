package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

//test
@TeleOp (name = "TylaOp")

public class TylaOp extends OpMode {
    Robot bsgRobot = new Robot();
   // public int speed = 1;


    @Override
    public void init() {
        bsgRobot.init(hardwareMap);

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
        //For Right motors
        if (Math.abs(gamepad1.right_stick_y) > .1)
        {
            bsgRobot.frontRight.setPower(-gamepad1.right_stick_y);
            bsgRobot.backRight.setPower(-gamepad1.right_stick_y);
        }
        else
        {
            bsgRobot.frontRight.setPower(0);
            bsgRobot.backRight.setPower(0);
        }



        /*
        if (-gamepad1.right_stick_y < -.1) {
            bsgRobot.frontRight.setPower(-speed);
            bsgRobot.backRight.setPower(-speed);
        }
        else {
            bsgRobot.frontRight.setPower(0);
            bsgRobot.backRight.setPower(0);
        }
         */

        //For Left Side
        if (Math.abs(gamepad1.left_stick_y) > .1)
        {
            bsgRobot.frontLeft.setPower(gamepad1.left_stick_y);
            bsgRobot.backLeft.setPower(gamepad1.left_stick_y);
        }
        else
        {
            bsgRobot.frontLeft.setPower(0);
            bsgRobot.backLeft.setPower(0);
        }
        //Telemetry to see values of motors 
        telemetry.addData("Front Right Value: ", bsgRobot.frontRight.getPower());
        telemetry.addData("Back Right Value: ", bsgRobot.backRight.getPower());
        telemetry.addData("Front Left Value: ", bsgRobot.frontLeft.getPower());
        telemetry.addData("Back  Left Value: ", bsgRobot.backLeft.getPower());
        telemetry.update();

        /*
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

         */

        // TO CONTROL THE FOUNDATION SERVOS
        //grab foundation
        if (gamepad1.a) {
            bsgRobot.rightFoundation.setPosition(1);
            bsgRobot.leftFoundation.setPosition(0);
        }
        if (gamepad1.b) {
            bsgRobot.rightFoundation.setPosition(.1);
            bsgRobot.leftFoundation.setPosition(.9);
        }



        // BUMPERS TO CONTROL CLAW
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
        // TRIGGERS TO CONTROL THE LIFT
        //if (gamepad1.left_trigger > .1)
        {
            //bsgRobot.lift.setPower(-.5);
            //bsgRobot.lift.setPower(.5)
        }

       /* if (gamepad1.left_stick_x > .1) // strafe RIGHT
        {
            bsgRobot.frontRight.setPower(-1);
            bsgRobot.backRight.setPower(1);
            bsgRobot. frontLeft.setPower(1);
            bsgRobot.backLeft.setPower(-1);
        }
        
        else
        {
            bsgRobot.frontRight.setPower(0);
            bsgRobot.backRight.setPower(0);
            bsgRobot.frontLeft.setPower(0);
            bsgRobot.backLeft.setPower(0);
        }*/


       /* if (gamepad1.left_stick_x < -.1) // strafe LEFT
        {
            bsgRobot.frontRight.setPower(1);
            bsgRobot.backRight.setPower(-1);
            bsgRobot.frontLeft.setPower(-1);
            bsgRobot.backLeft.setPower(1);
        }
        else
        {
            bsgRobot.frontRight.setPower(0);
            bsgRobot.backRight.setPower(0);
            bsgRobot.frontLeft.setPower(0);
            bsgRobot.backLeft.setPower(0);
        }
*/

    }
}
