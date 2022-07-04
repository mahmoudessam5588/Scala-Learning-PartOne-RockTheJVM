package lectures.ScalaBeginnerCourse.PartTwo

import math.Fractional.Implicits.infixFractionalOps
import math.Integral.Implicits.infixIntegralOps
import math.Numeric.Implicits.infixNumericOps

object Inheritance {
  //single class inheritance super class Animal and sub class
  /*
  * preventing overrides by final keyword on class members and seal keyword prevent extensions to other files the */
  class Animal:
    protected val eat:String = "Yummy"
  class Cats extends Animal:
    val crunch :String = eat

  sealed class Person(val name:String, val age:String, val id:Int ):

    def fullClass:String=s"$name , $age , $id"
  sealed class Adult(override val name:String, override val age: String, override val id: Int)extends Person(name,age, id):
    override val fullClass:String =s"$name , $age , $id"
    val superCredentials: String = super.fullClass


  def main(args:Array[String]):Unit={
  val animalType = new Cats
  println(animalType.crunch)
  val person:Person = new Person("Ali","25",123)
  println(person.fullClass)
  val adult:Person = new Adult("Osama","33",234)
  println(adult.fullClass)
  val adults:Adult =new Adult("Osman","55",6787)
  println(adults.superCredentials)
  }

}
