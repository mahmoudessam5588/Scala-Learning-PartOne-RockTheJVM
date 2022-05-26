package Lectures.PartThree

import Lectures.PartThree.Options.{Connection, connectionStatus, result}

import scala.util.*

object HandlingFailures extends App {
  def aSuccess = Success(3)
  def aFailure = Failure(new RuntimeException("Super Failure"))
  println(s"$aSuccess && $aFailure")
  def failedMethod:String = throw new RuntimeException("Go Away Failure")
  //try object via apply method
  val tried: Try[Failure[Nothing]] =Try(aFailure)
  val tryMethod: Try[String] = Try(failedMethod)
  println(tried)
  println(tryMethod)
  println(tryMethod.isSuccess)
  println(tryMethod.isFailure)
  def backupMethod: String ="valid Result"
  //chained try failBack
  val fallBack = Try(failedMethod).orElse(Try(backupMethod))
  println(fallBack)
  //API Design
  def betterUnsafeMethod():Try[String] = Failure(new RuntimeException("Valid Api"))
  def betterBackupMethod():Try[String] = Success("Valid Result")
  val betterApiHandling = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterApiHandling)
  //map flatmap failure
  println(aSuccess.map(_*2))
  println(aSuccess.flatMap(x=>Success(x*10)))
  println(aSuccess.filter(_>10))

  //exercises
  val hostName: String = "localhost"
  val port: String = "8080"
  def renderHTML(page:String): Unit = println(page)

  class Connection:
    def get(url:String):String =
      val random = new Random(System.nanoTime())
      if random.nextBoolean() then "<html>...</html>"
      else throw new RuntimeException("Connection Interrupted")
    def getSafe(url:String):Try[String] = Try(get(url))

  object HttpService:
    val random =new Random(System.nanoTime())

    def getConnection(host:String,port:String):Connection=
      if(random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

    def getSafeConnection(host:String,port:String):Try[Connection]= Try(getConnection(hostName,port))

  val result: Try[Unit] =
    for
    connect <- HttpService.getSafeConnection(hostName,port)
    t <- connect.getSafe("/home")
    yield renderHTML(t)
  println(result)
  println("--------------------")
  HttpService.getSafeConnection(hostName,port).flatMap(x=>x.getSafe("/home")).foreach(renderHTML)
  val res ="10000111".filter(_.equals('1')).toInt
  println(res)


}
