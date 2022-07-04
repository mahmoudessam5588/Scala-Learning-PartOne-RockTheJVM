package ExercisesAndProblemSolving.AdvancedCourseExercises.PartThree

object ConcurrencyProblemsExercises {

  /*EXERCISE ONE
  * construct 50 "Inception" Threads print("Hello from Thread 3") in reverse order*/
  def inceptionThreads(maxThreads:Int,i:Int):Thread=
    new Thread(()=>{
      if i < maxThreads
        then {val newThreads = inceptionThreads(maxThreads,i+1)
      newThreads.start()
      newThreads.join()}
      println(s"Hello From Thread $i")
    })
    /*EXERCISE TWO
    * what is the possible value for x
    * what is the smallest value for x*/
    /*var x = 0
    val threads = (1 to 100).map(_ => new Thread(() => x += 1))
    threads.foreach(_.start())
    threads.foreach(_.join())*/
  def main (args:Array[String]):Unit={
    inceptionThreads(50,1).start()

  }

}
