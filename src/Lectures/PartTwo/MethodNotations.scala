package Lectures.PartTwo

import scala.annotation.targetName

object MethodNotations extends App {
  class Movies(val name:String,val favouriteMovies:String):
    def likes(movie:String):Boolean = movie == this.favouriteMovies
    @targetName("HangOutWith")
    def +(friend:Movies):String = s"${this.name} is hanging out with ${friend.name}"

  val mahmoud = new Movies("Mahmoud","Lord Of The Rings")
  val john = new Movies("John","Inception")
  //infix notation
  println(mahmoud likes "Lord Of The Rings")
  println(mahmoud + john)

}
