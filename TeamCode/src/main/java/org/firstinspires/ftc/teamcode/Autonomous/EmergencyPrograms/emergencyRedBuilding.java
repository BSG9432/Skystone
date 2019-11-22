package org.firstinspires.ftc.teamcode.Autonomous.EmergencyPrograms;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
@Disabled
@Autonomous (name="emergencyRedBuilding")
public class emergencyRedBuilding extends LinearOpMode {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public DcMotor lift;

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

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lift = hardwareMap.dcMotor.get("lift");

        leftFoundation = hardwareMap.servo.get("leftFoundation");
        rightFoundation = hardwareMap.servo.get("rightFoundation");


        rightFoundation.setPosition(1);
        leftFoundation.setPosition(0);

        AutoTransitioner.transitionOnStop(this, "TylaOp");


        waitForStart();

        strafeRight(.5, 1000);
        drive(.5,.5,1750);

        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        sleep(200);

        foundationGrab(1000);

        //rightFoundation.setPosition(.1);
        //leftFoundation.setPosition(.9);
        //sleep(1000);
        drive(-1,-1,1700);

        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        sleep(200);

        foundationRelease(1000);
        strafeLeft(.5, 3400);


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
