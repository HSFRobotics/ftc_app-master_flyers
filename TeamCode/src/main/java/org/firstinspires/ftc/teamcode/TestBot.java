package org.firstinspires.ftc.teamcode;

/**
 * Created by twiese on 9/17/2017.
 */

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

@TeleOp (name= "TestBot", group="Pushbot")
public class TestBot extends OpMode {

    HardwarePushbot robot = new HardwarePushbot();

    DcMotor FrontR;
    DcMotor BackR;
    DcMotor FrontL;
    DcMotor BackL;
    DcMotor ArmHinge;

    Servo Extender;
    Servo Claw;

    @Override
    public void init() {

        FrontR = hardwareMap.dcMotor.get("motor_3");
        FrontL = hardwareMap.dcMotor.get("motor_4");
        BackR = hardwareMap.dcMotor.get("motor_1");
        BackL = hardwareMap.dcMotor.get("motor_2");
        ArmHinge = hardwareMap.dcMotor.get("motor_5");

        FrontR.setDirection(DcMotor.Direction.REVERSE);
        BackR.setDirection(DcMotor.Direction.REVERSE);


        Claw = hardwareMap.servo.get("servo1");
        Extender = hardwareMap.servo.get("servo2");


        Claw.setPosition(.5);
        Extender.setPosition(.5);


    }

    @Override
    public void loop() {
        float LeftY = gamepad1.left_stick_y;
        float LeftX = gamepad1.left_stick_x;
        float RightX = gamepad1.right_stick_x;
        float RightY = gamepad1.right_stick_y;
        float RightY2 = gamepad2.right_stick_y;
        float LeftY2 = gamepad2.left_stick_y;
        float RightX2 = gamepad2.right_stick_x;

        FrontL.setPower(LeftY);
        BackL.setPower(LeftY);
        FrontR.setPower(RightY);
        BackR.setPower(RightY);




    }

}