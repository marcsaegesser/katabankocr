/* basic project info */
name := "KataBankOCR"

organization := "org.saegesser"

version := "0.1.0-SNAPSHOT"

description := "A Scala implementation of the Code Kata Bank OCR"

homepage := Some(url("https://github.com/marcsaegesser/KataBankOCR"))

startYear := Some(2012)

licenses := Seq(
  ("Apache v2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))
)

scmInfo := Some(
  ScmInfo(
    url("https://github.com/marcsaegesser/KataBankOCR"),
    "scm:git:https://github.com/marcsaegesser/KataBankOCR.git",
    Some("scm:git:git@github.com:marcsaegesser/KataBankOCR.git")
  )
)

// organizationName := "My Company"

/* scala versions and options */
//scalaVersion := "2.9.2"
scalaVersion := "2.10.0"

// crossScalaVersions := Seq("2.9.1")

offline := false

scalacOptions ++= Seq("-deprecation", "-unchecked")

// scalacOptions ++= Seq("-Ydependent-method-types") // if using shapeless

javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

/* entry point */
mainClass in (Compile, packageBin) := Some("org.saegesser.katabankocr.Main")

mainClass in (Compile, run) := Some("org.saegesser.katabankocr.Main")

// CONTINUATIONS
// autoCompilerPlugins := true
// addCompilerPlugin("org.scala-lang.plugins" % "continuations" % "2.9.2")
// scalacOptions += "-P:continuations:enable"

/* dependencies */
libraryDependencies ++= Seq (
  // -- lang --
  // "org.apache.commons" % "commons-lang3" % "3.1",
  // "org.scalaz" %% "scalaz-core" % "7.0.0-M4",
  // "org.scalaz" %% "scalaz-effect" % "7.0.0-M4",
  // "joda-time" % "joda-time" % "2.1",
  // -- collections --
  // "com.google.guava" % "guava" % "13.0.1",
  // "com.chuusai" %% "shapeless" % "1.2.2",
  // -- io --
  // "commons-io" % "commons-io" % "2.4",
  // "com.github.scala-incubator.io" %% "scala-io-core" % "0.4.1-seq",
  // "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.1-seq",
  // -- logging & configuration --
  // "org.clapper" %% "grizzled-slf4j" % "0.6.10",
  // "ch.qos.logback" % "logback-classic" % "1.0.7" % "provided",
  // "com.typesafe" % "config" % "1.0.0",
  // -- persistence & serialization --
  // "org.json4s" %% "json4s-native" % "3.0.0",
  // "com.novus" %% "salat" % "1.9.2-SNAPSHOT",
  // "com.typesafe.akka" % "akka-actor" % "2.0.3",
  // "com.h2database" % "h2" % "1.2.127",
  // "mysql" % "mysql-connector-java" % "5.1.10",
  // -- concurrency --
  // "com.typesafe.akka" % "akka-actor" % "2.0.3",
  // "org.scala-stm" %% "scala-stm" % "0.6",
  // -- network --
  //  "net.databinder.dispatch" %% "dispatch-core" % "0.9.2",
  // -- testing --
  // "org.scalacheck" %% "scalacheck" % "1.10.0" % "test",
  // "org.specs2" %% "specs2" % "1.12.2" % "test",
  "com.github.scopt" %% "scopt" % "2.1.0",
  "org.scalatest" % "scalatest_2.10.0" % "2.0.M5" % "test",
  "junit" % "junit" % "4.10"
)

/* you may need these repos */
resolvers ++= Seq(
  Resolver.sonatypeRepo("public")
  // Resolver.sonatypeRepo("snapshots")
  // Resolver.typesafeIvyRepo("snapshots")
  // Resolver.typesafeIvyRepo("releases")
  // Resolver.typesafeRepo("releases")
  // Resolver.typesafeRepo("snapshots")
  // JavaNet2Repository,
  // JavaNet1Repository
)

// ivyXML := <dependencies>
//             <exclude module="logback-classic" />
//           </dependencies>

/* testing */
parallelExecution in Test := false

// testOptions += Tests.Argument(TestFrameworks.Specs2, "console", "junitxml")

// parallelExecution in Global := false //no parallelism between subprojects

/* sbt behavior */
logLevel in compile := Level.Warn

traceLevel := 5

/* publishing */
publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) Some(
    "snapshots" at nexus + "content/repositories/snapshots"
  )
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

mappings in (Compile, packageBin) ~= { (ms: Seq[(File, String)]) =>
  ms filter { case (file, toPath) =>
      toPath != "application.conf"
  }
}

publishArtifact in Test := false

// publishArtifact in (Compile, packageDoc) := false

// publishArtifact in (Compile, packageSrc) := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <developers>
    <developer>
      <id>MarcS</id>
      <name>Marc Saegesser</name>
      <email>marc@saegesser.org</email>
      <!-- <url></url> -->
    </developer>
  </developers>
)

// Josh Suereth's step-by-step guide to publishing on sonatype
// httpcom://www.scala-sbt.org/using_sonatype.html

/* assembly plugin */
mainClass in AssemblyKeys.assembly := Some("org.saegesser.KataBankOCR.Main")

assemblySettings

test in AssemblyKeys.assembly := {}
