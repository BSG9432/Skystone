package org.firstinspires.ftc.teamcode;//package org.firstinspires.ftc.teamcode.MineralMania2k19.Testing;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.Hardware.Robot;


@Autonomous(name="TylaIMU")
public class testIMUV2 extends LinearOpMode {
    /* Declare OpMode members. */

    Robot bsgRobot = new Robot();


    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */

        bsgRobot.frontRight = hardwareMap.dcMotor.get("frontRight");
        bsgRobot.frontLeft = hardwareMap.dcMotor.get("frontLeft");
        bsgRobot.backRight = hardwareMap.dcMotor.get("backRight");
        bsgRobot.backLeft = hardwareMap.dcMotor.get("backLeft");

        bsgRobot.leftFoundation = hardwareMap.servo.get("leftFoundation");
        bsgRobot.rightFoundation = hardwareMap.servo.get("rightFoundation");

        bsgRobot.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bsgRobot.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bsgRobot.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bsgRobot.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();


        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0", "Starting at %7d :%7d",
                bsgRobot.frontLeft.getCurrentPosition(),
                bsgRobot.frontRight.getCurrentPosition(),
                bsgRobot.backLeft.getCurrentPosition(),
                bsgRobot.backRight.getCurrentPosition());
        telemetry.update();


        //The parameters for the IMU
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;

        bsgRobot.imu = hardwareMap.get(BNO055IMU.class, "imu");

        bsgRobot.imu.initialize(parameters);

        //AutoTransitioner from Team 7203 KNO3 Robotics

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        rotate(90, 1);//CCW


        telemetry.addData("Path", "Complete");
        telemetry.update();



    }

    /*
     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */


    private void resetAngle()
    {
        bsgRobot.imuAngle = 0;
    }

    public double getHeading() {
        Orientation angles = bsgRobot.imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES);
        double heading = angles.firstAngle;
        return heading;
    }

    private void rotateCCW(int degrees, double power){
        resetAngle();
        double angles = getHeading();
        angles = angles + 180;

        double rightPower;

        if (degrees > 0 )
        {   // turn CCW.
            bsgRobot.frontLeft.setPower(0);
            bsgRobot.backLeft.setPower(0);
            bsgRobot.frontRight.setPower(power);
            bsgRobot.backRight.setPower(power);
            telemetry.addLine("TURN LEFT");
            telemetry.update();
        }


        // rotate until turn is completed.
        if (degrees < 0)
        {
            // On right turn we have to get off zero first.
            while (opModeIsActive() && getHeading() == 0) {}

            while (opModeIsActive() && getHeading() > degrees) {}
        }
        else    // left turn.
            while (opModeIsActive() && getHeading() < degrees) {}

        // turn the motors off.
        bsgRobot.frontLeft.setPower(0);
        bsgRobot.backLeft.setPower(0);
        bsgRobot.frontRight.setPower(0);
        bsgRobot.backRight.setPower(0);

        // wait for rotation to stop.
        sleep(1000);

        // reset angle tracking on new heading.
        resetAngle();
    }



    private void rotate(int degrees, double power)
    {
        double  leftPower, rightPower;

        // restart imu movement tracking.
        resetAngle();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        if (degrees < 0)
        {   // turn right.
            leftPower = -power;
            rightPower = -.3;
            telemetry.addLine("TURN BUT THE OTHER ONE");
            telemetry.update();
        }
        else if (degrees > 0)
        {   // turn left.
            leftPower = .3;
            rightPower = power;
            telemetry.addLine("TURN YEH");
            telemetry.update();
        }
        else return;

        // set power to rotate.
        bsgRobot.frontLeft.setPower(leftPower);
        bsgRobot.backLeft.setPower(leftPower);
        bsgRobot.frontRight.setPower(rightPower);
        bsgRobot.backRight.setPower(rightPower);

        // rotate until turn is completed.
        if (degrees < 0)
        {
            // On right turn we have to get off zero first.
            while (opModeIsActive() && getHeading() == 0) {}

            while (opModeIsActive() && getHeading() > degrees) {}
        }
        else    // left turn.
            while (opModeIsActive() && getHeading() < degrees) {}

        // turn the motors off.
        bsgRobot.frontLeft.setPower(0);
        bsgRobot.backLeft.setPower(0);
        bsgRobot.frontRight.setPower(0);
        bsgRobot.backRight.setPower(0);

        // wait for rotation to stop.
        sleep(1000);

        // reset angle tracking on new heading.
        resetAngle();
    }
}