package com.stephen.tutorial

import scala.annotation.tailrec

object Option extends App {
//  val number: Option[Int] = Some(3)
//  val noNumber: Option[Int] = None
//  val result1 = number.map(_ * 1.5)
//  val result2 = noNumber.map(_ * 1.5)
//
//  println(result1)
//  println(result2)

  val data: Seq[Int] = Seq(1, 5, 3, 2, 3, 4, 5, 9, 1, 6, 7, 5, 8, 8, 9, 7)

  @tailrec
  def sumData(lst: Seq[Int], acc: BigInt = 0): BigInt = {
    lst match {
      case h :: t => if(t.length >= 0) {
        sumData(t, acc + h)
      }else
        acc
      case _ => acc
    }
  }

  println(sumData(data))

  val sum_data = data.foldLeft(0)(_ + _)
  println(sum_data)

}
