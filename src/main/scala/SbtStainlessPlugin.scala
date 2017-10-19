package org.zjulambda.scala

import sbt._
import scala.reflect.io.Directory

object SbtStainlessPlugin extends AutoPlugin {
  override def trigger = allRequirements
  override def globalSettings = Seq(
    Keys.commands += StainlessCommand
  )

  var backend = "scalac"

  private[this] final val command = "verify"
  private[this] final val brief = s"Usage: $command <arg1> <arg2> ..."
  private[this] final val info = "Verify scala code using stainless."

  private[this] final val detail = s"""
    $info
    $brief
"""

  def subDir(dir:File):Iterator[File] = {
    val dirs = dir.listFiles().filter(_.isDirectory())
    val files = dir.listFiles().filter(_.isFile())
    files.toIterator ++ dirs.toIterator.flatMap(subDir _)
  }

  lazy val StainlessCommand =
    Command.args(command, (brief, info), detail, brief) { (state, args) =>
      subDir(new File(".")) foreach { file => if(file.getName.endsWith(".scala"))
        Resolvers.run((s"stainless-$backend $file --vccache" :: args.toList): _*)
      }
      state
    }
}
