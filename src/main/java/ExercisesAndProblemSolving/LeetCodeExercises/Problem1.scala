package ExercisesAndProblemSolving.LeetCodeExercises

import javax.xml.crypto.dsig.Transform

object Problem1 extends App{
    // you need treat n as an unsigned value
    //solution one
    def hammingWeight(n: Int): Unit =
      val convert = n.toBinaryString
      val transform = convert.filter(_.equals('1')).toList
      val listOfInt:List[Int] = for (c <-transform) yield c.getNumericValue
      println(listOfInt.sum)
      //shorter and fasrer solution
      println(n.toBinaryString.filter(_.equals('1')).toList.map(_.getNumericValue).sum)
    hammingWeight(0000111)






}
