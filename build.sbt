import ReleaseTransformations._

val contentEntityVersion = "2.2.1"
val contentAtomVersion = "3.4.0"
val storyPackageVersion = "2.2.0"
val contentApiModelsVersion = "17.3.1-SNAPSHOT"

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
	resolvers += Opts.resolver.sonatypeSnapshots
)

ThisBuild / organization := "com.gu"
ThisBuild / scalaVersion := "2.12.11"
ThisBuild / licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

lazy val scalaApiModels = project.in(file("models") / "scala")
  .settings(commonSettings)
  .settings(
    name := "apps-rendering-api-models",

	Compile / scroogeLanguages := Seq("scala"),

    libraryDependencies ++= Seq(
      "org.apache.thrift" % "libthrift" % "0.16.0",
      "com.twitter" %% "scrooge-core" % "22.1.0",
      "com.gu" %% "content-api-models-scala" % contentApiModelsVersion
    ) ++ libraryDeps,

    publishTo := sonatypePublishToBundle.value,

    scmInfo := Some(ScmInfo(
      url("https://github.com/guardian/apps-rendering-api-models"),
      "scm:git:git@github.com:guardian/apps-rendering-api-models.git"
    )),

    homepage := Some(url("https://github.com/guardian/apps-rendering-api-models")),

    developers := List(Developer(
      id = "Guardian",
      name = "Guardian",
      email = null,
      url = url("https://github.com/guardian")
    )),

    releaseProcess := Seq[ReleaseStep](
      // checkSnapshotDependencies,
      inquireVersions,
      runClean,
      runTest,
      setReleaseVersion,
      releaseStepCommand("publishSigned"),
      ReleaseStep(action = st => {
        // sonatypeBundleRelease should only be called if we are not doing a snapshot release
        // see: https://github.com/xerial/sbt-sonatype/blob/33b6ea4e5a6ca519213f20c349a8a4f57171929a/README.md#buildsbt
        if (!isSnapshot.value) {
          releaseStepCommand("sonatypeBundleRelease")
        }
        st
      }),
    )
  )

lazy val tsApiModels = project.in(file("models") / "ts")
  .enablePlugins(ScroogeTypescriptGen)
  .settings(commonSettings)
  .settings(
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
