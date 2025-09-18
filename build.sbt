import ReleaseTransformations._
import sbtversionpolicy.withsbtrelease.ReleaseVersion

val contentEntityVersion = "3.0.3"
val contentAtomVersion = "6.1.0"
val storyPackageVersion = "2.2.0"
val contentApiModelsVersion = "31.0.0"

val scroogeDependencies = Seq(
  "content-api-models",
  "story-packages-model-thrift",
  "content-atom-model-thrift",
  "content-entity-thrift"
)

val libraryDeps = Seq(
  "com.gu" % "content-api-models" % contentApiModelsVersion,
  "com.gu" % "story-packages-model-thrift" % storyPackageVersion,
  "com.gu" % "content-atom-model-thrift" % contentAtomVersion,
  "com.gu" % "content-entity-thrift" % contentEntityVersion
)

lazy val commonSettings = Seq(
  description := "Models used by the apps-rendering API",
	// downgrade scrooge reserved word clashes to warnings
	Compile / scroogeDisableStrict := true,
	Compile / scroogeThriftSourceFolder := baseDirectory.value / "../src/main/thrift",
	Compile / scroogeThriftDependencies ++= scroogeDependencies,
)

val artifactProductionSettings = Seq(
  organization := "com.gu",
  scalaVersion := "2.13.14",
  // scrooge 21.3.0: Builds are now only supported for Scala 2.12+
  // https://twitter.github.io/scrooge/changelog.html#id11
  crossScalaVersions := Seq("2.12.18", scalaVersion.value),
  scalacOptions ++= Seq("-release:11"),// going ahead with release option only. We might add more options if any implementation comes in future :  ("-feature", "-deprecation", "-unchecked", "-Xfatal-warnings")
  licenses := Seq(License.Apache2),
  Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-u", s"test-results/scala-${scalaVersion.value}", "-o")
)

lazy val root = (project in file("."))
  .aggregate(scalaApiModels)
  .settings(
    publish / skip := true,
    releaseVersion := ReleaseVersion.fromAggregatedAssessedCompatibilityWithLatestRelease().value,
    releaseCrossBuild := true,
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runClean,
      runTest,
      setReleaseVersion,
      commitReleaseVersion,
      tagRelease,
      setNextVersion,
      commitNextVersion
    )
  )

lazy val scalaApiModels = project.in(file("models") / "scala")
  .settings(artifactProductionSettings, commonSettings)
  .settings(
    name := "apps-rendering-api-models",
    scalacOptions := Seq("-release:11"),

    crossScalaVersions := Seq(scalaVersion.value, "2.12.20"),

	  Compile / scroogeLanguages := Seq("scala"),

    libraryDependencies ++= Seq(
      "org.apache.thrift" % "libthrift" % "0.22.0",
      "com.twitter" %% "scrooge-core" % "22.12.0",
      "com.gu" %% "content-api-models-scala" % contentApiModelsVersion
    ) ++ libraryDeps,
  )

lazy val tsApiModels = project.in(file("models") / "ts")
  .enablePlugins(ScroogeTypescriptGen)
  .settings(commonSettings)
  .settings(
    publish / skip := true,
    name := "apps-rendering-api-models-ts",
    scroogeTypescriptNpmPackageName := "@guardian/apps-rendering-api-models",
    Compile / scroogeDefaultJavaNamespace := scroogeTypescriptNpmPackageName.value,

    scroogeTypescriptPackageLicense := "Apache-2.0",
	  Compile / scroogeLanguages := Seq("typescript"),

    scroogeTypescriptPackageMapping := Map(
      "content-api-models" -> "@guardian/content-api-models",
      "content-entity-thrift" -> "@guardian/content-entity-model",
      "content-atom-model-thrift" -> "@guardian/content-atom-model",
      "story-packages-model-thrift" -> "@guardian/story-packages-model"
    ),

    libraryDependencies ++= libraryDeps,
  )
