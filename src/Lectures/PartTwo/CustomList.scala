package Lectures.PartTwo
import Lectures.PartTwo.Cons

import scala.annotation.targetName



trait MYPredicate[-T]:
  def test(n:T):Boolean

trait MyTransformer[-A,B]:
  def transform(x:A):B

abstract  class CustomList[+A] {
  def head: A

  def tail: CustomList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): CustomList[B]
  def printingElement:String
  def map[B](transformer:MyTransformer[A,B]):CustomList[B]
  def flatMap[B](transformer: MyTransformer[A,CustomList[B]]):CustomList[B]
  def filter(predicate:MYPredicate[A]):CustomList[A]
  @targetName("stringConcat")
  def ++ [B>:A](list:CustomList[B]):CustomList[B]

  //polymorphic call
  override def toString: String = "[" + printingElement + "]"

}

case object Empty extends CustomList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: CustomList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): CustomList[B] = Cons(element, Empty)

  override def printingElement: String = ""
  override def map[B](transformer:MyTransformer[Nothing,B]):CustomList[B] = Empty
  override def flatMap[B](transformer: MyTransformer[Nothing,CustomList[B]]):CustomList[B] = Empty
  override def filter(predicate:MYPredicate[Nothing]):CustomList[Nothing] = Empty

  @targetName("stringConcat")
  override def ++[B >: Nothing](list: CustomList[B]): CustomList[B] = list

}

case class Cons[+A](h:A, t: CustomList[A]) extends CustomList[A] {
  override def head: A = h

  override def tail: CustomList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): CustomList[B] =  Cons(element, this)

  override  def printingElement: String =
    if t.isEmpty then "" + h
    else s"$h" + " " + s"${t.printingElement}"
  override def filter(predicate:MYPredicate[A]):CustomList[A] =
    if predicate.test(h) then  Cons(h,t.filter(predicate))
    else t.filter(predicate)
  override def map[B](transformer:MyTransformer[A,B]):CustomList[B] =
    Cons[B](transformer.transform(h),t.map(transformer))

  @targetName("stringConcat")
  override def ++[B >: A](list: CustomList[B]): CustomList[B] = new Cons[B](h, t ++ list)

  override def flatMap[B](transformer: MyTransformer[A, CustomList[B]]): CustomList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)





}

/*class EvenPredicate extends MYPredicate[Int]:
  override def test(n:Int): Int =>Boolean = _ % 2 == 0
class StringToIntTransformer extends MyTransformer[String,Int]:
  override def transform(x:String): String =>Int = _.toInt*/

object ListTest extends App{
  val listOfInt :CustomList[Int] = new Cons[Int](1,new Cons[Int](2,new Cons[Int](3,Empty)))
  val anotherListOfInt :CustomList[Int] = new Cons[Int](5,new Cons[Int](6,new Cons[Int](7,Empty)))
  println(listOfInt.toString)
  println(listOfInt.map((x: Int) => x * 2).toString)
  println(listOfInt.filter((y:Int)=>y%2==0))
  println((listOfInt ++ anotherListOfInt).toString)

}
