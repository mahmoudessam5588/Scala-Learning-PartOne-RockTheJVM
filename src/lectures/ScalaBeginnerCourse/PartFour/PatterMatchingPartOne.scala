package lectures.ScalaBeginnerCourse.PartFour

import scala.util.Random
import math.Fractional.Implicits.infixFractionalOps
import math.Integral.Implicits.infixIntegralOps
import math.Numeric.Implicits.infixNumericOps

object PatterMatchingPartOne extends App {
  val random = new Random
  val x =random.nextInt(10)
  val matcher =
    x match
      case  1 => "The One"
      case  2 => "Double Or Nothing"
      case  3 => "Tripled"
      case _ => "Something Else"
  println(x)
  println(matcher)
  //General use
  //1. Decompose value epically with case classes
    case class Person(name:String,age:Int)

  private val bob: Person = Person("Bob",14)
  val greeting =
      bob match
        case Person(a,b) if b<16 => s"Hi Name Is $a My Age is $b I'm Still A Minor"
        case Person(a,b) => s"Hi My Name is $a And My Age is $b"
        case _ => "Who Am I??!!"
    println(greeting )
    /*
    * 1-cases are matched in order
    * 2- Match Error if there is no default or no cases match
    * 3- predefined by compiler for you unified type of all types in the cases*/
  sealed class Animal

  private case class Dog(breed:String) extends Animal

  private case class Parrot(breed:String) extends Animal

  private val dogBreed:Animal = Dog("Husky")
    dogBreed match
      case  Dog(otherBreed) => println(s"Matched Dog Of Breed $otherBreed Breed")
      case _:Animal => println("Other")

  sealed trait Expr
  case class Number(n:Int) extends Expr
  case class Sum(e1:Expr,e2:Expr) extends Expr
  case class Prod(e1:Expr,e2:Expr) extends Expr
  def show(e:Expr):String = e match
    case Number(n) => s"$n"
    case Sum(e1,e2) => show(e1) + " + " +show(e2)
    case Prod(e1,e2) =>
      def maybeParentheses(exp: Expr) = exp match
        case Prod(_,_)=>show(exp)
        case Number(_) => show(exp)
        case _ => "{"+ show(exp) +"}"
      maybeParentheses(e1) + " * " + maybeParentheses(e2)

    println(show(Sum(Number(2),Number(3))))
    println(show(Sum(Sum(Number(2),Number(3)),Number(4))))



}
