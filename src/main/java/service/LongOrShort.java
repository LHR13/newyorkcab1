package service;

import com.lhr13.newyorkcab.dao.DayDAO;
import com.lhr13.newyorkcab.pojo.Cab;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scala.Serializable;

import java.util.Map;

@Component
public class LongOrShort implements Serializable {
    @Autowired
    private DayDAO dayDAO;

    public Map<String, Long> run() throws Exception {
        JavaRDD<Cab> cabrecord = new CatchData().CatchData();

        JavaRDD<Cab> wash = cabrecord.filter(new Function<Cab, Boolean>() {
            @Override
            public Boolean call(Cab cab) throws Exception {
                return cab.getTrip_distance() != "null" && cab != null;
            }
        });

        JavaRDD<String> distanc = wash.map((Function<Cab, String>) cab -> {
            String distance = cab.getTrip_distance();
            if (Double.valueOf(distance) > 5.00) {
                return "long";
            }else {
                return "short";
            }
        });

        Map<String, Long> map = distanc.countByValue();
        return map;

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