package Lectures.PartTwo

object CaseClasses {
  case class  Person(name:String,age:Int)
  //equals + hashcode , toString
  def main(args:Array[String]):Unit={
    //1-class parameters are fields
    val person = Person("John",36)
    val person2 = Person("John",36)
    println(person.age)
    //2-to string
    println(person)
    //3-equals and hashcode implemented out of the box
    println(person==person2)
    //4-handy copy methods
    val person3 = person.copy()
    println(person3)
    val person4 = person.copy(age=50)
    println(person4)
    //5-case classes have companion object
    val person5 = Person
    println(person5.getClass)
    //6-constructor factory method
    val person6 = Person("Mary",44)
    val person7 = Person("Mary",44)
    println(person6==person7)
    //7-case classes are serializable effective when working with distributed systems
    //8-case classes have extractor patterns i.e can be used in pattern matching
    // there is also case objects that work the same as case classes






  }

}
