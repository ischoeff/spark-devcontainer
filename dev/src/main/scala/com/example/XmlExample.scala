package com.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object XmlExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("XmlExample")
      .master("local[*]")
      .getOrCreate()

    try {
      // Read XML files from the "data/" directory
      val df = spark.read
        .format("xml")
        .option("rowTag", "record")
        .load("data/*.xml")

      df.show()
      df.printSchema() // Inspect the schema

      // Write the DataFrame to a single JSON file in "data/output/json"
      df.coalesce(1)
        .write
        .mode("overwrite")
        .json("data/output/json")

      // Flatten the structure and write each structure to a separate file
     val explodedDf = df
        .withColumn("address", explode_outer(col("address")))
        .withColumn("order", explode_outer(col("orders.order")))

      explodedDf.show()
      explodedDf.printSchema()

      explodedDf.write
        .mode("overwrite")
        .json("data/output/flattened_json")

      println("XML processing and JSON output completed successfully.")

    } catch {
      case e: Exception =>
        println(s"An error occurred: ${e.getMessage}")
        e.printStackTrace() // Print the stack trace for debugging
    } finally {
      spark.stop()
    }
  }
}