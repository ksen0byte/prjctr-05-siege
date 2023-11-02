package app

import java.sql.*
import anorm._
import anorm.SqlParser._
import scala.util.Random

object MinimalApplication extends cask.MainRoutes {
  override def port: Int    = 8081
  override def host: String = "0.0.0.0"

  @cask.get("/")
  def put() = db.putData(Random.alphanumeric.filter(_.isLetter).take(10).toList.mkString)

  @cask.get("/tasks")
  def get() = ujson.Arr.from(db.getData)

  initialize()
  println("ready to serve")
}

object db {
  Class.forName("org.sqlite.JDBC")
  given Connection = DriverManager.getConnection("jdbc:sqlite:./sqlite/db/todolist.db")

  def putData(task: String): String =
    SQL(s"INSERT INTO tasks(description) VALUES ({description})").on("description" -> task).execute()
    s"written task $task"

  def getData: List[String] =
    SQL("SELECT description FROM tasks").as(str("description").*)

}
