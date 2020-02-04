package org.firstinspires.ftc.teamcode.Practice;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous(name = "Challenge2")
public class Challenge2 extends LinearOpMode {
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor frontLeft;
    DcMotor frontRight;
    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        waitForStart(); // Pivot Right 2.5 seconds
        frontLeft.setPower(-.5);
        frontRight.setPower(-.5);
        backLeft.setPower(-.5);
        backRight.setPower(-.5);
        sleep(2500);

        frontLeft.setPower(0);// Stop 1 second
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(1000);

        frontLeft.setPower(.5);// Pivot Left 4 seconds
        frontRight.setPower(.5);
        backRight.setPower(.5);
        backLeft.setPower(.5);
        sleep(4000);
    }

}
