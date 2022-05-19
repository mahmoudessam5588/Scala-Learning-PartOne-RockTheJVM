package Lectures.PartTwo

import org.w3c.dom.css.Counter

object OOPBasics extends App {
  val person = Person("mahmoud", 35)
  val personTwo = Person("ahmed", 25, 180.5)
  println(person.age)
  println(person.greeting("Ibrahim"))
  println(person.thisGreeting)

  val writer = new Writer("William","Shecper",1970)
  println(writer.fullName)
  val novel = new Novel("Space Brothers",1990,writer)
  println(novel.authorAge)
  println(novel.isWrittenBy)
  println(novel.newRelease(1995).yearOfRelease)

  val counter = new Counter(20)
  counter.inc.print()
  counter.inc.inc.print()
  counter.dec.print()
  counter.dec.dec.print()
  counter.inc.inc(30).print()




}

class Person(val name: String, val age: Int):
  def greeting(name: String): String = s"Hi $name"

  def thisGreeting: String = s"Hi ${this.name}"

  def this(name: String, age: Int, height: Double) = this(name, age)

class Writer(val firstName: String, val surName: String, val year: Int):
  def fullName: String = s"Writer Name : ${this.firstName} ${this.surName} Born In: ${this.year}"

class Novel(val novelName: String, val yearOfRelease: Int, val author: Writer):
  def authorAge: String = s"${this.author.year}"

  def isWrittenBy: String = s"${this.author.firstName} ${this.author.surName}"

  def newRelease(year:Int): Novel = new Novel(novelName, year, author)


class Counter(val value :Int = 0):
  def inc = new Counter(value+1)
  def dec = new Counter(value-1)
  def inc (n:Int):Counter=
    if n <= 0 then this
    else  inc.inc(n+1)
  def dec (n:Int):Counter=
    if n <= 0 then this
    else dec.dec(n-1)
  def print(): Unit = println(value)

