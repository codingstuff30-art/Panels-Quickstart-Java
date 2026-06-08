package org.firstinspires.ftc.teamcode.Teleops;

import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "FIlthyClanker")
public class TeleOp extends OpMode {

    private DriveSubsystem myDriveTrain;
    private Shooter        myShooter;
    private PanelsTelemetry dashboard = PanelsTelemetry.INSTANCE;

    private Gamepad currentGamepad  = new Gamepad();
    private Gamepad previousGamepad = new Gamepad();

    private static final double DEADBAND    = 0.1;
    private static final double DRIVE_SCALE = 0.5;

    @Override
    public void init() {
        myDriveTrain = new DriveSubsystem(hardwareMap);
        myShooter    = new Shooter(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void init_loop() {
        telemetry.update();
    }

    @Override
    public void loop() {
        previousGamepad.copy(currentGamepad);
        currentGamepad.copy(gamepad1);

        if (currentGamepad.b && !previousGamepad.b) {
            myShooter.toggleMotor();
        }

        if (currentGamepad.a && !previousGamepad.a) {
            myShooter.togglePickUp();
        }

        // --- Joystick inputs with deadband and scaling ---
        double y  = applyDeadband(-currentGamepad.left_stick_y) * DRIVE_SCALE;
        double x  = applyDeadband( currentGamepad.left_stick_x) * DRIVE_SCALE;
        double rx = applyDeadband( currentGamepad.right_stick_x) * DRIVE_SCALE;

        myDriveTrain.drive(y, x, rx);

        // --- Telemetry ---
        telemetry.addData("y / x / rx", "%.2f  %.2f  %.2f", y, x, rx);
        telemetry.addData("Heading (deg)", "%.1f", myDriveTrain.getHeadingDegrees());
        telemetry.update();
    }

    @Override
    public void stop() {
        myDriveTrain.stop();
    }

    /** Returns 0 if |value| < DEADBAND, otherwise returns value unchanged. */
    private double applyDeadband(double value) {
        return Math.abs(value) < DEADBAND ? 0.0 : value;
    }
}
