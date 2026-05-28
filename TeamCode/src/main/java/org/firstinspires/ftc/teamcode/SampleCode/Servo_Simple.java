package org.firstinspires.ftc.teamcode.SampleCode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;

/**
 *  The purpose of this class is to demonstrate how to program a simple servo. We define a simple
 *  servo as a servo that only sets positions. Adding @Config allows you to modify values in the
 *  FTCDashboard by declaring them here as public static instance variables. If there are 2 servos
 *  attached to the same joint, they must be at the same place at the same time and rotating in the
 *  same direction. We use ServoImplEx instead of Servo to increase the PWM range to utilize the
 *  entire range of the servo.
 *
 *  Before servos are physically installed, they must be set to their midpoint and installed such
 *  the midpoint is mid way between the endpoints that you would like the servo to move to.
 */

@Config
public class Servo_Simple {
    private final ServoImplEx servo;

    // @Config variables
    public static double position1 = .4;
    public static double position2 = .6;

    /**
     * this is the constructor, use it to set initial values to instant variables
     */
    public Servo_Simple(HardwareMap hardwareMap){
        servo = hardwareMap.get(ServoImplEx.class,"[device name in robot config]");

        // uncomment this line if the servo is going in the wrong direction
        // servo.setDirection(Servo.Direction.REVERSE);

        // when program initialized, send servo to this position
        servo.setPosition(position1);
    }

    /**
     * make one of these for every position you would like the servo to go to. Feel free to rename
     * the method.
     */
    public void goToPosition1(){
        servo.setPosition(position1);
    }
}
