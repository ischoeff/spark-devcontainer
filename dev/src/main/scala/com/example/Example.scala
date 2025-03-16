package com.example

import org.apache.spark.sql.SparkSession

object Example {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("Example")
      .master("local[*]")
      .getOrCreate()

    val data = Seq(("Alice", 29), ("Bob", 31), ("Cathy", 25))
    val df = spark.createDataFrame(data).toDF("Name", "Age")

    df.show()

    spark.stop()
  }
}
