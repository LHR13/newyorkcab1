package service;

import com.lhr13.newyorkcab.pojo.Cab;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.springframework.stereotype.Component;
import scala.Serializable;
import scala.Tuple2;

import javax.persistence.Column;
import java.util.Map;

@Component
public class HotAera implements Serializable {

    public void run() throws Exception {
        SparkConf conf = new SparkConf().setAppName("NewYarkCab2").setMaster("local");
        System.setProperty("hadoop.home.dir", "/usr/local/hadoop");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> file = sc.textFile("hdfs://localhost:9000/newYorkCab/trip_data_10.csv");

        JavaRDD<Cab> cabrecord = file.map(new Function<String, Cab>() {
            @Override
            public Cab call(String s) throws Exception {
                Cab cab = null;
                String[] strings = s.split(",");
                if (strings.length == 14) {
                    // 防止空值
                    int flag=0;
                    for (int i=0; i<14; i++){

                        if (strings[i] == null){
                            strings[i] = "null";
                        }
                    }
                    cab = new Cab(strings[0], strings[1], strings[2],
                            strings[3], strings[4], strings[5],
                            strings[6], strings[7], strings[8],
                            strings[9], strings[10], strings[11],
                            strings[12], strings[13]);

                }else{
                    String nullString= "null";
                    cab = new Cab(nullString, nullString, nullString,
                            nullString, nullString, nullString,
                            nullString, nullString, nullString,
                            nullString, nullString, nullString,
                            nullString, nullString);
                }

                return cab;
            }
        });

        JavaRDD<Cab> wash = cabrecord.filter(new Function<Cab, Boolean>() {
            @Override
            public Boolean call(Cab cab) throws Exception {
                return cab.getDropoff_latitude() != "null" && cab.getDropoff_longitude() != "null" && cab != null;
            }
        });

        JavaRDD<String> distinct = wash.map(new Function<Cab, String>() {
            @Override
            public String call(Cab cab) throws Exception {
                String latitude = cab.getDropoff_latitude();
                String longitude = cab.getDropoff_longitude();
                return latitude+longitude;
            }
        });

        PairFunction<String, String, Long> keyData = new PairFunction<String, String, Long>() {
            @Override
            public Tuple2<String, Long> call(String s) throws Exception {
                return new Tuple2(s, (long)1);
            }
        };

        JavaPairRDD<String, Long> cabPairs = distinct.mapToPair(keyData);

        JavaPairRDD<String, Long> endPairs = cabPairs.reduceByKey(new Function2<Long, Long, Long>() {
            @Override
            public Long call(Long aLong, Long aLong2) throws Exception {
                return aLong + aLong2;
            }
        });

        endPairs.saveAsTextFile("/media/hadoop/DATA/myJavaProjections/output");
    }
}
