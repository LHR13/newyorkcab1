package service;

import com.lhr13.newyorkcab.pojo.Cab;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import scala.Serializable;

import java.util.Map;

public class MoreCustomerTime implements Serializable {
    public Map<String, Long> run() throws Exception {
        JavaRDD<Cab> cabrecord = new CatchData().CatchData();

        JavaRDD<Cab> wash = cabrecord.filter(new Function<Cab, Boolean>() {
            @Override
            public Boolean call(Cab cab) throws Exception {
                return cab.getPickup_datatime() != "null" &&
                        cab != null;
            }
        });

        JavaRDD<String> more = wash.map(new Function<Cab, String>() {
            @Override
            public String call(Cab cab) throws Exception {
                return cab.getPickup_datatime().substring(11,13);
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
