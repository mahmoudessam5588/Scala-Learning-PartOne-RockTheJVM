package Lectures.PartTwo

import Lectures.PartTwo.Cons

import scala.annotation.targetName
import scala.collection.View.Zip


/*trait MYPredicate[-T]:
  def test(n:T):Boolean

trait MyTransformer[-A,B]:
  def transform(x:A):B*/

abstract class CustomList[+A] {
  def head: A

  def tail: CustomList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): CustomList[B]

  def printingElement: String

  def map[B](transformer: A => B): CustomList[B]

  def flatMap[B](transformer: A => CustomList[B]): CustomList[B]

  def filter(predicate: A => Boolean): CustomList[A]

  def forEach(iterator: A => Unit): Unit

  def sorting(compare: (A, A) => Int): CustomList[A]

  def zipWith[B, C](list: CustomList[B], zip: (A, B) => C): CustomList[C]

  def fold[B](start: B)(operator: (B, A) => B): B


  @targetName("stringConcat")
  def ++[B >: A](list: CustomList[B]): CustomList[B]

  //polymorphic call
  override def toString: String = "[" + printingElement + "]"

}

case object Empty extends CustomList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: CustomList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): CustomList[B] = Cons(element, Empty)

  override def printingElement: String = ""

  override def map[B](transformer: Nothing => B): CustomList[B] = Empty

  override def flatMap[B](transformer: Nothing => CustomList[B]): CustomList[B] = Empty

  override def filter(predicate: Nothing => Boolean): CustomList[Nothing] = Empty

  override def forEach(iterator: Nothing => Unit): Unit = ()

  override def sorting(compare: (Nothing, Nothing) => Int): CustomList[Nothing] = Empty

  override def zipWith[B, C](list: CustomList[B], zip: (Nothing, B) => C): CustomList[C] =
    if !list.isEmpty then throw new RuntimeException("Lists don't have same Length")
    else Empty

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start

  @targetName("stringConcat")
  override def ++[B >: Nothing](list: CustomList[B]): CustomList[B] = list

}

case class Cons[+A](h: A, t: CustomList[A]) extends CustomList[A] {
  override def head: A = h

  override def tail: CustomList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): CustomList[B] = Cons(element, this)

  override def printingElement: String =
    if t.isEmpty then "" + h
    else s"$h" + " " + s"${t.printingElement}"

  override def filter(predicate: A => Boolean): CustomList[A] =
    if predicate(h) then Cons(h, t.filter(predicate))
    else t.filter(predicate)

  override def map[B](transformer: A => B): CustomList[B] =
    Cons[B](transformer(h), t.map(transformer))

  @targetName("stringConcat")
  override def ++[B >: A](list: CustomList[B]): CustomList[B] = new Cons[B](h, t ++ list)

  override def flatMap[B](transformer: A => CustomList[B]): CustomList[B] =
    transformer(h) ++ t.flatMap(transformer)

  override def forEach(iterator: A => Unit): Unit =
    iterator(h)
    t.forEach(iterator)

  override def sorting(compare: (A, A) => Int): CustomList[A] =
    def insert(value: A, sortedList: CustomList[A]): CustomList[A] =
      if sortedList.isEmpty then Cons(value, Empty)
      else if compare(value, sortedList.head) <= 0 then Cons(value, sortedList)
      else Cons(sortedList.head, insert(value, sortedList.tail))

    insert(h, t.sorting(compare))

  override def zipWith[B, C](list: CustomList[B], zip: (A, B) => C): CustomList[C] =
    if list.isEmpty then throw RuntimeException("Length Don't Match")
    else Cons(zip(h, list.head), t.zipWith(list.tail, zip))

  override def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)


}

/*class EvenPredicate extends MYPredicate[Int]:
  override def test(n:Int): Int =>Boolean = _ % 2 == 0
class StringToIntTransformer extends MyTransformer[String,Int]:
  override def transform(x:String): String =>Int = _.toInt*/

object ListTest extends App {
  val listOfInt: CustomList[Int] = new Cons[Int](1, new Cons[Int](2, Empty))
  val anotherListOfInt: CustomList[Int] = new Cons[Int](5, new Cons[Int](6, Empty))
  val listOfStrings: CustomList[String] = new Cons[String]("Hello", new Cons[String]("Scala", Empty))
  println(listOfInt.toString)
  println(listOfInt.map(_ * 2).toString)
  println(listOfInt.filter(_ % 2 == 0))
  println(listOfInt.flatMap(x => Cons(x, Cons(x + 1, Empty))).toString)
  println((listOfInt ++ anotherListOfInt).toString)
  println("-----------------------")
  listOfInt.forEach(println)
  println("-------------------")
  println(listOfInt.sorting((x, y) => y - x))
  println(anotherListOfInt.zipWith[String, String](listOfStrings, _ + "-" + _))
  println(anotherListOfInt.fold(0)(_ + _))
  println(listOfInt.filter(_%2==0).flatMap(x=>anotherListOfInt.map(y=>x+y)))
  val comp = for(a<-anotherListOfInt ;b<-listOfInt ;c<-listOfStrings)yield a+b+c
  println(comp)
}
