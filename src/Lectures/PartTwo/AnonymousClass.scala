package Lectures.PartTwo

object AnonymousClass {
  abstract class Animal:
    val eat:Unit
  class Person(name:String):
    val sayHi:Unit = println(s"My Name is $name How Can I Help You")

  def main (args:Array[String]):Unit={
    val eating:Animal = new Animal:
      override val eat: Unit = println("Eating Fruits")
    println(eating.getClass)
    val person:Person =new Person("John"):
      override val sayHi: Unit = println("Hi my name is John How can I Help You")
    println(person.getClass)

  }

}
