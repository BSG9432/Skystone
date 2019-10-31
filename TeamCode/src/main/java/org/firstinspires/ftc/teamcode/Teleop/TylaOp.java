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
    //Robot bsgRobot = new Robot();
   // public int speed = 1;
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    public Servo leftFoundation;
    public Servo rightFoundation;

    @Override
    public void init() {
        //bsgRobot.init(hardwareMap);
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);



        leftFoundation = hardwareMap.servo.get("leftFoundation");
        rightFoundation = hardwareMap.servo.get("rightFoundation");

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
            frontRight.setPower(-gamepad1.right_stick_y);
            backRight.setPower(-gamepad1.right_stick_y);
        }
        else
        {
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        //For Left Motors (KEEP IN MIND THAT LEFT MOTORS ARE SET AT REVERSE DIRECTION IN THE ROBOT OBJECT CLASS)
        if (Math.abs(gamepad1.left_stick_y) > .1)
        {
            frontLeft.setPower(-gamepad1.left_stick_y);
            backLeft.setPower(-gamepad1.left_stick_y);
        }
        else
        {
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }
        telemetry.addData("Front Right Value: ", frontRight.getPower());
        telemetry.addData("Back Right Value: ", backRight.getPower());
        telemetry.addData("Front Left Value: ", frontLeft.getPower());
        telemetry.addData("Back  Left Value: ", backLeft.getPower());
        telemetry.update();

        // TO CONTROL THE FOUNDATION SERVOS
        if (gamepad1.a) //Down Position
        {
            rightFoundation.setPosition(1);
            leftFoundation.setPosition(0);
        }
        if (gamepad1.b) //Up Position
        {
            rightFoundation.setPosition(.1);
            leftFoundation.setPosition(.9);
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

        if (gamepad1.right_trigger > .1) // strafe Right
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


        if (gamepad1.left_trigger > .1) // strafe Left
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



    }
}
