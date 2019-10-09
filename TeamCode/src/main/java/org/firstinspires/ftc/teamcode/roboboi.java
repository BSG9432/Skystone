package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class roboboi {
    public void setup (){
        DcMotor frontLeft;
        DcMotor frontRight;
        DcMotor backLeft;
        DcMotor backRight;
    }
    public void init(HardwareMap hwmap) {
        frontLeft = hardwareMap.dcMotor.get();
    }

}
