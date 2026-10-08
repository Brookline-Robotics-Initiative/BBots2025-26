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
import com.turtletracerlib.pathing.ProgressTracker;
import java.io.IOException;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class reg__my_version_ extends SequentialCommandGroup {

  private final Follower follower;
  private final PoseFactory p = PoseFactory.degrees();
  private TurtleTracerReader pp;
  private ProgressTracker tracker;

  // Poses
  private Pose startPoint;
  private Pose BeforeFirstRow;
  private Pose BeforeFirstRow_line0_control1;
  private Pose AfterFirstRow;
  private Pose BeforeSecondRow;
  private Pose AfterSecondRow;
  private Pose ScoringPosition;
  private Pose back;
  private Pose point7;
  private Pose point8;
  private Pose point9;
  private Pose point10;

  // Path chains
  private Path startPointTOBeforeFirstRow;
  private Path point8TOpoint9;
  private Path point9TOpoint10;

  public reg__my_version_(
    final Follower follower,
    HardwareMap hw,
    Telemetry telemetry
  ) throws IOException {
    this.follower = follower;

    pp = new TurtleTracerReader("reg (my version).turt", hw.appContext);
    pp.onEvent("IntakeOn", NamedCommands.getCommand("IntakeOn"));

    tracker = new ProgressTracker(follower, telemetry);

    // Load poses
    startPoint = pp.get("startPoint");
    BeforeFirstRow = pp.get("BeforeFirstRow");
    BeforeFirstRow_line0_control1 = pp.get("BeforeFirstRow_line0_control1");
    AfterFirstRow = pp.get("AfterFirstRow");
    BeforeSecondRow = pp.get("BeforeSecondRow");
    AfterSecondRow = pp.get("AfterSecondRow");
    ScoringPosition = pp.get("ScoringPosition");
    back = pp.get("back");
    point7 = pp.get("point7");
    point8 = pp.get("point8");
    point9 = pp.get("point9");
    point10 = pp.get("point10");

    follower.setPose(startPoint);

    buildPaths();

    addCommands(
      new InstantCommand(() -> {
        tracker.clearPathEvents();
        pp.registerLineEvents(tracker, 0, 0);
        tracker.setCurrentPath(startPointTOBeforeFirstRow);
      }),
      new ParallelRaceGroup(
        new FollowPathCommand(follower, startPointTOBeforeFirstRow),
        new RunCommand(() -> tracker.update())
      ),
      new FollowPathCommand(follower, point8TOpoint9),
      new FollowPathCommand(follower, point9TOpoint10)
    );
  }

  public void buildPaths() {
    startPointTOBeforeFirstRow = path(
      curve(startPoint, p.of(94.526, 53.474, 0.0), BeforeFirstRow).linear(
        Math.toRadians(90),
        Math.toRadians(180)
      ),
      line(BeforeFirstRow, AfterFirstRow).tangent(),
      line(AfterFirstRow, BeforeSecondRow).tangent(),
      line(BeforeSecondRow, AfterSecondRow).tangent(),
      line(AfterSecondRow, ScoringPosition).tangent(),
      line(ScoringPosition, back).reverseTangent(),
      line(back, point7).reverseTangent(),
      line(point7, point8).reverseTangent()
    );

    point8TOpoint9 = line(point8, point9).reverseTangent();

    point9TOpoint10 = line(point9, point10).reverseTangent();
  }
}
