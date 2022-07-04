package lectures.ScalaBeginnerCourse.PartThree

//option implementation
abstract class Maybe[+T]{
  def filter(p:T=>Boolean):Maybe[T]
  def map[B](f:T=>B):Maybe[B]
  def flatMap[B](f:T=>Maybe[B]):Maybe[B]

}
case object MaybeNot extends Maybe[Nothing]{
  override def map[B](f: Nothing => B): Maybe[B] = MaybeNot

  override def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = MaybeNot

  override def filter(p:Nothing=> Boolean): Maybe[Nothing] = MaybeNot

}
case class maySomething[+T](value:T) extends Maybe[T] {
  override def map[B](f: T => B): Maybe[B] = maySomething(f(value))

  override def flatMap[B](f: T => Maybe[B]): Maybe[B] = f(value)

  override def filter(p: T => Boolean): Maybe[T] = if p(value) then this else MaybeNot
}
object main {
  def main(args: Array[String]): Unit = {
    val just3 = maySomething(3)
    println(just3)
    println(just3.map(_*2))
    println(just3.filter(_%2==0))
    println(just3.flatMap(x=>just3.map(y=>x+y)))
    println(just3.flatMap(x=>maySomething(x%2==0)))

  }
}
