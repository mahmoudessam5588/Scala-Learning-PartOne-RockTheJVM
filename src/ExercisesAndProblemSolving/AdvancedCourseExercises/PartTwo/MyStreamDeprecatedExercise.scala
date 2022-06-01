package ExercisesAndProblemSolving.AdvancedCourseExercises.PartTwo

import Lectures.ScalaAdvancedCourse.PartTwo.LazyEvaluation.res

import scala.annotation.{tailrec, targetName}

object MyStreamDeprecatedExercise {
  abstract class MyStream[+A]{
    def isEmpty:Boolean
    def head : A
    def tail : MyStream[A]

    @targetName("PrePend Add To Stream")
    def #:: [B >: A](element:B):MyStream[B]

    @targetName("Concat Two Streams")
    def ++ [B >: A](anotherStream: => MyStream[B]): MyStream[B]

    def forEach(f:A=>Unit):Unit
    def map[B](f:A => B):MyStream[B]
    def flatMap[B](f:A => MyStream[B]):MyStream[B]
    def filter(predicate:A=>Boolean):MyStream[A]
    def take(n:Int):MyStream[A]
    def takeAsList(n:Int):List[A] = take(n).toList()
    @tailrec
    final def toList[B >: A](acc:List[B]= Nil):List[B] =
      if isEmpty then acc
      else tail toList head :: acc
  }
  object EmptyStream extends MyStream[Nothing]{
    override def isEmpty:Boolean = true
    override def head : Nothing = throw new NoSuchElementException
    override def tail : MyStream[Nothing] = throw new NoSuchElementException

    @targetName("PrePend Add To Stream")
    override def #:: [B >: Nothing](element:B):MyStream[B] = new Cons[B](element,this)

    @targetName("Concat Two Streams")
    override def ++ [B >: Nothing](anotherStream: => MyStream[B]): MyStream[B] = anotherStream

    override def forEach(f:Nothing=>Unit):Unit = ()
    override def map[B](f:Nothing=> B):MyStream[B] = this
    override def flatMap[B](f:Nothing => MyStream[B]):MyStream[B] = this
    override def filter(predicate:Nothing=>Boolean):MyStream[Nothing] = this
    override def take(n:Int):MyStream[Nothing] = this

  }
  class Cons[+A](hd:A,tl : => MyStream[A]) extends MyStream[A]{
    override def isEmpty:Boolean = false
    override  val head : A = hd
    override lazy val tail : MyStream[A] = tl //call by need

    @targetName("PrePend Add To Stream")
    def #:: [B >: A](element:B):MyStream[B] = new Cons[B](element,this)

    @targetName("Concat Two Streams")
    def ++ [B >: A](anotherStream: => MyStream[B]): MyStream[B] =
      new Cons[B](head,tail++anotherStream)

    def forEach(f:A=>Unit):Unit=
      f(head)
      tail forEach f
    def map[B](f:A => B):MyStream[B] =
      new Cons[B](f(head) , tail map  f)
    def flatMap[B](f:A => MyStream[B]):MyStream[B] =
      f(head) ++ (tail flatMap f)
    def filter(predicate:A=>Boolean):MyStream[A] =
      if predicate(head) then new Cons(head,tail filter predicate)
      else tail filter predicate
    def take(n:Int):MyStream[A] =
      if n <= 0 then EmptyStream
      else if  n == 1 then new Cons(head,EmptyStream)
      else new Cons(head, tail take n-1)
  }
  object MyStream{
    //generate next value based on previous one
    def from[A](start:A)(generator:A=>A):MyStream[A] =
      new Cons[A](start,MyStream.from(generator(start))(generator))
  }

  def main (args:Array[String]):Unit={
    val natural = MyStream.from(1)(_+1)
    println(natural.head)
    println(natural.tail.head)
    println(natural.tail.tail .tail.head)
    val start0 = 0 #:: natural
    println(start0.head)
    start0.take(10000).map(_+1).filter(_%2==0).forEach(println)
    println(start0.take(10000).map(_*2).filter(_%2==0).toList().reverse)
    println(start0.flatMap(x=>new Cons(x ,new Cons(x+1,new Cons(x+2,EmptyStream)))).take(100).toList())
    //fibonacci both stream and lazyList
    def fibonacci(first:BigInt,second:BigInt):LazyList[BigInt]=
       LazyList.cons(first,fibonacci(second,second+first))
    println(fibonacci(1,1).take(100).toList)

    def fictionalSet(first:BigInt,second:BigInt):MyStream[BigInt]=
      new Cons(first,fictionalSet(second,first+second))
    print(fictionalSet(1,1).take(100).toList())
    //eratosthenes sieve both Stream and lazyList
    def eratosthenes(number:LazyList[Int]):LazyList[Int]=
      if number.isEmpty then number
      else LazyList.cons(number.head,number.tail.filter(_%number.head!=0))
    println("--------------")
    println(eratosthenes(LazyList.from(1).map(_+1).take(2000)).toList)
    println("--------------------")
    def demonstrativeness(n:MyStream[Int]):MyStream[Int]=
      if n.isEmpty then n
      else new Cons(n.head,n.tail.filter(_%n.head!=0))
    println(demonstrativeness(MyStream.from(1)(_+1).take(200)).toList())







  }

}
