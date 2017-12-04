// build with sbt docker:publish[Local]

dockerBaseImage := "ldaume/java:jdk8"


dockerCommands := dockerCommands.value.flatMap {
  case cmd@Cmd("FROM", _) => List(cmd,
    Cmd("RUN", "apk update && apk add bash curl tzdata"),
    Cmd("ENV", "TZ \"Europe/Berlin\""),
    Cmd("RUN", "echo \"$${TZ}\" > /etc/timezone")
  )
  case other => List(other)
}


// setting a maintainer which is used for all packaging types</pre>
maintainer := "Leonard Daume"

// exposing the play ports
dockerExposedPorts in Docker := Seq(9000, 9443)

// publish to repo
//dockerRepository := Some("quay.io/")
//dockerUpdateLatest := true

// run this with: docker run -p 9000:9000 <name>:<version>
