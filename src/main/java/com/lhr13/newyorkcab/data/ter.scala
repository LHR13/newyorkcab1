import org.apache.spark.{SparkConf, SparkContext}

class getWhere {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("NewYarkCab2").setMaster("local[4]"))

  }
}