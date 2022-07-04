package Lectures.ScalaAdvancedCourse.PartTwo

import scala.math.Fractional.Implicits.infixFractionalOps
import scala.math.Integral.Implicits.infixIntegralOps

object Monads {


  lazy val flatMappedInstance: Lazy[Int] = lazyInstance.flatMap(x => Lazy {
    10 * x
  })
  lazy val flatMappedInstance2: Lazy[Int] = lazyInstance.flatMap(x => Lazy {
    10 * x
  })
  val x: LazyList[Int] = LazyList(1, 22, 33, 56, 67).flatMap(x => LazyList(x + 1))
  val y: LazyList[Int] = LazyList(1, 22, 33, 56, 67).flatMap(x => LazyList(x + 1)).flatMap(y => LazyList(y))
  val lazyInstance: Lazy[Int] = Lazy {
    println("In The empty Streets I Walk Alone")
    35
  }

  def main(args: Array[String]): Unit = {
    /*
    left-identity:
      unit.flatmap(f) = f(x)
      attempt(x).flatmap(f) = f(x) //Success case
      success(x).flatmap(f) = f(x) // proved
    right-identity:
    attempt.flatmap(unit) = attempt
    success(x).flatmap(x=>attempt(x)) =attempt(x) =Success(x)
    fail(e).flatmap(...)= fail(e)

    associativity
    attempt.flatmap(f).flatmap(g) ==attempt.flatmap(x => f(x).flatmap(g))
    fail(e).flatmap(f).flatmap(g) = fail(e)
    fail(e).flatmap(x=>f(x).flatmap(g)) = fail(e)

    success(v).flatmap(F).flatmap(g) = f(v).flatmap(g) or fail(e)

    success.flatmap(x=>f(x).flatmap(g)) = f(v).flatmap(g) or fail(e)
    * */
    /*
    * Exercise
    * 1)Implement Lazy[T] monad = computation that will only be executed when needed
    * 2)Monads = unit +flatmap
    *   Monads = unit + map + flatten
    * Monad[T]{
    * def flatMap[B](f: T =>B):Monad[B] = .... (implemented)
    * def map[B](f: T=>B):Monad[B] = ???
    *def flatten(m:Monad[Monad[T]]):Monad[T]= ???  */

    println(x.toList)
    println(LazyList(x).toList.flatten)
    println(y.toList)
    println(LazyList(y).flatMap(x => LazyList(x)).toList.flatten)

    flatMappedInstance.use
    flatMappedInstance2.use

  }

  class Lazy[+A](value: => A) {
    private lazy val internalValue = value

    def use: A = internalValue

    def flatMap[B](f: (=> A) => Lazy[B]): Lazy[B] = f(value)

  }

  object Lazy {
    def apply[A](value: => A): Lazy[A] =
      new Lazy(value)
  }


}

trait MyAttempt[+A] {
  def flatmap[B](f: A => MyAttempt[B]): MyAttempt[B]
}

case class Fail(e: Throwable) extends MyAttempt[Nothing] {
  override def flatmap[B](f: Nothing => MyAttempt[B]): MyAttempt[B] = this
}

case class Success[+A](value: A) extends MyAttempt[A] {
  override def flatmap[B](f: A => MyAttempt[B]): MyAttempt[B] =
    try {
      f(value)
    } catch {
      case e: Throwable => Fail(e)
    }
}

object MyAttempt {
  def apply[A](a: => A): MyAttempt[A] = {
    try {
      Success(a)
    } catch {
      case e: Throwable => Fail(e)
    }
  }


}
