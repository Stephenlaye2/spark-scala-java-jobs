package com.stephen.tutorial

import com.stephen.Session
import org.apache.spark.sql.functions.{col, lit, when}

import scala.collection.Map

class Tutorial2 extends Session{
  val df1 = sparkSn.read.format("csv").options(Map("header"-> "true", "inferSchema"->"true")).csv("src/main/resources/organisation100.csv")
    .withColumn("Founded", col("Founded").cast("string"))
    .withColumn("Founded", when(col("Founded").startsWith("19"), null).otherwise(col("Founded")))
    .select("Country", "Industry", "Founded")

  df1.na.fill("90s", Array("Founded")).show(false)

  val df2 = sparkSn.read.format("csv").options(Map("header"-> "true", "inferSchema"->"true")).csv("src/main/resources/small_zipcode.csv")
  df2.na.fill(0).show(false)

}
