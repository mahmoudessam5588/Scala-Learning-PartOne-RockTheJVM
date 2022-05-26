package Lectures.PartThree

import scala.annotation.tailrec

object HigherOrderFunctionCurries {
  val plusOne: Int => Int = (x: Int) => x + 1
  val plusTen: Int => Int = nTimeBetter(plusOne, 10)
  val superAdder: Int => Int => Int = x => y => x + y
  val add3: Int => Int = superAdder(3)
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")

  //def superpower:(Int,(String,(Int=>Boolean))=>Int)=>(Int=>Int) = ???
  //nTimes(f,n,x)
  //nTimes(f,3 ,x) = f(f(f(x))
  //nTimes(f,n,x) = (f,n-1,f(x))
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if n <= 0 then x else nTimes(f, n - 1, f(x))

  def nTimeBetter(f: Int => Int, n: Int): Int => Int =
    if n <= 0 then (x: Int) => x
    else (x: Int) => nTimeBetter(f, n - 1)(f(x))

  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) = x => y => f(x, y)

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int = (x, y) => f(x)(y)

  def compose[A, B, T](f: A => B, g: T => A): T => B = x => f(g(x))

  def andThen[A, B, C](f: A => B, g: B => C): A => C = x => g(f(x))


  def main(args: Array[String]): Unit = {
    println(nTimes(plusOne, 10, 1))
    println(plusTen(1))
    println(superAdder(3)(10))
    println(add3(10))
    println(standardFormat(Math.PI))
    val adder = toCurry(_ + _)
    val adderByThree = (x: Int) => x * 3
    val addByTwo = (x: Int) => x + 2
    val addBy = adder(17)(18)
    println(addBy)
    val multiplier = fromCurry(x => y => x * y)
    println(multiplier(2, 5))
    val composed = compose(addByTwo, adderByThree)
    val composedThen = andThen(addByTwo, adderByThree)
    println(composed(4))
    println(composedThen(4))

  }

}
