package com.stephen.tutorial

import com.stephen.Session
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

import java.lang.Runtime.getRuntime

class WorkingWithSparkUI extends Session{

  case class riskResult (val a: String, val b: String)

  import sparkSn.implicits._


  val processor = getRuntime.availableProcessors() * (sparkSn.sparkContext.statusTracker.getExecutorInfos.length - 1)
  val df1 = sparkSn.read.option("header", true).csv("src/main/resources/organisation100.csv")
    .withColumn("json_data", lit("[{'person': {name: Stephen, age: 30}}]"))
  val to_jsonDf = df1.select(col("json_data"), to_json(struct(col("json_data"))).as("person"))
    sparkSn.read.json(sparkSn.sqlContext.createDataset(to_jsonDf.select("person").first().json :: Nil))
  to_jsonDf.show(false)
  to_jsonDf.count()

  val win1 = Window.partitionBy("Founded").orderBy("Founded")
  val df2 = df1.select(col("*"), row_number().over(win1).as("row_number"))
  df2.count()
  df2.select("index").filter(col("Founded") === 1987).show(false)
  println("processor: " + processor)
  println(sparkSn.conf.get("spark.sql.files.maxPartitionBytes"))
println(df1.rdd.getNumPartitions)

  System.in.read()
  sparkSn.stop()
}
