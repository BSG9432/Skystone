package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class owonewcode extends LinearOpMode {
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    @Override
    public void runOpMode() throws InterruptedException {
       frontLeft = hardwareMap.dcMotor.get("frontLeft");
       frontRight = hardwareMap.dcMotor.get("frontRight");
       backLeft = hardwareMap.dcMotor.get("backLeft");
       backRight = hardwareMap.dcMotor.get("backRight");

       waitForStart();
       frontLeft.setPower(-.5);
       backLeft.setPower(-.5);
       backLeft.setPower(.5);
       backRight.setPower(.5);
       sleep(3000);

       frontLeft.setPower(.5);
       frontRight.setPower(.5);
       backLeft.setPower(.5);
       backRight.setPower(.5);

    }
}
