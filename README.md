# Project Description

This project is focused on leveraging Apache Spark.
Initially it was created to test the xml processing (reading) capabilities.
Target was to have a devcontainer with apache spark installed where spark scala code can be compiled with maven and executed with spark-submit.
It should read xml files with complex data structure and write the result in json format.

## Prerequisites

Docker, VScode with devcontainer extension.

## Devcontainer

This project includes a devcontainer configuration to set up a consistent development environment using Docker. The Dockerfile in the `.devcontainer` directory is used to create this environment.

### Dockerfile

The Dockerfile is based on the `python:3.9-slim` image and includes the following steps:

1. **Install Java/MAVEN/Tools**: Java is required for running Apache Spark. The Dockerfile installs OpenJDK 17. For the scala builds maven is installed. Also additional tools required like curl/wget for downloads, procps fir spark
2. **Install Spark**: The specified version of Apache Spark is downloaded and extracted to `/opt/spark`.
3. **Install Scala**: Scala is installed to support Spark applications written in Scala.
4. **Set Working Directory**: The working directory is set to `/workspaces`.


## The "dev" folder
### Data used

The data used in the project is placed in the dev/data folder. At the moment the ouputs are generated in the dav/data/ouput folder.

### Scala Example
#### XmlExample.scala

The `XmlExample.scala` file demonstrates how to process XML data using Apache Spark. This example includes:

1. **Reading XML Data**: The example shows how to read XML data into a Spark DataFrame using the `spark-xml` library.
2. **Data Transformation**: It includes various transformations to parse and manipulate the XML data.
3. **Writing Data**: The transformed data is written back to a specified output format, such as Parquet or JSON.

This example is useful for understanding how to handle semi-structured data with Spark.

### Other files

There is also python pyspark code avialable in the python folder as a minimal example how to use it. 

## Usage

### Starting the Development Container

To start the development container, follow these steps:

1. Ensure you have Docker installed and running on your machine.
2. Open the project in Visual Studio Code.
3. Install the "Remote - Containers" extension if you haven't already.
4. Press `F1` to open the command palette.
5. Type `Remote-Containers: Open Folder in Container...` and select it.
6. Choose the folder containing the `.devcontainer` configuration.
7. VS Code will build and start the development container.

### Build the Application

To build the `XmlExample.scala` class using the existing `pom.xml`, follow these steps:

1. **Navigate to the Project Root Directory**: Open a terminal in the development container. You will start in /workpaces/spark. You need to cd to "dev"
    ```sh
    cd dev
    ```

2. **Compile the Scala Code with Maven**: Use Maven to compile the project. Maven will use the `pom.xml` file to resolve dependencies and compile the Scala code. Also the final jar will be created. The initial build will take longer as all the dependencies will be downloaded by the maven build.
    ```sh
    mvn clean package
    ```

Finally you should see somewhere at the end:
[INFO] BUILD SUCCESS

4. **Run the Scala Application**: Use `spark-submit` to run the packaged Scala application with Apache Spark.
    ```sh
    spark-submit --class com.example.XmlExample --master local[4] target/spark-example-1.0-SNAPSHOT.jar
    ```

This will execute the `com.example.XmlExample` class, processing the XML data by reading the files in the dev/data folder and writing the results to dev/data/output folder

