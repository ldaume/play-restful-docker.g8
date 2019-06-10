import java.io.File

import com.typesafe.sbt.packager.docker._
// build with sbt docker:publish


import com.typesafe.config.ConfigFactory

val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()

// java 12
dockerBaseImage := "openjdk:12"

dockerCommands := dockerCommands.value.flatMap {
  case cmd@Cmd("FROM", _) => List(cmd,
    Cmd("RUN", "yum update -y && yum install -y bash tzdata bzip2 unzip xz-utils curl"),
    Cmd("RUN", "rm -rf /etc/localtime"),
    Cmd("RUN", "ln -s /usr/share/zoneinfo/Europe/Berlin /etc/localtime"),
    Cmd("HEALTHCHECK", "--start-period=2m CMD curl --fail http://localhost:" + conf.getString("http.port") + "/heartbeat || exit" +
      " 1")
  )
  case other => List(other)
}

dockerBuildOptions += "--no-cache"

// setting a maintainer which is used for all packaging types</pre>
maintainer := "Me"

// exposing the play ports
dockerExposedPorts in Docker := Seq(9000, 9443)

// publish to repo
//dockerRepository := Some("quay.io/")
//dockerUsername := Some("user")

dockerUpdateLatest := true

// run this with: docker run -p 9000:9000 <name>:<version>
