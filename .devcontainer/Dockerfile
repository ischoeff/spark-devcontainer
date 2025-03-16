FROM python:3.9-slim

RUN apt-get update && \
    apt-get install -y --no-install-recommends openjdk-17-jdk-headless wget curl procps maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Find the actual Java home and set JAVA_HOME dynamically
RUN export JAVA_HOME=$(readlink -f /usr/bin/java | sed "s:bin/java::") && \
    echo "JAVA_HOME is set to: $JAVA_HOME" && \
    java -version

# Install Spark (download and extract)
ARG SPARK_VERSION=3.5.5
ARG HADOOP_VERSION=3
RUN wget https://archive.apache.org/dist/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz && \
    tar -xzf spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz && \
    mv spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION} /opt/spark && \
    rm spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz

# Install Scala
ARG SCALA_VERSION=2.13.8
RUN wget https://downloads.lightbend.com/scala/${SCALA_VERSION}/scala-${SCALA_VERSION}.deb && \
    dpkg -i scala-${SCALA_VERSION}.deb && \
    rm scala-${SCALA_VERSION}.deb

# Set SPARK_HOME environment variable
ENV SPARK_HOME=/opt/spark

# Add Spark bin directory to PATH
ENV PATH=$SPARK_HOME/bin:$SPARK_HOME/sbin:$PATH
ENV PYTHONPATH=$SPARK_HOME/python/lib

# Set working directory
WORKDIR /workspaces

