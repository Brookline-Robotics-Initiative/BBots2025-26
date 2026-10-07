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

public class B12_mirrored extends SequentialCommandGroup {

  private final Follower follower;
  private final PoseFactory p = PoseFactory.degrees();

  // Poses
  private Pose startPoint;
  private Pose shootPreload;
  private Pose topStart;
  private Pose topDone;
  private Pose shootTop;
  private Pose middleBegin;
  private Pose middleDone;
  private Pose shootMiddle;
  private Pose shootMiddle_line6_control1;
  private Pose bottomBegin;
  private Pose bottomEnd;
  private Pose shootBottom;
  private Pose toGate;

  // Path chains
  private Path startPointTOshootPreload;
  private Path shootPreloadTOtopStart;
  private Path topStartTOtopDone;
  private Path topDoneTOshootTop;
  private Path shootTopTOmiddleBegin;
  private Path middleBeginTOmiddleDone;
  private Path middleDoneTOshootMiddle;
  private Path shootMiddleTObottomBegin;
  private Path bottomBeginTObottomEnd;
  private Path bottomEndTOshootBottom;
  private Path shootBottomTOtoGate;

  public B12_mirrored(
    final Follower follower,
    HardwareMap hw,
    Telemetry telemetry
  ) throws IOException {
    this.follower = follower;

    TurtleTracerReader pp = new TurtleTracerReader(
      "B12_mirrored.turt",
      hw.appContext
    );

    // Load poses
    startPoint = pp.get("startPoint");
    shootPreload = pp.get("shootPreload");
    topStart = pp.get("topStart");
    topDone = pp.get("topDone");
    shootTop = pp.get("shootTop");
    middleBegin = pp.get("middleBegin");
    middleDone = pp.get("middleDone");
    shootMiddle = pp.get("shootMiddle");
    shootMiddle_line6_control1 = pp.get("shootMiddle_line6_control1");
    bottomBegin = pp.get("bottomBegin");
    bottomEnd = pp.get("bottomEnd");
    shootBottom = pp.get("shootBottom");
    toGate = pp.get("toGate");

    follower.setPose(startPoint);

    buildPaths();

    addCommands(
      new FollowPathCommand(follower, startPointTOshootPreload),
      new WaitCommand(500),
      new FollowPathCommand(follower, shootPreloadTOtopStart),
      new WaitCommand(500),
      new FollowPathCommand(follower, topStartTOtopDone),
      new WaitCommand(500),
      new FollowPathCommand(follower, topDoneTOshootTop),
      new WaitCommand(500),
      new FollowPathCommand(follower, shootTopTOmiddleBegin),
      new WaitCommand(500),
      new FollowPathCommand(follower, middleBeginTOmiddleDone),
      new WaitCommand(500),
      new FollowPathCommand(follower, middleDoneTOshootMiddle),
      new WaitCommand(500),
      new FollowPathCommand(follower, shootMiddleTObottomBegin),
      new WaitCommand(500),
      new FollowPathCommand(follower, bottomBeginTObottomEnd),
      new WaitCommand(500),
      new FollowPathCommand(follower, bottomEndTOshootBottom),
      new WaitCommand(500),
      new FollowPathCommand(follower, shootBottomTOtoGate)
    );
  }

  public void buildPaths() {
    startPointTOshootPreload = line(startPoint, shootPreload).heading(
      Interpolator.linear(Math.toRadians(38), Math.toRadians(45)).reverse()
    );

    shootPreloadTOtopStart = line(shootPreload, topStart).linear(
      Math.toRadians(45),
      Math.toRadians(0)
    );

    topStartTOtopDone = line(topStart, topDone).constant(Math.toRadians(0));

    topDoneTOshootTop = line(topDone, shootTop).linear(
      Math.toRadians(0),
      Math.toRadians(30)
    );

    shootTopTOmiddleBegin = line(shootTop, middleBegin).linear(
      Math.toRadians(30),
      Math.toRadians(0)
    );

    middleBeginTOmiddleDone = line(middleBegin, middleDone).constant(
      Math.toRadians(0)
    );

    middleDoneTOshootMiddle = curve(
      middleDone,
      p.of(99.000, 69.000, 0.0),
      shootMiddle
    ).linear(Math.toRadians(0), Math.toRadians(30));

    shootMiddleTObottomBegin = line(shootMiddle, bottomBegin).linear(
      Math.toRadians(30),
      Math.toRadians(0)
    );

    bottomBeginTObottomEnd = line(bottomBegin, bottomEnd).constant(
      Math.toRadians(0)
    );

    bottomEndTOshootBottom = line(bottomEnd, shootBottom).linear(
      Math.toRadians(0),
      Math.toRadians(30)
    );

    shootBottomTOtoGate = line(shootBottom, toGate).linear(
      Math.toRadians(30),
      Math.toRadians(0)
    );
  }
}
