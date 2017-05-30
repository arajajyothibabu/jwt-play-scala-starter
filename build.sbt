name := "JwtPlayScalaStarter"

version := "1.0"

lazy val `jwtplayscalastarter` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.pauldijou" %% "jwt-play-json" % "0.12.1",
  ws,
  specs2 % Test
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  