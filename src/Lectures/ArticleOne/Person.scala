package Lectures.ArticleOne

class Person (val name:String,val age:Int):
  override def toString: String = s"$name , $age"

  val printInfo:String= s"name: $name  , age: $age"

  def this(name:String,age:Int,id:Int) = this(name,age)


object Test:
  def main(args:Array[String]):Unit=
    val person: Person = new Person("ahmed", 34)
    println(person)
    println(person.name)
    println(person.printInfo)

    val axillary:Person = new Person("Omar",5,123)



