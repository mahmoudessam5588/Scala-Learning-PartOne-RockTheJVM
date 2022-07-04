package Lectures.ScalaAdvancedCourse.PartTwo

object PartialFunctions  {

  val aTotalFunction:Int=>Int = _ match
    case 1=> 42
    case 2 => 56
    case 3 => 999
  val aPartialFunctionImplementation: PartialFunction[Int,Int] =
    case 1=> 42
    case 2 => 56
    case 3 => 999
  //partial functions utilities




  def main (args: Array[String]):Unit={
    println(aPartialFunctionImplementation(2))
    println(aTotalFunction(2))
    //partial functions utilities
    //1.isDefinedAt
    println(aPartialFunctionImplementation.isDefinedAt(2))
    println(aPartialFunctionImplementation.isDefinedAt(4))
    //2.lift
    val lifted = aPartialFunctionImplementation.lift //Int=>Option(Int)
    println(lifted(2))
    println(lifted(6))
    val pfChain = aPartialFunctionImplementation.orElse[Int,Int]{
      case 45 => 1000
    }
    //println(aPartialFunctionImplementation(45))
    println(pfChain(45))
    val mappedList =List(1,2,3).map{
      case 1 => 42
      case 2 =>78
      case 3 => 100
    }
  println(mappedList)
    /*
    * Partial Functions Can Only Have One Parameter Type
    * */

  }
}
