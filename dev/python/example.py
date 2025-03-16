from pyspark.sql import SparkSession

spark = SparkSession.builder.appName("SimplePySparkApp").getOrCreate()

data = [("Alice", 25), ("Bob", 30), ("Charlie", 35)]
df = spark.createDataFrame(data, ["Name", "Age"])

df.show()

spark.stop()