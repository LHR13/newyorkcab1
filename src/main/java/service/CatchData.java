package service;

import com.lhr13.newyorkcab.pojo.Cab;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import scala.Serializable;

public class CatchData implements Serializable {

    public JavaRDD<Cab> CatchData() throws Exception {
        SparkConf conf = new SparkConf().setAppName("NewYarkCab2").setMaster("local");
        System.setProperty("hadoop.home.dir", "/usr/local/hadoop");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> file = sc.textFile("hdfs://localhost:9000/newYorkCab/trip_data_10.csv").cache();

        JavaRDD<Cab> cabrecord = file.map((Function<String, Cab>) s -> {
            Cab cab;
            String[] strings = s.split(",");
            if (strings.length == 14) {
                // 防止空值
                int flag = 0;
                for (int i = 0; i < 14; i++) {

                    if (strings[i] == null) {
                        strings[i] = "null";
                    }
                }
                cab = new Cab(strings[0], strings[1], strings[2],
                        strings[3], strings[4], strings[5],
                        strings[6], strings[7], strings[8],
                        strings[9], strings[10], strings[11],
                        strings[12], strings[13]);

            } else {
                String nullString = "null";
                cab = new Cab(nullString, nullString, nullString,
                        nullString, nullString, nullString,
                        nullString, nullString, nullString,
                        nullString, nullString, nullString,
                        nullString, nullString);
            }

            return cab;
        });
        return cabrecord;
    }
}
