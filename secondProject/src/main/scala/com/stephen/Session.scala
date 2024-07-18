package com.stephen

import org.apache.spark.sql.SparkSession
import scala.collection.immutable.Map

trait Session {
  val sparkSn: SparkSession = SparkSession.builder()
    .appName("com.stephen")
    .master("local[3]")
    .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
//    .config("spark.sql.files.maxPartitionBytes", "5k")
//    .config("spark.scheduler.mode", "FAIR")
    .getOrCreate()

  sparkSn.sparkContext.setLogLevel("ERROR")

}
