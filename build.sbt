import Dependencies.{PROVIDED, TEST, _}
import xerial.sbt.Sonatype._
import ReleaseTransformations._

val testcontainersVersion = "1.6.0"
val seleniumVersion = "2.53.1"
val slf4jVersion = "1.7.25"
val scalaTestVersion = "3.0.5"
val specs2TestVersion = "4.0.3"
val mysqlConnectorVersion = "5.1.46"
val postgresqlDriverVersion = "9.4.1212"
val mockitoVersion = "1.10.19"
val shapelessVersion = "2.3.3"

lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")

//lazy val root = (project in file("."))
//  .settings(
//    organization in ThisBuild := "com.dimafeng",
//    scalaVersion in ThisBuild := "2.12.5",
//    crossScalaVersions := Seq("2.11.11", "2.12.5"),
//    name := "testcontainers-scala",
//    compileScalastyle := scalastyle.in(Compile).toTask("").value,
//    test in Test := (test in Test)
//      .dependsOn(compileScalastyle in Compile)
//      .value,
//    /**
//      * Dependencies
//      */
//    libraryDependencies ++=
//      COMPILE(
//        "org.testcontainers" % "testcontainers" % testcontainersVersion,
//        "com.chuusai" %% "shapeless" % shapelessVersion
//      )
//        ++ PROVIDED(
//          "org.seleniumhq.selenium" % "selenium-java" % seleniumVersion,
//          "org.testcontainers" % "selenium" % testcontainersVersion,
//          "org.slf4j" % "slf4j-simple" % slf4jVersion,
//          "org.scalatest" %% "scalatest" % scalaTestVersion,
//          "org.testcontainers" % "mysql" % testcontainersVersion,
//          "org.testcontainers" % "postgresql" % testcontainersVersion
//        )
//        ++ TEST(
//          "mysql" % "mysql-connector-java" % mysqlConnectorVersion,
//          "junit" % "junit" % "4.12",
//          "org.testcontainers" % "selenium" % testcontainersVersion,
//          "org.postgresql" % "postgresql" % postgresqlDriverVersion,
//          "org.mockito" % "mockito-all" % mockitoVersion
//        ),
//    /**
//      * Publishing
//      */
//    useGpg := true,
//    publishTo := sonatypePublishTo.value,
//    publishMavenStyle := true,
//    sonatypeProfileName := "testcontainers-scala",
//    sonatypeProjectHosting := Some(
//      GitLabHosting("testcontainers",
//                    "testcontainers-scala",
//                    "dimafeng@gmail.com")),
//    licenses := Seq(
//      "The MIT License (MIT)" -> new URL(
//        "https://opensource.org/licenses/MIT")),
//    releaseCrossBuild := true,
//    releaseProcess := Seq[ReleaseStep](
//      checkSnapshotDependencies,
//      inquireVersions,
//      runClean,
//      runTest,
//      setReleaseVersion,
//      commitReleaseVersion,
//      tagRelease,
//      releaseStepCommandAndRemaining("+publishSigned"),
//      setNextVersion,
//      commitNextVersion,
//      releaseStepCommand("sonatypeReleaseAll"),
//      pushChanges
//    )
//  )

lazy val commonSettings = Seq(
  organization in ThisBuild := "com.dimafeng",
  scalaVersion in ThisBuild := "2.12.5",
  crossScalaVersions := Seq("2.11.11", "2.12.5"),
  compileScalastyle := scalastyle.in(Compile).toTask("").value,
  test in Test := (test in Test).dependsOn(compileScalastyle in Compile).value
)

lazy val core = (project in file("core"))
  .settings(
    commonSettings,
    libraryDependencies ++=
      COMPILE(
        "org.testcontainers" % "testcontainers" % testcontainersVersion,
        "com.chuusai" %% "shapeless" % shapelessVersion
      )
        ++ PROVIDED(
        "org.seleniumhq.selenium" % "selenium-java" % seleniumVersion,
        "org.testcontainers" % "selenium" % testcontainersVersion,
        "org.slf4j" % "slf4j-simple" % slf4jVersion,
        "org.testcontainers" % "mysql" % testcontainersVersion,
        "org.testcontainers" % "postgresql" % testcontainersVersion
      )
        ++ TEST(
        "mysql" % "mysql-connector-java" % mysqlConnectorVersion,
        "junit" % "junit" % "4.12",
        "org.testcontainers" % "selenium" % testcontainersVersion,
        "org.postgresql" % "postgresql" % postgresqlDriverVersion,
        "org.mockito" % "mockito-all" % mockitoVersion
      )
  )

lazy val scalatest = (project in file("scalatest"))
  .dependsOn(core)
  .settings(
    libraryDependencies ++=
      PROVIDED(
        "org.scalatest" %% "scalatest" % scalaTestVersion,
        "org.seleniumhq.selenium" % "selenium-java" % seleniumVersion,
        "org.testcontainers" % "selenium" % testcontainersVersion
      )
        ++ TEST(
        "org.mockito" % "mockito-all" % mockitoVersion
      )
  )

lazy val specs2 = (project in file("specs2"))
  .dependsOn(core)
  .settings(
    libraryDependencies ++=
      PROVIDED(
        "org.specs2" %% "specs2-core" % specs2TestVersion,
        "org.seleniumhq.selenium" % "selenium-java" % seleniumVersion,
        "org.testcontainers" % "selenium" % testcontainersVersion)
  )
