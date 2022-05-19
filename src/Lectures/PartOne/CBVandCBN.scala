package Lectures.PartOne

object CBVandCBN {
  def callByValue(x: Long): Unit =
    println("By Value " + x)
    println("By Value " + x)

  def callByName(c: => Long): Unit = //lazy evaluation invoke each times it runs
    println("By Name " + c)
    println("By Name " + c)

  def infinite(): Int = 1 + infinite()

  def passTheInfinite(x: Int, y: => Int): Unit = println(x)

  def main(args: Array[String]): Unit =
    callByValue(System.nanoTime())
    callByName(System.nanoTime())
    passTheInfinite(32, infinite())


}
