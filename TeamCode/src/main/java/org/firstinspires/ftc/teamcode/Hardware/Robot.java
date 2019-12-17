package org.firstinspires.ftc.teamcode.Hardware;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Locale;

//NOTE: This is off the top of my head

public class Robot {
  public DcMotor frontLeft;
  public DcMotor frontRight;
  public DcMotor backLeft;
  public DcMotor backRight;
  public DcMotor lift;

  public BNO055IMU imu;
  public double imuAngle;

  public static Orientation angles;
  Acceleration gravity;

  //for moving the foundation
  public Servo leftFoundation;
  public Servo rightFoundation;


  public static Telemetry telemetry;

  public Robot() { //constructor

  }

  public void init(HardwareMap hMap) {
    frontLeft = hMap.dcMotor.get("frontLeft");
    backLeft = hMap.dcMotor.get("backLeft");
    frontRight = hMap.dcMotor.get("frontRight");
    backRight = hMap.dcMotor.get("backRight");

    frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    lift = hMap.dcMotor.get("lift");

    leftFoundation = hMap.servo.get("leftFoundation");
    rightFoundation = hMap.servo.get("rightFoundation");

    //leftClaw = hMap.servo.get("leftClaw");
    //rightClaw = hMap.servo.get("rightClaw");

    //Telemetry to show on phone to confirm that  initialization occured
    //telemetry.addLine("We done bois");//DS
    //Lines that show up in the internal log (can be accessed on the phone
    //Log.d("#BSG", "Started Encoders");
    //Log.d("#ROBOTSTUFF", "Robot Initalized");//Internal Log
  }

  public void initIMU(HardwareMap hMap) {

    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

    parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
    parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
    parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
    parameters.loggingEnabled = true;
    parameters.loggingTag = "IMU";
    parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

    // Retrieve and initialize the IMU
    imu = hMap.get(BNO055IMU.class, "imu");
    imu.initialize(parameters);

    // Set up our telemetry dashboard
    composeTelemetry();

  }

  public void encoderDrive(double speed,
                           double leftInches, double rightInches,
                           double timeoutS) {
    int newLeftTarget;
    int newRightTarget;

    // Ensure that the opmode is still active
    if (opModeIsActive()) {

      // Determine new target position, and pass to motor controller
      newLeftTarget = bsgRobot.frontLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
      newLeftTarget = bsgRobot.backLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
      newRightTarget = bsgRobot.frontRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
      newRightTarget = bsgRobot.backRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);

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
              (bsgRobot.frontLeft.isBusy() && bsgRobot.frontRight.isBusy() &&
                      bsgRobot.backLeft.isBusy() && bsgRobot.backRight.isBusy())) {

        // Display it for the driver.
        telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
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

  public void moveForward(double power) {
    frontLeft.setPower(power);
    backLeft.setPower(power);
    frontRight.setPower(power);
    backRight.setPower(power);
  }

  public void stopWheels() {
    frontLeft.setPower(0);
    backLeft.setPower(0);
    frontRight.setPower(0);
    backRight.setPower(0);
  }


  public void composeTelemetry() {

    // At the beginning of each telemetry update, grab a bunch of data
    // from the IMU that we will then display in separate lines.
    telemetry.addAction(new Runnable() { @Override public void run()
    {
      // Acquiring the angles is relatively expensive; we don't want
      // to do that in each of the three items that need that info, as that's
      // three times the necessary expense.
      angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
      gravity  = imu.getGravity();
    }
    });

    telemetry.addLine()
            .addData("status", new Func<String>() {
              @Override public String value() {
                return imu.getSystemStatus().toShortString();
              }
            })
            .addData("calib", new Func<String>() {
              @Override public String value() {
                return imu.getCalibrationStatus().toString();
              }
            });

    telemetry.addLine()
            .addData("x", new Func<String>() {
              @Override public String value() {
                return formatAngle(angles.angleUnit, angles.firstAngle);
              }
            })
            .addData("y", new Func<String>() {
              @Override public String value() {
                return formatAngle(angles.angleUnit, angles.secondAngle);
              }
            })
            .addData("z", new Func<String>() {
              @Override public String value() {
                return formatAngle(angles.angleUnit, angles.thirdAngle);
              }
            });

    telemetry.addLine()
            .addData("grvty", new Func<String>() {
              @Override public String value() {
                return gravity.toString();
              }
            })
            .addData("mag", new Func<String>() {
              @Override public String value() {
                return String.format(Locale.getDefault(), "%.3f",
                        Math.sqrt(gravity.xAccel*gravity.xAccel
                                + gravity.yAccel*gravity.yAccel
                                + gravity.zAccel*gravity.zAccel));
              }
            });
  }

  String formatAngle(AngleUnit angleUnit, double angle) {
    return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
  }

  String formatDegrees(double degrees){
    return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
  }

  public void resetAngle()
  {
    imuAngle = 0;
  }

  public double getHeading() {
    angles = imu.getAngularOrientation(AxesReference.INTRINSIC,
            AxesOrder.ZYX, AngleUnit.DEGREES);
    double heading = angles.firstAngle;
    return heading;
  }

  public void foundationDown(int pause) {
    bsgRobot.rightFoundation.setPosition(.2);
    bsgRobot.leftFoundation.setPosition(.8);
    sleep(pause);
  }

  public void foundationUp(int pause) {
    bsgRobot.rightFoundation.setPosition(1);
    bsgRobot.leftFoundation.setPosition(0);
    sleep(pause);
  }

  public void strafeLeft(long time) {
    bsgRobot.frontRight.setPower(1);
    bsgRobot.backRight.setPower(-1);
    bsgRobot.frontLeft.setPower(-1);
    bsgRobot.backLeft.setPower(1);
    sleep(time);
  }

  public void strafeRight(long time) {
    bsgRobot.frontRight.setPower(-1);
    bsgRobot.backRight.setPower(1);
    bsgRobot.frontLeft.setPower(1);
    bsgRobot.backLeft.setPower(-1);
    sleep(time);
  }
}
