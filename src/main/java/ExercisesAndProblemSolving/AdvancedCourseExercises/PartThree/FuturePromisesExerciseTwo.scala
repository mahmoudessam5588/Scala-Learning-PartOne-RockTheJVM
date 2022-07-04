package ExercisesAndProblemSolving.AdvancedCourseExercises.PartThree


import scala.util.{Failure, Random, Success}
import scala.concurrent.{Await, Future, Promise}
import scala.language.postfixOps
import scala.util.*
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*

import scala.concurrent.Future

object FuturePromisesExerciseTwo extends App {
  /*
  * fulfill a future immediately with a value
  * IN-sequence (fa , fb)
  * first (fa,fb)
  * last(fa,fb)
  * retryUntil(action:()=>Future[T] ,condition[T} => Boolean):Future[T]
  * */
  //1) fulfill immediately
  def fulfillImmediately[T](value:T):Future[T] = Future(value)

  def inSequence[A,B](first :Future[A] , second: Future[B]):Future[B] =
    first.flatMap(_ => second)

  def first[A](fa:Future[A],fb:Future[A]):Future[A] =
    val promise = Promise[A]
    fa.onComplete(promise.tryComplete)
    fb.onComplete(promise.tryComplete)
    promise.future

  def last[A](fa:Future[A],fb:Future[A]):Future[A]=
    val promiseLast = Promise[A]
    val promiseBoth = Promise[A]
    val checkIfEither=
      (result: Try[A]) =>{
        if !promiseBoth.tryComplete(result) then promiseLast.complete(result)
      }
    fa.onComplete(checkIfEither)
    fb.onComplete(checkIfEither)
    promiseLast.future

  val fast = Future{
    Thread.sleep(100)
    42
  }

  val slow = Future{
    Thread.sleep(240)
    45
  }
  first(fast,slow).foreach(f =>println(s"First: $f"))
  last(fast,slow).foreach(l =>println(s"Last: $l"))
  Thread.sleep(1000)

  def retryUntil[A](action: () => Future[A], condition: A => Boolean ):Future[A] = {
    action().filter(condition).recoverWith{
      case _ => retryUntil(action,condition)
    }
  }
  val random: Random =new Random()
  val action: () => Future[Int] = {
    () =>
      Future {
        Thread.sleep(300)
        val nextValue = random.nextInt(50)
        println("Generated: " + nextValue)
        nextValue .+(random.nextInt(55))
      }
  }
    retryUntil(action,(x:Int) => x <50).foreach(result =>println("settled: "+result))
    Thread.sleep(5000)


}
