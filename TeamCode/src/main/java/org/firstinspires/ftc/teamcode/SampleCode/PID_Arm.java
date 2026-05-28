package org.firstinspires.ftc.teamcode.SampleCode;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 *  The purpose of this class is to demonstrate how to program an arm while using a PID
 *  controller. Adding @Config allows you to modify values in the FTCDashboard by declaring them here
 *  as public static instance variables.
 */
public class PID_Arm {
    private final DcMotorEx motor;
    private int targetPosition = 0;
    private final PIDController controller;
    private double degreesPerTick = 0;

    // use a digital level and determine the starting angle. level (straight out) is 0 degrees
    private int startingAngleInDegrees;

    // @Config variables
    public static double kP = 0;
    public static double kI = 0;
    public static double kD = 0;
    public static double ff = 0;
    public static int position1 = 200;
    public static int position2 = 600;
    public static double maxPower = .4; // used to limit max power to motor, increase as necessary


    public PID_Arm(HardwareMap hardwareMap) {
        // TODO - what did you name your motor in Robot Config? stick it in here
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

    // physically move the arm to 0 degrees (straight out) and continue to increase ff until the
    // arm does not fall. Record this number. Continue to increase ff until the arm starts moving up.
    // record this number. Find that average between those 2 numbers and use that as the ff value.
    public void determineFF() {
        motor.setPower(ff);
    }

    // when you test this, send the return value of this method to the FTCdashboard. physically move
    // the arm to 0 degrees (straight out). Record this number. physically move the arm to 90 degrees
    // (straight up). Record this number. take the absolute value of 90 divided by the difference of
    // the 2 numbers recorded.
    //make su
    public int determineDegreesPerTick() {
        return motor.getCurrentPosition();
    }

    // this method will calculate the angle of the arm. You can call on this method in your main
    // program and see if you are getting correct angles.
    public double calcAngleInDegrees() {
        //if the arm rotates down from start use this
        return startingAngleInDegrees - motor.getCurrentPosition() * degreesPerTick;

        // otherwise use this
        // return startingAngleInDegrees + motor.getCurrentPosition() * degreesPerTick;
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
        output = limiter(output, maxPower) + ff * Math.cos(Math.toRadians(calcAngleInDegrees()));
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
