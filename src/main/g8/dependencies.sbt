
val playVersion = "2.6.20"
val guiceVersion = "4.2.2"

val guiceDeps = Seq(
  "com.google.inject" % "guice" % guiceVersion,
  "com.google.inject.extensions" % "guice-assistedinject" % guiceVersion,
)

libraryDependencies ++= Seq(
  ehcache,
  guice,
  javaWs,

  // Commons
  "software.reinvent" % "commons" % "0.3.12",

  // Json
  "com.jayway.jsonpath" % "json-path" % "2.4.0",

  // for Java > 8
  "javax.xml.bind" % "jaxb-api" % "2.3.0",

  // Testing
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "org.junit.jupiter" % "junit-jupiter-api" % "5.3.2" % Test,
  "org.assertj" % "assertj-core" % "3.11.1" % Test,
  "org.assertj" % "assertj-guava" % "3.2.1" % Test exclude("com.google.guava", "guava"),
  "net.javacrumbs.json-unit" % "json-unit" % "2.1.1" % Test,
  "net.javacrumbs.json-unit" % "json-unit-assertj" % "2.1.1" % Test,

  // mocking
  "org.powermock" % "powermock-module-junit4-common" % "2.0.0-beta.5" % Test,
  "org.powermock" % "powermock-api-mockito2" % "2.0.0-beta.5" % Test,
  "org.mockito" % "mockito-core" % "2.23.4" % Test
) ++ guiceDeps

dependencyUpdatesFailBuild := true

dependencyUpdatesFilter -=
    moduleFilter(organization = "com.typesafe.play", name = "twirl-api")
