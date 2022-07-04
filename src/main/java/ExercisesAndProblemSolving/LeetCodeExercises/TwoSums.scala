package ExercisesAndProblemSolving.LeetCodeExercises

object TwoSums extends App {
  val list = List(1, 2, 3, 4, 5)

  twoSums(Array(4, 5, 10, 5, 78), 9)
  val sum = 9
  val inputMap = list.zipWithIndex.toMap
  val solution: Set[(Int, Int)] = list.zipWithIndex.flatMap { case (item, itemIndex) =>
    inputMap.get(sum - item).map { restIndex =>
      val minIndex = Math.min(itemIndex, restIndex)
      val maxIndex = Math.max(itemIndex, restIndex)
      minIndex -> maxIndex
    }
  }.toSet

  /*Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.*/
  def twoSums(nums: Array[Int], target: Int): Unit =
    val set = nums.toSet
    val solution = nums.flatMap { item =>
      val rest = target - item
      val min = Math.min(item, rest)
      val max = Math.max(item, rest)
      if set(rest) then Some(min, max) else None
    }.distinct



}
