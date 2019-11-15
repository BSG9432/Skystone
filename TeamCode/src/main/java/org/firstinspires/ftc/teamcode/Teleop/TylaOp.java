package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

//test
@TeleOp (name = "TylaOp")

public class TylaOp extends OpMode {
    Robot bsgBot = new Robot();
   // public int speed = 1;

   /* public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public Servo leftFoundation;
    public Servo rightFoundation;

    public DcMotor lift;

    */


    @Override
    public void init() {
        bsgBot.init(hardwareMap);

        bsgBot.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bsgBot.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bsgBot.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bsgBot.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        /*
        frontLeft = hardwareMap.dcMotor.get("frontLeft");'
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");

        lift = hardwareMap.dcMotor.get("lift");

        bsgBot.frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        bsgBot.backLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        bsgBot.leftFoundation = hardwareMap.servo.get("leftFoundation");
        bsgBot.rightFoundation = hardwareMap.servo.get("rightFoundation");
        */
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
        if (Math.abs(gamepad1.right_stick_y) > .1)
        {
            bsgBot.frontRight.setPower(-gamepad1.right_stick_y);
            bsgBot.backRight.setPower(-gamepad1.right_stick_y);
        }
        else
        {
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(0);
        }

        //For Left Motors (KEEP IN MIND THAT LEFT MOTORS ARE SET AT REVERSE DIRECTION IN THE ROBOT OBJECT CLASS)
        if (Math.abs(gamepad1.left_stick_y) > .1)
        {
            bsgBot.frontLeft.setPower(-gamepad1.left_stick_y);
            bsgBot.backLeft.setPower(-gamepad1.left_stick_y);
        }
        else
        {
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(0);
        }
        telemetry.addData("Front Right Value: ", bsgBot.frontRight.getPower());
        telemetry.addData("Back Right Value: ", bsgBot.backRight.getPower());
        telemetry.addData("Front Left Value: ", bsgBot.frontLeft.getPower());
        telemetry.addData("Back  Left Value: ", bsgBot.backLeft.getPower());
        telemetry.update();

        // TO CONTROL THE FOUNDATION SERVOS
        if (gamepad1.a) //Down Position
        {
            bsgBot.rightFoundation.setPosition(1);
            bsgBot.leftFoundation.setPosition(0);
        }
        if (gamepad1.b) //Up Position
        {
            bsgBot.rightFoundation.setPosition(.1);
            bsgBot.leftFoundation.setPosition(.9);
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
        //{
            //bsgRobot.lift.setPower(-.5);
            //bsgRobot.lift.setPower(.5)
       // }

        if (gamepad1.right_trigger > .1) // strafe Right
        {
            bsgBot.frontRight.setPower(-1);
            bsgBot.backRight.setPower(1);
            bsgBot.frontLeft.setPower(1);
            bsgBot.backLeft.setPower(-1);
        }
        else
        {
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(0);
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(0);
        }


        if (gamepad1.left_trigger > .1) // strafe Left
        {
            bsgBot.frontRight.setPower(1);
            bsgBot.backRight.setPower(-1);
            bsgBot.frontLeft.setPower(-1);
            bsgBot.backLeft.setPower(1);
        }
        else
        {
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(0);
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(0);
        }

        if(gamepad1.right_bumper)
        {
            bsgBot.lift.setPower(1);
        }
        else if (gamepad1.left_bumper)
        {
            bsgBot.lift.setPower(-1);
        }
        else
        {
            bsgBot.lift.setPower(0);
        }



    }
}
