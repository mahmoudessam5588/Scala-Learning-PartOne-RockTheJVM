package Lectures.ScalaAdvancedCourse.PartThree
import scala.util.{Failure, Random, Success}
import scala.concurrent.{Await, Future, Promise}
import scala.language.postfixOps
import scala.util.*
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*



object FutureAndPromisesPArtTwo extends App {
  //block on future with promise feature
  //online Bank App
  case class User(name: String)

  case class Transaction(sender: String, receiver: String, amount: Double, status: String)

  object MiniBankingApp {
    val name = "Zero Interest People's Bank"

    def fetchUser(name: String): Future[User] = {
      //simulate fetching from DB
      Future {
        Thread.sleep(400)
        User(name)
      }
    }

    def fetchTransaction(username: User, merchantName: String, amount: Double): Future[Transaction] = {
      //simulate some processing
      Future {
        Thread.sleep(1000)
        Transaction(username.name, merchantName, amount, "SUCCESS")
      }
    }

    def purchase(userName: String, merchantName: String, cost: Double): String =
      //fetch the user
      //create Transaction
      //wait Transaction to finish
      val validTransaction =
        for
          user <- MiniBankingApp.fetchUser(userName)
          transactions <- MiniBankingApp.fetchTransaction(user, merchantName, cost)

        yield transactions.status
      Await.result(validTransaction,2.seconds)


  }
  println(MiniBankingApp.purchase("Mahmoud","Lenovo",22000))
  //promises
  val promise = Promise[Int]()//controller over future
  val future = promise.future
  //Thread consumer 1
  future.onComplete {
    case Success(value) => println(s"[Consumer] I Received The $value")
    case Failure(exception) => exception.printStackTrace()
  }
  //Thread producer 2
  val producer = new Thread(()=>{
    println("Producer Computing Numbers ....")
    Thread.sleep(500)
    //fulfilling the promise
    promise.success(42)
    println("[Producer] done")
  })
  producer.start()
  Thread.sleep(1000)
}

