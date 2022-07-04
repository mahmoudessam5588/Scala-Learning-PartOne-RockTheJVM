package ExercisesAndProblemSolving.AdvancedCourseExercises.PartTwo

object CurriedPartialAppliedFunctionExercises {
  val simpleAddFunction: (Int, Int) => Int = (x: Int, y: Int) => x + y
  val add7: Int => Int = (x: Int) => simpleAddFunction(7, x)
  val add: (Int, Int) => Int = (x: Int, y: Int) => simpleAddMethod(x, y)
  val curried: (Int, Int) => Int = (x: Int, y: Int) => curriedAddFunction(x)(y)
  val add7_1: Int => Int = simpleAddFunction.curried(7)
  val add7_2: Int => Int = curriedAddFunction(7)(_: Int)
  val add7_3: Int => Int = simpleAddFunction(7, _: Int)
  val add7_4: Int => Int = simpleAddMethod(7, _: Int)
  val concat: (String, String) => String = concatenate("I'm", _, _)

  def simpleAddMethod(x: Int, y: Int): Int = x + y

  def curriedAddFunction(x: Int)(y: Int): Int = x + y

  def concatenate(a: String, b: String, c: String): String = a + b + c
  /*
  * process List Of Number And Return Their Representation In Different Format
  * %4.2f, %8.6f, %14.12f with curried formatter*/
  def formatting(x:String)(y:List[String]): List[String] = x.format(Math.PI) :: y.map(_.format(Math.PI).trim)
  val curriedFormat: (String, List[String]) => List[String] = (x:String, y:List[String])=>formatting(x)(y)
  "%4.2f".format(Math.PI)
  def formattedNumber(s:String)(n:Double):String= s.format(n)
  def mathConstants: List[Double] = List(Math.PI,Math.E,1,9.8,1.3e-12)
  /*
  * difference between functions and methods
  * functions vs methods
  * parameters: by-name , 0-lambda
  * calling byName and byFunction
  * int
  * method
  * parenMethod
  * lambda
  * PAF*/
  def byName(n: => Int): Int = n+1
  def byFunction(f: () => Int):Int =f() +1
  def method:Int =42
  def parenMethod():Int =42



  def main(args: Array[String]): Unit = {
    println(add7(1))
    println(add(7, 1))
    println(curried(7, 1))
    println(concat("Mahmoud ", " I'm 20 YEARS OlD"))
    println(curriedFormat("%4.2f",List("%8.9f","%14.5f","%45.3f")))
    val firstValue=formattedNumber("%4.2f")_
    val secondValue = formattedNumber("%8.6f")_
    println(mathConstants.map(firstValue))
    println(mathConstants.map(secondValue))

    byName(42)
    byName(method)
    byName(parenMethod())
    byName((()=>42)()) //ok
    //byName(()=>42) //not ok
    //byName(parenMethod()_) //not ok
    //byFunction(45) //not ok
    //byFunction(method) //not ok
    byFunction(parenMethod) //not ok
    byFunction(()=>45) //ok
  }

}
