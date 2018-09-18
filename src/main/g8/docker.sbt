import java.io.File

import com.typesafe.sbt.packager.docker._
// build with sbt docker:publish


import com.typesafe.config.ConfigFactory

val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()

// change to smaller base image
dockerBaseImage := "openjdk:11-jre-slim-sid"


dockerCommands := dockerCommands.value.flatMap {
  case cmd@Cmd("FROM", _) => List(cmd,
    Cmd("RUN", "apt-get update -y && apt-get install -y bash tzdata bzip2 unzip xz-utils curl"),
    Cmd("ENV", "TZ \"Europe/Berlin\""),
    Cmd("RUN", "echo \"\${TZ}\" > /etc/timezone"),
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
