package Lectures.ScalaAdvancedCourse.PartThree

import scala.collection.mutable
import scala.util
import scala.util.Random

object ThreadCommunication extends App {
  /*def naiveProducerConsumer(): Unit = {
    val container = new simpleContainer
    val consumer = new Thread(() => {
      println("Consumer Waiting .....")
      while (container.isEmpty) {
        println("Consumer Actively Waiting")
      }
      println("Acquired a Value Of" + container.get)
    })

    val producer = new Thread(() => {
      println("Producer Computing .....")
      Thread.sleep(500)
      val value = 42
      println("Starting Inserting Value of" + value + " In The Container")
      container.set(value)
    })
      consumer.start()
      producer.start()
  }*/

  //naiveProducerConsumer()
  //smartProducerConsumer()
  //producerConsumerLargeBuffer()
  //multiProdCon(5,5)
  //miniKafka(5,5)
  carRaceMimic()


  def smartProducerConsumer(): Unit = {
    val container = new simpleContainer
    val consumer = new Thread(() => {
      println("Consumer waiting ......")
      container.synchronized {
        container.wait()
      }
      println(s"Consumer acquired and consumed ${container.get} from the container")
    })
    val producer = new Thread(() => {
      println("producer computing some logic")
      Thread.sleep(2000)
      val value = 42
      container.synchronized {
        container.set(value)
        println(s"Done Computing Inserting [ $value ] to the Container")
        container.notify()
      }
    })
    consumer.start()
    producer.start()
  }

  def producerConsumerLargeBuffer(): Unit = {
    val buffer: mutable.Queue[Int] = new mutable.Queue[Int]
    val capacity: Int = 7
    val consumer = new Thread(() => {
      val random = new Random()
      while (true) {
        buffer.synchronized {
          if buffer.isEmpty then {
            println("Consumer Is Waiting For Inserting Value In The Container")
            buffer.wait()
          }
          val x = buffer.dequeue()
          println(s"Acquired And Consumed $x from the container")
          buffer.notify()
        }
        Thread.sleep(random.nextInt(500))
      }

    })
    val producer = new Thread(() => {
      val random = new Random()
      var i = 0
      while (true) {
        buffer.synchronized {
          if buffer.size == capacity then {
            println("Producer Container Is Full Waiting For Values To Be Consumed")
            buffer.wait()
          }
          println(s"Producer Inserting value $i In The Container")
          buffer.enqueue(i)
          buffer.notify()

          i += 1
        }
        Thread.sleep(random.nextInt(500))
      }
    })
    consumer.start()
    producer.start()
  }
  //producer consumer level 3
  class Consumer (id:Int,buffer:mutable.Queue[Int])extends Thread{
    override def run(): Unit ={
      val consumer = new Thread(() => {
        val random = new Random()
        while (true) {
          buffer.synchronized {
            while buffer.isEmpty do {
              println(s"Consumer's Id: $id  Is Waiting For Inserting Value In The Container")
              buffer.wait()
            }
            val x = buffer.dequeue()
            println(s"Consumer's Id : $id Acquired And Consumed $x value from the container")
            buffer.notify()
          }
          Thread.sleep(random.nextInt(500))
        }

      })
      consumer.start()

      }
    }
    class Producer(id :Int,buffer: mutable.Queue[Int],capacity: Int) extends Thread{
      override def run(): Unit ={
        val producer = new Thread(() => {
          val random = new Random()
          var i = 0
          while (true) {
            buffer.synchronized {
              while buffer.size == capacity do {
                println("Producer Container Is Full Waiting For Values To Be Consumed")
                buffer.wait()
              }
              println(s"Producer Inserting value $i In The Container")
              buffer.enqueue(i)
              buffer.notify()

              i += 1
            }
            Thread.sleep(random.nextInt(500))
          }
        })
        producer.start()

      }
    }
    def multiProdCon(nProducer:Int,nConsumer:Int):Unit={
      val buffer: mutable.Queue[Int] = new mutable.Queue[Int]
      val capacity: Int =5
      (1 to nProducer).foreach(i => new Producer(i, buffer , capacity ).start())
      (1 to nConsumer).foreach(i => new Consumer(i, buffer).start())
    }

    class Consumers(ids:Int,buffer:mutable.Queue[Int]) extends Thread{

      override def run(): Unit ={
        val consumers = new Thread(()=>{
          val random = new Random()
          while(true){
            buffer.synchronized{
            while buffer.isEmpty do {
              println(s"Consumer Id:$ids is waiting for value to be inserted inside the Container")
              buffer.wait()
            }
            }
            val x = buffer.dequeue()
            println(s"Consumer Id: $ids Acquired and Consumed Value $x From the container")
            buffer.notify()

          }
          Thread.sleep(random.nextInt(500))
        })
          consumers.start()
      }
    }

    class producers(Ids:Int , buffer:mutable.Queue[Int],capacity:Int) extends Thread{
      override def run(): Unit = {
        val producers = new Thread(()=>{
          while(true){
            val random = new  Random()

            buffer.synchronized{
              var i = 0
              while buffer.size == capacity do{
                println("Producer Filled Container To Maximum Capacity Waiting for Consumer")
                buffer.wait()
              }
              println(s"producer Id: $Ids Inserted Value $i In The Container ")

              buffer.enqueue(i)
              buffer.notify()
              i+=1
            }
            Thread.sleep(random.nextInt(500))
          }
        })
        producers.start()
      }
    }

    def miniKafka(nProd:Int,nCons:Int):Unit={
      val buffer  = new mutable.Queue[Int]()
      val capacity =5
      (1 to nProd).foreach(i => new Producer(i, buffer, capacity ).start())
      (1 to nCons).foreach(i => new Consumer(i ,buffer).start())
  }
  /*Exercises*/
  //1) notify all act in a different way
  def carRaceMimic():Unit={
    val cars = new mutable.Queue[Int]()
    (1 to 10).foreach(i=>new Thread(()=>{
      cars.synchronized{
        println(s"\n Waiting For Race Car Number: $i To Start it's Engine ")
        cars.enqueue(i)
        println(s"\n Car number :$i ready")
        println(s" \n Car Number: $i on Standby Ready For [ GO !!! ] Signal")
        cars.wait()
        println(s"\n GO !!!! Car: $i Takes off")
      }
    }).start())
    new Thread(() => {
      cars.synchronized{
        cars.dequeue()
        println("\n All Cars  Ready For [ Go !!! ] Signal ")
        cars.notifyAll()
        Thread.sleep(2000)

      }
    }).start()
  }
  //deadlock







  /*
* Producer Consumer Problem
* producer -> [?] -> consumer*/
  class simpleContainer {
    val isEmpty: Boolean = value == 0
    var value: Int = 0

    //producer i.e set
    def set(newValue: Int): Unit = value = newValue

    //consumer i.e get
    def get: Int = {
      val result = value; value = 0; result
    }
  }
}



