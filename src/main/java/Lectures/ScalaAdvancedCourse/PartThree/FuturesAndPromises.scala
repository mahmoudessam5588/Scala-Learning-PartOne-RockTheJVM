package Lectures.ScalaAdvancedCourse.PartThree

import java.lang
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.postfixOps
import scala.util.*

object FuturesAndPromises {
  def main(args: Array[String]): Unit = {
    val aFuture: Future[Int] = Future(calculateTheMeaningOfLife) //global injected into futures to allocate and manage future threads
    println(aFuture.value) //returns an Option[Try[Int]]
    println("Waiting for Future To Complete")
    aFuture.onComplete {
      case Success(meaningOfLife) => println(s"The Answer IS $meaningOfLife")
      case Failure(e) => println(s"I Have Failed With $e")
    }
    Thread.sleep(3000)

    /*val mark = socialNetwork.fetchProfile("fb.id.1.Zuckerberg")
    mark.onComplete {
      case Success(markProfile) =>
        val bill = socialNetwork.fetchBestFriend(markProfile)
        bill.onComplete {
          case Success(billProfile) =>markProfile.poke(billProfile)
          case Failure(e) => e.printStackTrace()}
      case Failure(exception) => exception.printStackTrace()*/
    }

    //mini social network
    case class Profile(id: String, names: String):
      def poke(anotherProfile: Profile): Unit =
        println(s"$this.name poking ${anotherProfile.names}")

    object socialNetwork {
      //Database
      val names: Map[String, String] = Map(
        "fb.id.1.Zuckerberg" -> "Mark",
        "fb.id.2.Bill" -> "Bill",
        "fb.id.3.Fake" -> "Faker"
      )
      //friendships
      val friendship: Map[String, String] = Map(
        "fb.id.1.Zuckerberg" -> "fb.id.2.Bill"
      )
      val random = new Random() //simulate computing

      //API
      def fetchProfile(id: String): Future[Profile] =
        Future {
          Thread.sleep(random.nextInt(300))
          Profile(id, names(id))
        }
      def fetchBestFriend(profile:Profile):Future[Profile]=
        Future{
          Thread.sleep(random.nextInt(400))
          val bestFriend = friendship(profile.id)
          Profile(bestFriend , names(bestFriend))
        }
    }


  }

  def calculateTheMeaningOfLife: Int ={
    Thread.sleep(2000)
    42


}
