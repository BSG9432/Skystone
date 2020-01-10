package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

//test
@TeleOp (name = "TylaOp")

public class TylaOp extends OpMode {
    Robot bsgBot = new Robot();
   // public int speed = 1;

   /* public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public Servo leftFoundation;
    public Servo rightFoundation;

    public DcMotor lift;

    */


    @Override
    public void init() {
        bsgBot.init(hardwareMap);

        bsgBot.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bsgBot.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bsgBot.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bsgBot.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        bsgBot.rightFoundation.setPosition(1);
        bsgBot.leftFoundation.setPosition(0);
        bsgBot.clamp.setPosition(.4);

       // bsgBot.openClamp();
    }

    @Override
    public void loop() {

       /* if((gamepad1.dpad_up = true) && (speed < 1)) //makes speed go up when dpad up is pressed
        {
            speed += .25;
        }
        if((gamepad1.dpad_down = true) && (speed > -1))//makes speed go down when dpad down is pressed
        {
            speed -= .25;
        }
*/
        //For Right Motors
        if (Math.abs(gamepad1.right_stick_y) > .1) {
            bsgBot.frontRight.setPower(-gamepad1.right_stick_y);
            bsgBot.backRight.setPower(-gamepad1.right_stick_y);
        } else {
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(0);
        }

        //For Left Motors (KEEP IN MIND THAT LEFT MOTORS ARE SET AT REVERSE DIRECTION IN THE ROBOT OBJECT CLASS)
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            bsgBot.frontLeft.setPower(-gamepad1.left_stick_y);
            bsgBot.backLeft.setPower(-gamepad1.left_stick_y);
        } else {
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(0);
        }
        telemetry.addData("Front Right Value: ", bsgBot.frontRight.getPower());
        telemetry.addData("Back Right Value: ", bsgBot.backRight.getPower());
        telemetry.addData("Front Left Value: ", bsgBot.frontLeft.getPower());
        telemetry.addData("Back  Left Value: ", bsgBot.backLeft.getPower());
        telemetry.update();

        // TO CONTROL THE FOUNDATION SERVOS
        if (gamepad1.a) //Up Position
        {
            bsgBot.rightFoundation.setPosition(1);
            bsgBot.leftFoundation.setPosition(0);
        }
        if (gamepad1.b) //Down Position
        {
            bsgBot.rightFoundation.setPosition(0);
            bsgBot.leftFoundation.setPosition(1);
        }
        //open clamp
        if (gamepad1.y)
        {
            bsgBot.clamp.setPosition(.4);
        }
        //close clamp
        if (gamepad1.x)
        {
            bsgBot.clamp.setPosition(.7);
        }

        //side arm up
        if (gamepad1.left_bumper) {
            bsgBot.sideArm.setPower(.5);
            telemetry.addData("sideArm Power",bsgBot.sideArm.getPower());
            telemetry.addData("bumperValue", gamepad1.left_bumper);
        }
        //side arm down
        else if (gamepad1.right_bumper) {
            bsgBot.sideArm.setPower(-.5);
            telemetry.addData("sideArm Power",bsgBot.sideArm.getPower());
            telemetry.addData("bumperValue", gamepad1.right_bumper);
        }
        else {
            bsgBot.sideArm.setPower(0);
        }

        // BUMPERS TO CONTROL CLAW
        /*
        if (gamepad1.left_bumper) {
        bsgRobot.rightClaw.setPosition(.4);
        bsgRobot.leftClaw.setPosition(.6);
        }
        if(gamepad1.right_bumper)
        {
            bsgRobot.rightClaw.setPosition(.9);
            bsgRobot.leftClaw.setPosition(.1);
        }
*/
        // TRIGGERS TO CONTROL THE LIFT
        //if (gamepad1.left_trigger > .1)
        //{
        //bsgRobot.lift.setPower(-.5);
        //bsgRobot.lift.setPower(.5)
        // }

        if (gamepad1.right_trigger > .1) // strafe Right
        {
            bsgBot.frontRight.setPower(-gamepad1.right_trigger);
            bsgBot.backRight.setPower(gamepad1.right_trigger);
            bsgBot.frontLeft.setPower(gamepad1.right_trigger);
            bsgBot.backLeft.setPower(-gamepad1.right_trigger);
        } else {
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(0);
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(0);
        }


        if (gamepad1.left_trigger > .1) // strafe Left
        {
            bsgBot.frontRight.setPower(gamepad1.left_trigger);
            bsgBot.backRight.setPower(-gamepad1.left_trigger);
            bsgBot.frontLeft.setPower(-gamepad1.left_trigger);
            bsgBot.backLeft.setPower(gamepad1.left_trigger);
        } else {
            bsgBot.frontRight.setPower(0);
            bsgBot.backRight.setPower(0);
            bsgBot.frontLeft.setPower(0);
            bsgBot.backLeft.setPower(0);
        }

        /*//move lift up and down
        if (Math.abs(gamepad2.left_stick_y) > .1) {
            bsgBot.lift.setPower(gamepad2.left_stick_y);
        }
        else {
            bsgBot.lift.setPower(0);
        }

        //wheel intake
        if (Math.abs(gamepad2.left_trigger) > .1){
            bsgBot.leftIntake.setPower(gamepad2.left_trigger);
            bsgBot.rightIntake.setPower(-gamepad2.left_trigger);
        }
        else {
            bsgBot.leftIntake.setPower(0);
        }
        //wheel outtake
        if (Math.abs(gamepad2.right_trigger) > .1){
            bsgBot.leftIntake.setPower(-gamepad2.right_trigger);
            bsgBot.rightIntake.setPower(gamepad2.right_trigger);
        }
        else {
            bsgBot.rightIntake.setPower(0);
            bsgBot.rightIntake.setPower(0);
        }

        //close intake
        if (gamepad2.left_bumper){
            bsgBot.clampL.setPosition(0);
            bsgBot.clampR.setPosition(1);
        }
        telemetry.addData("IntakeL: ", bsgBot.clampL.getPosition());
        telemetry.addData("IntakeR: ", bsgBot.clampR.getPosition());

        //open intake
        if (gamepad2.right_bumper) {
           bsgBot.openClamp();
        }
        //close intake
        if (gamepad2.left_bumper){
            bsgBot.closeClamp();
        }

         */
    }

}
