package service;

import com.lhr13.newyorkcab.pojo.Cab;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import scala.Serializable;

import java.util.Map;

public class GetPlatoonTime implements Serializable {
    public Map<String, Long> run() throws Exception {
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
                return cab != null &&
                        cab.getPickup_datatime() != "null" &&
                        cab.getPassenger_count() != "null" &&
                        Integer.valueOf(cab.getPassenger_count()) > 0;
            }
        });

        JavaRDD<String> more = wash.map(new Function<Cab, String>() {
            @Override
            public String call(Cab cab) throws Exception {
                String dayornight;
                String passcount = null;
                Integer time = Integer.valueOf(cab.getPickup_datatime().substring(11,13));
                if (time > 6 && time <= 10) {
                    dayornight = "11";
                }else if (time > 10 && time <= 14) {
                    dayornight = "12";
                }else if (time > 14 && time <= 18) {
                    dayornight = "13";
                }else if (time > 18 && time <= 22) {
                    dayornight = "14";
                }else if (time > 22 || time <= 2){
                    dayornight = "15";
                }else {
                    dayornight = "16";
                }
                Integer count = Integer.valueOf(cab.getPassenger_count());
                if (count >= 1 && count < 3) {
                    passcount = "1";
                }else if (count >= 3 && count < 6){
                    passcount = "2";
                }else if (count >= 6) {
                    passcount = "3";
                }
                return passcount + "," + dayornight;
            }
        });

        Map<String, Long> morePassagers = more.countByValue();
//        for (Map.Entry<String, Long> map1 : morePassagers.entrySet()) {
//            System.out.println(map1.getKey() + " " + map1.getValue());
//        }
        return morePassagers;

//        PairFunction<String, String, Long> keyData = new PairFunction<String, String, Long>() {
//            @Override
//            public Tuple2<String, Long> call(String s) throws Exception {
//                return new Tuple2(s, (long)1);
//            }
//        };
//
//        JavaPairRDD<String, Long> morePairs = more.mapToPair(keyData);
//
//        JavaPairRDD<String, Long> endPairs = morePairs.reduceByKey(new Function2<Long, Long, Long>() {
//            @Override
//            public Long call(Long aLong, Long aLong2) throws Exception {
//                return aLong + aLong2;
//            }
//        });
//
//        endPairs.saveAsTextFile("/media/hadoop/DATA/myJavaProjections/MoreCuntomer");

//        Map<String, Long> morePassagers = more.countByValue();
//        for (Map.Entry<String, Long> map1 : morePassagers.entrySet()) {
//            System.out.println(map1.getKey() + " " + map1.getValue());
//        }
    }
}
