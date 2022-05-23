package Lectures.PartTwo

import scala.annotation.tailrec
import scala.runtime.Nothing$

object LazyListWithUglyNumberExample {
  def main(args: Array[String]): Unit = {
    @tailrec
    def uglyNumber(n: Int): Boolean =
      n match
        case x if x == 1 => true
        case x if x % 5 == 0 => uglyNumber(x / 5)
        case x if x % 3 == 0 => uglyNumber(x / 3)
        case x if x % 2 == 0 => uglyNumber(x / 2)
        case _ => false


    def uglyNumbers: LazyList[Int] =


      def go(x: Int): LazyList[Int] =
        if uglyNumber(x) then x #:: go(x + 1)
        else go(x + 1)

      go(1)


    println(uglyNumbers.take(100).toList.sorted)

  }

}
