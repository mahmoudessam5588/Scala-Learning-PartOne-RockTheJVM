package ExercisesAndProblemSolving.LeetCodeExercises
import math.Fractional.Implicits.infixFractionalOps
import math.Integral.Implicits.infixIntegralOps
import math.Numeric.Implicits.infixNumericOps
object PlusOne extends App {
  def plusOne(digits: Array[Int]): Array[Int] = {
      val z: Int = digits.last.+(1)
      if z >= 9 then (digits :+ z).mkString("Array(", ", ", ")").replaceAll("\\D+","").map(_.asDigit).toArray
      else  digits.dropRight(1):+z
  }
  println(plusOne(digits = Array(1, 2,400)).mkString("Array(", ", ", ")"))



}
