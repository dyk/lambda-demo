import com.typesafe.sbt.SbtNativePackager.packageArchetype
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import com.typesafe.sbt.packager.docker.DockerPlugin
import AssemblyKeys._

name := "lambda-demo"

organization := "vevo"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies ++= {
  val akkaVersion = "2.3.11"
  val sprayVersion = "1.3.3"
  val sparkVersion = "1.3.0"
  Seq(
    "com.amazonaws" % "aws-lambda-java-core" % "1.0.0",
    "com.amazonaws" % "aws-lambda-java-events" % "1.0.0",
    "com.amazonaws" % "aws-java-sdk-core" % "1.9.34" % "provided",
    "com.amazonaws" % "aws-java-sdk" % "1.9.34" % "provided",
    "io.spray" %% "spray-client" % sprayVersion,
    "com.typesafe.play" %% "play-json" % "2+",
    "org.specs2" %% "specs2-core" % "3.6.4" % "test",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test"
  )
}

assemblySettings

jarName in assembly := "assembly-project.jar"

//assembly settings
packageArchetype.java_server

mappings in Universal := {
  val universalMappings = (mappings in Universal).value
  val fatJar = (assembly in Compile).value
  val filtered = universalMappings filter {
    case (file, name) => !name.endsWith(".jar")
  }
  filtered :+ (fatJar -> ("lib/" + fatJar.getName))
}

scriptClasspath := Seq((jarName in assembly).value)
