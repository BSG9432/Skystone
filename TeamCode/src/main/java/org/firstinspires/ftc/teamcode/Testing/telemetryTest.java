package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

@TeleOp(name="TelemetryTesting")
public class telemetryTest extends OpMode {
    Robot bsgRobot = new Robot();
    @Override
    public void init() {
        bsgRobot.init(hardwareMap);
        telemetry.addLine("Robot is set up correctly");

    }

    @Override
    public void loop() {
        if(gamepad1.a){
            telemetry.addLine("The A button was pressed");
        }
        if(gamepad1.x){
            telemetry.addLine("The X button was pressed");
        }

    }
}
