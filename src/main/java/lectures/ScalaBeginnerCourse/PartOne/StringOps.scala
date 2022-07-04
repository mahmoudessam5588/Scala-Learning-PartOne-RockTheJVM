package lectures.ScalaBeginnerCourse.PartOne

object StringOps extends App {
  val str: String = "Hi I'm Learning Scala"
  println(str.codePointCount(2, 3))
  println(str.concat(" And It's Awesome So Far"))
  println(str.substring(3, 7))
  println(str.split(" ").toList)
  println(str.endsWith("Far"))
  println(str.replace("Scala", "Java"))
  println(str.toLowerCase)
  println(str.length)
  //scala specific
  val aNumberString = "46"
  val toNumber = aNumberString.toInt
  println(toNumber)
  println("a" + aNumberString + "z")
  println(str.reverse)
  //s-interpolates
  println(s"$aNumberString && $str")
  //f-interpolates
  val speed = 1.2f
  val name = "Daniel"
  val myth = f"$name can eat $speed%2.2f per minutes"
  println(myth)
  //raw-interpolates
  val escaped: String = "\n Printed  \n in \n each line"
  println(raw"$escaped")
  println(raw"\n Printed \n In \n Each Line")

}
