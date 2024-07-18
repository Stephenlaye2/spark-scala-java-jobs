package com.stephen

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer


object TailAndFold extends App {
  val data: Seq[Int] = Seq(1, 5, 3, 2, 3, 4, 5, 9, 1, 6, 7, 5, 8, 8, 9, 7)
  val newData = mutable.Stack[Int](4, 5)
  newData.push(2)
  newData.push(3)
  newData.pop()
  val singleInt = data.count(x => x == 1)
  println("singleInt: " + singleInt)

  println(newData)

  @tailrec
  def sum(lst: Seq[Int], a: Int): Int = {
    lst match {
      case h :: t => if (lst.length > 0) sum(t, a + h)
      else a
      case _ => a
    }
  }

  println(sum(data, 0))

//  val sumData = data.foldLeft(0)(_+_)
//  println(sumData)

//  Fibonacci Series
//  @tailrec
//  def fibonacci(n: Int, acc: Int = 0): Int = {
//     if(n <= 0) acc
//    else fibonacci(n-1, n-2 + acc)
//  }
//
//  val fib5 = fibonacci(6)
//  println("fib5: " + fib5)

  def fibonacci2(n: Int, prev: BigInt = 0, current: BigInt = 1): BigInt = {
    if(n <= 0) prev
    else fibonacci2(n-1, current, prev + current)
  }
  val fib6 = fibonacci2(5)
  println("fib6: " + fib6)

  val expr = "(((2*(8/2)+10)*30)*2)"
  println("Is valid expression?: " + isExpressionValid(expr))
//Validate expression
  def isExpressionValid(expression: String): Boolean = {
    val stack = mutable.Stack[Char]()
    var previousChar: Option[Char] = None

    for(char <- expression) {
      char match {
        case '(' => stack.push(char)
        case ')' => if(stack.isEmpty) return false
        stack.pop()
        case '/' => if(previousChar.exists(_ == 0)) return false
        case _ => //Ignore other chars
      }
      previousChar = Some(char)
    }
    stack.isEmpty
  }

  import scala.collection.mutable

  val expression = "(((2*(8/2)+10)*30)*2)"

  val string: String = "88442rfne4/2"

  def factorial(n: Int, current: BigInt = 1): BigInt = {
    if(n <= 1) current
    else factorial(n-1, n * current)
  }

  println("Factorial: " + factorial(10))

//  List(1,1,2,3,4,4,4,5,5,3,1,5,6,7,1,9) find the integers which has only  1 occurance in the list without using in built function
  val lst = List(1,1,2,3,4,4,4,5,5,3,1,5,6,7,1,9)
  println("distinct list: " + lst.distinct)

  val singleIntsStack = mutable.Stack[Int]()
  for(i <- lst) {
    var count = 0
    for(j <- lst) {
      if( i == j) count += 1
    }
    if(count == 1) singleIntsStack.push(i)
  }
  println("Single Ints: " + singleIntsStack)

}
