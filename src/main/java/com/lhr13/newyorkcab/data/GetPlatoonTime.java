package com.lhr13.newyorkcab.data;

import com.lhr13.newyorkcab.pojo.Cab;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import scala.Serializable;

import java.util.Map;

public class GetPlatoonTime implements Serializable {
    public Map<String, Long> run() throws Exception {
        SparkConf conf = new SparkConf().setAppName("NewYarkCab2").setMaster("local[4]");
        System.setProperty("hadoop.home.dir", "/usr/local/hadoop");

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<Cab> cabrecord = new CatchData().CatchData(sc);

        JavaRDD<Cab> wash = cabrecord.filter((Function<Cab, Boolean>) cab -> cab != null &&
                cab.getPickup_datatime() != "null" &&
                cab.getPassenger_count() != "null" &&
                Integer.valueOf(cab.getPassenger_count()) > 0);

        JavaRDD<String> more = wash.map((Function<Cab, String>) cab -> {
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
        });

        Map<String, Long> morePassagers = more.countByValue();

        sc.close();

        return morePassagers;
    }
}
