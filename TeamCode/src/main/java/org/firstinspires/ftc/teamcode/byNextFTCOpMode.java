package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//import org.firstinspires.ftc.teamcode.Subsystem.Claw;
//import org.firstinspires.ftc.teamcode.Subsystem.Lift;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.ParallelGroup;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.FieldCentric;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.Direction;
import dev.nextftc.hardware.impl.IMUEx;
import dev.nextftc.hardware.impl.MotorEx;

public class byNextFTCOpMode {

/*    @Autonomous(name = "NextFTC Autonomous Program Java" , group = "byNextFTCOpMode")
    public static class AutonomousProgram extends NextFTCOpMode {
        public AutonomousProgram() {
            addComponents(
                    new SubsystemComponent(Lift.INSTANCE, Claw.INSTANCE),
                    BulkReadComponent.INSTANCE
            );
        }

        private Command autonomousRoutine() {
            return new SequentialGroup(
                    Lift.INSTANCE.toHigh,
                    new ParallelGroup(
                            Lift.INSTANCE.toMid,
                            Claw.INSTANCE.close
                    ),
                    new Delay(0.5),
                    new ParallelGroup(
                            Claw.INSTANCE.open,
                            Lift.INSTANCE.toLow
                    )
            );
        }

        @Override
        public void onStartButtonPressed() {
            autonomousRoutine().schedule();
        }
    }*/
    @TeleOp(name = "NextFTC TeleOp Program Java", group="byNextFTCOpMode")
    public static class TeleOpProgram extends NextFTCOpMode {
        public TeleOpProgram(){
        addComponents(
                //new SubsystemComponent(Lift.INSTANCE, Claw.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );}
        // change the names and directions to suit your robot
        private final MotorEx frontLeftMotor = new MotorEx("LeftFront").brakeMode().reversed();
        private final MotorEx frontRightMotor = new MotorEx("RightFront").brakeMode();
        private final MotorEx backLeftMotor = new MotorEx("LeftBack").brakeMode().reversed();
        private final MotorEx backRightMotor = new MotorEx("RightBack").brakeMode();
    private IMUEx imu = new IMUEx("imu", Direction.UP, Direction.FORWARD).zeroed();

        @Override
        public void onStartButtonPressed() {
            Command driverControlled = new MecanumDriverControlled(
                    frontLeftMotor,
                    frontRightMotor,
                    backLeftMotor,
                    backRightMotor,
                    Gamepads.gamepad1().leftStickY().negate(),
                    Gamepads.gamepad1().leftStickX(),
                    Gamepads.gamepad1().rightStickX(),
                    new FieldCentric(imu)
            );
            driverControlled.schedule();
            /*Gamepads.gamepad2().dpadUp()
                    .whenBecomesTrue(Lift.INSTANCE.toHigh)
                    .whenBecomesFalse(Claw.INSTANCE.open);

            Gamepads.gamepad2().rightTrigger().greaterThan(0.2)
                    .whenBecomesTrue(
                            Claw.INSTANCE.close.then(Lift.INSTANCE.toHigh)
                    );

            Gamepads.gamepad2().leftBumper().whenBecomesTrue(
                    Claw.INSTANCE.open.and(Lift.INSTANCE.toLow)
            );*/
        }
    }
    }
