package Lectures.ArticleOne

object SingletonObject:
  private val name :String = "Mahmoud"

class SingletonObject:
  def printName():Unit= println(SingletonObject.name)


object TestInfo:
  def main (args:Array[String]):Unit={
    val singleton:SingletonObject = SingletonObject()
    singleton.printName()
    val singletonObject = SingletonObject
  }