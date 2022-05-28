package lectures.ScalaBeginnerCourse.PartThree

import scala.math.Fractional.Implicits.infixFractionalOps
import scala.math.Integral.Implicits.infixIntegralOps
import scala.math.Numeric.Implicits.infixNumericOps


object WhatIsFunctions {
  /*
  * Functions in Functional Programming Paradigm as first class members not as instance of the class
  * in-contrary to OOP */

  val stringToInt: String => Int = (str: String) => str.toInt
  val stringConcat: (String, String) => String = (str1: String, str2: String) => str1 + str2
  //Function types Function2[A,B,R]=>R
  //All Scala Functions Are Objects
  val triple: (Int, Int, Int) => Unit = (x: Int, y: Int, z: Int) => println(x + y + z)
  val flat: (List[Double], List[Double]) => Unit = (x: List[Double], y: List[Double]) => println(x.zip(y))
  val firstList: Seq[Int] => List[Int] = _.toList.sorted
  val specialFunction0:Function[Int,Function[Int,Int]]  = new Function[Int,Function[Int,Int]]{
    override def apply(x: Int): Function[Int, Int] = new Function[Int,Int]{
      override def apply(y: Int): Int = x+y
    }
  }
  val specialFunction:Function[Int,Function[Int,Int]] = (x: Int) => (y: Int) => x + y
  val specialFunction2: Int => Int =>Int = X => y => X + y
  def curried(x:Int)(y:Int): Int = x+y


  def main(args: Array[String]): Unit = {
    val doubler = new Action[Int, Int] :
      override def apply(element: Int): Int = element + 2
    println(doubler(2))
    println(stringToInt("3" + 4))
    println(stringConcat("Ahmed", "Ali"))
    triple(3, 5, 6)
    flat(List(2, 3, 4), List(5, 6, 7))
    val res = firstList(Seq(2, 5, 6))
    val total = res :: List(2, 4, 6).zip(res)
    println(total)
    val adder0 = specialFunction0(3)
    println(adder0(4))
    val adder = specialFunction(3)
    println(adder(4))
    val adder2 = specialFunction2(3)
    println(adder2(4))
    println(curried(3)(4))
  }

  //this is oop declare a class with method inside then instantiate the method from the instance of the class
  trait Action[A, B]:
    def apply(element: A): B



}
