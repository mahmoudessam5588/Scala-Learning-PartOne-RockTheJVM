package lectures.ScalaBeginnerCourse.PartOne

import scala.annotation.tailrec

object RecursionExample {
  def factorialInt(x: Int): Int =
    if x <= 1 then 1
    else
      println(s"Computing Factorial Of $x - I Need Factorial of ${x - 1}")
      val result = x * factorialInt(x - 1)
      println(s"Computed Factorial of $x")
      result

  def anotherFactorial(n: Int): BigInt =
    @tailrec
    def factorialHelper(x: Int, accumulator: Int): Int =
      if x <= 1 then accumulator
      else
        factorialHelper(x - 1, x * accumulator)

    factorialHelper(n, 1)

  @tailrec
  def concatenatedString(aString: String, accumulator: String, n: Int): String =
    if n <= 0 then accumulator else concatenatedString(aString, aString + accumulator, n - 1)

  def isPrime(n: Int): Boolean =
    @tailrec
    def isPrimeUntil(t: Int, isPrime: Boolean): Boolean =
      if !isPrime then false
      else if t <= 1 then true
      else isPrimeUntil(t - 1, n % t != 0 && isPrime)

    isPrimeUntil(n / 2, true)

  /*def fibonacci(n: Int): Int =
      @tailrec
      def fiboTailrec(i: Int, last: Int, nextToLast: Int): Int =
        if i >= n then last
        else fiboTailrec(i + 1, last + nextToLast, last)

      if n <= 2 then 1
      else fiboTailrec(2, 1, 1)
    println(fibonacci(230))*/


  def main(args: Array[String]): Unit = {
    println(factorialInt(10))
    println(anotherFactorial(30))
    println(concatenatedString("Hello ", " ", 3))
    println(isPrime(4))
    println(isPrime(7))
  }

}
