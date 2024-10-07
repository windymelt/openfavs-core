val scala3Version   = "3.5.1"
val http4sVersion   = "0.23.28"
val tapirVersion    = "1.11.5"
val airframeVersion = "24.9.3"
val scribeVersion   = "3.15.0"

lazy val root = project
  .in(file("."))
  .settings(
    name         := "openfavs-core",
    version      := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    scalacOptions ++= Seq("-Wsafe-init"),
    run / fork := true,
    run / javaOptions ++= Seq(
      "-Xms64M",
      "-Xmx64M",
      "-XX:+UseParallelGC",
      "-verbose:gc",
    ),
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-core"          % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % tapirVersion,
    ),
    libraryDependencies ++= Seq(
      // "org.http4s" %% "http4s-ember-client" % http4sVersion,
      "org.http4s" %% "http4s-ember-server" % http4sVersion,
      "org.http4s" %% "http4s-dsl"          % http4sVersion,
    ),
    libraryDependencies += "org.wvlet.airframe" %% "airframe" % airframeVersion,
    libraryDependencies += "com.outr"           %% "scribe"   % scribeVersion,
    libraryDependencies += "com.outr" %% "scribe-slf4j2" % scribeVersion,
    libraryDependencies += "com.outr" %% "scribe-cats"   % scribeVersion,
    libraryDependencies += "com.github.pureconfig" %% "pureconfig-core" % "0.17.7",
    libraryDependencies += "org.tpolecat"  %% "skunk-core" % "0.6.4",
    libraryDependencies += "org.scalameta" %% "munit"      % "1.0.0" % Test,
  )
