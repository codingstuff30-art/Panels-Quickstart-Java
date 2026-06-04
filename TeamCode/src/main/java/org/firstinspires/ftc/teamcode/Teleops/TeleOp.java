package org.firstinspires.ftc.teamcode.Teleops;

//import com.acmerobotics.dashboard.FtcDashboard;
//import com.arcrobotics.ftclib.gamepad.GamepadEx;
//import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.bylazar.telemetry.PanelsTelemetry;
        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.bylazar.gamepad.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;


        import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "FIlthyClanker")

public class TeleOp extends OpMode {

    private DriveSubsystem myDriveTrain;
    private Shooter myShooter;
    private Gamepad g1;
    private PanelsTelemetry dashboard = PanelsTelemetry.INSTANCE;


    @Override
    public void init() {
        myDriveTrain = new DriveSubsystem(hardwareMap);
        myShooter    = new Shooter(hardwareMap);
        g1           = new Gamepad();

        myDriveTrain.setHeadingToMaintain(0);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void init_loop() {
        telemetry.addData("Target Heading (deg)", myDriveTrain.getHeadingToMaintain());
        telemetry.addData("Current Heading (deg)", myDriveTrain.getCurrentHeadingDeg());
        telemetry.update();
    }

    @Override
    public void start() {
        myDriveTrain.setHeadingToMaintain(myDriveTrain.getCurrentHeadingDeg());
    }

    @Override
    public void loop() {
        double y = 0;
        double x = 0;
        double rx = gamepad1.right_stick_x;


        if (g1.bWasPressed()) {
            myShooter.toggleMotor();
        }

        if (g1.aWasPressed()){
            myShooter.togglePickUp();
        }

        /*if (g1.wasJustPressed(GamepadKeys.Button.X)) {
            myShooter.servopos2();
        } else if (g1.wasJustReleased(GamepadKeys.Button.X)) {
            myShooter.servopos1();
        }*/

        if ( -0.1 < gamepad1.left_stick_x && gamepad1.left_stick_x < 0.1){
            x = 0;
        }else if (-0.1 > gamepad1.left_stick_x || gamepad1.left_stick_x > 0.1){
            x = gamepad1.left_stick_x/10;
        }

        if (-0.1 < -gamepad1.left_stick_y && -gamepad1.left_stick_y < 0.1){
            y = 0;
        }else if (-0.1 > -gamepad1.left_stick_y || -gamepad1.left_stick_y > 0.1){
            y = -gamepad1.left_stick_y/10;
        }

        if (-0.1 < gamepad1.right_stick_x && gamepad1.right_stick_x < 0.1){
            rx = 0;
        } else if (-0.1 > gamepad1.right_stick_x || gamepad1.right_stick_x > 0.1) {
            rx = gamepad1.right_stick_x/10;
        }

        myDriveTrain.drive2(x, y, rx);


        if (g1.yWasPressed()) {
            myDriveTrain.setHeadingToMaintain(0);
        }

    }

    @Override
    public void stop() {
        myDriveTrain.stopMotors();
    }
}
