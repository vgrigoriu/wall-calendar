val scala3Version = "3.3.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "calendar",
    version := "2024.0.0",

    scalaVersion := scala3Version,
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,

    scalacOptions += "-Werror",
    scalacOptions += "-Wunused:imports",

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
