package org.firstinspires.ftc.teamcode.Subsystem;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

/*public class Lift implements Subsystem {
    public static final Lift INSTANCE = new Lift();
    private Lift() { }
    private MotorEx motor = new MotorEx("lift_motor");
    private ControlSystem controlSystem = ControlSystem.builder()
            .posPid(0.005, 0, 0)
            .elevatorFF(0)
            .build();
    @Override
    public void periodic() {
        motor.setPower(controlSystem.calculate(motor.getState()));

    }
    public Command toLow = new RunToPosition(controlSystem, 0).requires(this);
    public Command toHigh = new RunToPosition(controlSystem, 10000).requires(this);
    public Command toMid = new RunToPosition(controlSystem, 5000).requires(this);
}

*/