package ExercisesAndProblemSolving.AdvancedCourseExercises.PartOne

object PatternMatchingPartOneExercises extends App{
  //example 1need pattern matching computation against n:Int in elegant way instead of if or nested if inside
  object evenNumbers:
    def unapply(x:Int) :Option[Boolean]=
      if x % 2 == 0 then Some(true) else None

  object singleDigits:
    def unapply(y:Int) : Option[Boolean]=
      if y > -10 && y < 10 then Some(true) else None

  val n : Int = 42
  val matching =  n match
    case evenNumbers(_) => "Even"
    case  singleDigits(_) => "Digits"
    case _ => "No Value"


  println(matching)
}

