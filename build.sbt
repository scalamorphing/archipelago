// Project archipelago written by Maxim Chepel

lazy val archipelago = (
    project in file(".")
  ).enablePlugins(
    ScalaJSPlugin
  ).settings(
    inThisBuild(List(
      organization := "expert.scalamorphing",
      scalaVersion := "2.11.8"
    )),
    name := "archipelago",
    clippyColorsEnabled := true,
    wartremoverErrors ++= Warts.allBut(Wart.Throw, Wart.ExplicitImplicitTypes, Wart.PublicInference, Wart.Nothing, Wart.Equals, Wart.TryPartial),
    wartremoverErrors ++= Seq(
      ScalaJSWart.ArrayPartial,
      ScalaJSWart.UndefOrOpsPartial
    ),
    scalaSource in Compile := file(s"${baseDirectory.value.getAbsolutePath}/library"),
    scalaSource in Test := file(s"${baseDirectory.value.getAbsolutePath}/library-test"),
    version := "0.0.1",
    libraryDependencies := Seq(
      "org.scalactic" %%% "scalactic" % "3.0.1" % "test",
      "org.scalatest" %%% "scalatest" % "3.0.1" % "test",
      "com.lihaoyi" %%% "scalatags" % "0.6.2",
      "io.monix" %%% "monix" % "2.2.1",
      "io.monix" %%% "monix-cats" % "2.2.1",
      "org.scala-js" %%% "scalajs-dom" % "0.9.0"
    ),
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
    addCompilerPlugin("org.wartremover" %% "wartremover" % "2.0.2"),
    resolvers += Resolver.sonatypeRepo("releases"),
    addCompilerPlugin("com.softwaremill.clippy" %% "plugin" % "0.5.1" classifier "bundle")
  )

lazy val archipelagoSample = (
    project in file("./archipelago-sample")
  ).enablePlugins(
    ScalaJSPlugin
  ).settings(
    inThisBuild(List(
      organization := "expert.scalamorphing",
      scalaVersion := "2.11.8"
    )),
    name := "archipelagoSample",
    clippyColorsEnabled := true,
    wartremoverErrors ++= Warts.allBut(Wart.Throw, Wart.ExplicitImplicitTypes, Wart.PublicInference, Wart.Nothing, Wart.Equals, Wart.TryPartial),
    wartremoverErrors ++= Seq(
      ScalaJSWart.ArrayPartial,
      ScalaJSWart.UndefOrOpsPartial
    ),
    scalaSource in Compile := file(s"${baseDirectory.value.getAbsolutePath}/app"),
    scalaSource in Test := file(s"${baseDirectory.value.getAbsolutePath}/test"),
    version := "0.0.1",
    libraryDependencies := Seq(
      "org.scalactic" %%% "scalactic" % "3.0.1" % "test",
      "org.scalatest" %%% "scalatest" % "3.0.1" % "test",
      "com.lihaoyi" %%% "scalatags" % "0.6.2",
      "io.monix" %%% "monix" % "2.2.1",
      "io.monix" %%% "monix-cats" % "2.2.1",
      "org.scala-js" %%% "scalajs-dom" % "0.9.0"
    ),
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
    addCompilerPlugin("org.wartremover" %% "wartremover" % "2.0.2"),
    resolvers += Resolver.sonatypeRepo("releases"),
    addCompilerPlugin("com.softwaremill.clippy" %% "plugin" % "0.5.1" classifier "bundle")
  ).dependsOn(archipelago)