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

    double ClampOffset = 0.1;
    final double ClampSpeed = 0.4;

    double StringOffset = 0.2;
    final double StringSpeed = 1;

    DcMotor FrontR;
    DcMotor BackR;
    DcMotor FrontL;
    DcMotor BackL;
    DcMotor ArmHinge;

    Servo Extender;
    Servo Claw;


    public static final double STRING = 0.5;
    public static final double LEFT_CLAMP = 0.5;
    public static final double RIGHT_CLAMP = 0.5;

    double x = 0;
    double y = 0;

    @Override
    public void init() {

        FrontR = hardwareMap.dcMotor.get("motor_3");
        FrontL = hardwareMap.dcMotor.get("motor_4");
        BackR = hardwareMap.dcMotor.get("motor_2");
        BackL = hardwareMap.dcMotor.get("motor_1");
        ArmHinge = hardwareMap.dcMotor.get("motor_5");


        FRight.setDirection(DcMotor.Direction.REVERSE);
        BLeft.setDirection(DcMotor.Direction.REVERSE);

        Claw = hardwareMap.servo.get("servo_1");
        Extender = hardwareMap.servo.get("servo_2");


        Clamp1.setPosition(.5);

        Clamp5.setPosition(.5);
        Clamp6.setPosition(.5);

        LeftClamp.setPosition(.5);
        RightClamp.setPosition(.5);

        String3.setPosition(.5);
        String4.setPosition(.5);
    }

    @Override
    public void loop() {
        float LeftY = -gamepad1.left_stick_y;
        float LeftX = gamepad1.left_stick_x;
        float RightX = gamepad1.right_stick_x;
        float RightY = -gamepad1.right_stick_y;
        float RightY2 = gamepad2.right_stick_y;
        float LeftY2 = gamepad2.left_stick_y;
        //Turning and Clamp and String

        double Front = -scaleInput(LeftX) - scaleInput(RightX);
        double Right = scaleInput(LeftY) - scaleInput(RightX);
        double Back = scaleInput(LeftX) - scaleInput(RightX);
        double Left = -scaleInput(LeftY) - scaleInput(RightX);

        Front = Range.clip(Front, -1, 1);
        Right = Range.clip(Right, -1, 1);
        Back = Range.clip(Back, -1, 1);
        Left = Range.clip(Left, -1, 1);

        FrontM.setPower(Front);
        RightM.setPower(Right);
        BackM.setPower(Back);
        LeftM.setPower(Left);

        FRight.setPower(LeftY2);
        BLeft.setPower(LeftY2);
        BRight.setPower(LeftY2);
        FLeft.setPower(LeftY2);

        StringOffset = Range.clip(ClampOffset, -1, 1);
        if (gamepad2.a) {
            StringOffset += StringSpeed;
            Clamp1.setPosition(StringOffset);
        } else if (gamepad2.y) {
            StringOffset -= StringSpeed;
            Clamp1.setPosition(StringOffset);
        } else {
            Clamp1.setPosition(.5);
        }

        StringOffset = Range.clip(ClampOffset, -1, 1);
        if (gamepad1.dpad_up) {
            StringOffset -= StringSpeed;
            String3.setPosition(StringOffset);
            String4.setPosition(StringOffset);
        } else if (gamepad1.dpad_down) {
            StringOffset += StringSpeed;
            String4.setPosition(StringOffset);
            String3.setPosition(StringOffset);
        } else {
            String4.setPosition(.5);
            String3.setPosition(.5);
        }

        if (gamepad2.right_bumper) {
            Clamp6.setPosition(1);
            Clamp5.setPosition(0);
        } else if (gamepad2.left_bumper) {
            Clamp5.setPosition(1);
            Clamp6.setPosition(0);
        } else {
            Clamp5.setPosition(.5);
            Clamp6.setPosition(.5);
        }

        ClampOffset = Range.clip(ClampOffset, -1, 1);
        if (gamepad2.right_trigger > 0.5) {
            ClampOffset += ClampSpeed;
            LeftClamp.setPosition(ClampOffset);
            RightClamp.setPosition(-ClampOffset);
        } else if (gamepad2.left_trigger > 0.5) {
            ClampOffset += ClampSpeed;
            LeftClamp.setPosition(-ClampOffset);
            RightClamp.setPosition(ClampOffset);
        } else {
            LeftClamp.setPosition(.5);
            RightClamp.setPosition(.5);
        }
    }

}