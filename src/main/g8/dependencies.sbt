libraryDependencies ++= Seq(
  ehcache,
  guice,
  javaWs,

  // Commons
  "software.reinvent" % "commons" % "0.3.12",

  // Json
  "com.jayway.jsonpath" % "json-path" % "2.4.0",

  // jdk 11 workaround
  "javax.xml.bind" % "jaxb-api" % "2.3.0",

  // Testing
  "org.assertj" % "assertj-core" % "3.10.0" % "test",
  "org.assertj" % "assertj-guava" % "3.1.0" % "test" exclude("com.google.guava", "guava")
)

dependencyUpdatesFailBuild := true

dependencyUpdatesFilter -=
    moduleFilter(organization = "com.typesafe.play", name = "twirl-api")
