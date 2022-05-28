package lectures.ScalaBeginnerCourse.PartThree

object AnonymousFunctions {
  val doubler: Int => Int = x => x * 2
  val doSomething: () => Int = ()=>3
  val increment : Int => Int = _+1
  val addition : (Int,Int) => Int = _+_


  def main(args:Array[String]):Unit={
    println(doubler(2))
    println(doSomething())
    println(s"$increment , $addition")
  }

}
