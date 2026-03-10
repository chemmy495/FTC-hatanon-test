package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(6.5)
            .forwardZeroPowerAcceleration(-45.52123832770442)
            .lateralZeroPowerAcceleration(-71.8474247174287)
            .useSecondaryTranslationalPIDF(true)
            .useSecondaryHeadingPIDF(true)
            .useSecondaryDrivePIDF(true)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.05,0.005,0.000001,0.022))
            .secondaryTranslationalPIDFCoefficients(new PIDFCoefficients(0.05,0.005,0.00001,0.0022))
            .headingPIDFCoefficients(new PIDFCoefficients(0.5, 0.00005, 0.0001, 0.025))
            .secondaryHeadingPIDFCoefficients(new PIDFCoefficients(0.4,0.000005,0,0.025))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.2,0.01,0.001,0.6,0.0))
            .secondaryDrivePIDFCoefficients(new FilteredPIDFCoefficients(0.1,0,0.0,0.6,0.001))
            .centripetalScaling(0.00068);
    public static PathConstraints pathConstraints = new PathConstraints(0.6, 50, 0.5, 0.5);
    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-100)
            .strafePodX(-160)
            .distanceUnit(DistanceUnit.MM)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            //.encoderResolution(19.183) //自作オドメトリーを使うときのencoderRevolutionの値
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);

    public static MecanumConstants driveConstants;

    static {
        driveConstants = new MecanumConstants()
                .maxPower(1)
                .rightFrontMotorName("RightFront")
                .rightRearMotorName("RightBack")
                .leftRearMotorName("LeftBack")
                .leftFrontMotorName("LeftFront")
                .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
                .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
                .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
                .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
                .xVelocity(76.123950868141)
                .yVelocity(54.80883885180856);
    }

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(localizerConstants)
                .build();
    }
}
