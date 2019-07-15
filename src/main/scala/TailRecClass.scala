import scala.util.control.TailCalls.TailRec
import scala.util.control.TailCalls._

object TailRecClass extends App {

  println("Hello tail")

  def tailrecSimple(n: Int): Int = if (n <= 0) 1 else n * tailrecSimple(n - 1)

  def tailFunc(n: Int): TailRec[BigInt] = {
    if (n == 0) done(1)
    else for {
      x <- tailFunc(n - 1)
      _ = println("Now n = " + n)
      _ = println("Now X = " + x)
      //      y <- done(n)
    } yield x * n
  }

  def factorial2(n: Int): TailRec[BigInt] = {
    if (n < 1) done(1)
    //    else tailcall(fuctorial2(n -1)).flatMap(x => done(x * n))
    else tailcall(factorial2(n -1)).map(_ * n)
  }

  println(tailrecSimple(10))
  println(tailFunc(100).result)
  println(factorial2(100).result)

}