resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += Resolver.bintrayIvyRepo("lolhens", "sbt-plugins")
resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.jcenterRepo
sbtPlugin := true

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.7.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.22")

addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.4.0")

addSbtPlugin("au.com.onegeek" %% "sbt-dotenv" % "1.2.88")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.0")

addSbtPlugin("com.iheart" %% "sbt-play-swagger" % "0.7.5-PLAY2.7")

addSbtPlugin("net.aichler" % "sbt-jupiter-interface" % "0.7.0")
