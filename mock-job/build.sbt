resolvers += "Artifactory" at "https://artifactory.dev.cba/artifactory/omnia-dev"

scalaVersion := "2.11.8"
name := "mock-job"
mainClass in Compile := Some("mock_job.MockJob")
libraryDependencies ++= {
  val sparkVersion = "2.1.0"
  Seq(
    // Spark
    "org.apache.spark" %% "spark-core"   % sparkVersion     % "provided",
    "org.apache.spark" %% "spark-sql"    % sparkVersion     % "provided"
  )
}
