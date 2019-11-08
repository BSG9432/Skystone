package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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

@Autonomous(name="DistrictsAutoREDDepot", group="Pushbot")
public class AckEncoders extends LinearOpMode {
    /* Declare OpMode members. */

    Robot bsgBot = new Robot();

    // For the encoders
    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = .55;
    static final double TURN_SPEED = 0.3;

    /*
    //HANG CONSTANTS
    static final double     DRIVE_GEAR_REDUCTION_HANG    = 3.0 ;     // GEAR RATIO
    static final double     WHEEL_DIAMETER_INCHES_HANG   = .82 ;     // WHEEL DIAMETER, THIS CASE PINION GEAR
    static final double COUNTS_PER_INCH_HANG = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION_HANG) /
            (WHEEL_DIAMETER_INCHES_HANG * 3.1415);
     */


    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */

        bsgBot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        bsgBot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bsgBot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bsgBot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bsgBot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        bsgBot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bsgBot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bsgBot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bsgBot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);




        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0", "Starting at %7d :%7d",
                bsgBot.frontLeft.getCurrentPosition(),
                bsgBot.frontRight.getCurrentPosition(),
                bsgBot.backLeft.getCurrentPosition(),
                bsgBot.backRight.getCurrentPosition());
        telemetry.update();


        //The parameters for the IMU
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;

        bsgBot.imu = hardwareMap.get(BNO055IMU.class, "imu");

        bsgBot.imu.initialize(parameters);

        //AutoTransitioner from Team 7203 KNO3 Robotics
        AutoTransitioner.transitionOnStop(this, "OOFdistrictsTeleOp");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)

        //NOTA BENE TO ADJUST THE TIMEOUTS


        //rotating out of latch
        encoderDrive(DRIVE_SPEED, -4, 4, 2.0);
        //NOTA BENE TO TEST THIS ROTATION
        rotate(120, .4);

        //move forward for 40 inches
        encoderDrive(.3, -40, 40, 5.0);

        //rotate clockwise approximately 90 degrees
        encoderDrive(.5,-12,-12, 4.0);

        //move backwards 18 seconds towards cargo
        encoderDrive(DRIVE_SPEED, 18, -18, 2.0);


        //Backing up to fully grab mobile cargo bay
        encoderDrive(DRIVE_SPEED, 4, -4, .5);

        //move forward for 63 inches into depot
        encoderDrive(DRIVE_SPEED, -63, 63, 6.0);

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
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = bsgBot.frontLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newLeftTarget = bsgBot.backLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = bsgBot.frontRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newRightTarget = bsgBot.backRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);

            bsgBot.frontLeft.setTargetPosition(newLeftTarget);
            bsgBot.frontRight.setTargetPosition(newRightTarget);
            bsgBot.backLeft.setTargetPosition(newLeftTarget);
            bsgBot.backRight.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            bsgBot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bsgBot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bsgBot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bsgBot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            bsgBot.frontLeft.setPower(Math.abs(speed));
            bsgBot.frontRight.setPower(Math.abs(speed));
            bsgBot.backLeft.setPower(Math.abs(speed));
            bsgBot. backRight.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (bsgBot.frontRight.isBusy() && bsgBot.frontLeft.isBusy() && bsgBot.backRight.isBusy() && bsgBot.backLeft.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        bsgBot.frontRight.getCurrentPosition(),
                        bsgBot.frontLeft.getCurrentPosition(),
                        bsgBot. backRight.getCurrentPosition(),
                        bsgBot.backLeft.getCurrentPosition());

                telemetry.update();
            }

            // Stop all motion;
            bsgBot.frontRight.setPower(0);
            bsgBot.frontLeft.setPower(0);
            bsgBot.backRight.setPower(0);
            bsgBot.backLeft.setPower(0);

            // Turn off RUN_TO_POSITION
            bsgBot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bsgBot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bsgBot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bsgBot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
/*
    public void liftEncoders(double speed, double numOfInches, double timeoutS) {
        int target;
        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller
            target = hang.getCurrentPosition() + (int)(numOfInches * COUNTS_PER_INCH_HANG);

            hang.setTargetPosition(target);

            // Turn On RUN_TO_POSITION
            hang.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();
            hang.setPower(Math.abs(speed));
            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) && hang.isBusy()&& hang.getCurrentPosition()<target) {
                // Display it for the driver.
                telemetry.addData("Target Encoder Value: ",   target);
                telemetry.addData("Current Encoder Value: ", hang.getCurrentPosition());
                telemetry.update();
            }
            // Stop all motion;
            hang.setPower(0);
            // Turn off RUN_TO_POSITION
            hang.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }

 */

    private void resetAngle()
    {
        bsgBot.imuAngle = 0;
    }

    public double getHeading() {
        Orientation angles = bsgBot.imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES);
        double heading = angles.firstAngle;
        return heading;
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
        bsgBot.frontLeft.setPower(leftPower);
        bsgBot.backLeft.setPower(leftPower);
        bsgBot.frontRight.setPower(rightPower);
        bsgBot.backRight.setPower(rightPower);

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
        bsgBot.frontLeft.setPower(0);
        bsgBot.backLeft.setPower(0);
        bsgBot.frontRight.setPower(0);
        bsgBot.backRight.setPower(0);

        // wait for rotation to stop.
        sleep(1000);

        // reset angle tracking on new heading.
        resetAngle();
    }
}