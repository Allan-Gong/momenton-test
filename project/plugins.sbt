// Plugin for sbt to create Eclipse project definitions.
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "4.0.0")

// sbt-scoverage is a plugin for SBT that integrates the scoverage code coverage library.
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.0")

// Create a fat JAR of your project with all of its dependencies.
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.4")