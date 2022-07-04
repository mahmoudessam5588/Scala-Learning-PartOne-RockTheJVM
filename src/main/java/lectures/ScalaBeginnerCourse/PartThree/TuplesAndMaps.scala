package lectures.ScalaBeginnerCourse.PartThree

import scala.annotation.tailrec
import scala.compiletime.ops.string.+


object TuplesAndMaps extends App {

  //tuples = finite ordered ,"kind like List",Heterogeneous
  val groups = Tuple2('A', "hello") //at most 22 for conjunctions of function types
  println(groups._1)
  println(groups.copy(_1 = "Hi"))
  println(groups.swap)
  //Maps keys -> values
  val phoneBook = Map("Daniel" -> 333, "John" -> 456).withDefaultValue("Invalid Data Not Found")
  println(phoneBook)
  //Maps Operations
  println(phoneBook("Mary"))
  println(phoneBook + ("Mary" -> 222))
  //map,flatmap,filter
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))
  //filterKeys
  //mapValues
  println(phoneBook get ("John"))
  println(phoneBook.keys)
  println(phoneBook.keySet)
  println(phoneBook.toList)
  val names = List("Donny", "James", "Daniel", "John")
  println(names.groupBy(_.charAt(0)))
  val romanNumeral = Map(1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV", 5 -> "V")
  val four = romanNumeral(4)
  println(romanNumeral + (10 -> "G")) // "IV"
  println(four)
  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "John"), "Marry")
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val test1 = friendship(people, "Bob", "Jim")
  val test2 = friendship(test1, "Bob", "Mary")

  /*val treasureMap = mutable.Map.empty[Int, String]
  treasureMap += (1 -> "Go to island.")
  treasureMap += (2 -> "Find big X on ground.")
  treasureMap += (3 -> "Dig.")
  val step2 = treasureMap(2)
  println(step2) */
  // "Find big X on ground."
  //social network modeling
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())
  println(network)
  println(friendship(network, "John", "Marry"))
  println(unfriend(friendship(network, "John", "Marry"), "Marry", "John"))
  println(remove(friendship(network, "John", "Marry"), "John"))

  def friendship(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    val friendWithA: Set[String] = network(a)
    val friedWithB: Set[String] = network(b)
    val connection: Set[String] = friendWithA & friedWithB
    //network + ((a -> connection)) + ((b -> connection))

    network + (a -> (friendWithA + b)) + (b -> (friedWithB + a))

  //network((a->connection-b),(b->connection-a))
  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    @tailrec
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if friends.isEmpty then networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    val friendWithA: Set[String] = network(a)
    val friedWithB: Set[String] = network(b)
    network + (a -> (friendWithA - b)) + (b -> (friedWithB - a))

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if !network.contains(person) then 0
    else network(person).size

  println(nFriends(test2, "Bob"))

  def maxFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(maxFriends(test2))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
  //network.view.filterKeys(k=>network(k).isEmpty).size
    network.count(_._2.isEmpty)

  println(nPeopleWithNoFriends(test1))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean =
    @tailrec
    def bfs(target: String, considered: Set[String], discovered: Set[String]): Boolean =
      if discovered.isEmpty then false
      else if discovered.head == target then true
      else if considered.contains(discovered.head) then bfs(target, considered, discovered.tail)
      else bfs(target, considered + discovered.head, discovered.tail ++ network(discovered.head))

    bfs(b, Set(), network(a) + a)

  println(socialConnection(test2, "Mary", "Jim"))


}
