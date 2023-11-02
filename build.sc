import mill._, scalalib._

import $ivy.`com.lihaoyi::mill-contrib-docker:$MILL_VERSION`
import contrib.docker.DockerModule

object app extends ScalaModule with DockerModule {
  object docker extends DockerConfig {
    override def tags = List("cask-sqlite-app")
    override def exposedPorts = Seq(8081)
  }

  def scalaVersion = "3.3.1"

  override def ivyDeps = Agg(
    ivy"com.lihaoyi::cask:0.9.1",
    ivy"org.xerial:sqlite-jdbc:3.43.2.2",
    ivy"org.playframework.anorm::anorm:2.7.0"
  )
}
