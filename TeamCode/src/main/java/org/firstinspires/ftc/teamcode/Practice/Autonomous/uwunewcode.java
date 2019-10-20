package org.firstinspires.ftc.teamcode.Practice.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@Disabled
@Autonomous (name = "uwunewcode")
public class uwunewcode extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor backLeft;
    DcMotor frontRight;
    DcMotor backRight;

    @Override
    public void runOpMode() throws InterruptedException {
   frontLeft = hardwareMap.dcMotor.get("frontLeft");
   frontRight = hardwareMap.dcMotor.get("frontRight");
   backLeft = hardwareMap.dcMotor.get("backLeft");
   backRight = hardwareMap.dcMotor.get("backRight");

        waitForStart();

        //forward at full speed for 2.5 seconds
        frontLeft.setPower(1);
        backLeft.setPower(1);
        frontRight.setPower(1);
        backRight.setPower(1);
        sleep(2500);

        //point right turn .5 speed for 2.5 seconds
        frontLeft.setPower(.5);
        backLeft.setPower(.5);
        frontRight.setPower(0);
        backRight.setPower(0);
        sleep(2500);



    }
    public void goforward (double power, int time){
        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(power);
        backRight.setPower(power);
        sleep(time);
    }

}
