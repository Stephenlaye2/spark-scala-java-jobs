package com.stephen.tutorial

import com.stephen.Session
import org.apache.spark.sql.Column
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions.Window
import java.sql.Date


class Tutorial1 extends Session{

  val data = Seq(("0", "Mango", "20", null), ("1", "Apple", "16", null), ("2", null, "10", List("Cardiff", "Birmingham")),
    ("3", "Mango", "25", null), ("4", "Apple", "14", List("Hatfield", "London", "Huddersfield")))
  val columns = Seq("id", "fruit", "price", "from")

  import sparkSn.implicits._

  val df1 = data.toDF(columns:_*)
  df1.show()

  val finalDf = df1.as[riskResult]

  finalDf.show(false)


//  df1.select(coalesce(col("fruit"), col("price")).as("fruit2")).show(false)
//  val df2 = df1.withColumn("delivery_date", current_date())
//    .withColumn("from", explode_outer(col("from")))
//  val from = df2.select(explode_outer(col("from"))).rdd.map(x => x.getString(0)).collect.toSeq
//  println("from: " + from)
//  df2.select("from").dropDuplicates().show(false)
//
//  import org.apache.spark.sql.expressions.Window
//
//  val df3 = df2.withColumn("price2", max("price").over(Window.partitionBy("fruit")))
//    .where(col("price") === col("price2"))
//  df3.show(false)
//
//val df4 = df1.select("*")
//  df4.show(false)
//
//  val df5 = df1.select(col("*"), current_date().as("delivery_date"))
//  df5.show(false)
//
//    val df6 = df5.select(col("*"),
//      max("price").over(Window.partitionBy("fruit")).as("price"))
//      .dropDuplicates("fruit")
//  println("df6")
//    df6.show(false)

//  df1.intersect(df4).show(false)

  System.in.read()
  sparkSn.stop()
}

