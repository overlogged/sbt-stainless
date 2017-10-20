package org.zjulambda.scala

import sbt._
import sbt.Keys.sources
import scala.sys.process

object SbtStainlessPlugin extends AutoPlugin {

  object autoImport {
    val stainlessBackend = SettingKey[String]("backend for stainless")
    val stainlessFlags = SettingKey[Seq[String]]("flags for stainless")
    val stainlessVerify = TaskKey[Unit]("verify","Verify this project using stainless")
  }
  import autoImport._

  override def trigger = allRequirements
  override lazy val projectSettings:Seq[sbt.Def.Setting[_]] = Seq(
    stainlessBackend:="scalac",
    stainlessFlags:= Seq("--vccache"),
    sources in stainlessVerify := (sources in Compile).value,
    stainlessVerify:= {
      val flags = stainlessFlags.value
      val backend = stainlessBackend.value
      val files = sources.in(stainlessVerify).value.map(_.getAbsolutePath)
      doVerify(backend,files,flags)
    }
  )

  def doVerify(backend:String,files:Seq[String],flags:Seq[String]): Unit = {
    val cmd = (files++flags).foldLeft(s"stainless-$backend")(_+" "+_)
    println(cmd)
    process.Process(cmd) !
  }
}
