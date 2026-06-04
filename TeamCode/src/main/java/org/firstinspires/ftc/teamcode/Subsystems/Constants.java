/*package org.firstinspires.ftc.teamcode.Subsystems;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {

    public static FollowerConstants followerConstants =
            new FollowerConstants()
                    .mass(6.0);

    public static PathConstraints pathConstraints =
            new PathConstraints(
                    1.0,
                    100,
                    1.0,
                    1.0
            );

    public static MecanumConstants driveConstants =
            new MecanumConstants()
                    .maxPower(1)

                    .leftFrontMotorName("leftFront")
                    .leftRearMotorName("leftRear")
                    .rightFrontMotorName("rightFront")
                    .rightRearMotorName("rightRear")

                    .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
                    .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
                    .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
                    .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .build();
    }
}*/