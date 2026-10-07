/* ============================================================= *
 *                 Turtle Tracer — Auto-Generated                *
 *                                                               *
 *  Version: 2.4.0.                                              *
 *  Copyright (c) 2026 Matthew Allen                             *
 *                                                               *
 *  THIS FILE IS AUTO-GENERATED — DO NOT EDIT MANUALLY.          *
 *  Changes will be overwritten when regenerated.                *
 * ============================================================= */

package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.api.Paths.line;
import static com.pedropathing.api.Paths.path;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.interpolator.Interpolator;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import com.turtletracerlib.TurtleTracerReader;
import com.turtletracerlib.pathing.NamedCommands;
import java.io.IOException;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AutoBase extends SequentialCommandGroup {

  private final Follower follower;
  private final PoseFactory p = PoseFactory.degrees();

  // Poses
  private Pose startPoint;
  private Pose Path1;
  private Pose Path2;

  // Path chains
  private Path startPointTOPath1;
  private Path Path1TOPath2;

  public AutoBase(final Follower follower, HardwareMap hw, Telemetry telemetry)
    throws IOException {
    this.follower = follower;

    TurtleTracerReader pp = new TurtleTracerReader(
      "AutoBase.turt",
      hw.appContext
    );

    // Load poses
    startPoint = pp.get("startPoint");
    Path1 = pp.get("Path1");
    Path2 = pp.get("Path2");

    follower.setPose(startPoint);

    buildPaths();

    addCommands(
      new FollowPathCommand(follower, startPointTOPath1),
      new FollowPathCommand(follower, Path1TOPath2)
    );
  }

  public void buildPaths() {
    startPointTOPath1 = line(startPoint, Path1).linear(
      Math.toRadians(90),
      Math.toRadians(90)
    );

    Path1TOPath2 = line(Path1, Path2).tangent();
  }
}
