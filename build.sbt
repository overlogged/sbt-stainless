sbtPlugin := true

organization := "org.zjulambda.scala"
name := "sbt-stainless"
version := "0.1.0"

crossSbtVersions := Seq("1.0.1", "0.13.16")

scriptedSettings
scriptedBufferLog := false
scriptedLaunchOpts += "-Dproject.version=" + version.value
licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))
bintrayPackageLabels := Seq("sbt", "plugin")
