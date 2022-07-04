package lectures.ScalaBeginnerCourse.PartThree

import scala.compiletime.ops.string.+
import scala.math.Fractional.Implicits.infixFractionalOps
import scala.math.Integral.Implicits.infixIntegralOps
import scala.math.Numeric.Implicits.infixNumericOps

object MapFlatmapFilterFor {

  def main(arg: Array[String]): Unit = {
    val list = List(8, 6, 5)
    println(list.map(_ * 2))
    println(list.filter(_ % 2 == 0))
    println(list.flatMap(_.+("3")))
    println(list.zip(List(3, 4, 6)))
    println(list.flatMap((x: Int) => List(x, x + 1)))
    println("----------")
    val sort = list.sorted
    println(sort)
    println(sort.reverse)
    val numbers: List[String] = List("1", "2", "3")
    val chars:List[String] = List("a", "b", "c")
    val colors:List[String] = List("white","red","black")
    val res = numbers.flatMap(x=>chars.flatMap(y=>colors.map(z=>s"$y"+s"$x"+s"$z")))
    println(res)
    val forComp = for n <-numbers  ; c<- chars  ; color <- colors yield  c+n+color
    println(forComp)

  }
}
