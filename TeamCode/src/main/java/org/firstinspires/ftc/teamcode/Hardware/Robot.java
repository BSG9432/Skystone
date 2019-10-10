package org.firstinspires.ftc.teamcode.Hardware;
import com.qualcomm.hardware.bosch.BNO055IMU;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.*;
import com.vuforia.CameraDevice;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

//NOTE: This is off the top of my head

public class Robot {
  public DcMotor frontLeft;
  public DcMotor frontRight;
  public DcMotor backLeft;
  public DcMotor backRight;

  public Servo myServo;
}

public Robot(){ //constructor

}

public void init(HardwareMap hMap){
  frontLeft = hMap.dcMotor.get("frontLeft");
  //TODO: Fill the rest of motors out.
}

public void moveForward(double power){
  frontLeft.setPower(power);
  //TODO: Fill the rest out.
}
