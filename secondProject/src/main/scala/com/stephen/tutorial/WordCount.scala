package com.stephen.tutorial

import com.stephen.Session
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SparkSession}


object WordCount extends App{
  val sparkSn = SparkSession.builder()
    .master("local[*]")
    .appName("com.stephen.wordcount")
    .getOrCreate()
  sparkSn.sparkContext.setLogLevel("ERROR")

  val text: Dataset[String] = sparkSn.read.textFile("src/main/resources/shakespeare.txt")
    import sparkSn.implicits._
    val wordCount = text.flatMap(_ split " ")
      .map(x => (x, 1)).rdd
      .reduceByKey(_ + _)
      .sortBy(x => (-x._2, x._1))

    wordCount.foreach(println)
  //
  //  val sequ = Seq()

  //  val df = sequ.toDF()
  //  df.show()
//  val lst: List[Int] = List(3, 5, 2, 7, 9, 1, 4, 6, 8)
//  val max_num = lst.max
//  println(s"Max Number: $max_num")
//  val new_lst = lst.filterNot(_ == max_num)
//  println(s"New List: $new_lst")
//  println(s"length: ${new_lst.length}")
//  var second_max = 0
//   for( i <- 0 to new_lst.length - 1){
//     second_max = if(new_lst(i) > second_max)new_lst(i) else second_max
//   }
//  println(s"second_max: $second_max")

  //  print(text.collect().toSeq.map(_.split(" ")).toList)
  //val point = Point(5, 6)
  //  println(s"X axis: ${point.x}, Y axis: ${point.y}")
  val lst2: List[List[Int]] = List(List(1), List(2), List(3), List(4, 5), List(6), List(8))
//  println(lst2.flatMap(x => x))

//  val lst3 = lst2.flatMap(_.filter(_%2==0))
//  println(s"lst3: $lst3")
//  def getEven(innerLst: List[Int]): List[Int] = innerLst.filter(_%2==0)
//  val lst4 = lst2.flatMap(x => getEven(x))
//  println(lst4)

//  for (elem <- src if (conditions)) yield (elem operation)

//  val lst5 = for(i <- lst2; j <- i if j%2 == 0 )yield j
//  println(lst5)
print(Point(5, 6))

}
case class Point(x: Int, y: Int)

