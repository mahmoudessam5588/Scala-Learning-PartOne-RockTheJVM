package ExercisesAndProblemSolving.AdvancedCourseExercises.PartThree

import scala.util.{Failure, Random, Success}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object FuturesPromisesExercise extends App {
  case class Profile(ids:String,names:String):
    def poke(anotherProfile:Profile):Unit=
      println(s"$names poking ${anotherProfile.names}")

  object SocialNetwork {
    val random = new Random()
    //database
    val names: Map[String, String] = Map (
      "FB.1.islam" -> "Mohammed",
      "FB.2.Yousef" -> "Yakoub",
      "FB.3.Mussa" -> "Haroon",
      "FB.4.Solomon" -> "David"
    )

    val friendShipsByIds: Map[String, String] = Map (
      "FB.1.islam" -> "FB.3.Mussa",
      "FB.2.Yousef" -> "FB.4.Solomon"
    )

    //API
    def fetchByProfile(id: String): Future[Profile] = {
      Future {
        Thread.sleep(random.nextInt(500))
        Profile(id, names(id))
      }
    }

    def fetchByFriends(profile: Profile): Future[Profile] = {
      Future {
        Thread.sleep(random.nextInt(600))
        Profile(friendShipsByIds(profile.ids), names(friendShipsByIds(profile.ids)))
      }
    }
  }

    val islam: Future[Profile] = SocialNetwork.fetchByProfile("FB.1.islam")
    islam.onComplete {
      case Success(islamMohammed) =>
        val mussa = SocialNetwork.fetchByFriends(islamMohammed)
        mussa.onComplete{
          case Success(bothAreFriends) => islamMohammed.poke(bothAreFriends)
          case Failure(exception) => exception.printStackTrace()
        }
      case Failure(exception) => exception.printStackTrace()
    }
  Thread.sleep(3000)
  //better way of writing code
  //map , flatmap and filter
  val extractName: Future[String] = islam.map(profile=>profile.names)
  val extractBestFriend: Future[Profile] = islam.flatMap(profile=>SocialNetwork.fetchByFriends(profile))
  val filterBestFriend: Future[Profile] = extractBestFriend.filter(profile =>profile.names.startsWith("H"))
  //for comprehensions so much better fells natural way
  for
    islam <- SocialNetwork.fetchByProfile("FB.1.islam")
    islamFriend <- SocialNetwork.fetchByFriends(islam)
  yield islam.poke(islamFriend);Thread.sleep(3000)

  //fallback for recovery in case of wrong entry for example
  val dummyProfile = SocialNetwork.fetchByProfile("Unknown Id").recover {
    case e: Throwable => Profile("FB.4.Solomon" ,"David")}
  // in case we don't need to get the whole  profile use recover with instead that fetches the profile
  val fetchedAnyProfile = SocialNetwork.fetchByProfile("Unknown Id").recoverWith{
    case e: Throwable => SocialNetwork.fetchByProfile("FB.4.Solomon")
  }
  //fallbackTo
  val fallBack = SocialNetwork.fetchByProfile("Unknown Id").fallbackTo(SocialNetwork.fetchByProfile("FB.4.Solomon"))





}
