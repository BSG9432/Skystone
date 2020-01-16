package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Tired extends OpMode {
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor frontLeft;
    DcMotor frontRight;
    @Override
    public void init() {
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
    }

    @Override
    public void loop() {
if(Math.abs(gamepad1.left_stick_y)>.1){
    frontLeft.setPower(gamepad1.left_stick_y);
    backLeft.setPower(gamepad1.left_stick_y);
}
else {
    backLeft.setPower(0);
    frontLeft.setPower(0);
}
if(Math.abs(gamepad1.right_stick_y)>.1){
    frontRight.setPower(gamepad1.right_stick_y);
    backRight.setPower(gamepad1.right_stick_y);
}
else{
    backRight.setPower(0);
    frontRight.setPower(0);
    }
}
