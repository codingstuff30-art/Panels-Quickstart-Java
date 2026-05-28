package org.firstinspires.ftc.teamcode.SampleCode;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 *  The purpose of this class is to demonstrate how to program an elevator while using a PID
 *  controller. Adding @Config allows you to modify values in the FTCDashboard by declaring them here
 *  as public static instance variables.
 */
public class PID_Motor {
    private final DcMotorEx motor;
    private int targetPosition = 0;
    private final PIDController controller;

    // use a digital level and determine the starting angle. level (straight out) is 0 degrees
    private int startingAngleInDegrees;

    // @Config variables
    public static double kP = 0;
    public static double kI = 0;
    public static double kD = 0;
    public static int position1 = 200;
    public static int position2 = 600;
    public static double maxPower = .4; // used to limit max power to motor, increase as necessary


    public PID_Motor(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, "[device name in robot config]");

        // uncomment this line if the motor is going in the wrong direction. Positive should be up.
        // motor.setDirection(DcMotorSimple.Direction.REVERSE);

        motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        controller = new PIDController(kP, kI, kD);
    }

    // when given positive values, the arm should go up
    public void manualMove(double input) {
        motor.setPower(input);
    }

    public void goToPosition1(){
        targetPosition = position1;
    }

    public void goToPosition2(){
        targetPosition = position2;
    }

    public int error(){
        return Math.abs(targetPosition- motor.getCurrentPosition());
    }

    /**
     * use this to tune and to use your PID controlled arm. For tuning, graph the return of the error
     * method and move between 2 set points of your choosing. Gradually increase P, starting from
     * .00001 until the error is low. There should be no oscillation. Then gradually increase D,
     * starting from .000001 until the error is almost 0.
     */
    public void update() {
        controller.setPID(kP, kI, kD);
        double output = this.controller.calculate(motor.getCurrentPosition(), this.targetPosition);
        output = limiter(output, maxPower);
        motor.setPower(output);
    }

    /**
     * this will limit the input to a range of -limiter to limiter
     * @param input the value to be limited
     * @param limiter the max value the input can be
     * @return the limited input
     */
    private double limiter(double input, double limiter){
        if (input > limiter) {
            input = limiter;
        } else if (input < -limiter) {
            input = -limiter;
        }
        return input;
    }
}
