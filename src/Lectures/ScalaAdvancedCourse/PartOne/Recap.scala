package Lectures.ScalaAdvancedCourse.PartOne

import scala.annotation.tailrec

object Recap extends App {
  val aBoolean:Boolean =false
  //val aCondition:Int = if condition % 2 == 0 then true else false
  @tailrec
  def factorial(n:Int, accumulator:Int):Int=
    if n <= 0 then accumulator
    else factorial(n-1,n*accumulator)
  println(factorial(4,10))
  

}
