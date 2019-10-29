package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.Hardware.Robot;

@Autonomous(name="BlueBuilding")
public class BlueBuilding extends LinearOpMode {

    //taking the hardware from our Robot class with our hardware
    Robot bsgRobot = new Robot();

    //for encoders...
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // Neverest 40
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {

        bsgRobot.init(hardwareMap);
        AutoTransitioner.transitionOnStop(this, "TylaOp");

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        bsgRobot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bsgRobot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bsgRobot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bsgRobot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        bsgRobot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bsgRobot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bsgRobot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bsgRobot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                bsgRobot.frontLeft.getCurrentPosition(),
                bsgRobot.backLeft.getCurrentPosition(),
                bsgRobot.frontRight.getCurrentPosition(),
                bsgRobot.backRight.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)

        /*encoderDrive(.8,  40,  40, 3); //forward 40 inches towards foundation

        sleep(500);

        foundationDown(800); //grab foundation

        encoderDrive(.8, -40, -40, 3); //drag foundation backwards 40 inches into build zone

        sleep(500);

        foundationUp(800); //let go of foundation */

       // rotate(90, .8); //rotate RIGHT to face towards alliance bridge

        bsgRobot.frontLeft.setPower(.5);
        bsgRobot.backLeft.setPower(.5);
        bsgRobot.frontRight.setPower(-.3);
        bsgRobot.backRight.setPower(-.3);
        sleep(750);

       /* sleep(500);

        encoderDrive(.8, 35, 35, 3); //drive forward 35 inches to park under alliance bridge

        sleep(500);

        */

        telemetry.addData("Path", "Complete");
        telemetry.update();

       // AutoTransitioner.transitionOnStop(this, "TylaOp");
    }

    /*
     *  Method to perfmorm a relative move, based on encoder counts.
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
            newLeftTarget =  bsgRobot.frontLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newLeftTarget =  bsgRobot.backLeft.getCurrentPosition()  + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget =  bsgRobot.frontRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newRightTarget =  bsgRobot.backRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

            bsgRobot.frontLeft.setTargetPosition(newLeftTarget);
            bsgRobot.backLeft.setTargetPosition(newLeftTarget);
            bsgRobot.frontRight.setTargetPosition(newRightTarget);
            bsgRobot.backRight.setTargetPosition(newRightTarget);


            // Turn On RUN_TO_POSITION
            bsgRobot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bsgRobot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bsgRobot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bsgRobot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            bsgRobot.frontLeft.setPower(Math.abs(speed));
            bsgRobot.backLeft.setPower(Math.abs(speed));
            bsgRobot.frontRight.setPower(Math.abs(speed));
            bsgRobot.backRight.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    ( bsgRobot.frontLeft.isBusy() &&  bsgRobot.frontRight.isBusy() &&
                            bsgRobot.backLeft.isBusy() &&  bsgRobot.backRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        bsgRobot.frontLeft.getCurrentPosition(),
                        bsgRobot.backLeft.getCurrentPosition(),
                        bsgRobot.frontRight.getCurrentPosition(),
                        bsgRobot.backRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            bsgRobot.stopWheels();

            // Turn off RUN_TO_POSITION
            bsgRobot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bsgRobot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bsgRobot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bsgRobot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

    //rotate function using IMU's
    public void rotate (int degrees, double power) {

        double leftPower, rightPower;

        //restart imu movement tracking
        bsgRobot.resetAngle();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        if (degrees < 0) {   // turn left.
            leftPower = power;
            rightPower = .3;
            telemetry.addLine("left");
            telemetry.update();
        } else if (degrees > 0) {   // turn right.
            leftPower = -.3;
            rightPower = -power;
            telemetry.addLine("right");
            telemetry.update();
        } else return;

        // set power to rotate.
        bsgRobot.frontLeft.setPower(leftPower);
        bsgRobot.backLeft.setPower(leftPower);
        bsgRobot.frontRight.setPower(rightPower);
        bsgRobot.backRight.setPower(rightPower);

        // rotate until turn is completed.
        if (degrees < 0) //-10
        {
            // On left turn we have to get off zero first.
            while (opModeIsActive() && bsgRobot.getHeading() == 0) {
            }

            while (opModeIsActive() && bsgRobot.getHeading() < degrees) {
            }
        } else    // right turn.
            while (opModeIsActive() && bsgRobot.getHeading() > degrees) {
            }

        // turn the motors off.
        bsgRobot.frontLeft.setPower(0);
        bsgRobot.backLeft.setPower(0);
        bsgRobot.frontRight.setPower(0);
        bsgRobot.backRight.setPower(0);

        // wait for rotation to stop.
        sleep(1000);

        // reset angle tracking on new heading.
        bsgRobot.resetAngle();

    }

    public void foundationDown(long pause)
    {
        bsgRobot.rightFoundation.setPosition(1);
        bsgRobot.leftFoundation.setPosition(0);
        sleep(pause);
    }

    public void foundationUp(long pause)
    {
        bsgRobot.rightFoundation.setPosition(.1);
        bsgRobot.leftFoundation.setPosition(.9);
        sleep(pause);
    }
}


