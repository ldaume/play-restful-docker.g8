
resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.jcenterRepo,
  Resolver.sonatypeRepo("releases"),
  "ReInvent Software OSS" at "https://maven.reinvent-software.de/nexus/content/groups/public"
)

libraryDependencies ++= Seq(
  ehcache,
  guice,
  javaWs,

  // Commons
  "org.apache.commons" % "commons-lang3" % "3.7",
  "com.google.guava" % "guava" % "23.5-jre",
  "org.apache.commons" % "commons-collections4" % "4.1",
  "commons-io" % "commons-io" % "2.6",
  "org.unbescape" % "unbescape" % "1.1.5.RELEASE",

  // Json
  "com.jayway.jsonpath" % "json-path" % "2.4.0",

  // Testing
  "org.assertj" % "assertj-core" % "3.8.0" % "test",
  "org.assertj" % "assertj-guava" % "3.1.0" % "test" exclude("com.google.guava", "guava")
)

dependencyUpdatesFailBuild := true

dependencyUpdatesFilter -= moduleFilter(name = "sbt-twirl") | moduleFilter(name = "twirl-api")
