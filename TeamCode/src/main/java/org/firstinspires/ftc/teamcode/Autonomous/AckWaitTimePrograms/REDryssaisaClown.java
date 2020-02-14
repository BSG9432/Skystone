package org.firstinspires.ftc.teamcode.Autonomous.AckWaitTimePrograms;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
@Autonomous (name = "REDryssaisaClown")
public class REDryssaisaClown extends LinearOpMode {


    Robot bsgRobot = new Robot();


    @Override
    public void runOpMode() throws InterruptedException {
        bsgRobot.init(hardwareMap);


        waitForStart();

        bsgRobot.moveForward(50);
        bsgRobot.strafeLeft(2000);
        sleep(500);

        //move backwards
        bsgRobot.moveForward(-.5);
        sleep(2000);

        bsgRobot.stopWheels();
        sleep(2000);

        bsgRobot.foundationDown();
        sleep(2000);

        bsgRobot.stopWheels();
        sleep(500);

        //moveforward
        bsgRobot.moveForward(.5);
        sleep(3000);

        bsgRobot.stopWheels();
        sleep(2000);

        bsgRobot.foundationUp();
        sleep(2000);

        bsgRobot.strafeRight(1000);
        sleep(000);


    }
}


// Shawn wishes u good luck *quack*