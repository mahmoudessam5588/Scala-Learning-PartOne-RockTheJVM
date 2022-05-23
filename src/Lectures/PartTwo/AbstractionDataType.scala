package Lectures.PartTwo

object AbstractionDataType {
  abstract class Animal( val creatureType:String,val eat:String)

  class Dog extends Animal("Canine","Bone")

  trait Carnivore:
    def eat(animal:Animal):Unit

  class Crocodile extends Animal("Reptile","Meat") with Carnivore:
    override def eat(animal: Animal): Unit = println(s"I'm Croc and I'm Eating a ${animal.creatureType}")

/*Traits vs Abstract Classes
*both abstract and Traits classes can have both abstract and non abstract methods
* traits don't have Constructor parameters
* multiple traits may be inherited by same class
* multiple inheritance not supported in abstract classes
* traits = behaviour , abstract class = "thing"  */



  def main (args:Array[String]):Unit={
    val animal:Animal = new Dog
    val crocodile:Carnivore = new Crocodile
    println(animal.creatureType)
    println(animal.eat)
    crocodile.eat(animal)

  }

}
