package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
@Autonomous (name="strafeTest")
public class strafeTest extends LinearOpMode {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public Servo leftFoundation;
    public Servo rightFoundation;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        leftFoundation = hardwareMap.servo.get("leftFoundation");
        rightFoundation = hardwareMap.servo.get("rightFoundation");


        rightFoundation.setPosition(1);
        leftFoundation.setPosition(0);

        AutoTransitioner.transitionOnStop(this, "TylaOp");


        waitForStart();

        strafeLeft(.5, 1000);
       strafeRight(.5,1000);

        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        sleep(200);

    }

    public void drive(double leftPower, double rightPower, long sleepTime)
    {
        frontLeft.setPower(leftPower);
        backLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backRight.setPower(rightPower);
        sleep(sleepTime);
    }

    public void strafeRight(double speed, long sleepTime)
    {
        frontLeft.setPower(speed);
        backLeft.setPower(-speed);
        frontRight.setPower(-speed);
        backRight.setPower(speed);
        sleep(sleepTime);
    }

    public void strafeLeft(double speed, long sleepTime)
    {
        frontLeft.setPower(-speed);
        backLeft.setPower(speed);
        frontRight.setPower(speed);
        backRight.setPower(-speed);
        sleep(sleepTime);
    }

    public void foundationRelease(long sleepTime)
    {
        rightFoundation.setPosition(1);
        leftFoundation.setPosition(0);
        sleep(sleepTime);
    }

    public void foundationGrab(long sleepTime)
    {
        rightFoundation.setPosition(.1);
        leftFoundation.setPosition(.9);
        sleep(sleepTime);
    }

}
