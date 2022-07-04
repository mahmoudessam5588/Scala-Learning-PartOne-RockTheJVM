package lectures.ScalaBeginnerCourse.PartTwo

object Generics {
  class Animal
  class Cats extends Animal
  class Dogs extends Animal
  //if  List[cats] extends List[Animal] => COVARIANCE represented by [+A]
  //if Animal and cats don't extends each other i.e 2 separate entities => INVARIANT represented by [A]
  //widening the Type removing the Limiters => Contravariance represented By [-A]
  class CovarianceList[+A]
  class InvariantList[A]
  class Contravariance[-A]
  //bound Types
  //allow to use your generic classes only for certain type only subclass of different type or super class of different type
  class Cage[A <: Animal](animal:A)
  class Car


  def main(args:Array[String]):Unit={
    //variance problem
    val covarianceList:CovarianceList[Animal]= new CovarianceList[Cats]
    val invariantList:InvariantList[Animal] = new InvariantList[Animal]
    val contravariance:Contravariance[Cats] = new Contravariance[Animal]
    //bound types i.e Upper Bound
    val cage: Cage[Animal] = new Cage(new Dogs)





  }

}
