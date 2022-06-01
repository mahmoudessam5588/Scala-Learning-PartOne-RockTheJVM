package Lectures.ScalaAdvancedCourse.PartOne

object DarkSugar extends App {
  //1.Method with Single Param
  def singleMethod(x:Int):String = s"$x"
  val description = singleMethod{
    42 //complex coding logic here
  }
  List(1,2,3).map{x=> x+1}.foreach(println)
  //2.single abstract method
  trait Action{
    def act(x:Int):Int
  }
  val actionTaken:Action  = (x:Int)  => x+1
  //3. Runnable
  val InsteadOfThis = new Thread(new Runnable{
    override def run(): Unit = println("Java Syntax")})

  val doThisInstead = new Thread(()=>println("Scala Syntax"))

  abstract class AnAbstractType:
    def implemented :Int =23
    def f(a:Int):Unit
  val anAbstractInstance:AnAbstractType = (a:Int)=>println("Sweet")

  //4. :: & #:: method
  println(1::2::3::4::List(5,6).::(7).::(8))
  //last character defines the associativity of method
  //5. multi-word method naming
  class TeenGirl(name:String):
    def `and then said` (gossip:String):Unit=
      println(s"$name and $gossip")

  val lilly = new TeenGirl("Lilly")
  val e: Unit =lilly `and then said` "Scala is Awesome"
  println(e)
  //6-infix types
  //class Composite[A,B]
  //val composite: Int Composite String = ???
  //7-update() for mutable collection
  /*def anArray = Array(1,2,4)
  anArray(2)=7
  anArray.foreach(println)*/
  class Mutable:
    def internalMember:Int = 0
    def member:Int = internalMember
    def member_=(value:Int):Unit= {
      value
    }
  val mutableMembers = new Mutable
  mutableMembers.member = 42




}
