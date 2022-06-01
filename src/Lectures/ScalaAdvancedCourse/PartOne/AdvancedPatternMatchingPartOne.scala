package Lectures.ScalaAdvancedCourse.PartOne

import scala.runtime.Nothing$

object AdvancedPatternMatchingPartOne extends App {
  /*class Person(val name: String, val age: Int)

  object Person:
    val bob: Person = new Person("bob", 20)
    val greeting: String = bob match
      case Person(n, a) => s"hi name is $n and Age is $a"

    def unapply(person: Person): Option[(String, Int)] =
      if person.age <= 20 then None
      else Some((person.name, person.age))

    println(greeting)*/

    //infix patterns
    case class or[A,B](a:A,b:B)
    val either: or[Int, String] = or(2,"Two")
    val humanDescription: String = either match
        case number or string => s"$number as $string"

    println(humanDescription)

    abstract class MyList[+A]:
        def head : A = ???
        def tail : MyList[A] = ???
    case object Empty extends MyList[Nothing]
    case class Cons[+A](override val head: A ,override val tail: MyList[A]) extends MyList[A]
    object MyList {
        def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
            if list == Empty then Some(Seq.empty)
            else unapplySeq(list.tail).map(list.head +: _)
        }
    val myList :MyList[Int] = Cons(1,Cons(2,Cons(3,Empty)))
    val decompose = myList match
        case MyList(1,2,_*) => "Starting from 1,2"
        case _ => "Else"
    println(decompose)

}
