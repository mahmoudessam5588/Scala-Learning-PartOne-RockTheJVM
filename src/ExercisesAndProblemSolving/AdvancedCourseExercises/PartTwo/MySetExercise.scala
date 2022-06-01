package ExercisesAndProblemSolving.AdvancedCourseExercises.PartTwo

import scala.annotation.{tailrec, targetName}

trait MySetExercise[A] extends (A => Boolean) {
  /*Implement A Functional Set*/

  def contains(elem: A): Boolean

  def apply(elem: A): Boolean = contains(elem)

  @targetName("Add To Set")
  def +(elem: A): MySetExercise[A]

  @targetName("Add Another Set To Set")
  def ++(anotherSet: MySetExercise[A]): MySetExercise[A]

  def map[B](x: A => B): MySetExercise[B]

  def flatmap[B](x: A => MySetExercise[B]): MySetExercise[B]

  def filter(predicate: A => Boolean): MySetExercise[A]

  def forEach(x: A => Unit): Unit

  //part TWO
  @targetName("Remove An Element From Set")
  def -(elem: A): MySetExercise[A]

  @targetName("differance between Two Sets")
  def --(anotherSet: MySetExercise[A]): MySetExercise[A]

  @targetName("InterSection Between Two Sets")
  def &(anotherSet: MySetExercise[A]): MySetExercise[A]

  //part Three
  @targetName("Not Equal")
  def unary_! : MySetExercise[A]

}

object setTest extends App {
  val set = MySetExercise(1, 2, 3, 4)
  set + 5 ++ MySetExercise(-1, -10) map (_ * 10) flatmap (y => MySetExercise(y, 10 * y)) filter (_ > 40) forEach println
  val negative = !set
  println(negative(2))
  println(negative(5))
  val negativeEven = negative.filter(_ % 2 == 0)
  println(negativeEven(5))
  val negativeEven5 = negativeEven + 5
  println(negativeEven5(5))


}


class EmptySet[A] extends MySetExercise[A] {
  override def contains(elem: A): Boolean = false

  @targetName("Add To Set")
  override def +(elem: A): MySetExercise[A] = new NonEmptySet[A](elem, this)

  @targetName("Add Another Set To Set")
  override def ++(anotherSet: MySetExercise[A]): MySetExercise[A] = anotherSet

  override def map[B](x: A => B): MySetExercise[B] = new EmptySet[B]

  override def flatmap[B](x: A => MySetExercise[B]): MySetExercise[B] = new EmptySet[B]

  override def filter(predicate: A => Boolean): MySetExercise[A] = this

  override def forEach(x: A => Unit): Unit = ()


  //Part Two
  @targetName("Remove An Element From Set")
  override def -(elem: A): MySetExercise[A] = this

  @targetName("differance between Two Sets")
  override def --(anotherSet: MySetExercise[A]): MySetExercise[A] = this

  @targetName("InterSection Between Two Sets")
  override def &(anotherSet: MySetExercise[A]): MySetExercise[A] = this

  //Part Three
  @targetName("Not Equal")
  override def unary_! : MySetExercise[A] = new PropertyBasedSet[A](x => true)


}

/*class InclusiveSet[A] extends MySetExercise[A]{
  override def contains(elem: A): Boolean = true

  @targetName("Add To Set")
  override def +(elem: A): MySetExercise[A] = this

  @targetName("Add Another Set To Set")
  override def ++(anotherSet: MySetExercise[A]): MySetExercise[A] = this

  override def map[B](x: A => B): MySetExercise[B] = ???

  override def flatmap[B](x: A => MySetExercise[B]): MySetExercise[B] = ???

  override def filter(predicate: A => Boolean): MySetExercise[A] = ??? //property-based set

  override def forEach(x: A => Unit): Unit = ???

  @targetName("Remove An Element From Set")
  override def -(elem: A): MySetExercise[A] = ???

  @targetName("differance between Two Sets")
  override def --(anotherSet: MySetExercise[A]): MySetExercise[A] = filter(!anotherSet)

  @targetName("InterSection Between Two Sets")
  override def &(anotherSet: MySetExercise[A]): MySetExercise[A] = filter(anotherSet)

  @targetName("Not Equal")
  override def unary_! : MySetExercise[A] = new EmptySet[A]
}*/
//all elements of type Which satisfy a Property
// { x in A | property(x)}
class PropertyBasedSet[A](property: A => Boolean) extends EmptySet[A] {
  override def contains(elem: A): Boolean = property(elem)

  //{x in A | property}+element = {x in A | property || x==element}
  @targetName("Add To Set")
  override def +(elem: A): MySetExercise[A] =
    new PropertyBasedSet[A](x => property(x) || x == elem)

  @targetName("Add Another Set To Set")
  override def ++(anotherSet: MySetExercise[A]): MySetExercise[A] =
    new PropertyBasedSet[A](x => property(x) || anotherSet(x))

  override def map[B](x: A => B): MySetExercise[B] = politelyFail

  override def flatmap[B](x: A => MySetExercise[B]): MySetExercise[B] = politelyFail

  override def forEach(x: A => Unit): Unit = politelyFail

  def politelyFail = throw new IllegalArgumentException("Deep Rabbit Hole")

  @targetName("Remove An Element From Set")
  override def -(elem: A): MySetExercise[A] = filter(x => x != elem)

  @targetName("differance between Two Sets")
  override def --(anotherSet: MySetExercise[A]): MySetExercise[A] = filter(!anotherSet)

  override def filter(predicate: A => Boolean): MySetExercise[A] =
    new PropertyBasedSet[A](x => property(x) && predicate(x)) //property-based set

  @targetName("InterSection Between Two Sets")
  override def &(anotherSet: MySetExercise[A]): MySetExercise[A] = filter(anotherSet)

  @targetName("Not Equal")
  override def unary_! : MySetExercise[A] = new PropertyBasedSet[A](x => !property(x))

}

class NonEmptySet[A](head: A, tail: MySetExercise[A]) extends MySetExercise[A] {

  @targetName("Add Another Set To Set")
  override def ++(anotherSet: MySetExercise[A]): MySetExercise[A] = tail ++ anotherSet + head

  override def map[B](x: A => B): MySetExercise[B] = (tail map x) + x(head)

  @targetName("Add To Set")
  override def +(elem: A): MySetExercise[A] =
    if this contains elem then this
    else new NonEmptySet[A](elem, this)

  override def contains(elem: A): Boolean = elem == head || tail.contains(elem)

  override def flatmap[B](x: A => MySetExercise[B]): MySetExercise[B] = (tail flatmap x) ++ x(head)

  override def forEach(f: A => Unit): Unit =
    f(head)
    tail forEach f

  //PartTwo
  @targetName("Remove An Element From Set")
  override def -(elem: A): MySetExercise[A] = if elem == head then tail else tail - elem + head

  @targetName("differance between Two Sets")
  override def --(anotherSet: MySetExercise[A]): MySetExercise[A] = filter(!anotherSet) //flatmap(x=>anotherSet.filter(_!=x))

  @targetName("InterSection Between Two Sets")
  override def &(anotherSet: MySetExercise[A]): MySetExercise[A] = filter(anotherSet) //filter(x=>anotherSet.contains(x))

  override def filter(predicate: A => Boolean): MySetExercise[A] =
    val filteredTail = tail filter predicate
    if predicate(head) then filteredTail + head
    else filteredTail

  @targetName("Not Equal")
  override def unary_! : MySetExercise[A] = new PropertyBasedSet[A](x => !this.contains(x))
}

object MySetExercise {
  def apply[A](values: A*): MySetExercise[A] =
    @tailrec
    def buildSet(valSeq: Seq[A], acc: MySetExercise[A]): MySetExercise[A] =
      if valSeq.isEmpty then acc
      else buildSet(valSeq.tail, acc + valSeq.head)

    buildSet(values.toSeq, new EmptySet[A])

}



