package Lectures.PartTwo

object Exceptions {
  def main(args: Array[String]): Unit = {
    /*
    * Throwable classes extend the Throwable class
    * Exceptions and Errors are the major Throwable Subtype
    * how to catch exceptions */
    try getInt(false): Int{
    } catch {
      case e: Exception => println(s"caught an exception ,${e.printStackTrace()}")
    }


    def getInt(withExceptions: Boolean): Int = if withExceptions then throw new RuntimeException else 42
    //val array =Array.ofDim(Int.MaxValue) cause crash
    class OverFlowException extends RuntimeException
    class UnderFlowException extends RuntimeException
    case class ObjectCalculator(x:Int,y:Int):
      val add: Int =
        val result = x + y
        if x > 0 && y > 0 && result < 0 then throw new OverFlowException
        else if x < 0 && y < 0 && result > 0 then throw new UnderFlowException
        else result






  }
}
