package org.firstinspires.ftc.teamcode.Practice;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "hewwonewcode")

public class hewwonewcode extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor backLeft;
    DcMotor frontRight;
    DcMotor backRight;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");

        waitForStart(); //Point Turn Left for 2 seconds
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(.5);
        backRight.setPower(.5);
        sleep(2000);


    }
}
