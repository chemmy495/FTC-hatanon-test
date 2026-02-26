package org.firstinspires.ftc.teamcode.pedroPathing;
    import com.qualcomm.robotcore.eventloop.opmode.OpMode;
    import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
    import com.bylazar.configurables.annotations.Configurable;
    import com.bylazar.telemetry.TelemetryManager;
    import com.bylazar.telemetry.PanelsTelemetry;
    import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
    import com.pedropathing.geometry.BezierLine;
    import com.pedropathing.follower.Follower;
    import com.pedropathing.paths.PathChain;
    import com.pedropathing.geometry.Pose;
    
    @Autonomous(name = "Pedro Pathing Autonomous", group = "Autonomous")
    @Configurable // Panels
    public class PedroAutonomous extends OpMode {
      private TelemetryManager panelsTelemetry; // Panels Telemetry instance
      public Follower follower; // Pedro Pathing follower instance
      private Paths paths; // Paths defined in the Paths class
      private boolean autonomousStarted = false; // Flag to start autonomous only once

      @Override
      public void init() {
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(111.700, 122, Math.toRadians(270)));

        paths = new Paths(follower); // Build paths

        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);
      }
      
      @Override
      public void loop() {
        if (!autonomousStarted) {
            follower.followPath(paths.autonomousPath);
            autonomousStarted = true;
        }
        follower.update(); // Update Pedro Pathing

        // Log values to Panels and Driver Station
        panelsTelemetry.debug("Path Status", follower.isBusy() ? "Running" : "Finished");
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", Math.toDegrees(follower.getPose().getHeading()));
        panelsTelemetry.update(telemetry);
      }

      
  public static class Paths {
    public PathChain autonomousPath;
    
    public Paths(Follower follower) {
      autonomousPath = follower.pathBuilder()
          .addPath(
              new BezierLine(
                new Pose(111.700, 122),
                new Pose(111.700, 99)
              )
          ).setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(270))
          .addPath(
              new BezierLine(
                new Pose(111.700, 99),
                new Pose(88, 99)
              )
          ).setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(270))
          .addPath(
              new BezierLine(
                new Pose(88, 99),
                new Pose(111.700, 122)
              )
          ).setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(270))
          .build();
    }
  }
}