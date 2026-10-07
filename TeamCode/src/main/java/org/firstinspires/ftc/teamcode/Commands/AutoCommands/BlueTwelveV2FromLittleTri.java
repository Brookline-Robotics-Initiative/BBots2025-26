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

public class BlueTwelveV2FromLittleTri extends SequentialCommandGroup {

  private final Follower follower;
  private final PoseFactory p = PoseFactory.degrees();
  private TurtleTracerReader pp;
  private ProgressTracker tracker;

  // Poses
  private Pose startPoint;
  private Pose shoot;
  private Pose ToIntakeOne;
  private Pose IntakeOne;
  private Pose shoot_line3_control1;
  private Pose ToIntakeTwo;
  private Pose IntakeTwo;
  private Pose shoot_line6_control1;
  private Pose ToIntakeThree;
  private Pose IntakeThree;
  private Pose OuttakeThree;

  // Path chains
  private Path startPointTOshoot;
  private Path shootTOToIntakeOne;
  private Path ToIntakeOneTOIntakeOne;
  private Path IntakeOneTOshoot;
  private Path shootTOToIntakeTwo;
  private Path ToIntakeTwoTOIntakeTwo;
  private Path IntakeTwoTOshoot;
  private Path shootTOToIntakeThree;
  private Path ToIntakeThreeTOIntakeThree;
  private Path IntakeThreeTOOuttakeThree;

  public BlueTwelveV2FromLittleTri(
    final Follower follower,
    HardwareMap hw,
    Telemetry telemetry
  ) throws IOException {
    this.follower = follower;

    pp = new TurtleTracerReader(
      "BlueTwelveV2FromLittleTri.turt",
      hw.appContext
    );
    pp.onEvent("ShootCenter", NamedCommands.getCommand("ShootCenter"))
      .onEvent("IntakeOn", NamedCommands.getCommand("IntakeOn"))
      .onEvent("IntakeOff", NamedCommands.getCommand("IntakeOff"));

    tracker = new ProgressTracker(follower, telemetry);

    // Load poses
    startPoint = pp.get("startPoint");
    shoot = pp.get("shoot");
    ToIntakeOne = pp.get("ToIntakeOne");
    IntakeOne = pp.get("IntakeOne");
    shoot_line3_control1 = pp.get("shoot_line3_control1");
    ToIntakeTwo = pp.get("ToIntakeTwo");
    IntakeTwo = pp.get("IntakeTwo");
    shoot_line6_control1 = pp.get("shoot_line6_control1");
    ToIntakeThree = pp.get("ToIntakeThree");
    IntakeThree = pp.get("IntakeThree");
    OuttakeThree = pp.get("OuttakeThree");

    follower.setPose(startPoint);

    buildPaths();

    addCommands(
      new InstantCommand(() -> {
        tracker.clearPathEvents();
        pp.registerLineEvents(tracker, 0, 0);
        tracker.setCurrentPath(startPointTOshoot);
      }),
      new ParallelRaceGroup(
        new FollowPathCommand(follower, startPointTOshoot),
        new RunCommand(() -> tracker.update())
      ),
      new FollowPathCommand(follower, shootTOToIntakeOne),
      new InstantCommand(() -> {
        tracker.clearPathEvents();
        pp.registerLineEvents(tracker, 2, 0);
        tracker.setCurrentPath(ToIntakeOneTOIntakeOne);
      }),
      new ParallelRaceGroup(
        new FollowPathCommand(follower, ToIntakeOneTOIntakeOne),
        new RunCommand(() -> tracker.update())
      ),
      new InstantCommand(() -> {
        tracker.clearPathEvents();
        pp.registerLineEvents(tracker, 3, 0);
        tracker.setCurrentPath(IntakeOneTOshoot);
      }),
      new ParallelRaceGroup(
        new FollowPathCommand(follower, IntakeOneTOshoot),
        new RunCommand(() -> tracker.update())
      ),
      new FollowPathCommand(follower, shootTOToIntakeTwo),
      new InstantCommand(() -> {
        tracker.clearPathEvents();
        pp.registerLineEvents(tracker, 5, 0);
        tracker.setCurrentPath(ToIntakeTwoTOIntakeTwo);
      }),
      new ParallelRaceGroup(
        new FollowPathCommand(follower, ToIntakeTwoTOIntakeTwo),
        new RunCommand(() -> tracker.update())
      ),
      new InstantCommand(() -> {
        tracker.clearPathEvents();
        pp.registerLineEvents(tracker, 6, 0);
        tracker.setCurrentPath(IntakeTwoTOshoot);
      }),
      new ParallelRaceGroup(
        new FollowPathCommand(follower, IntakeTwoTOshoot),
        new RunCommand(() -> tracker.update())
      ),
      new FollowPathCommand(follower, shootTOToIntakeThree),
      new InstantCommand(() -> {
        tracker.clearPathEvents();
        pp.registerLineEvents(tracker, 8, 0);
        tracker.setCurrentPath(ToIntakeThreeTOIntakeThree);
      }),
      new ParallelRaceGroup(
        new FollowPathCommand(follower, ToIntakeThreeTOIntakeThree),
        new RunCommand(() -> tracker.update())
      ),
      new InstantCommand(() -> {
        tracker.clearPathEvents();
        pp.registerLineEvents(tracker, 9, 0);
        tracker.setCurrentPath(IntakeThreeTOOuttakeThree);
      }),
      new ParallelRaceGroup(
        new FollowPathCommand(follower, IntakeThreeTOOuttakeThree),
        new RunCommand(() -> tracker.update())
      )
    );
  }

  public void buildPaths() {
    startPointTOshoot = line(startPoint, shoot).linear(
      Math.toRadians(180),
      Math.toRadians(136)
    );

    shootTOToIntakeOne = line(shoot, ToIntakeOne).linear(
      Math.toRadians(136),
      Math.toRadians(180)
    );

    ToIntakeOneTOIntakeOne = line(ToIntakeOne, IntakeOne).tangent();

    IntakeOneTOshoot = curve(
      IntakeOne,
      p.of(69.895, 48.000, 0.0),
      shoot
    ).linear(Math.toRadians(180), Math.toRadians(134));

    shootTOToIntakeTwo = line(shoot, ToIntakeTwo).linear(
      Math.toRadians(136),
      Math.toRadians(180)
    );

    ToIntakeTwoTOIntakeTwo = line(ToIntakeTwo, IntakeTwo).linear(
      Math.toRadians(180),
      Math.toRadians(180)
    );

    IntakeTwoTOshoot = curve(
      IntakeTwo,
      p.of(63.579, 33.684, 0.0),
      shoot
    ).linear(Math.toRadians(180), Math.toRadians(136));

    shootTOToIntakeThree = line(shoot, ToIntakeThree).linear(
      Math.toRadians(136),
      Math.toRadians(180)
    );

    ToIntakeThreeTOIntakeThree = line(ToIntakeThree, IntakeThree).linear(
      Math.toRadians(180),
      Math.toRadians(180)
    );

    IntakeThreeTOOuttakeThree = line(IntakeThree, OuttakeThree).linear(
      Math.toRadians(180),
      Math.toRadians(136)
    );
  }
}
