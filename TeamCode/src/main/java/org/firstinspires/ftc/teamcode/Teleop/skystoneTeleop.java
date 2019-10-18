package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//test
@TeleOp (name = "skystoneTeleop")

public class skystoneTeleop extends OpMode {
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;
    public int speed = 1;


    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");
    }

    @Override
    public void loop() {
        /*if((gamepad1.dpad_up = true) && (speed < 1)) //makes speed go up when dpad up is pressed
        {
            speed += .25;
        }
        if((gamepad1.dpad_down = true) && (speed > -1))//makes speed go down when dpad down is pressed
        {
            speed -= .25;
        }

         */
        
        if(Math.abs(gamepad1.right_stick_y) > .1)
        {
            frontRight.setPower(-gamepad1.right_stick_y);
            backRight.setPower(-gamepad1.right_stick_y);
        }
        else
        {
            frontRight.setPower(0);
            backRight.setPower(0);
        }
        
        if(Math.abs(gamepad1.left_stick_y) > .1)
        {
            frontLeft.setPower(gamepad1.left_stick_y);
            backLeft.setPower(gamepad1.left_stick_y);
        }
        else
        {
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }
        
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
