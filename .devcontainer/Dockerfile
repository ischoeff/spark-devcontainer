FROM python:3.10-slim-bullseye

# Install Java (required for Spark) and update package lists in the same RUN
RUN apt-get update && \
    apt-get install -y --no-install-recommends openjdk-17-jdk wget curl procps maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Install Spark (download and extract)
ARG SPARK_VERSION=3.5.5
ARG HADOOP_VERSION=3
RUN wget https://archive.apache.org/dist/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz && \
    tar -xzf spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz && \
    mv spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION} /opt/spark && \
    rm spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz
    
# Install Scala
ARG SCALA_VERSION=2.12.18
RUN wget https://downloads.lightbend.com/scala/${SCALA_VERSION}/scala-${SCALA_VERSION}.deb && \
    dpkg -i scala-${SCALA_VERSION}.deb && \
    rm scala-${SCALA_VERSION}.deb

# Set SPARK_HOME environment variable
ENV SPARK_HOME=/opt/spark

# Add Spark bin directory to PATH
ENV PATH=$SPARK_HOME/bin:$SPARK_HOME/sbin:$PATH
ENV PYTHONPATH=$SPARK_HOME/python:$SPARK_HOME/python/lib/py4j-0.10.9.7-src.zip:$PYTHONPATH

RUN pip install ipykernel

# Set working directory
WORKDIR /workspaces
