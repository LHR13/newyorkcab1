package service;

import com.lhr13.newyorkcab.dao.DayDAO;
import com.lhr13.newyorkcab.pojo.Cab;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scala.Serializable;
import scala.Tuple2;

import java.util.Map;

@Component
public class LongOrShort implements Serializable {
    @Autowired
    private DayDAO dayDAO;

    public void run() throws Exception {
        SparkConf conf = new SparkConf().setAppName("NewYarkCab3").setMaster("local");
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
                return cab.getTrip_distance() != "null" && cab != null;
            }
        });

        JavaRDD<String> distanc = wash.map(new Function<Cab, String>() {
            @Override
            public String call(Cab cab) throws Exception {
                String distance = cab.getTrip_distance();
                if (Double.valueOf(distance) > 5.00) {
                    return "long";
                }else {
                    return "short";
                }
            }
        });

        Map<String, Long> map = distanc.countByValue();
        for (Map.Entry<String, Long> map1 : map.entrySet()) {
            System.out.println(map1.getKey() + " " + map1.getValue());
        }

//        JavaRDD<String> day = wash.map(new Function<Cab, String>() {
//            @Override
//            public String call(Cab cab) throws Exception {
//                String distance = cab.getTrip_distance();
//                if (Integer.valueOf(distance) > 1000) {
//                    return "long";
//                }else {
//                    return "short";
//                }
//            }
//        });
//
//        PairFunction<String, String, Long> keyData = (PairFunction<String, String, Long>) s -> new Tuple2(s, (long)1);
//
//        JavaPairRDD<String, Long> disPairs = distanc.mapToPair(keyData);
//
//        JavaPairRDD<String, Long> endPairs = disPairs.reduceByKey(new Function2<Long, Long, Long>() {
//            @Override
//            public Long call(Long aLong, Long aLong2) throws Exception {
//                return aLong + aLong2;
//            }
//        });
//
//        endPairs.saveAsTextFile("/media/hadoop/DATA/myJavaProjections/longandshort");
    }
}