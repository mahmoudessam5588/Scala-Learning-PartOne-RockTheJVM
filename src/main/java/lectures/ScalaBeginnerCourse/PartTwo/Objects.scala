package lectures.ScalaBeginnerCourse.PartTwo

object Objects {
  def main(args: Array[String]): Unit =
    val ahmed = new Person("Ahmed")
    val mohammed = new Person("Mohamed")
    val mary = Person("Mary")
    val john = Person("John")
    //val bobbie = Person("Yussuf","Joseph")




    println(ahmed == mohammed)
    println(mary == john)



  /*
  * members of objects independent of instance of the object like static access modifier in java
  * can declared by var val def keyword
  * differences:
      they are singleton instance = own type + only instance
      * we declare class with same name of object and same level for instance-level functionality called companions **/
  class Person(val name:String)

  object Person
  def apply(father:Person,mother:Person):Person = new Person("Bobbie")

}
