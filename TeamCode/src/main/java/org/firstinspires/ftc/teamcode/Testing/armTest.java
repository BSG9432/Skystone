package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.Hardware.Robot;

@Autonomous (name="armTest") //jk its testing the side arm
@Disabled
public class armTest extends LinearOpMode {
    Robot bsgbot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {


        bsgbot.init(hardwareMap);


       bsgbot.rightFoundation.setPosition(1);
        bsgbot.leftFoundation.setPosition(0);


        AutoTransitioner.transitionOnStop(this, "TylaOp");


        waitForStart();

        bsgbot.arm.setPower(.5);
        sleep(1000);

        bsgbot.arm.setPower(-.5);
        sleep(1000);

    }

    public void drive(double leftPower, double rightPower, long sleepTime)
    {
        bsgbot.frontLeft.setPower(leftPower);
        bsgbot.backLeft.setPower(leftPower);
        bsgbot.frontRight.setPower(rightPower);
        bsgbot.backRight.setPower(rightPower);
        sleep(sleepTime);
    }

    public void strafeRight(double speed, long sleepTime)
    {
        bsgbot.frontLeft.setPower(speed);
        bsgbot.backLeft.setPower(-speed);
        bsgbot.frontRight.setPower(-speed);
        bsgbot.backRight.setPower(speed);
        sleep(sleepTime);
    }

    public void strafeLeft(double speed, long sleepTime)
    {
        bsgbot.frontLeft.setPower(-speed);
        bsgbot.backLeft.setPower(speed);
        bsgbot.frontRight.setPower(speed);
        bsgbot.backRight.setPower(-speed);
        sleep(sleepTime);
    }

    public void foundationRelease(long sleepTime)
    {
        bsgbot.rightFoundation.setPosition(1);
        bsgbot.leftFoundation.setPosition(0);
        sleep(sleepTime);
    }

    public void foundationGrab(long sleepTime)
    {
        bsgbot.rightFoundation.setPosition(.1);
        bsgbot.leftFoundation.setPosition(.9);
        sleep(sleepTime);
    }

}
