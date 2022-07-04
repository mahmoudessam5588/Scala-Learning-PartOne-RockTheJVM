package lectures.ScalaBeginnerCourse.PartThree

import scala.util.Random

object Options extends App {
  val optionSome: Option[Int] = Some(4)
  val optionNone: Option[Int] = None
  println(s"$optionSome  ,  $optionNone")
  //val result = Some(unsafeMethod())//WRONG
  val result = Option(unsafeMethod()) //safe check some or none
  val checkApi = Option(unsafeMethod()).orElse(Option(backupMethod()))
  println(result)
  val betterApiCheck = betterNone() orElse betterSome()

  def unsafeMethod(): String = null

  def backupMethod(): String = "A Valid Result"

  //better checked api result
  def betterNone(): Option[String] = None
  println(betterApiCheck)
  println(optionSome.map(_ * 2))
  println(optionSome.filter(_ % 2 == 0))
  println(optionSome.flatMap(x => Option(x.*(7))))

  def betterSome(): Option[String] = Some("Valid")

  val Config: Map[String, String] = Map("host" -> "172.165.145", "Port" -> "90")
  class Connection:
    def connect: String = "Connected"

  object Connection:
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if random.nextBoolean() then Some(new Connection)
      else None
  //chain calls
  Config.get("host").flatMap(host=>Config.get("Port")
    .flatMap(port=>Connection(host,port)).map(Connection=>Connection.connect)).foreach(println)
  //connection status
  val connectionStatus =
    for
      host <- Config.get("host")
      port <- Config.get("Port")
      connection <- Connection(host,port)
    yield connection.connect
  connectionStatus.foreach(println)


}
