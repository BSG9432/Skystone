package org.firstinspires.ftc.teamcode.Practice.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

@Disabled
@Autonomous (name = "functionprac")
public class fuctionprac extends LinearOpMode {
    Robot bsgBot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        bsgBot.init(hardwareMap);
        bsgBot.frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        bsgBot.backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        pointTurnRight(1, 2500);
        pivotTurnLeft(.5, 2500);

    }
    public void pointTurnRight (double power, int sleep){
        bsgBot.frontLeft.setPower(power);
        bsgBot.backLeft.setPower(power);
        bsgBot.frontRight.setPower(0);
        bsgBot.backRight.setPower(0);
        sleep(sleep);
    }
    public void pivotTurnLeft (double power, int sleep) {
        bsgBot.frontLeft.setPower(-power);
        bsgBot.backLeft.setPower(-power);
        bsgBot.frontRight.setPower(power);
        bsgBot.backRight.setPower(power);
    }
}
