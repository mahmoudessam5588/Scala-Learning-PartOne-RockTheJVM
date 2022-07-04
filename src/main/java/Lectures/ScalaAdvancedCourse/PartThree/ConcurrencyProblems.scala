package Lectures.ScalaAdvancedCourse.PartThree

object ConcurrencyProblems {
  //non synchronized racing condition
  def runInParallel(): Unit = {
    var x = 0

    val thread1 = new Thread(() => {
      println(Thread.currentThread().getName)
      x = 1
    })
    val thread2 = new Thread(() => {
      println(Thread.currentThread().getName)
      x = 2
    })
    thread1.start()
    thread2.start()
    println(x)
  }

  def main(args: Array[String]): Unit = {
    //for _ <- 1 to 1000 do runInParallel()
    for _ <- 10 to 10000 do {
      val account = new BankAccount(5000)
      synchronized {

        val thread1 = new Thread(() => buySafe(account, "IPhone12", 3000))
        thread1.start()
        thread1.join()
      }
      synchronized {
        val thread2 = new Thread(() => buySafe(account, "Lenovo Legion 5i", 4300))
        thread2.start()
        thread2.join()
      }


    }
  }

  /*def buy(account:BankAccount,item:String,price:Int):Unit =
    account.amount -= price
    println(s"I've Bought $item")
    println(s"your Account now is $account")*/
  def buySafe(account: BankAccount, item: String, price: Int): Unit =
    account.synchronized {
      account.amount -= price
      println(s"I've Bought $item")
      println(s"your Account now is $account")
    }

  //another Example
  //solved by 2 options
  class BankAccount(@volatile var amount: Int):
    override def toString: String = s"$amount"


}
