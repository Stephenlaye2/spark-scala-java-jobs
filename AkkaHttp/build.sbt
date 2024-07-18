ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "AkkaHttp"
  )


libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.2.7"
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.2.7"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.6.17"
libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % "2.6.17"
libraryDependencies += "io.spray" %% "spray-json" % "1.3.6"