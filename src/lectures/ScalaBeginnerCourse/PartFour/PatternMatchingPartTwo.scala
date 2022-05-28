package lectures.ScalaBeginnerCourse.PartFour

import lectures.ScalaBeginnerCourse.PartTwo.{Cons, CustomList, Empty}

object PatternMatchingPartTwo extends App{
  //All possibilities
  //1-Constants
  val x:Any = "Scala"
  val constant = x match
    case 1 => "A Number"
    case "Scala" => "Scala Language"
    case true => "The Truth"
    case PatternMatchingPartTwo => "Singleton Object"
    //2- match anything & wildcard
    val matchAnything = x match
      case _ => "Any"
    //3-tuples
    val aTuple = (1,2)
    val matchingTuple = aTuple match
      case (1,1) => "1,1"
      case (some,1) => s"this $some "
    val nestedTuple = (1,(2,3))
    val matchedNestedTuple = nestedTuple match
      case (_,(_,v)) => "Another Any"
    //4-case classes called constructors and pattern matches can be nested as well
    val aList:CustomList[Int] = Cons(1,Cons(2,Empty))
    val matchedList = aList match
      case Empty => "empty List"
      case Cons(head,Cons(subHead,subTail)) => "There"
    //5-list Patterns "Extremely Powerful"
    val standardList = List(2,4,78,55)
    val matchedStandardList = standardList match
      case List(1,_,_,_) => "Extractor"
      case List(1,_*) => "List of arbitrary List"
      case 1::List(_) => "Infix Pattern"
      case List(1,2,3) :+ 42 => "another Infix Pattern"

      //6-type specifiers
      val unknown:Any =2
      val unknownMatch = unknown match
        case list:List[Int] => AnyRef
        case _ =>
      //7-nameBinding
      val nameBinding  = aList match
        case nonEmptyList @ Cons(_, _) => "name binding to be used later"
        case Cons(1,rest @ Cons(2, _)) => "name binding inside nested patterns"
      //8- multiPatterns
      val multiPatterns = aList match
        case Empty | Cons(_,_) =>"Compound Pattern You Can Chain As Much As You Can"
      //9- if guards
      val secondElementSpecial = aList match
        case Cons(_,Cons(specialElement,_)) if specialElement % 2 == 0 => "True"




}
