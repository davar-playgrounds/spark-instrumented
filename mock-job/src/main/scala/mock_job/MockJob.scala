package mock_job

import org.apache.spark.sql.SparkSession

object MockJob extends App {
  val name = "mock_job"

  val spark = SparkSession.builder.appName(name).getOrCreate
  import spark.implicits._

  val df = (1 to 100).toDS
  val start = System.currentTimeMillis
  println(s"start: $start")
  val res = df.filter(_ % 3 != 0).map(_ * 100).rdd.fold(0) { case (x, y) => maybeFail(); sleep(); x + y }
  maybeFail()
  val end = System.currentTimeMillis
  println(s"end: $end, duration = ${end - start}, res = $res")

  def sleep() =
    Thread.sleep(System.currentTimeMillis % 100)

  def maybeFail() =
    if (System.currentTimeMillis % 101 == 0)
      throw new Exception("boo!")
}

