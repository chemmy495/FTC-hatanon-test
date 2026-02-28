package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystem.Claw;
import org.firstinspires.ftc.teamcode.Subsystem.Lift;

import dev.nextftc.core.components.SubsystemComponent;

public class NextFTCOpMode {
    @Autonomous(name = "NextFTC Autonomous Program Java")
    public class AutonomousProgram extends NextFTCOpMode {
        public AutonomousProgram() {
            addComponents(
                    new SubsystemComponent(Lift.INSTANCE, Claw.INSTANCE)
            );
        }
    }
}
