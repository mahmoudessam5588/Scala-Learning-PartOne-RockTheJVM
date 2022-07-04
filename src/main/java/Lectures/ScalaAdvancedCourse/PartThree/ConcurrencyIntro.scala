package Lectures.ScalaAdvancedCourse.PartThree

import java.util.concurrent.{Executor, ExecutorService, Executors}

object ConcurrencyIntro {
  /*
  * interface Runnable{
  * public void Run()
  * }*/

  //jvm Threads =  Instance of A Class
  val aThread = new Thread(() => print("Running In Parallel"))
  val threadHello: Thread = new Thread(() => (1 to 5).foreach(_ => println("Hello " + Thread.currentThread().getName)))
  val threadBye: Thread = new Thread(() => (1 to 5).foreach(_ => println("Bye " + Thread.currentThread().getName)))
  val pool: ExecutorService = Executors.newFixedThreadPool(10)

  //Concurrency Problem racing condition
  


  def main(args: Array[String]): Unit = {
    aThread.start() //start jvm thread on top of os thread
    //Runnable Run doesn't do anything in parallel
    aThread.join() //block until Thread Finishes
    threadHello.start()
    threadHello.join()
    threadBye.start()
    threadBye.join()
    pool.execute(() => println("Running Something In Thread Pool"))
    pool.execute(()=>{Thread.sleep(1000);println("Done After One Second")})
    pool.execute(()=>{Thread.sleep(1000);println("AlmostDone");Thread.sleep(1000);println("Done In 2 Seconds")})
    pool.shutdown()
    //if we try pool.execute again show exception error in the main thread
    println(pool.isShutdown) // give true even before he thread in pool complete the running

    



  }
}
