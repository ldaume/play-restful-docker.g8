resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += Resolver.bintrayIvyRepo("rtimush", "sbt-plugin-snapshots")
resolvers += Resolver.bintrayIvyRepo("lolhens", "sbt-plugins")
resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.jcenterRepo
sbtPlugin := true

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.20")

addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.2")


// Play enhancer - this automatically generates getters/setters for public fields
// and rewrites accessors of these fields to use the getters/setters. Remove this
// plugin if you prefer not to have this feature, or disable on a per project
// basis using disablePlugins(PlayEnhancer) in your build.sbt
addSbtPlugin("com.typesafe.sbt" % "sbt-play-enhancer" % "1.2.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.4")

addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.3.5-5+g017b7e0")

addSbtPlugin("au.com.onegeek" %% "sbt-dotenv" % "1.2.88")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.0")


addSbtPlugin("net.aichler" % "sbt-jupiter-interface" % "0.7.0")
