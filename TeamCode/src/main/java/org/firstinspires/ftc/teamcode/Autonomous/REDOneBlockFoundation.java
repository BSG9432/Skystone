package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.KNO3AutoTransitioner.AutoTransitioner;
import org.firstinspires.ftc.teamcode.Hardware.Robot;


@Autonomous(name="REDOneBlockFoundation", group="UWU")
public class REDOneBlockFoundation extends LinearOpMode {
    Robot bsgRobot = new Robot();

    /*
    *
    VARIABLES FOR ENCODERS
    *
    */
    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1120;    // Neverest 40
    static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;

    //For turning with encoders
    Integer cpr = 28; //counts per rotation originally 28
    Integer gearratio = 40; //IDK IT WAS ORIGINALLY 40
    Double diameter = 4.0;
    Double cpi = (cpr * gearratio) / (Math.PI * diameter); //counts per inch, 28cpr * gear ratio / (2 * pi * diameter (in inches, in the center))
    Double bias = 0.8;//default 0.8
    Double meccyBias = 0.9;//change to adjust only strafing movement (was .9)
    //
    Double conversion = cpi * bias;
    Boolean exit = false;

    @Override
    public void runOpMode() {
        /*
         * Retrieve the camera we are to use.
         */
        bsgRobot.init(hardwareMap);

        bsgRobot.foundationUp();
        bsgRobot.closeClamp();

        waitForStart();

//Put the arm stop up
        bsgRobot.armStopUp();
        sleep(500);

        //Use encoders to put the arm down
        armDown();
        sleep(500);

        //open the clamp
        bsgRobot.openClamp();
        sleep(750);

        //drive 18 inches forward towards stones
        encoderDrive(.6, 18, 18, 4);

        //close clamp
        bsgRobot.closeClamp();
        sleep (600);

        //drive 2.5 inches backwards
        encoderDrive(.7, -2.5, -2.5, 3.0);

        //rotate CCW approximately 90
        encoderDrive(.7,-10, 10, 3.0);

        //drive forward 25 inches into the building side
        encoderDrive(.7, 25, 25, 3.0);

        //release stone
        bsgRobot.openClamp();
        sleep(600);

        //drive 12 inches backward under alliance bridge
        encoderDrive(.7, -12, -12,3.0);


        //auto transitioner to automatically switch to TeleOp
        //AutoTransitioner.transitionOnStop(this, "TylaOp");
    }


    /*
    *
    *
    *
    FUNCTIONS FOR ENCODERS
    *
    *
    *
    *
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = bsgRobot.frontLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newBackLeftTarget = bsgRobot.backLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newFrontRightTarget = bsgRobot.frontRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newBackRightTarget = bsgRobot.backRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);

            bsgRobot.frontLeft.setTargetPosition(newFrontLeftTarget);
            bsgRobot.backLeft.setTargetPosition(newBackLeftTarget);
            bsgRobot.frontRight.setTargetPosition(newFrontRightTarget);
            bsgRobot.backRight.setTargetPosition(newBackRightTarget);


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
                    (bsgRobot.frontLeft.isBusy() && bsgRobot.frontRight.isBusy() &&
                            bsgRobot.backLeft.isBusy() && bsgRobot.backRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newFrontLeftTarget, newFrontRightTarget,
                        newBackLeftTarget, newBackRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
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

    //strafing with encoders
    public void strafeToPosition(double inches, double speed) {
        //
        int move = (int) (Math.round(inches * cpi * meccyBias * 1.265));
        //
        bsgRobot.backLeft.setTargetPosition(bsgRobot.backLeft.getCurrentPosition() - move);
        bsgRobot.frontLeft.setTargetPosition(bsgRobot.frontLeft.getCurrentPosition() + move);
        bsgRobot.backRight.setTargetPosition(bsgRobot.backRight.getCurrentPosition() + move);
        bsgRobot.frontRight.setTargetPosition(bsgRobot.frontRight.getCurrentPosition() - move);
        //
        bsgRobot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bsgRobot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bsgRobot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bsgRobot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //
        bsgRobot.frontLeft.setPower(speed);
        bsgRobot.backLeft.setPower(speed);
        bsgRobot.frontRight.setPower(speed);
        bsgRobot.backRight.setPower(speed);
        //
        while (bsgRobot.frontLeft.isBusy() && bsgRobot.frontRight.isBusy() &&
                bsgRobot.backLeft.isBusy() && bsgRobot.backRight.isBusy()) {
        }
        bsgRobot.frontRight.setPower(0);
        bsgRobot.frontLeft.setPower(0);
        bsgRobot.backRight.setPower(0);
        bsgRobot.backLeft.setPower(0);
        return;

    }

    //encoders for arm
    public void armEncoder(double speed,
                           int targetTicks, double timeoutS) {
        int newTarget;
        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newTarget = bsgRobot.arm.getCurrentPosition() + (int) (targetTicks);
            //newLeftTarget = bsgRobot.backLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            //newRightTarget = bsgRobot.frontRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            // newRightTarget = bsgRobot.backRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

            bsgRobot.arm.setTargetPosition(newTarget);
            // bsgRobot.backLeft.setTargetPosition(newLeftTarget);
            // bsgRobot.frontRight.setTargetPosition(newRightTarget);
            // bsgRobot.backRight.setTargetPosition(newRightTarget);


            // Turn On RUN_TO_POSITION
            bsgRobot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //bsgRobot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // bsgRobot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // bsgRobot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            bsgRobot.arm.setPower(Math.abs(speed));
            //bsgRobot.backLeft.setPower(Math.abs(speed));
            // bsgRobot.frontRight.setPower(Math.abs(speed));
            // bsgRobot.backRight.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (bsgRobot.arm.isBusy() /*&& bsgRobot.frontRight.isBusy() &&
                            bsgRobot.backLeft.isBusy() && bsgRobot.backRight.isBusy()*/)) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to  :%7d", targetTicks);
                telemetry.addData("Path2", "Running at 3 :%7d",
                        bsgRobot.arm.getCurrentPosition()
                        /*bsgRobot.backLeft.getCurrentPosition(),
                        bsgRobot.frontRight.getCurrentPosition(),
                        bsgRobot.backRight.getCurrentPosition()*/);
                telemetry.update();
            }

            // Stop all motion;
            bsgRobot.arm.setPower(0);

            // Turn off RUN_TO_POSITION
            bsgRobot.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //bsgRobot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            // bsgRobot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            // bsgRobot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

    public void armUp() {
        armEncoder(.4, -360, 2);
    }

    public void armDown(){
        armEncoder(.4,400,2);
    }

}
