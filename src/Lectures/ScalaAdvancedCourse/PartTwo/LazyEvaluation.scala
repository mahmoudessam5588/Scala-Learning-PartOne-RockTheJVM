package Lectures.ScalaAdvancedCourse.PartTwo

import scala.annotation.tailrec
import scala.collection.WithFilter

object LazyEvaluation {
  //lazy delay the evaluation of the values
  lazy val x: Int = throw new RuntimeException
  //evaluate other value once and print return value each time
  lazy val yes: Int =
    println("hello")
    56
  def valMethod(n: => Int):Int=
    lazy val t = n
    t+t+t+1
  def retrieveMethod:Int =
    println("Computing")
    Thread.sleep(2000)
    42
  def lessThanThirty(i:Int):Boolean = {
    print(s"\n $i less than 30")
    i < 30
  }
  def greaterThanTwenty(i:Int):Boolean= {
    print(s"\n $i more than 20")
    i > 20
  }
  val number: LazyList[Int] = LazyList(1,25,40,5,23)
  val lt30: WithFilter[Int, LazyList] = number.withFilter(lessThanThirty)
  val mt20: WithFilter[Int, LazyList] = lt30.withFilter (greaterThanTwenty)

  val res: LazyList[Int] = for
    a <- LazyList(1,45,67,78,98,34) if a % 2 == 0
  yield a+1
  val isEven: Int => Boolean = (x:Int) => x % 2 == 0
  ((1000 to 10000) filter isEven)(1)
  @tailrec
  def findEven(from:Int, to:Int, n:Int): Int =
    if from >= to then throw new RuntimeException("Not Found")
    else if isEven(from) then
      if n == 0 then from else findEven(from + 1,to,n-1)
    else findEven(from + 1,to,n)

  val xs: LazyList[Int] = LazyList.cons(1,LazyList.cons(2,LazyList.cons(3,LazyList.empty)))
  def llRange(lo:Int,hi:Int):LazyList[Int]=
    if lo >= hi then LazyList.empty
    else LazyList.cons(lo,llRange(lo+1,hi))

  def lRange(lo:Int,hi:Int):List[Int]=
    if lo >= hi then Nil
    else lo::lRange(lo+1,hi)

  val result: Int = (llRange(100,200) filter isEven)(1)


  def main(args: Array[String]): Unit = {
    //println(x=34)
    println(yes)
    println(yes)
    println(valMethod(retrieveMethod))//call by need
    mt20.foreach(println)
    res.foreach(println)
    println("------------")
    println(findEven(100,1000,1))
    println("--------------------")
    llRange(10,50).foreach(println)
    println("---------------------")
    println(lRange(10,50))
    println("--------------------")
    println(result)


  }
}
