package org.firstinspires.ftc.teamcode.SampleCode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 *  The purpose of this class is to demonstrate how to program an enhanced servo. We define an
 *  enhanced servo as a servo that not only sets positions but also how long it will take to get
 *  there. Adding @Config allows you to modify values in the FTCDashboard by declaring them here
 *  as public static instance variables. We use ServoImplEx instead of Servo to increase the PWM
 *  range to utilize the entire range of the servo.
 *
 *  Before servos are physically installed, they must be set to their midpoint and installed such
 *  the midpoint is mid way between the endpoints that you would like the servo to move to.
 */
@Config
public class Servo_Enhanced {
    private final ServoImplEx servo;
    private double currentPos;
    private double target;
    private int time;
    private final ElapsedTime timer = new ElapsedTime();
    private double moveIncrement;

    // @Config variables
    public static double position1 = .4;
    public static double position2 = .6;
    public static int timeFromPosition1ToPosition2 = 5000; //time in milliseconds

    /**
     * this is the constructor, use it to set initial values to instant variables. If there
     * are 2 servos attached to the same joint, they must be at the same place at the same
     * time and rotating in the same direction.
     */
    public Servo_Enhanced(HardwareMap hardwareMap) {
        servo = hardwareMap.get(ServoImplEx.class, "[device name in robot config]");

        // uncomment this line if the servo is going in the wrong direction
        // servo.setDirection(Servo.Direction.REVERSE);

        // when program initialized, send servo to this position. Just be mindful that the
        // servo will move at full speed
        servo.setPosition(position1);
        currentPos = target;
    }

    /**
     * make one of these for every position you would like the servo to go to. Feel free to
     * rename the method.
     */
    public void goFromPosition1ToPosition2() {
        setTarget(position2, timeFromPosition1ToPosition2);
    }

    /**
     * Will set desired target for the servo and the time it will take to get there
     * servo will move gradually to target in the update function
     *
     * @param input value between 0 and 1, make sure servo can handle it
     * @param t     time it will take to go from current location to new location in milliseconds,
     *              a value of 0 will move at the max servo speed
     */
    public void setTarget(double input, int t) {
        if (input != currentPos) {
            target = input;
            time = t;
            timer.reset();
            moveIncrement = (input - currentPos) / t;
        }
    }

    /**
     * standard update function that will move the wrist if not at the desired location.
     * you call this method in your main program's loop.
     */
    public void update() {
        double duration = timer.milliseconds();
        if (Math.abs(currentPos - target) > .01 && duration < time) {
            double temp = currentPos + duration * moveIncrement;
            servo.setPosition(temp);
        } else {
            servo.setPosition(target);
            currentPos = target;
        }

    }
}

