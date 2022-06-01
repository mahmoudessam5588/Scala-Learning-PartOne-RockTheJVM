package ExercisesAndProblemSolving.AdvancedCourseExercises.PartTwo

object PartialFunctionsExercises {
  /*
  * Construct PF Instance YourSelf (anonymous class)
  * dumb ChatBot as PF*/
  val aPartial: PartialFunction[Int, Int] = new PartialFunction[Int,Int]{
    override def apply(v1: Int): Int = v1 match
      case 1 => 42
      case 5 => 99
      case 55 =>88

    override def isDefinedAt(x: Int): Boolean =
      x == 1 || x == 5 || x == 55
  }

  val lines :PartialFunction[String,String]=
    case "Hello" => "Hi There"
    case "Hi There" => "Hello"
    case _ => "Else"




  def main(args:Array[String]):Unit={
    println("Type Hi There Or Hello")
    //scala.io.Source.stdin.getLines().foreach(x=>println(lines(x)))
    scala.io.Source.stdin.getLines().map(lines).foreach(println)


  }

}
