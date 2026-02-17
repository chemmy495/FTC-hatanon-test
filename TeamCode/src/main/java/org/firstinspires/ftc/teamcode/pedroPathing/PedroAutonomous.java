package org.firstinspires.ftc.teamcode.pedroPathing;
    import com.qualcomm.robotcore.eventloop.opmode.OpMode;
    import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
    import com.bylazar.configurables.annotations.Configurable;
    import com.bylazar.telemetry.TelemetryManager;
    import com.bylazar.telemetry.PanelsTelemetry;
    import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
    import com.pedropathing.geometry.BezierCurve;
    import com.pedropathing.geometry.BezierLine;
    import com.pedropathing.follower.Follower;
    import com.pedropathing.paths.PathChain;
    import com.pedropathing.geometry.Pose;
    
    @Autonomous(name = "Pedro Pathing Autonomous", group = "Autonomous")
    @Configurable // Panels
    public class PedroAutonomous extends OpMode {
      private TelemetryManager panelsTelemetry; // Panels Telemetry instance
      public Follower follower; // Pedro Pathing follower instance
      private int pathState; // Current autonomous path state (state machine)
      private Paths paths; // Paths defined in the Paths class
      
      @Override
      public void init() {
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(72, 8, Math.toRadians(90)));

        paths = new Paths(follower); // Build paths

        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);
      }
      
      @Override
      public void loop() {
        follower.update(); // Update Pedro Pathing
        pathState = autonomousPathUpdate(); // Update autonomous state machine

        // Log values to Panels and Driver Station
        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);
      }

      
  public static class Paths {
    public PathChain Path1;
public PathChain Path2;
public PathChain Path3;
    
    public Paths(Follower follower) {
      Path1 = follower.pathBuilder().addPath(
          new BezierLine(
            new Pose(111.974, 111.974),
            
            new Pose(111.750, 80.837)
          )
        ).setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(270))
        
        .build();

Path2 = follower.pathBuilder().addPath(
          new BezierLine(
            new Pose(111.750, 80.837),
            
            new Pose(95.560, 95.455)
          )
        ).setTangentHeadingInterpolation()
        
        .build();

Path3 = follower.pathBuilder().addPath(
          new BezierLine(
            new Pose(95.560, 95.455),
            
            new Pose(111.662, 111.870)
          )
        ).setTangentHeadingInterpolation()
        
        .build();
    }
  }
  

      public int autonomousPathUpdate(){
          switch (pathState) {
              case 0:
                  follower.followPath(paths.Path1);
                  if (!follower.isBusy()) {
                      return 1;
                  }
                  break;
              case 1:
                  follower.followPath(paths.Path2);
                  if (!follower.isBusy()) {
                      return 2;
                  }
                  break;
              case 2:
                  follower.followPath(paths.Path3);
                  if (!follower.isBusy()) {
                      return 3;
                  }
                  break;
              case 3:
                  // Autonomous is finished
                  break;
          }
          return pathState;
      }
    }
