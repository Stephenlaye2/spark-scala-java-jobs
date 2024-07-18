package com.stephen.SparkJob

import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._
import scala.util.Random

object SaltingTechnique {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("SaltingExample")
      .getOrCreate()

    import spark.implicits._

    // Sample skewed data
    val data = Seq(
      ("key1", 1),
      ("key1", 1),
      ("key1", 1),
      ("key2", 1),
      ("key3", 1)
    ).toDF("key", "value")

    // Number of salts to distribute the skewed keys
    val numSalts = 10

    // Add a salt column
    val saltedData = data.withColumn("salt", (rand() * numSalts).cast("int"))

    // Create a new key with the salt appended
    val saltedKeyData = saltedData.withColumn("saltedKey", concat($"key", lit("_"), $"salt"))

    // Perform aggregation on the salted keys
    val saltedAggregation = saltedKeyData
      .groupBy("saltedKey")
      .agg(sum("value").as("sum_value"))

    // Remove the salt from the key after aggregation
    val unsaltedAggregation = saltedAggregation
      .withColumn("key", split($"saltedKey", "_")(0))
      .groupBy("key")
      .agg(sum("sum_value").as("total_value"))

    // Show the result
    unsaltedAggregation.show()

    spark.stop()
  }
}
