package ExercisesAndProblemSolving.LeetCodeExercises


object Problem2 extends App {
  def compareVersion(version1: String, version2: String): Unit =
  //throw error in leetcode compiler because of empty string "" not here all work fine
    val a = version1.toList.filter(n => n.equals('1'))
      .map(_.getNumericValue).toString.replaceAll("\\D+","").toInt
    val b = version2.toList.filter(n => n.equals('1'))
      .map(_.getNumericValue).toString.replaceAll("\\D+","").toInt
    if a < b then println(-1)
    else if a > b then println(1)
    else println(0)


  compareVersion("0.1","1.1")
  compareVersion("1.01","1.001")
  compareVersion("1.0","1.0.0")


}





