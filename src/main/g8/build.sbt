import com.typesafe.sbt.packager.docker._

name := "$name$"

organization := "$organization$"

version := "$version$"

lazy val root = (project in file(".")).enablePlugins(PlayJava, JavaAppPackaging, DockerPlugin)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalaVersion := "2.12.2"

resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.jcenterRepo,
  "ReInvent Software OSS" at "https://maven.reinvent-software.de/nexus/content/groups/public"
)

libraryDependencies ++= Seq(
  // If you enable PlayEbean plugin you must remove these
  // JPA dependencies to avoid conflicts.
  javaJpa,
  "org.hibernate" % "hibernate-core" % "5.2.10.Final",
  "mysql" % "mysql-connector-java" % "5.1.43",
  ehcache,
  javaWs,
  guice,
  "com.jayway.jsonpath" % "json-path" % "2.4.0",
  "software.reinvent" % "commons" % "0.3.4",
  "org.assertj" % "assertj-core" % "3.8.0" % "test"
)

dependencyUpdatesExclusions := moduleFilter(organization = "mysql")

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

// --------------------
// ------ DOCKER ------
// --------------------
// build with sbt docker:publishLocal

// change to smaller base image
dockerBaseImage := "frolvlad/alpine-oraclejdk8:latest"
dockerCommands := dockerCommands.value.flatMap {
  case cmd@Cmd("FROM", _) => List(cmd, Cmd("RUN", "apk update && apk add bash"))
  case other => List(other)
}

// setting a maintainer which is used for all packaging types</pre>
maintainer := "Me"

// exposing the play ports
dockerExposedPorts in Docker := Seq(9000, 9443)

// publish to repo
//dockerRepository := Some("quay.io/")
//dockerUpdateLatest := true

// run this with: docker run -p 9000:9000 <name>:<version>
