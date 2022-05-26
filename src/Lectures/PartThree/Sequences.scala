package Lectures.PartThree

import scala.collection.mutable

object Sequences extends App {
  /*SEQUENCES:
  *Immutable
  *Ordered
  * can be indexed
  * have well defined order
  * support =>length,iterator,reverse,indexing and iteration
  * concatenation , appending ,prepending
  * grouping ,zipping ,slicing , searching
   */
  val sequence: Seq[Int] =Seq(4,3,6,8)
  //printed list due to factory method
  println((sequence ++ sequence.reverse).sorted)
  println(sequence(2))//index
  println((1 to 10).toList)
  println((1 until  5).toSet)
  (1 to 10).foreach(x=>println("hello"+x))
/*
* LISTS:
* lists are immutable linear sequence
* insertion => fast head,tail,isEmpty O(1)
*most other operations are o(n) => length ,reverse
* lists are sealed has two subTypes object Nil(empty)
* class :: i.e add */
  println(42+:List(456,345,678):+89)
  val fiveApples = List.fill(5)("Apples")
  println(fiveApples)
  print(fiveApples.mkString("-"))
  /*
  * ARRAYS:
  *manually constructed by predefined length
  * can be mutated (update by place) => mutable
  * interprets with java T Array*
  *indexing is fast access */
  val array = Array(1,2,3,4)
  array.foreach(println)
  val elem = Array.ofDim[String](3)
  val elem2 = Array.ofDim[Int](3)
  elem.foreach(println)
  elem2.foreach(println)
  //mutation
  array(0)=100 //syntax sugar for numbers.update(100,0)
  println(array.mkString("Array(", ", ", ")"))
  //seq and array relation
  val numberSequence:Seq[Int] =array //implicit conversion
  println(numberSequence)
  /*
  * VECTOR:
  * Default Implementation for Immutable Sequence
  * effectively constant index read and write O(log(n))
  * fast element addition append/prepend
  * Implemented as Fixed-branched trie (branch factor 32)
  *good performance for large sizes */
  //set
  val example1 = mutable.Set("Ahmed","Mohammed","Omar")
  println(example1 += "Yasser")
  val example2 = Set("Yara","Eman","Magda")
  println(example2+"Nora")





}
