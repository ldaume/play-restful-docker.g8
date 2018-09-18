credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.jcenterRepo,
  Resolver.sonatypeRepo("releases")
)
