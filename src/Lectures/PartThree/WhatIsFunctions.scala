package Lectures.PartThree

object WhatIsFunctions {
  /*
  * Functions in Functional Programming Paradigm as first class members not as instance of the class
  * in-contrary to OOP */

  //this is oop declare a class with method inside then instantiate the method from the instance of the class
  trait Action[A,B]:
    def apply(element:A):B
  val stringToInt: String => Int = (str: String) => str.toInt
  //Function types Function2[A,B,R]=>R
  //All Scala Functions Are Objects

  val stringConcat:(String,String)=>String= (str1:String,str2:String) =>str1 + str2


  def main (args:Array[String]):Unit=
    val doubler = new Action[Int,Int]:
      override def apply(element: Int): Int = element +2
    println(doubler(2))
    println(stringToInt("3"+4))
    println(stringConcat("Ahmed","Ali"))


}
