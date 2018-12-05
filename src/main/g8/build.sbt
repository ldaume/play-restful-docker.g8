import java.io.File

import com.typesafe.config.ConfigFactory


val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()

val appName = conf.getString("app.name")
val appVersion = conf.getString("app.version")
val appPort = conf.getString("http.port")

PlayKeys.devSettings := Seq("play.server.http.port" -> appPort)

name := appName
version := appVersion
organization := "$organization$"

lazy val root = (project in file(".")).enablePlugins(PlayJava, JavaAppPackaging, DockerPlugin)

javacOptions ++= Seq("-source", "11", "-target", "11")

scalaVersion := "2.12.8"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "11")
    sys.error("Java 11 is required for this project. Found " + sys.props("java.specification.version"))
}
