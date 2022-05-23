package Lectures.PartTwo

import scala.annotation.targetName
import scala.language.postfixOps

object MethodNotations extends App {
  val mahmoud = new Movies("Mahmoud", "Lord Of The Rings")
  val john = new Movies("John", "Inception")
  //prefix operators all about urinary operator but limited to certain operators + - ~ !
  val number = -1 // == .unary_-
  //infix notation
  println(mahmoud likes "Lord Of The Rings")
  println(mahmoud + john)

  class Movies(val name: String, val favouriteMovies: String, val age: Int = 0):
    def likes(movie: String): Boolean = movie == this.favouriteMovies

    @targetName("HangOutWith")
    def +(friend: Movies): String = s"${this.name} is hanging out with ${friend.name}"

    @targetName("NickName")
    def +(nickName: String): Movies = new Movies(s"${this.name}($nickName)", s"$favouriteMovies")

    @targetName("The Heck")
    def unary_! : String = s"$name , What The Heck??!!"

    @targetName("Test")
    def unary_+ : Movies = new Movies(this.name, this.favouriteMovies, age + 1)

    def isAlive: Boolean = true

    def apply(): String = s"my name is $name and my favourite movie is $favouriteMovies"
    def apply(n:Int):String = s"$name watched the $favouriteMovies $n number of times"

    def learnsScala: String = this learns "Scala"

    def learns(thing: String): String = s"$name is learning $thing"
  println(!mahmoud)
  //postfix operators methods with no parameters
  println(mahmoud isAlive)
  //apply with parentheses
  println(mahmoud()) //called as if it's a method
  println((mahmoud + "The Preacher") ())
  println((+mahmoud).age)
  println(mahmoud learnsScala)
  println(mahmoud(10))


}

