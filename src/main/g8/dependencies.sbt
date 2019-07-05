
libraryDependencies ++= Seq(
  ehcache,
  guice,
  javaWs,

  // Commons
  "software.reinvent" % "commons" % "0.3.12",  
  "org.projectlombok" % "lombok" % "1.18.8" % "provided",

  // Json
  "com.jayway.jsonpath" % "json-path" % "2.4.0",

  // for Java > 8
  "javax.xml.bind" % "jaxb-api" % "2.3.1",

  // Testing
  "org.assertj" % "assertj-core" % "3.12.2" % Test,
  "org.assertj" % "assertj-guava" % "3.2.1" % Test exclude("com.google.guava", "guava"),
  "net.javacrumbs.json-unit" % "json-unit" % "2.7.0" % Test,
  "net.javacrumbs.json-unit" % "json-unit-assertj" % "2.7.0" % Test,

  // mocking
  "org.powermock" % "powermock-module-junit4-common" % "2.0.2" % Test,
  "org.powermock" % "powermock-api-mockito2" % "2.0.2" % Test,
  "org.mockito" % "mockito-core" % "2.28.2" % Test
)

dependencyUpdatesFailBuild := true

dependencyUpdatesFilter -= moduleFilter(name = "scala-library")
