package com.stephen.SparkJob
import com.databricks.dbutils_v1.DBUtilsHolder.dbutils
import org.apache.spark.sql.SparkSession

trait SparkSS{
  def spark: SparkSession = {
    SparkSession.builder()
      .appName("com.stephen")
      .master("*")
//      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      //    .config("spark.sql.files.maxPartitionBytes", "5k")
      //    .config("spark.scheduler.mode", "FAIR")
      .getOrCreate()
  }
}

object TransformationJob extends SparkSS {
  def main(arg: Array[String]): Unit = {
    val storage_account = "stephenlayestorage"
    val raw_container = "raw"
    val curated_container = "curated"
    val directory = "datasets/"
    val secret_scope = "dev-secret-scope"
    val applicationId = dbutils.secrets.get(scope = secret_scope, key = "applicationId")
    val clientSecret = dbutils.secrets.get(scope = secret_scope, key = "clientSecret")
    val directoryId = dbutils.secrets.get(scope = secret_scope, key = "directoryId")
    val spark = TransformationJob.spark


    spark.conf.set(f"fs.azure.account.auth.type.$storage_account.dfs.core.windows.net", "OAuth")
    spark.conf.set(f"fs.azure.account.oauth.provider.type.$storage_account.dfs.core.windows.net", "org.apache.hadoop.fs.azurebfs.oauth2.ClientCredsTokenProvider")
    spark.conf.set(f"fs.azure.account.oauth2.client.id.$storage_account.dfs.core.windows.net", applicationId)
    spark.conf.set(f"fs.azure.account.oauth2.client.secret.$storage_account.dfs.core.windows.net", clientSecret)
    spark.conf.set(f"fs.azure.account.oauth2.client.endpoint.$storage_account.dfs.core.windows.net", f"https://login.microsoftonline.com/$directoryId/oauth2/token")

    import org.apache.spark.sql.functions.current_date

    val rawPath = f"abfss://$raw_container@$storage_account.dfs.core.windows.net/$directory"
    val curatedPath = f"abfss://$curated_container@$storage_account.dfs.core.windows.net/$directory"

    val df = spark.read.format("parquet").load(rawPath).withColumn("current_date", current_date)
    df.show(false)

    df.write.partitionBy("current_date").mode("append").format("parquet").save(f"$curatedPath/organisation")
    println("Writing to curated zone succeeded!")
  }

}
