package service;

import com.lhr13.newyorkcab.pojo.Cab;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.springframework.stereotype.Component;
import scala.Serializable;

@Component
public class Init implements Serializable {

    public void run() throws Exception {
        SparkConf conf = new SparkConf().setAppName("NewYarkCab").setMaster("local");
        System.setProperty("hadoop.home.dir", "/usr/local/hadoop");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> file = sc.textFile("hdfs://localhost:9000/newYorkCab/trip_data_10.csv");

        JavaRDD<Cab> cabrecord = file.map(new Function<String, Cab>() {
            @Override
            public Cab call(String s) throws Exception {
                Cab cab = null;
                String[] strings = s.split(",");
                if (strings.length == 14) {
                    cab = new Cab(strings[0], strings[1], strings[2],
                                        strings[3], strings[4], strings[5],
                                        strings[6], strings[7], strings[8],
                                        strings[9], strings[10], strings[11],
                                        strings[12], strings[13]);
                }
                return cab;
            }
        });

        System.out.println(cabrecord.count());
    }
}
