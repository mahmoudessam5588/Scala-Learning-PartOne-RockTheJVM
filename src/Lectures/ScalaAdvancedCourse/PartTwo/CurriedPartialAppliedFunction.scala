package Lectures.ScalaAdvancedCourse.PartTwo

object CurriedPartialAppliedFunction {


def main(args:Array[String]):Unit={
  val superAdder:Int=>Int=>Int = x=>y=>x+y
  val add3 =superAdder(3)(5)
  println(add3)
  def curriedAdder(x:Int)(y:Int):Int = x + y
  val add = curriedAdder(5) _
  println(add(5))
}
}
