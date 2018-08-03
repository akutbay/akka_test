name := "akka-spawning-actors"

version := "1.0"

scalaVersion := "2.12.6"

lazy val akkaVersion = "2.5.14"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"
)

cancelable in Global := true // to make CTRL-C work without killing the sbt-shell
