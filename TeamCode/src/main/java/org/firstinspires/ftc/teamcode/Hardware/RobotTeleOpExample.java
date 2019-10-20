package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

@TeleOp(name = "RobotTeleOpExample")
@Disabled
public class RobotTeleOpExample extends OpMode {
    /*
    The cool thing about using classes is the your class "Robot" is essentially it's own type now
    bsgRobot comes from the classRobot, and we are creating a new Robot instance of a class.
    bsgRobot now have access to all of the methods and variables inside of the Robot class

    This class is designed to act as an example template of how to apply our robot class
    */
    Robot bsgRobot = new Robot();

    @Override
    public void init() {
        bsgRobot.init(hardwareMap);    //Initialize all the robot components
    }

    @Override
    public void loop() {


    }
}

